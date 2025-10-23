#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

pid_t pid_hijo1, pid_hijo2;

void manejador_hijo1(int sig){
    printf("Hijo1: Recibí la señal. Iniciando tarea\n");
    sleep(2);
    printf("Hijo1: Tarea completada. Avisando a padre\n");
    kill(getppid(), SIGUSR2);
}

void manejador_hijo2(int sig){
    printf("Hijo2: Recibí la señal. Iniciando tarea\n");
    sleep(2);
    printf("Hijo2: Tarea completada. Avisando a padre\n");
    kill(getppid(), SIGUSR2);
}

void manejador_padre(int sig){
    static int contador = 0;
    contador++;

    if (contador == 1){
        printf("Padre: Hijo 1 terminado.\n");
        kill(pid_hijo2,SIGUSR1);
    } else if (contador == 2) {
        printf("Padre: Hijo 2 terminado.\n");
    }
}

int main() {
    pid_hijo1 = fork();

    if (pid_hijo1 == -1){
        perror("Error al crear Hijo 1");
        exit(EXIT_FAILURE);
    }

    if (pid_hijo1 == 0) {
        signal(SIGUSR1, manejador_hijo1);
        printf("Hijo 1: Esperando señal\n");
        pause();
        exit(EXIT_SUCCESS);
    }

    pid_hijo2 = fork();

    if (pid_hijo2 == -1){
        perror("Error al crear Hijo 1");
        exit(EXIT_FAILURE);
    }

    if (pid_hijo2 == 0) {
        signal(SIGUSR1, manejador_hijo2);
        printf("Hijo 2: Esperando señal\n");
        pause();
        exit(EXIT_SUCCESS);
    }

    signal(SIGUSR2, manejador_padre);
    
    sleep(1);
    printf("Padre: Enviando señal a Hijo 1\n");
    kill(pid_hijo1,SIGUSR1);

    wait(NULL);
    wait(NULL);

    printf("Padre: Todos los hijos han finalizado\n");

    return 0;
}
