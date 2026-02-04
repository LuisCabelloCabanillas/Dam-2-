import csv
import os

from django.core.paginator import Paginator
from django.http import JsonResponse
from django.shortcuts import render
from AcDatosParaMongo import settings


# Create your views here.

def mostrar_inicio(request):
    return render(request, 'inicio.html')

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

def lista_juegos(request):
    saga_filtro = request.GET.get('saga', None)
    juegos = []

    csv_path = os.path.join(settings.BASE_DIR, 'datos', 'juegos.csv')
    with open(csv_path, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            # Detectar la saga automáticamente
            juego_saga = "Otras"
            for saga, palabras in SAGAS.items():
                if any(p.lower() in row['Titulo'].lower() for p in palabras):
                    juego_saga = saga
                    break

            # Filtrar por saga si se ha seleccionado
            if saga_filtro and saga_filtro != "Todas" and saga_filtro != juego_saga:
                continue

            juegos.append({
                'Titulo': row['Titulo'],
                'Plataforma': row['Plataforma'],
                'Fecha': row['Fecha'],
                'Descripcion': row['Descripcion'],
                'Saga': juego_saga
            })

    # Paginación (6 juegos por página)
    paginator = Paginator(juegos, 9)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    # Enviar todas las sagas al template
    return render(request, 'lista_juegos.html', {
        'page_obj': page_obj,
        'sagas': ["Todas"] + list(SAGAS.keys()),
        'saga_filtro': saga_filtro
    })

def rankings(request):
    busqueda = request.GET.get('nombre', '').strip().lower()

    # 1️⃣ Cargar todos los juegos desde CSV
    all_juegos = []
    csv_path = os.path.join(settings.BASE_DIR, 'datos', 'juegos.csv')
    with open(csv_path, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            if busqueda and busqueda not in row['Titulo'].lower():
                continue
            all_juegos.append({
                'code': row['Titulo'].replace(" ", "_"),
                'name': row['Titulo'],
            })

    # 2️⃣ Obtener top items de la sesión
    top_items_sesion = request.session.get('top_items', {})

    # Lista de 10 slots, None si está vacío
    top_items_slots = [None] * 10
    for key, code in top_items_sesion.items():
        try:
            index = int(key) - 1
            juego = next((j for j in all_juegos if j['code'] == code), None)
            if juego:
                top_items_slots[index] = juego
        except (ValueError, IndexError):
            pass

    # 3️⃣ Excluir juegos que ya están en los slots para la paginación
    top_codes = set(code for code in top_items_sesion.values())
    juegos_para_pagina = [j for j in all_juegos if j['code'] not in top_codes]

    # 4️⃣ Paginación solo para los juegos no-top
    paginator = Paginator(juegos_para_pagina, 12)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)

    top_slots = list(range(1, 11))

    return render(request, 'ranking.html', {
        'items': all_juegos,               # todos los juegos, para poder buscar por code
        'top_items_slots': top_items_slots, # slots rellenados desde sesión
        'top_slots': top_slots,
        'page_obj': page_obj,              # solo los juegos no-top en la página actual
        'busqueda': busqueda
    })




def save_top(request):
    if request.method == 'POST':
        import json
        data = json.loads(request.body)
        request.session['top_items'] = data
        request.session.modified = True
        return JsonResponse({"status": "ok"})
    return JsonResponse({"status": "error"}, status=400)