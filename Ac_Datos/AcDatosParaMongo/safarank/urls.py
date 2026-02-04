from django.urls import path
from safarank import views

urlpatterns = [
    path('', views.mostrar_inicio, name='inicio'),
    path('juegos/', views.lista_juegos, name='Lista_de_juegos'),
    path('rankings/', views.rankings, name='Rankings'),
    path('rankings/save_top/', views.save_top, name='save_top'),
]