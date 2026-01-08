from django.contrib import admin
from django.urls import path
from safarank.views import *

urlpatterns = [
    path('', mostrar_inicio, name='app'),
    path('inicio/', mostrar_inicio, name='inicio'),
]