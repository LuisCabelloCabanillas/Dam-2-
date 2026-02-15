import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

public class GestionServidorFTP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FTPClient ftpClient = new FTPClient();
        int usuariosCorrectos = 0;

        while (true) {
            System.out.print("Nombre de usuario (* para salir): ");
            String user = sc.nextLine();
            if (user.equals("*")) break;

            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            try {
                ftpClient.connect("localhost");
                if (ftpClient.login(user, pass)) {
                    System.out.println("Conexión exitosa para: " + user);
                    usuariosCorrectos++;

                    // --- INICIO DEL BLOQUE NUEVO ---

                    // 1. Entrar a la carpeta LOG
                    ftpClient.changeWorkingDirectory("LOG");

                    // 2. Preparar archivo local (temp_log.txt)
                    File localFile = new File("temp_log.txt");
                    boolean existeEnServidor = false;

                    // Intentamos descargar el archivo actual para no borrar lo anterior
                    try (OutputStream os = new FileOutputStream(localFile)) {
                        existeEnServidor = ftpClient.retrieveFile("LOG.TXT", os);
                    }

                    // 3. Escribir/Añadir la nueva línea con formato exacto
                    // EEE (Día), MMM (Mes), dd (Día mes), HH:mm:ss (Hora), z (Zona), yyyy (Año)
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    String fechaFormateada = sdf.format(new java.util.Date());

                    try (PrintWriter pw = new PrintWriter(new FileWriter(localFile, true))) {
                        // Si el archivo no existía en el servidor o está vacío, ponemos la cabecera
                        if (!existeEnServidor || localFile.length() == 0) {
                            pw.println("Conexiones del usuario.");
                        }
                        pw.println("Hora de conexión: " + fechaFormateada);
                    }

                    // 4. Subir el fichero actualizado al servidor
                    try (InputStream is = new FileInputStream(localFile)) {
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                        ftpClient.storeFile("LOG.TXT", is);
                    }

                    // Limpieza: borrar archivo temporal local para que no se acumule basura
                    localFile.delete();

                    System.out.println("Log actualizado para " + user);

                    // --- FIN DEL BLOQUE NUEVO ---

                    ftpClient.logout();
                } else {
                    System.out.println("Usuario o contraseña incorrectos.");
                }
                ftpClient.disconnect();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        enviarCorreo(usuariosCorrectos);
    }

    private static void enviarCorreo(int cantidad) {
        // Configura aquí tus datos reales de SMTP
        String server = "smtp.gmail.com";
        int port = 587;
        String remitente = "tu_correo@gmail.com";
        String password = "tu_password_de_aplicacion";
        String destino = "destino@gmail.com";

        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        try {
            client.connect(server, port);
            if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                client.disconnect();
                return;
            }

            client.execTLS();
            if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, remitente, password)) {

                SimpleSMTPHeader header = new SimpleSMTPHeader(remitente, destino, "Resumen Conexiones FTP");
                client.setSender(remitente);
                client.addRecipient(destino);

                Writer writer = client.sendMessageData();
                if (writer != null) {
                    writer.write(header.toString());
                    writer.write("Número de usuarios conectados correctamente: " + cantidad);
                    writer.close();
                    client.completePendingCommand();
                }
            }
            client.logout();
            client.disconnect();
            System.out.println("Correo enviado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}