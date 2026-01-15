from bs4 import BeautifulSoup
import pandas as pd

# Cargar HTML local
with open("level5.html", "r", encoding="utf-8") as f:
    soup = BeautifulSoup(f.read(), "lxml")

juegos = []

# Cada juego es una tarjeta
cards = soup.select("div.games-grid-card")

print(f"Encontrados {len(cards)} juegos")

for card in cards:
    # Título
    titulo_tag = card.select_one("h3")
    titulo = titulo_tag.get_text(strip=True) if titulo_tag else ""

    # Plataforma
    plataforma_tag = card.select_one(".cardTitle p")
    plataforma = plataforma_tag.get_text(strip=True) if plataforma_tag else ""

    # Fecha
    fecha_tag = card.select_one(".releaseDate h5")
    fecha = fecha_tag.get_text(strip=True) if fecha_tag else ""

    # Descripción
    descripcion_tag = card.select_one(".cardContent > p")
    descripcion = descripcion_tag.get_text(strip=True) if descripcion_tag else ""

    foto_tag = card.select_one("img")

    # Enlace al juego
    link_tag = card.select_one("a")
    if link_tag and link_tag.has_attr("href"):
        url = "https://gamesdb.launchbox-app.com" + link_tag["href"]
    else:
        url = ""

    juegos.append({
        "Titulo": titulo,
        "Plataforma": plataforma,
        "Fecha": fecha,
        "Descripcion": descripcion,
        "URL": url
    })

# Guardar en CSV
df = pd.DataFrame(juegos)
df.to_csv("juegos_level5.csv", index=False, encoding="utf-8")

print("✔ Listo. Archivo generado: juegos_level5.csv")
