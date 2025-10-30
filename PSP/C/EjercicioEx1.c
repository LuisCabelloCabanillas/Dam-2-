#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pid_gerente1, pid_gerente2;

    printf("Director (PID: %d, PPID: %d)\n", getpid(), getppid());

    pid_gerente1 = fork();
    if (pid_gerente1 == 0) {
        printf("Gerente 1 (PID: %d, PPID: %d)\n", getpid(), getppid());

        pid_t pid_empleado1_1 = fork();
        if (pid_empleado1_1 == 0) {
            printf("Empleado 1 del Gerente 1 (PID: %d, PPID: %d)\n", getpid(), getppid());
            exit(0);
        }

        pid_t pid_empleado2_1 = fork();
        if (pid_empleado2_1 == 0) {
            printf("Empleado 2 del Gerente 1 (PID: %d, PPID: %d)\n", getpid(), getppid());
            exit(0);
        }

        wait(NULL);
        exit(0);
    }

    pid_gerente2 = fork();
    if (pid_gerente2 == 0) {
        printf("Gerente 2 (PID: %d, PPID: %d)\n", getpid(), getppid());

        pid_t pid_empleado1_2 = fork();
        if (pid_empleado1_2 == 0) {
            printf("Empleado 1 del Gerente 2 (PID: %d, PPID: %d)\n", getpid(), getppid());
            exit(0);
        }

        pid_t pid_empleado2_2 = fork();
        if (pid_empleado2_2 == 0) {
            printf("Empleado 2 del Gerente 2 (PID: %d, PPID: %d)\n", getpid(), getppid());
            exit(0);
        }

        wait(NULL);
        exit(0);
    }

    wait(NULL);
    return 0;
}