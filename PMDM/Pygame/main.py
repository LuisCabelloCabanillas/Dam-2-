import random

import pygame
import sys


#Tamaño
WIDTH= 480
HEIGHT = 600
FPS = 60

#Colores
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

#Inicio
pygame.init()
pygame.mixer.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Brick Breaker")
clock = pygame.time.Clock()

#Sonido

try:
    sonido_rebote = pygame.mixer.Sound("sonido_rebote.mp3")
    sonido_romper = pygame.mixer.Sound("sonido_romper.mp3")
    sonido_rebote.set_volume(0.4)
    sonido_romper.set_volume(0.5)
except:
    sonido_rebote = sonido_romper = None

#Clases
class Barra:
    def __init__(self):
        self.width = 100
        self.height = 15
        self.x = WIDTH // 2 - self.width // 2
        self.y = HEIGHT - 80
        self.speed = 7
        self.rect = pygame.Rect(self.x, self.y, self.width, self.height)
    def mover(self , llave):
        if llave[pygame.K_LEFT]and self.rect.left > 0:
            self.rect.x -= self.speed
        if llave[pygame.K_RIGHT] and self.rect.right < WIDTH:
            self.rect.x += self.speed
    def dibujo(self):
        pygame.draw.rect(screen, BLUE, self.rect, border_radius=8)

class Bola:
    def __init__(self):
        self.radius = 8
        self.reset()
    def reset(self):
        self.x = barra.rect.centerx
        self.y = barra.rect.top - self.radius - 1
        self.speed_x = 0
        self.speed_y = 0
    def lanzar(self):
        self.speed_x = random.choice([-4, 4])
        self.speed_y = -5
    def mover(self):
        self.x += self.speed_x
        self.y += self.speed_y

        if self.x <= 0 or self.x >= WIDTH:
            self.speed_x *= -1
            if sonido_rebote: sonido_rebote.play()

        if self.y <= 0:
            self.speed_y *= -1
            if sonido_rebote: sonido_rebote.play()

    def dibujo(self):
        pygame.draw.circle(screen, WHITE, (int(self.x), int(self.y)), self.radius)

    @property
    def rect(self):
        return pygame.Rect(self.x - self.radius, self.y - self.radius, self.radius*2, self.radius*2)

class Bloques:
    def __init__(self, x, y, color):
        self.rect = pygame.Rect(x, y, 60, 25)
        self.color = color

    def dibujo(self):
        pygame.draw.rect(screen, self.color, self.rect)

#Crear bloques
def crear_bloques():
    bloques = []
    colores = [RED,GREEN,BLUE]
    for fila in range(6):
        for columna in range(6):
            x = 30 + columna * 70
            y = 80 + fila * 40
            bloques.append(Bloques(x, y, colores[fila % 3]))
    return bloques

#Función Propia
def agregar_fila():
    global fila_actual, bloques
    colores = [RED,GREEN,BLUE]
    fila_actual += 1
    y= 80 +(fila_actual - 1) * 40
    for columna in range(6):
        x = 30 + columna * 70
        bloques.append(Bloques(x, y, colores[(fila_actual-1) % 3]))

#Inicialización de objetos
barra = Barra()
bola = Bola()
bloques = crear_bloques()

bola_lanzada = False
esperando_lanzamiento = True

Puntuacion = 0
vidas = 3
fuente = pygame.font.SysFont("Arial", 20)

fila_actual = 6
tiempo_fila = 0
Tiempo_para_fn= 40000
tiempo_jugado= 0


#Bucle principal
running = True
while running:
    dt = clock.tick(FPS)
    tiempo_jugado += dt

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE and not bola_lanzada:
                bola.lanzar()
                bola_lanzada = True
                esperando_lanzamiento = False

    teclas = pygame.key.get_pressed()

    if teclas[pygame.K_RIGHT] and barra.rect.right < WIDTH:
        barra.rect.x += barra.speed
    if teclas[pygame.K_LEFT] and barra.rect.left > 0:
        barra.rect.x -= barra.speed


    mouse_x, _ = pygame.mouse.get_pos()

    if pygame.mouse.get_focused():
        barra.rect.centerx = mouse_x

    if barra.rect.left < 0:
        barra.rect.left = 0
    if barra.rect.right > WIDTH:
        barra.rect.right = WIDTH

    if pygame.mouse.get_pressed()[0] and esperando_lanzamiento:
        bola.lanzar()
        bola_lanzada = True
        esperando_lanzamiento = False

    if bola_lanzada:
        bola.mover()
    else:
        bola.x = barra.rect.centerx
        bola.y = barra.rect.top - bola.radius - 1

    if bola.rect.colliderect(barra.rect):
        bola.speed_y *= -1
        if sonido_rebote: sonido_rebote.play()

    for bloque in bloques[:]:
        if bola.rect.colliderect(bloque.rect):
            bloques.remove(bloque)
            bola.speed_y *= -1.05
            Puntuacion += 10
            if sonido_romper: sonido_romper.play()
            break

    if bola.y > HEIGHT:
        vidas -= 1
        bola.reset()
        bola_lanzada=False
        esperando_lanzamiento = True

        if vidas <= 0:
            running = False

    tiempo_fila += dt
    if tiempo_fila >= Tiempo_para_fn:
        tiempo_fila = 0
        if fila_actual >= 9:
            running = False
        else:
            agregar_fila()

    if len(bloques) <= 0:
        running = False


    screen.fill(BLACK)

    barra.dibujo()
    bola.dibujo()
    for bloque in bloques[:]:
        bloque.dibujo()

    segundos_jugados= tiempo_jugado // 1000
    segundos_restantes = (Tiempo_para_fn - tiempo_fila) // 1000
    if segundos_restantes < 0:
        segundos_restantes = 0

    texto1 = fuente.render(f"Puntos: {Puntuacion}     Vidas: {vidas}", True, WHITE)
    texto2 = fuente.render(f"Tiempo jugado: {segundos_jugados}s", True, WHITE)
    texto3 = fuente.render(f"Siguiente fila en: {segundos_restantes}s", True, WHITE)


    screen.blit(texto1, (20, 10))
    screen.blit(texto2, (20, 30))
    screen.blit(texto3, (20, 50))

    pygame.display.flip()

pygame.quit()
sys.exit()