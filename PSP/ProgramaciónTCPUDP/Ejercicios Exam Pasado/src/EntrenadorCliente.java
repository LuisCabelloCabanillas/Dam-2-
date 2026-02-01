import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EntrenadorCliente {

    public static void main(String[] args) {


        try (
                Socket socket = new Socket("localhost", 6000);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
        ) {
            int id = in.readInt();
            System.out.println("Conectado al servidor como Entrenador " + id);

            while (true){
                System.out.print("Ingrese el número de Pokédex del Pokémon que desea consultar (o '*' para salir): ");
                String entrada = scanner.nextLine();
                if (entrada.equals("*")) {
                    out.writeObject(entrada);
                    out.flush();
                    System.out.println("Desconectando del servidor...");
                    break;
                }
                int numero = Integer.parseInt(entrada);
                out.writeObject(numero);

                Pokemon p = (Pokemon) in.readObject();
                mostrarPokemon(p);
            }
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }

    private static void mostrarPokemon(Pokemon p) {
        System.out.println("----- Información del Pokémon -----");
        System.out.println("Número: " + p.getNumeroPokedex());
        System.out.println("Nombre: " + p.getNombre());
        System.out.println("Elemento: " + p.getTipo().getElemento());
        System.out.println("Debilidad: " + p.getTipo().getDebilidades());
        System.out.println("Ataque: " + p.getAtaque().getAtaque());
        System.out.println("Daño: " + p.getAtaque().getDamage());
        System.out.println("-------------------------");
    }
}
