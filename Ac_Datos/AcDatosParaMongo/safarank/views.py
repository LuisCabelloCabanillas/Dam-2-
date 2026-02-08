import json
from datetime import datetime
from django.core.paginator import Paginator
from django.http import JsonResponse
from django.shortcuts import render
from django.conf import settings  # Importamos settings para usar la conexión MONGO_DB

# 1. Accedemos a la colección de juegos
db = settings.MONGO_DB
coleccion_juegos = db['juegos']  # 'juegos' es el nombre de la colección en Compass
coleccion_rankings = db['rankings_usuario']

def mostrar_inicio(request):
    return render(request, 'inicio.html')


def lista_juegos(request):
    busqueda = request.GET.get('nombre','').strip()

    query = {}
    if busqueda:
        query = {
            "$or": [
                {"Titulo": {"$regex": busqueda, "$options": "i"}},
                {"titulo": {"$regex": busqueda, "$options": "i"}}
            ]
        }

    cursor = coleccion_juegos.find(query)
    juegos_list = []

    for doc in cursor:
        titulo = doc.get('Titulo') or doc.get('titulo') or "Sin Título"
        descripcion = doc.get('Descripcion') or doc.get('descripcion') or ""
        plataforma = doc.get('Plataforma') or doc.get('plataforma') or ""
        fecha = doc.get('Fecha') or doc.get('fecha') or ""

        juegos_list.append({
            'Titulo': titulo,
            'Plataforma': plataforma,
            'Fecha': fecha,
            'Descripcion': descripcion
        })



    paginator = Paginator(juegos_list, 9)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    return render(request, 'lista_juegos.html', {
        'page_obj': page_obj,
        'busqueda': busqueda
    })


def rankings(request):
    busqueda = request.GET.get('nombre', '').strip()
    saga = request.GET.get('saga', 'Global')

    # 1. Construcción inteligente de la Query
    query = {}

    # Filtro por Saga (si no es Global)
    if saga != 'Global':
        query["$or"] = [
            {"Titulo": {"$regex": saga, "$options": "i"}},
            {"titulo": {"$regex": saga, "$options": "i"}}
        ]

    # Filtro por Nombre (se añade a lo anterior si existe)
    if busqueda:
        # Usamos $and para que busque el nombre DENTRO de la saga seleccionada
        busqueda_query = {
            "$or": [
                {"Titulo": {"$regex": busqueda, "$options": "i"}},
                {"titulo": {"$regex": busqueda, "$options": "i"}}
            ]
        }
        if query:  # Si ya había filtro de saga
            query = {"$and": [query, busqueda_query]}
        else:
            query = busqueda_query

    cursor = coleccion_juegos.find(query)
    all_juegos = []

    # 2. Procesamiento de resultados
    for doc in cursor:
        titulo = doc.get('Titulo') or doc.get('titulo') or "Sin Título"
        all_juegos.append({
            'code': titulo.replace(" ", "_"),  # ID único basado en nombre
            'name': titulo,
        })

    # 3. Lógica de Slots por Saga
    session_key = f'top_items_{saga}'
    top_items_sesion = request.session.get(session_key, {})

    top_items_slots = [None] * 10
    for key, code in top_items_sesion.items():
        try:
            index = int(key) - 1
            # Buscamos el juego en la DB completa para que aparezca en el slot
            # aunque no esté en los resultados de la búsqueda actual
            juego_doc = coleccion_juegos.find_one({
                "$or": [{"Titulo": code.replace("_", " ")}, {"titulo": code.replace("_", " ")}]
            })
            if juego_doc:
                nombre_juego = juego_doc.get('Titulo') or juego_doc.get('titulo')
                top_items_slots[index] = {'code': code, 'name': nombre_juego}
        except (ValueError, IndexError):
            pass

    # Excluir juegos que ya están en el Top de la lista de abajo
    top_codes = set(top_items_sesion.values())
    juegos_para_pagina = [j for j in all_juegos if j['code'] not in top_codes]

    paginator = Paginator(juegos_para_pagina, 12)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    return render(request, 'ranking.html', {
        'page_obj': page_obj,
        'top_items_slots': top_items_slots,
        'busqueda': busqueda,
        'saga_actual': saga,
        'sagas_disponibles': ['Global', 'Inazuma Eleven', 'Professor Layton',
                              'Yo-kai Watch', 'Ni no kuni','Danball Senki',
                              'Megaton Musashi','Fantasy Life', 'Time Travelers',
                              'Dragon Quest','Dark Cloud', 'White Knight Chronicles'],
        'top_slots': range(1, 11)
    })


def save_top(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            # Manejamos si los datos vienen directos o anidados
            items = data.get('items') if 'items' in data else data
            saga = data.get('saga', 'Global')

            session_key = f'top_items_{saga}'
            request.session[session_key] = items
            request.session.modified = True

            # Guardado en MongoDB
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
            return JsonResponse({"status": "ok"})
        except Exception as e:
            return JsonResponse({"status": "error", "message": str(e)}, status=400)
    return JsonResponse({"status": "error"}, status=400)