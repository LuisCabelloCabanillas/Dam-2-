#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

volatile sig_atomic_t signal_received = 0;
volatile sig_atomic_t partial_result = 0;

pid_t pid_hijos[3];

// Manejador genérico para hijos
void manejador_hijo(int sig) {
    if (sig == SIGUSR1) {
        printf("Proceso %d: Recibí SIGUSR1 - iniciar tarea\n", getpid());
        // Simular tarea, por ejemplo cálculo parcial
        sleep(1);
        partial_result = getpid() % 10 + 1; // ejemplo de resultado parcial
        printf("Proceso %d: Enviando resultado parcial %d al padre\n", getpid(), partial_result);
        kill(getppid(), SIGUSR2);  // avisar al padre que resultado parcial está listo
    }
    else if (sig == SIGUSR2) {
        printf("Proceso %d: Recibí SIGUSR2 - pausa o proceso siguiente\n", getpid());
        // Simular pausa
        sleep(1);
        kill(getppid(), SIGTERM);  // avisar padre que terminó tarea
    }
}

void manejador_padre(int sig, siginfo_t *info, void *context) {
    signal_received = sig;
    printf("Padre: Recibí señal %d de proceso %d\n", sig, info->si_pid);
}

int main() {
    struct sigaction sa_padre;
    sa_padre.sa_flags = SA_SIGINFO;
    sa_padre.sa_sigaction = manejador_padre;
    sigemptyset(&sa_padre.sa_mask);
    sigaction(SIGUSR2, &sa_padre, NULL);
    sigaction(SIGTERM, &sa_padre, NULL);

    for (int i = 0; i < 3; i++) {
        pid_hijos[i] = fork();
        if (pid_hijos[i] < 0) {
            perror("fork");
            exit(EXIT_FAILURE);
        }
        if (pid_hijos[i] == 0) {
            // Proceso hijo
            signal(SIGUSR1, manejador_hijo);
            signal(SIGUSR2, manejador_hijo);
            signal(SIGTERM, manejador_hijo);
            printf("Hijo %d: PID %d esperando señales\n", i+1, getpid());
            while(1) pause();
            exit(EXIT_SUCCESS);
        }
    }

    // Padre coordina la secuencia
    for (int i = 0; i < 3; i++) {
        printf("Padre: Enviando SIGUSR1 a hijo %d (PID %d) para iniciar tarea\n", i+1, pid_hijos[i]);
        kill(pid_hijos[i], SIGUSR1);

        // Esperar resultado parcial (SIGUSR2)
        while (signal_received != SIGUSR2) pause();
        signal_received = 0;

        printf("Padre: Recibido resultado parcial de hijo %d\n", i+1);

        // Enviar señal de pausa/proceso siguiente (SIGUSR2)
        kill(pid_hijos[i], SIGUSR2);

        // Esperar confirmación de finalización (SIGTERM)
        while (signal_received != SIGTERM) pause();
        signal_received = 0;

        printf("Padre: Hijo %d ha finalizado tarea\n", i+1);
    }

    // Terminar hijos (opcional)
    for (int i = 0; i < 3; i++) {
        kill(pid_hijos[i], SIGKILL);
    }

    // Esperar hijos
    for (int i = 0; i < 3; i++) {
        wait(NULL);
    }

    printf("Padre: Todos los hijos finalizados. Programa terminado.\n");

    return 0;
}
