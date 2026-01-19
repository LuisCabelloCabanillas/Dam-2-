import java.net.*;
import java.io.*;

public class ServidorNumeros {

    public static void main(String[] args) throws IOException {

        int puerto = 5000;

        System.out.println("Iniciando servidor...");

        try (ServerSocket servidor = new ServerSocket(puerto)){

            System.out.println("Esperando cliente...");
            Socket cliente = servidor.accept();
            System.out.println("Cliente creado...");

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());

            while(true){
                try{
                    Object obj = entrada.readObject();

                    if(!(obj instanceof Numeros)){
                        System.out.println("Objeto desconocido recibido.");
                        continue;
                    }

                    Numeros n = (Numeros) obj;

                    int numero = n.getNumero();
                    System.out.println("Recibido:" + numero);

                    if(numero <= 0){
                        System.out.println("Número negativo. Cerrando el servidor...");
                        break;
                    }

                    long cuadrado = (long)numero * numero;
                    long cubo = cuadrado*numero;

                    n.setCuadrado(cuadrado);
                    n.setCubo(cubo);

                    salida.writeObject(n);
                    salida.flush();


                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            cliente.close();
            System.out.println("Servidor cerrado");

        } catch (IOException e){
            System.err.println("Error al cargar el servidor. El error es: " + e.getMessage());
        }
    }
}
