import json
from django.core.paginator import Paginator
from django.http import JsonResponse
from django.shortcuts import render
from django.conf import settings  # Importamos settings para usar la conexión MONGO_DB

# 1. Accedemos a la colección de juegos
db = settings.MONGO_DB
coleccion_juegos = db['juegos']  # 'juegos' es el nombre de la colección en Compass

# 2. Tu diccionario de SAGAS se queda aquí
SAGAS = {
    "Profesor Layton": ["Professor Layton", "Layton", "Layton Brothers"],
    "Ni no Kuni": ["Ni no Kuni"],
    "Inazuma Eleven": ["Inazuma Eleven"],
    "Danball Senki": ["Danball Senki", "LBX"],
    "Fantasy Life": ["Fantasy Life"],
    "Yo-kai Watch": ["Yo-kai Watch", "Youkai Watch", "Yo-Kai Watch", "Youkai uotchi"],
    "Megaton Musashi": ["Megaton Musashi"],
    "Rogue Galaxy": ["Rogue Galaxy"],
    "Time Travelers": ["Time Travelers"],
    "Weapon Shop De Omasse": ["Weapon Shop De Omasse"],
    "White Knight Chronicles": ["White Knight Chronicles"],
    "Kidou Senshi Gundam AGE": ["Kidou Senshi Gundam AGE"],
    "Dragon Quest": ["Dragon Quest"],
    "ParaWorld": ["ParaWorld"],
    "Sloane to MacHale": ["Sloane to MacHale"],
    "Snack World": ["Snack World"],
    "Tago Akira no Atama no Taisou": ["Tago Akira no Atama no Taisou"],
    "The Starship Damrey": ["The Starship Damrey"],
    "Crimson Shroud": ["Crimson Shroud"],
    "Attack of the Friday Monsters": ["Attack of the Friday Monsters"],
    "Bugs vs Tanks": ["Bugs vs Tanks"],
}


def mostrar_inicio(request):
    return render(request, 'inicio.html')


def lista_juegos(request):
    saga_filtro = request.GET.get('saga', "Todas")

    cursor = coleccion_juegos.find({})
    juegos_list = []

    for doc in cursor:
        # Normalizamos los datos de la DB (por si vienen en mayúscula o minúscula)
        titulo = doc.get('Titulo') or doc.get('titulo') or "Sin Título"
        descripcion = doc.get('Descripcion') or doc.get('descripcion') or ""
        plataforma = doc.get('Plataforma') or doc.get('plataforma') or ""
        fecha = doc.get('Fecha') or doc.get('fecha') or ""
        foto = doc.get('Foto') or doc.get('foto') or ""

        # Lógica de detección de Saga
        juego_saga = "Otras"
        for saga, palabras in SAGAS.items():
            if any(p.lower() in titulo.lower() for p in palabras):
                juego_saga = saga
                break

        # FILTRO DE SAGA: Ahora comparamos correctamente
        if saga_filtro != "Todas" and saga_filtro != juego_saga:
            continue

        juegos_list.append({
            'Titulo': titulo,
            'Plataforma': plataforma,
            'Fecha': fecha,
            'Descripcion': descripcion,
            'Foto': foto,
            'Saga': juego_saga
        })

    paginator = Paginator(juegos_list, 9)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    return render(request, 'lista_juegos.html', {
        'page_obj': page_obj,
        'sagas': ["Todas"] + list(SAGAS.keys()),
        'saga_filtro': saga_filtro
    })

def rankings(request):
    busqueda = request.GET.get('nombre', '').strip()

    # Búsqueda flexible en MongoDB para que encuentre por Titulo o titulo
    query = {}
    if busqueda:
        query = {
            "$or": [
                {"Titulo": {"$regex": busqueda, "$options": "i"}},
                {"titulo": {"$regex": busqueda, "$options": "i"}}
            ]
        }

    cursor = coleccion_juegos.find(query)
    all_juegos = []
    for doc in cursor:
        titulo = doc.get('Titulo') or doc.get('titulo') or "Sin Título"
        all_juegos.append({
            'code': titulo.replace(" ", "_"),
            'name': titulo,
        })

    # Resto de la lógica de slots (se mantiene igual)
    top_items_sesion = request.session.get('top_items', {})
    top_items_slots = [None] * 10
    for key, code in top_items_sesion.items():
        try:
            index = int(key) - 1
            juego = next((j for j in all_juegos if j['code'] == code), None)
            if juego:
                top_items_slots[index] = juego
        except (ValueError, IndexError):
            pass

    top_codes = set(top_items_sesion.values())
    juegos_para_pagina = [j for j in all_juegos if j['code'] not in top_codes]

    paginator = Paginator(juegos_para_pagina, 12)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    return render(request, 'ranking.html', {
        'top_items_slots': top_items_slots,
        'page_obj': page_obj,
        'busqueda': busqueda,
        'top_slots': range(1, 11)
    })


def save_top(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        request.session['top_items'] = data
        request.session.modified = True
        return JsonResponse({"status": "ok"})
    return JsonResponse({"status": "error"}, status=400)