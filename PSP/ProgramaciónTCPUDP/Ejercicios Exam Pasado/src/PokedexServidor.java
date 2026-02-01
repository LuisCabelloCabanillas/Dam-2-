import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PokedexServidor {

    private static int contadorClientes = 1;
    private static Pokemon[] pokedex = new Pokemon[3];

    public static void main(String[] args) {
        inicializarPokedex();

        try(ServerSocket servidor = new ServerSocket(6000)) {
            System.out.println("Servidor de Pokedex iniciado en el puerto 6000");

            while(true) {
                Socket socket = servidor.accept();
                int idCliente = contadorClientes++;
                System.out.println("Cliente " + idCliente + " conectado.");

                new HiloCliente(socket, idCliente, pokedex).start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static void inicializarPokedex(){
        pokedex[0] = new Pokemon(1,"Bulbasaur",new TipoElemental("Planta","Fuego"),new Ataque("Follaje",45));
        pokedex[1] = new Pokemon(4,"Charmander",new TipoElemental("Fuego","Agua"),new Ataque("Ascuas",40));
        pokedex[2] = new Pokemon(7,"Squirtle",new TipoElemental("Agua","Planta"),new Ataque("Pistola Agua",40));}
}
