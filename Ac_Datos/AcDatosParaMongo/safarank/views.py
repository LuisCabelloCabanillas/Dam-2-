import json
import csv
from datetime import datetime

from django.conf import settings
from django.contrib.auth import authenticate, login, logout
from django.core.paginator import Paginator
from django.http import JsonResponse
from django.shortcuts import render, redirect
from django.utils import timezone

# 1. Configuración de MongoDB
db = settings.MONGO_DB
coleccion_juegos = db['juegos']
coleccion_rankings = db['rankings_usuario']


# --- NAVEGACIÓN BÁSICA ---

def go_home(request):
    """Equivalente a mostrar_inicio"""
    return render(request, 'inicio.html')


def logout_user(request):
    logout(request)
    return redirect('go_home')


# --- AUTENTICACIÓN (Siguiendo el estilo Brotherhoods) ---

def do_login(request):
    if request.method == 'POST':
        # Asumiendo que tienes un LoginForm definido similar al ejemplo
        from .forms import LoginForm
        form = LoginForm(request, data=request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')
            user = authenticate(request, username=username, password=password)
            if user is not None:
                login(request, user)
                return redirect('go_home')
    else:
        from .forms import LoginForm
        form = LoginForm()
    return render(request, 'login.html', {"form": form})


# --- GESTIÓN DE JUEGOS Y RANKINGS ---

def rankings(request):
    """Muestra el ranking interactivo (el que modificamos en el HTML anterior)"""
    busqueda = request.GET.get('nombre', '').strip()
    saga = request.GET.get('saga', 'Global')

    # Lógica de filtrado en MongoDB
    query = {}
    if saga != 'Global':
        query["$or"] = [
            {"Titulo": {"$regex": saga, "$options": "i"}},
            {"titulo": {"$regex": saga, "$options": "i"}}
        ]

    if busqueda:
        busqueda_query = {"$or": [
            {"Titulo": {"$regex": busqueda, "$options": "i"}},
            {"titulo": {"$regex": busqueda, "$options": "i"}}
        ]}
        query = {"$and": [query, busqueda_query]} if query else busqueda_query

    cursor = coleccion_juegos.find(query)
    all_juegos = []
    for doc in cursor:
        titulo = doc.get('Titulo') or doc.get('titulo') or "Sin Título"
        # Incluimos el logo si existe en el documento de Mongo
        all_juegos.append({
            'code': titulo.replace(" ", "_"),
            'name': titulo,
            'logo': doc.get('Logo') or doc.get('logo') or ""
        })

    # Recuperar estado de la sesión
    session_key = f'top_items_{saga}'
    top_items_sesion = request.session.get(session_key, {})

    top_items_slots = [None] * 10
    for key, code in top_items_sesion.items():
        try:
            index = int(key) - 1
            juego_doc = coleccion_juegos.find_one({
                "$or": [{"Titulo": code.replace("_", " ")}, {"titulo": code.replace("_", " ")}]
            })
            if juego_doc:
                top_items_slots[index] = {
                    'code': code,
                    'name': juego_doc.get('Titulo') or juego_doc.get('titulo'),
                    'logo': juego_doc.get('Logo') or juego_doc.get('logo') or ""
                }
        except (ValueError, IndexError):
            pass

    top_codes = set(top_items_sesion.values())
    juegos_para_pagina = [j for j in all_juegos if j['code'] not in top_codes]

    paginator = Paginator(juegos_para_pagina, 12)
    page_obj = paginator.get_page(request.GET.get('page'))

    return render(request, 'ranking.html', {
        'page_obj': page_obj,
        'top_items_slots': top_items_slots,
        'busqueda': busqueda,
        'saga_actual': saga,
        'sagas_disponibles': [
            'Global', 'Inazuma Eleven', 'Professor Layton', 'Yo-kai Watch',
            'Ni no kuni', 'Fantasy Life', 'Dragon Quest'
        ],
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


# --- CARGA DE DATOS (Estilo Brotherhoods pero para Mongo) ---

def data_load(request):
    if request.method == "POST" and request.FILES.get('csvFile'):
        uploaded_file = request.FILES.get('csvFile')
        decoded_file = uploaded_file.read().decode('utf-8').splitlines()
        reader = csv.DictReader(decoded_file)

        for row in reader:
            # Adaptamos los campos del CSV a tu estructura de Mongo
            juego_doc = {
                "Titulo": row.get('Titulo') or row.get('Juego'),
                "Plataforma": row.get('Plataforma'),
                "Logo": row.get('Logo'),
                "Descripcion": row.get('Descripcion'),
                "Fecha": row.get('Fecha')
            }
            coleccion_juegos.insert_one(juego_doc)

        return render(request, 'data_load.html', {'mensaje': 'Datos cargados en Mongo'})

    return render(request, 'data_load.html')

def select_user(request):
    return render(request,'Select_conec.html')