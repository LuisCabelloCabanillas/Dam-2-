from django.utils import timezone
from django.contrib.auth.base_user import BaseUserManager
from django.contrib.auth.models import AbstractUser, PermissionsMixin
from django.db import models
from django.contrib.auth.models import update_last_login
from django.contrib.auth.signals import user_logged_in
# IMPORTANTE: Importamos el campo específico de MongoDB
from django_mongodb_backend.fields import ObjectIdAutoField


class Juego(models.Model):
    # En MongoDB, si usas un campo 'code' como PK, asegúrate de que sea único
    code = models.IntegerField(primary_key=True)
    titulo = models.CharField(max_length=255)
    plataforma = models.CharField(max_length=100)
    fecha = models.CharField(max_length=100)
    descripcion = models.TextField()

    class Meta:
        db_table = 'juegos'
        managed = False

    def __str__(self):
        return self.titulo


class UserManager(BaseUserManager):
    def create_user(self, email, username, password=None):
        if not email or not username:
            raise ValueError('El usuario debe tener un correo electrónico y nombre')

        user = self.model(
            email=self.normalize_email(email),
            username=username,
        )
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, username, password=None):
        user = self.create_user(email, username, password)
        user.is_staff = True
        user.is_superuser = True
        user.save(using=self._db)
        return user


class User(AbstractUser, PermissionsMixin):
    # AÑADIDO: Definimos el ID compatible con MongoDB explícitamente
    id = ObjectIdAutoField(primary_key=True)

    email = models.EmailField(unique=True)
    username = models.CharField(max_length=150, unique=True)
    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=False)
    date_joined = models.DateTimeField(default=timezone.now)

    objects = UserManager()

    USERNAME_FIELD = 'username'
    REQUIRED_FIELDS = ['email']

    class Meta:
        db_table = 'auth_user'

    def __str__(self):
        return self.username


# FIX para evitar errores de actualización en el login de MongoDB
user_logged_in.disconnect(update_last_login)