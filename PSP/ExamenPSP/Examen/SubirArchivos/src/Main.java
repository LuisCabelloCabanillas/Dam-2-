import java.io.*;
import javax.swing.JFileChooser; // Para seleccionar el fichero
import org.apache.commons.net.ftp.*;

public class Main{
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();

        // Configuración de conexión
        String servidor = "localhost";
        String user = "usuario";
        String pasw = "usuario";

        try {
            // 1. SELECCIONAR EL FICHERO LOCAL
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Selecciona el archivo para subir al FTP");
            int seleccion = chooser.showOpenDialog(null);

            if (seleccion != JFileChooser.APPROVE_OPTION) {
                System.out.println("No se seleccionó ningún archivo. Cancelando...");
                return;
            }

            File archivoLocal = chooser.getSelectedFile();
            String nombreArchivo = archivoLocal.getName(); // Nombre que tendrá en el servidor

            // 2. CONECTAR AL SERVIDOR
            System.out.println("Conectándose a " + servidor);
            cliente.connect(servidor);

            // IMPORTANTE: Modo pasivo para FileZilla Server 1.12.1
            cliente.enterLocalPassiveMode();

            if (cliente.login(user, pasw)) {
                System.out.println("Login correcto.");

                // Configurar tipo de archivo binario (para PDFs, imágenes, etc.)
                cliente.setFileType(FTP.BINARY_FILE_TYPE);

                // 3. SUBIR EL FICHERO AL DIRECTORIO RAÍZ
                System.out.println("Subiendo archivo...");
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivoLocal));

                if (cliente.storeFile(nombreArchivo, in)) {
                    System.out.println("SISTEMA: Fichero subido correctamente.");
                } else {
                    System.out.println("ERROR: No se pudo subir el fichero.");
                }
                in.close();

                // 4. MOSTRAR CONTENIDO DEL DIRECTORIO RAÍZ
                System.out.println("\n--- Contenido del directorio raíz ---");
                FTPFile[] files = cliente.listFiles();
                for (FTPFile f : files) {
                    String tipo = f.isDirectory() ? "[DIR]" : "[FILE]";
                    System.out.println(tipo + " " + f.getName() + " - " + f.getSize() + " bytes");
                }
                System.out.println("--------------------------------------");

                // Cerrar sesión
                cliente.logout();
                cliente.disconnect();
            } else {
                System.out.println("Error: Usuario o contraseña incorrectos.");
            }

        } catch (IOException ioe) {
            System.out.println("Error de comunicación: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }
}