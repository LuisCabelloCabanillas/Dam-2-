from django.urls import path
from safarank import views

urlpatterns = [
    path('', views.select_user ,name='Select_conec'),
    path('inicio/', views.go_home, name='go_home'),
    path('juegos/', views.rankings, name='Rankings'),
    path('login/', views.do_login, name='do_login'),
    path('logout/', views.logout_user, name='logout_user'),
    path('datos/', views.data_load, name='data_load'),
    path('rankings/save_top/', views.save_top, name='save_top'),
]