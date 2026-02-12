from django.urls import path
from safarank import views

urlpatterns = [
    path('', views.select_user ,name='Select_conec'),
    path('select-conec/', views.go_conex, name='go_conex'),
    path('inicio/', views.go_home, name='go_home'),
    path('rankings/', views.rankings, name='rankings'),
    path('login/', views.do_login, name='do_login'),
    path('register/', views.do_register, name='do_register'),
    path('logout/', views.logout_user, name='logout_user'),
    path('datos/', views.data_load, name='data_load'),
    path('rankings/save_top/', views.save_top, name='save_top'),
]