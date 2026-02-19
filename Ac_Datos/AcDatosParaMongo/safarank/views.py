import json
import csv
from datetime import datetime
import io

from django.conf import settings
from django.contrib.auth import authenticate, login, logout
from django.core.paginator import Paginator
from django.http import JsonResponse
from django.shortcuts import render, redirect

db = settings.MONGO_DB
coleccion_juegos = db['juegos']
coleccion_rankings = db['rankings_usuario']
coleccion_categorias = db['categorias']


# --- NAVEGACIÓN BÁSICA ---

def go_conex(request):
    return render(request, 'Select_conec.html')


from .forms import RegisterForm, LoginForm  # Asegúrate de importar tu formulario


def do_register(request):
    if request.method == 'POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            user=form.save(commit=False)
            user.set_password(form.cleaned_data['password'])
            user.save()
            return redirect('do_login')
        else:
            return render(request, 'register.html', {"form": form})
    else:
        form = RegisterForm()
        return render(request, 'register.html', {"form": form})

def go_home(request):
    return render(request, 'inicio.html')


def logout_user(request):
    logout(request)
    return redirect('go_home')


# --- AUTENTICACIÓN (Siguiendo el estilo Brotherhoods) ---

def do_login(request):
    if request.method == 'POST':
        form = LoginForm(request, data=request.POST)

        if form.is_valid():
            username = form.cleaned_data['username']
            password = form.cleaned_data['password']
            user = authenticate(request, username=username, password=password)
            if user is not None:
                login(request, user)
                return redirect('go_home')
    else:
        form = LoginForm()
    return render(request, 'login.html', {"form": form})


def rankings(request):
    busqueda = request.GET.get('nombre', '').strip()
    saga = request.GET.get('saga', 'Global')

    # 1. Recuperar categorías personalizadas de la DB
    coleccion_categorias = db['categorias']
    categorias_db = list(coleccion_categorias.find())

    # Lista base de sagas
    sagas_disponibles = [
        'Global', 'Inazuma Eleven', 'Professor Layton', 'Yo-kai Watch',
        'Ni no kuni', 'Fantasy Life', 'Dragon Quest'
    ]

    # 2. Añadir los nombres de las categorías creadas a la lista (si no están ya)
    nombres_categorias = [c['name'] for c in categorias_db]
    for nombre in nombres_categorias:
        if nombre not in sagas_disponibles:
            sagas_disponibles.append(nombre)

    # 3. Lógica de filtrado (Si es una categoría de la DB, filtramos por sus juegos)
    categoria_especifica = coleccion_categorias.find_one({"name": saga})

    if categoria_especifica:
        # Si la saga seleccionada es una categoría creada, buscamos los juegos por su lista de 'code'
        codigos_juegos = categoria_especifica.get('juegos_asociados', [])
        query = {"$or": [
            {"Titulo": {"$in": codigos_juegos}},
            {"titulo": {"$in": codigos_juegos}}
        ]}
    else:
        # Lógica original para sagas normales
        query = {}
        if saga != 'Global':
            query["$or"] = [
                {"Titulo": {"$regex": saga, "$options": "i"}},
                {"titulo": {"$regex": saga, "$options": "i"}}
            ]

    # Aplicar búsqueda por texto si existe
    if busqueda:
        busqueda_query = {"$or": [
            {"Titulo": {"$regex": busqueda, "$options": "i"}},
            {"titulo": {"$regex": busqueda, "$options": "i"}}
        ]}
        query = {"$and": [query, busqueda_query]} if query else busqueda_query

    # Obtener juegos y paginar
    cursor = coleccion_juegos.find(query)
    all_juegos = []
    for doc in cursor:
        titulo = doc.get('Titulo') or doc.get('titulo') or "Sin Título"
        all_juegos.append({
            'code': titulo.replace(" ", "_"),
            'name': titulo
        })

    # Lógica de Sesión y Top 10 (se mantiene igual que tu código)
    session_key = f'top_items_{saga}'
    top_items_slots = [None] * 10


    paginator = Paginator(all_juegos, 12)
    page_obj = paginator.get_page(request.GET.get('page'))

    return render(request, 'ranking.html', {
        'page_obj': page_obj,
        'top_items_slots': top_items_slots,
        'busqueda': busqueda,
        'saga_actual': saga,
        'sagas_disponibles': sagas_disponibles,
    })


def save_top(request):
    """Guarda el ranking en la sesión y en la base de datos de Mongo"""
    if request.method == 'POST':
        # Detectar si viene de un fetch (JSON) o de un form tradicional
        if request.content_type == 'application/json':
            data = json.loads(request.body)
            items = data.get('items')
            saga = data.get('saga', 'Global')
        else:
            # Estilo Brotherhoods: usa request.POST['order']
            order_raw = request.POST.get('order')
            items = json.loads(order_raw) if order_raw else {}
            saga = request.POST.get('category_code', 'Global')

        # 1. Guardar en Sesión
        session_key = f'top_items_{saga}'
        request.session[session_key] = items
        request.session.modified = True

        # 2. Guardar en MongoDB (Colección Rankings)
        ranking_doc = {
            "usuario": request.user.username if request.user.is_authenticated else "Anonimo",
            "saga": saga,
            "items": items,
            "fecha_creacion": datetime.now()
        }

        coleccion_rankings.update_one(
            {"usuario": ranking_doc["usuario"], "saga": saga},
            {"$set": ranking_doc},
            upsert=True
        )

        if request.content_type == 'application/json':
            return JsonResponse({"status": "ok"})
        return redirect('go_home')

    return JsonResponse({"status": "error"}, status=400)


def select_user(request):
    return render(request,'Select_conec.html')


def data_load(request):
    mensaje = None
    error = None

    if request.method == "POST":
        uploaded_file = request.FILES.get('csvFile')

        if not uploaded_file:
            error = "No se seleccionó ningún archivo."
        else:
            try:
                # Leer el archivo con 'utf-8-sig' para eliminar el símbolo BOM (muy común en Excel)
                decoded_file = uploaded_file.read().decode('utf-8-sig')
                io_string = io.StringIO(decoded_file)
                reader = csv.DictReader(io_string)

                juegos_a_insertar = []
                for row in reader:
                    # Limpiamos los nombres de las columnas (quitamos espacios en blanco)
                    row = {k.strip(): v for k, v in row.items()}

                    # Verificamos si la fila tiene al menos un título antes de guardarla
                    titulo = row.get('Titulo') or row.get('titulo')
                    if titulo:
                        juego_doc = {
                            "titulo": titulo,
                            "plataforma": row.get('Plataforma') or row.get('plataforma'),
                            "fecha": row.get('Fecha') or row.get('fecha'),
                            "descripcion": row.get('Descripcion') or row.get('descripcion'),
                        }
                        juegos_a_insertar.append(juego_doc)

                if juegos_a_insertar:
                    coleccion_juegos.insert_many(juegos_a_insertar)
                    mensaje = f"Éxito: Se han insertado {len(juegos_a_insertar)} juegos."
                else:
                    error = "El CSV parece estar vacío o las columnas no se llaman 'Titulo', 'Plataforma', etc."

            except Exception as e:
                error = f"Error crítico: {e}"

    return render(request, 'data_load.html', {'mensaje': mensaje, 'error': error})

def admin_panel(request):
    return render(request, 'admin_panel.html')

def go_categorias(request):
    return render(request, 'categorias.html')


def manage_categories(request):
    coleccion_categorias = db['categorias']

    if request.method == 'POST':
        name = request.POST.get('name')
        selected_games_json = request.POST.get('selected_games')
        selected_games = json.loads(selected_games_json) if selected_games_json else []

        nueva_cat = {
            "code": name.lower().replace(" ", "_"),
            "name": name,
            "juegos_asociados": selected_games  # Lista de nombres reales
        }

        coleccion_categorias.update_one({"code": nueva_cat["code"]}, {"$set": nueva_cat}, upsert=True)
        return redirect('go_categorias')

    # --- Lógica de búsqueda y paginación para los juegos ---
    busqueda = request.GET.get('nombre', '').strip()
    query = {}
    if busqueda:
        query = {"$or": [{"titulo": {"$regex": busqueda, "$options": "i"}},
                         {"Titulo": {"$regex": busqueda, "$options": "i"}}]}

    cursor_juegos = coleccion_juegos.find(query).sort("titulo", 1)

    juegos_lista = []
    for doc in cursor_juegos:
        nombre = doc.get('titulo') or doc.get('Titulo')
        if nombre:
            juegos_lista.append({'name': nombre})

    # Paginación de 12 juegos por página
    paginator = Paginator(juegos_lista, 12)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    categories = list(coleccion_categorias.find())

    return render(request, 'categorias.html', {
        'categories': categories,
        'page_obj': page_obj,
        'busqueda': busqueda
    })


def delete_category(request, category_code):
    coleccion_categorias.delete_one({"code": category_code})
    return redirect('go_categorias')


def ver_juegos(request):
    coleccion_juegos = db['juegos']
    # 1. Traemos los 400 documentos
    cursor_sucio = list(coleccion_juegos.find().sort("titulo", 1))

    print(f"DEBUG: Juegos encontrados en la DB: {len(cursor_sucio)}")

    juegos_formateados = []
    for doc in cursor_sucio:
        # 2. Mapeo exacto. Tu CSV tiene 'Titulo', 'Plataforma', 'Fecha', 'Descripcion'
        # Buscamos tanto en Mayúscula (CSV) como minúscula (Mongo)
        juegos_formateados.append({
            'titulo': doc.get('titulo') or doc.get('Titulo') or "Sin título",
            'plataforma': doc.get('plataforma') or doc.get('Plataforma') or "N/A",
            'fecha': doc.get('fecha') or doc.get('Fecha') or "-",
            'descripcion': doc.get('descripcion') or doc.get('Descripcion') or ""
        })

    # Enviamos la lista bajo el nombre 'lista'
    return render(request, 'level5.html', {'lista': juegos_formateados})