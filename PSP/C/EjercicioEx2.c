#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>

#include <stdbool.h>

#include <stdbool.h>

bool numeros_primos(int n){
    if(n<2) return false;
    for (int i = 2; i*i <=n;i++){
        if (n%i == 0) return false;
    }
    return true;
}

int main() {
    int pipe1[2];
    int pipe2[2];

    if (pipe(pipe1)== -1 || pipe(pipe2)== -1)
    {
        perror("Error al crear pipes");
        exit(EXIT_FAILURE);
    }

    pid_t pid_hijo = fork();

    if (pid_hijo < 0){
        perror("Error al crear proceso hijo");
        exit(EXIT_FAILURE);
    }

    if (pid_hijo == 0)
    {
        close(pipe1[1]);
        close(pipe2[0]);

        int numeros[10];
        int leidos = read(pipe1[0],numeros,sizeof(numeros));
        if (leidos <=0){
            perror("La lectura no es correcta");
            exit(EXIT_FAILURE);
        }

        printf("Hijo: Números recibidos\n");


        int primos[10];
        int count = 0;
        int suma = 0;

        for (int i = 0; i < 10; i++)
        {
            if (numeros_primos(numeros[i]))
            {
                primos[count++] = numeros [i];
                suma += numeros[i];
            }
        }

        write(pipe2[1],&count, sizeof(int));
        write(pipe2[1],primos, count * sizeof(int));
        write(pipe2[1],&suma, sizeof(int));

        printf("Hijo:Enviados %d primos y su suma %d al Padre.\n",count, suma);

        close(pipe1[0]);
        close(pipe2[1]);
        exit(EXIT_SUCCESS);
    }
    else
    {
        close(pipe1[0]);
        close(pipe2[1]);

        int numeros[10];
        for (int i = 1; i<=10;i++){
            numeros[i] = i + 1;
        }

        printf("Padre: Enviando numeros al hijo\n");
        write(pipe1[1], numeros, sizeof(numeros));

        int count=0, suma=0;
        int primos[10];

        read(pipe2[0], &count, sizeof(int));
        
        read(pipe2[0], primos, count * sizeof(int));
        read(pipe2[0], &suma, sizeof(int));

        printf("Padre: numeros primos recibidos: ");
        for (int i = 0; i < count;i++){
            printf("%d ",primos[i]);
        }

        printf("\nPadre: Suma del total: %d\n",suma);

        close(pipe1[1]);
        close(pipe2[0]);
    }
    return 0;
}