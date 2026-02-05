import os
import sys

import django
import pandas as pd

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

# 1. Configuración de Django
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'AcDatosParaMongo.settings')
django.setup()

from safarank.models import Juego # Asegúrate de que el nombre de la app sea correcto

# 2. Ruta al archivo (Ajusta el nombre del archivo según sea necesario)
ruta_csv = os.path.join('datos', 'juegos.csv')

def cargar_juegos():
    try:
        if os.path.exists(ruta_csv):
            print(f"Leyendo archivo: {ruta_csv}")
            df = pd.read_csv(ruta_csv)

            print(f"Insertando {len(df)} juegos en MongoDB...")

            for i, fila in enumerate(df.iterrows(), start=1):
                _, datos = fila
                Juego.objects.using('default').update_or_create(
                    code=i,  # Asigna el número de línea como código
                    defaults={
                        'titulo': datos['Titulo'],
                        'plataforma': datos['Plataforma'],
                        'fecha': datos['Fecha'],
                        'descripcion': datos['Descripcion']
                    }
                )
            print("¡Éxito! Datos cargados en la base de datos 'safarank'.")
        else:
            print(f"Error: No se encontró el archivo en {ruta_csv}")
    except Exception as e:
        print(f"Ocurrió un error: {e}")

if __name__ == '__main__':
    cargar_juegos()