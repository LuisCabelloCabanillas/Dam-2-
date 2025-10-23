#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

// Función para generar la serie de Fibonacci
void generar_fibonacci(int *arr, int n) {
    arr[0] = 0;
    arr[1] = 1;
    for (int i = 2; i < n; i++) {
        arr[i] = arr[i-1] + arr[i-2];
    }
}

// Función para calcular el factorial
long long factorial(int n) {
    if (n < 2) return 1;
    long long fact = 1;
    for (int i = 2; i <= n; i++) {
        fact *= i;
    }
    return fact;
}

int main() {
    int pipe1[2]; // Hijo1 -> Hijo2
    int pipe2[2]; // Hijo2 -> Hijo3

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1) {
        perror("Error al crear pipes");
        exit(EXIT_FAILURE);
    }

    pid_t pid_hijo1, pid_hijo2, pid_hijo3;

    pid_hijo1 = fork();
    if (pid_hijo1 == -1) {
        perror("Error al crear hijo 1");
        exit(EXIT_FAILURE);
    }

    if (pid_hijo1 == 0) {
        close(pipe1[0]); // No lee de pipe1
        close(pipe2[0]);
        close(pipe2[1]); // No usa pipe2

        int fib[10];
        generar_fibonacci(fib, 10);

        printf("[Hijo1] Serie Fibonacci generada:\n");
        for (int i = 0; i < 10; i++) {
            printf("%d ", fib[i]);
            write(pipe1[1], &fib[i], sizeof(int)); // Enviar a Hijo2
        }
        printf("\n[Hijo1] Serie enviada a Hijo2.\n");

        close(pipe1[1]);
        exit(EXIT_SUCCESS);
    }

    pid_hijo2 = fork();
    if (pid_hijo2 == -1) {
        perror("Error al crear hijo 2");
        exit(EXIT_FAILURE);
    }

    if (pid_hijo2 == 0) {
        close(pipe1[1]); // No escribe en pipe1
        close(pipe2[0]); // No lee de pipe2

        int num;
        while (read(pipe1[0], &num, sizeof(int)) > 0) {
            int resultado = num * 2;
            write(pipe2[1], &resultado, sizeof(int)); // Enviar a Hijo3
            printf("[Hijo2] Recibido %d, enviado %d\n", num, resultado);
        }

        close(pipe1[0]);
        close(pipe2[1]);
        exit(EXIT_SUCCESS);
    }

    pid_hijo3 = fork();
    if (pid_hijo3 == -1) {
        perror("Error al crear hijo 3");
        exit(EXIT_FAILURE);
    }

    if (pid_hijo3 == 0) {
        close(pipe1[0]);
        close(pipe1[1]); // No usa pipe1
        close(pipe2[1]); // No escribe en pipe2

        int num;
        printf("\n[Hijo3] Resultados finales:\n");
        while (read(pipe2[0], &num, sizeof(int)) > 0) {
            printf("Número: %d, Factorial: %lld\n", num, factorial(num));
        }

        close(pipe2[0]);
        exit(EXIT_SUCCESS);
    }

    close(pipe1[0]);
    close(pipe1[1]);
    close(pipe2[0]);
    close(pipe2[1]);

    // Esperar a los 3 hijos
    waitpid(pid_hijo1, NULL, 0);
    waitpid(pid_hijo2, NULL, 0);
    waitpid(pid_hijo3, NULL, 0);

    printf("\n[Padre] Todos los procesos han finalizado correctamente.\n");
    return 0;
}
