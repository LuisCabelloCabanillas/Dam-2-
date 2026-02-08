import json
from datetime import datetime
from wsgiref import headers

from Tools.scripts.make_ctype import method
from django.core.paginator import Paginator
from django.http import JsonResponse
from django.shortcuts import render
from django.conf import settings  # Importamos settings para usar la conexión MONGO_DB
from django.template.context_processors import request

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

    query = {}
    if saga != 'Global':
        query["$or"] = [{"Titulo": {"$regex": saga, "$options": "i"}}, {"titulo": {"$regex": saga, "$options": "i"}}]

    if busqueda:
        busqueda_query = {"$or": [{"Titulo": {"$regex": busqueda, "$options": "i"}},
                                  {"titulo": {"$regex": busqueda, "$options": "i"}}]}
        query = {"$and": [query, busqueda_query]} if query else busqueda_query

    cursor = coleccion_juegos.find(query)
    all_juegos = []
    for doc in cursor:
        titulo = doc.get('Titulo') or doc.get('titulo') or "Sin Título"
        all_juegos.append({'code': titulo.replace(" ", "_"), 'name': titulo})

    # CORRECCIÓN AQUÍ: Usar request.session, no request.GET
    session_key = f'top_items_{saga}'
    top_items_sesion = request.session.get(session_key, {})

    top_items_slots = [None] * 10
    for key, code in top_items_sesion.items():
        try:
            index = int(key) - 1
            juego_doc = coleccion_juegos.find_one(
                {"$or": [{"Titulo": code.replace("_", " ")}, {"titulo": code.replace("_", " ")}]})
            if juego_doc:
                nombre_juego = juego_doc.get('Titulo') or juego_doc.get('titulo')
                top_items_slots[index] = {'code': code, 'name': nombre_juego}
        except (ValueError, IndexError):
            pass

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
        'sagas_disponibles': ['Global', 'Inazuma Eleven', 'Professor Layton', 'Yo-kai Watch', 'Ni no kuni',
                              'Danball Senki', 'Megaton Musashi', 'Fantasy Life', 'Time Travelers', 'Dragon Quest',
                              'Dark Cloud', 'White Knight Chronicles'],
        'top_slots': range(1, 11)
    })


def save_top(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            items = data.get('items') if 'items' in data else data
            saga = data.get('saga', 'Global')

            session_key = f'top_items_{saga}'
            request.session[session_key] = items
            request.session.modified = True

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