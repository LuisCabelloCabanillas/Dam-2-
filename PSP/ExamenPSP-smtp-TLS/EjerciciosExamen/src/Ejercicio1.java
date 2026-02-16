import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class Ejercicio1 extends JFrame {
    private JTextField txtServidor, txtPuerto, txtUsuario, txtRemitente, txtDestinatario, txtAsunto;
    private JPasswordField txtClave;
    private JTextArea txtCuerpo;
    private JButton btnConectar, btnEnviar;

    private Session sesion;
    private Transport transporte;

    public Ejercicio1(){
        setTitle("Cliente SMTP");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        agregarCampo(0,"Servisor SMTP:", txtServidor = new JTextField("localhost"), c);
        agregarCampo(1, "Puerto:", txtPuerto = new JTextField("587"), c);
        agregarCampo(2, "Usuario:", txtUsuario = new JTextField(""), c);
        agregarCampo(3, "Contraseña:", txtClave = new JPasswordField(""), c);
        agregarCampo(4, "Remitente:", txtRemitente = new JTextField(""), c);
        agregarCampo(5, "Destinatario:", txtDestinatario = new JTextField(""), c);
        agregarCampo(6, "Asunto:", txtAsunto = new JTextField(""), c);

        c.gridx = 0;
        c.gridy = 7;
        add(new JLabel("Cuerpo:"), c);
        txtCuerpo = new JTextArea(5, 20);
        c.gridy = 8;
        c.gridwidth = 2;
        add(new JScrollPane(txtCuerpo), c);

        btnConectar = new JButton("Conectar");
        btnEnviar = new JButton("Enviar mensaje");
        btnEnviar.setEnabled(false);

        c.gridy = 9;
        c.gridwidth = 2;
        add(btnConectar, c);
        c.gridy = 13;
        add(btnEnviar, c);

        btnConectar.addActionListener(e -> gestionarConexion());
        btnEnviar.addActionListener(e -> enviarMensaje());

    }

    private void agregarCampo(int fila, String etiqueta, JTextField campo, GridBagConstraints c){
        c.gridx = 0;
        c.gridy = fila;
        c.gridwidth = 1;
        add(new JLabel(etiqueta), c);
        c.gridx = 1;
        add(campo, c);
    }

    private void gestionarConexion(){
        if (btnConectar.getText().equals("Conectar")){
            try{
                Properties props = new Properties();
                props.put("mail.smtp.host", txtServidor.getText());
                props.put("mail.smtp.port", txtPuerto.getText());
                props.put("mail.smtp.auth", "true");

                props.put("mail.smtp.starttls.enable", "false");
                props.put("mail.smtp.starttls.required", "false");
                props.put("mail.smtp.ssl.enable", "false");

                sesion = Session.getInstance(props, null);

                transporte = sesion.getTransport("smtp");
                transporte.connect(txtServidor.getText(), txtUsuario.getText(), new String(txtClave.getPassword()));

                JOptionPane.showMessageDialog(this, "Conectado realizada y usuario verificado");

                btnConectar.setText("Desconectar");
                btnEnviar.setEnabled(true);
                bloquearCampos(false);

            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else{
            desconectar();
        }
    }

    private void enviarMensaje(){
        try{
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(txtRemitente.getText()));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtDestinatario.getText()));
            mensaje.setSubject(txtAsunto.getText());
            mensaje.setText(txtCuerpo.getText());

            transporte.sendMessage(mensaje, mensaje.getAllRecipients());
            JOptionPane.showMessageDialog(this, "Mensaje enviado realizado");
        } catch (MessagingException ex){
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void desconectar(){
        try{
            if(transporte != null) transporte.close();
            btnConectar.setText("Conectar");
            btnEnviar.setEnabled(false);
            bloquearCampos(true);
            JOptionPane.showMessageDialog(this, "Desconectado del servidor");
        } catch (MessagingException ex){
            ex.printStackTrace();
        }
    }

    private void bloquearCampos(boolean estado){
        txtServidor.setEditable(estado);
        txtPuerto.setEditable(estado);
        txtUsuario.setEditable(estado);
        txtClave.setEditable(estado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio1().setVisible(true));
    }

}
