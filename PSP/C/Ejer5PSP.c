#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

pid_t hijo1, hijo2;
int tareas_completadas = 0;

void manejador_hijo(int sig) {
    printf("Hijo %d: Recibí la señal. Iniciando tarea...\n", getpid());
    sleep(2);
    printf("Hijo %d: Tarea completada. Avisando al padre.\n", getpid());
    kill(getppid(), SIGUSR2);  
}

void manejador_padre(int sig) {
    tareas_completadas++;
    if (tareas_completadas == 1) {
        printf("Padre: Hijo 1 terminó. Enviando señal a Hijo 2...\n");
        kill(hijo2, SIGUSR1);
    } else if (tareas_completadas == 2) {
        printf("Padre: Hijo 2 terminó. Todos los hijos han completado sus tareas.\n");
    }
}

int main() {
    signal(SIGUSR2, manejador_padre);

    hijo1 = fork();
    if (hijo1 == 0) {
        signal(SIGUSR1, manejador_hijo);
        printf("Hijo 1: Esperando señal...\n");
        pause();
        exit(EXIT_SUCCESS);
    }

    hijo2 = fork();
    if (hijo2 == 0) {
        signal(SIGUSR1, manejador_hijo);
        printf("Hijo 2: Esperando señal...\n");
        pause();
        exit(EXIT_SUCCESS);
    }

    sleep(1);
    printf("Padre: Enviando señal a Hijo 1...\n");
    kill(hijo1, SIGUSR1);

    wait(NULL);
    wait(NULL);

    printf("Padre: Fin del programa.\n");
    return 0;
}
