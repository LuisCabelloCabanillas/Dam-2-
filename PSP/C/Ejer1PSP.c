#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

void contar(const char* nombre, int limite) {
    for (int i = 1; i <=limite; i++){
        printf("%s: %d\n", nombre,i);
        sleep(1);
    }
}

int main() {
    pid_t pid_hijo1, pid_hijo2, pid_hijo3;

    printf("Proceso PADRE - PID: %d\n", getpid());

    pid_hijo1 = fork();
    if (pid_hijo1 == 0) {
        printf("Proceso HIJO 1 - PID: %d, PPID: %d\n", getpid(),getppid());
        
        pid_t pid_nieto1 = fork();
        if (pid_nieto1 == 0){
            printf("Proceso NIETO 1 - PID: %d, PPID: %d\n", getpid(),getppid());
            contar("Nieto 1", 5);
            exit(0);
        }else {
        wait(NULL);
        contar("Hijo 1", 3);
        exit(0);
    }
}

    pid_hijo2 = fork();
    if (pid_hijo2 == 0) {
        printf("Proceso HIJO 2 - PID: %d, PPID: %d\n", getpid(),getppid());
        
        pid_t pid_nieto2 = fork();
        if (pid_nieto2 == 0){
            printf("Proceso NIETO 2 - PID: %d, PPID: %d\n", getpid(),getppid());
            contar("Nieto 2", 5);
            exit(0);
        }else {
        wait(NULL);
        contar("Hijo 2", 3);
        exit(0);
    }
}

    pid_hijo3 = fork();
    if (pid_hijo3 == 0) {
        printf("Proceso HIJO 3 - PID: %d, PPID: %d\n", getpid(),getppid());
        contar("Hijo 3", 4);
        exit(0);
    }

    wait(NULL);
    wait(NULL);
    wait(NULL);

    printf("Procedo PADRE finaliza - PID: %d\n", getpid());

    return 0;

}