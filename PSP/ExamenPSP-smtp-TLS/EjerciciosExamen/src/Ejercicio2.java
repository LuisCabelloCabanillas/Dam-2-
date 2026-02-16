import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;


public class Ejercicio2 {
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


                    ftpClient.changeWorkingDirectory("LOG");

                    File localFile = new File("temp_log.txt");
                    boolean existeEnServidor = false;

                    try (OutputStream os = new FileOutputStream(localFile)) {
                        existeEnServidor = ftpClient.retrieveFile("LOG.TXT", os);
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    String fechaFormateada = sdf.format(new java.util.Date());

                    try (PrintWriter pw = new PrintWriter(new FileWriter(localFile, true))) {
                        if (!existeEnServidor || localFile.length() == 0) {
                            pw.println("Conexiones del usuario.");
                        }
                        pw.println("Hora de conexión: " + fechaFormateada);
                    }

                    try (InputStream is = new FileInputStream(localFile)) {
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                        ftpClient.storeFile("LOG.TXT", is);
                    }

                    localFile.delete();

                    System.out.println("Log actualizado para " + user);


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

    private static void enviarCorreo(int cantidad){
        String server = "smtp.gmail.com";
        int port = 587;
        String remitente = "lcabellocabanillas@safareyes.es";
        String password = "wfbf nmif bwkk xlbu";
        String destinatario = "lcabellocabanillas@safareyes.es";

        AuthenticatingSMTPClient cliente = new AuthenticatingSMTPClient();

        try{
            cliente.connect(server, port);
            if(!SMTPReply.isPositiveCompletion(cliente.getReplyCode())){
                cliente.disconnect();
                return;
            }

            cliente.execTLS();
            if(cliente.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, remitente, password)){
                SimpleSMTPHeader header = new SimpleSMTPHeader(remitente,destinatario, "Resumen Conexiones FTP");
                cliente.setSender(remitente);
                cliente.addRecipient(destinatario);

                Writer writer = cliente.sendMessageData();
                if(writer != null){
                    writer.write(header.toString());
                    writer.write("Número de usuarios conectados válidamente son de " + cantidad);
                    writer.close();
                    cliente.completePendingCommand();
                }
            }
            cliente.logout();
            cliente.disconnect();
            System.out.println("Correo enviado correctamente");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
