from django.db import models

class Juego(models.Model):
    code = models.IntegerField(primary_key=True)
    titulo = models.CharField(max_length=255)
    plataforma = models.CharField(max_length=100)
    fecha = models.CharField(max_length=100)
    descripcion = models.TextField()

    class Meta:
        db_table = 'juegos' # Esto crea la "carpeta" dentro de safarank
        managed = False