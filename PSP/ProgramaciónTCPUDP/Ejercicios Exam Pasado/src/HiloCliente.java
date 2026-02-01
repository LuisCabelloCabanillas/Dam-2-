import java.net.Socket;
import java.io.*;
import java.util.Objects;

public class HiloCliente extends Thread {

    private Socket socket;
    private int idCliente;
    private Pokemon[] pokedex;

    public HiloCliente(Socket socket, int idCliente, Pokemon[] pokedex) {
        this.socket = socket;
        this.idCliente = idCliente;
        this.pokedex = pokedex;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            out.writeInt(idCliente);
            out.flush();

            while (true) {
                Object recibido = in.readObject();

                if(recibido instanceof String && recibido.equals("*")){
                    break;
                }

                int numeroPokedex = (int) recibido;
                Pokemon encontrado = buscarPokemon(numeroPokedex);
                out.writeObject(encontrado);
                out.flush();
            }
        } catch (Exception e ){
            System.out.println("Cliente " + idCliente + " desconectado.");
        }
    }

    private Pokemon buscarPokemon(int numeroPokedex) {
        for (Pokemon p : pokedex) {
            if (p.getNumeroPokedex() == numeroPokedex) {
                return p;
            }
        }
        return null;
    }

}
