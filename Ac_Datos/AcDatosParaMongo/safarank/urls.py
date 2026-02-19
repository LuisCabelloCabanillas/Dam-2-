from django.urls import path
from safarank import views

urlpatterns = [
    path('', views.select_user, name='Select_conec'),
    path('inicio/', views.go_home, name='go_home'),
    path('rankings/', views.rankings, name='rankings'),
    path('login/', views.do_login, name='do_login'),
    path('register/', views.do_register, name='do_register'),
    path('logout/', views.logout_user, name='logout_user'),
    path('datos/', views.data_load, name='data_load'),
    path('rankings/save_top/', views.save_top, name='save_top'),
    path('admin_panel/', views.admin_panel, name='admin_panel'),
    path('categorias/', views.manage_categories, name='go_categorias'),
    path('categorias/delete/<str:category_code>/', views.delete_category, name='delete_category'),
    path('level5/', views.ver_juegos, name='go_juegos'),
]