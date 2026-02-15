import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class ClienteSMTP extends JFrame {

    private JTextField txtServidor, txtPuerto, txtUsuario, txtRemitente, txtDestinatario, txtAsunto;
    private JPasswordField txtClave;
    private JTextArea txtCuerpo;
    private JButton btnConectar, btnEnviar;

    private Session session;
    private Transport transport;

    public ClienteSMTP() {
        setTitle("Cliente SMTP - XAMPP Mercury");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        agregarCampo(0, "Servidor SMTP:", txtServidor = new JTextField("localhost"), c);
        agregarCampo(1, "Puerto:", txtPuerto = new JTextField("587"), c);
        agregarCampo(2, "Usuario:", txtUsuario = new JTextField(), c);
        agregarCampo(3, "Clave:", txtClave = new JPasswordField(), c);

        agregarCampo(4, "Remitente:", txtRemitente = new JTextField(), c);
        agregarCampo(5, "Destinatario:", txtDestinatario = new JTextField(), c);
        agregarCampo(6, "Asunto:", txtAsunto = new JTextField(), c);

        c.gridx = 0; c.gridy = 7;
        add(new JLabel("Cuerpo:"), c);
        txtCuerpo = new JTextArea(5, 20);
        c.gridy = 8; c.gridwidth = 2;
        add(new JScrollPane(txtCuerpo), c);

        btnConectar = new JButton("Conectar");
        btnEnviar = new JButton("Enviar Mensaje");
        btnEnviar.setEnabled(false);

        c.gridy = 9; c.gridwidth = 1;
        add(btnConectar, c);
        c.gridx = 1;
        add(btnEnviar, c);

        btnConectar.addActionListener(e -> gestionarConexion());
        btnEnviar.addActionListener(e -> enviarCorreo());
    }

    private void agregarCampo(int fila, String etiqueta, JTextField campo, GridBagConstraints c) {
        c.gridx = 0; c.gridy = fila; c.gridwidth = 1;
        add(new JLabel(etiqueta), c);
        c.gridx = 1;
        add(campo, c);
    }

    private void gestionarConexion() {
        if (btnConectar.getText().equals("Conectar")) {
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", txtServidor.getText());
                props.put("mail.smtp.port", txtPuerto.getText());
                props.put("mail.smtp.auth", "true");

                props.put("mail.smtp.starttls.enable", "false");
                props.put("mail.smtp.starttls.required", "false");
                props.put("mail.smtp.ssl.enable", "false");

                props.put("mail.smtp.ssl.trust", "*");
                props.put("mail.smtp.ssl.protocols", "TLSv1 TLSv1.1 TLSv1.2");
                props.remove("mail.smtp.ssl.ciphersuites");
                props.put("mail.smtp.ssl.checkserveridentity", "false");

                session = Session.getInstance(props, null);

                transport = session.getTransport("smtp");

                transport.connect(txtServidor.getText(), txtUsuario.getText(), new String(txtClave.getPassword()));

                JOptionPane.showMessageDialog(this, "Conexión realizada y Usuario autenticado");

                btnConectar.setText("Desconectar");
                btnEnviar.setEnabled(true);
                bloquearCampos(false);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            desconectar();
        }
    }

    private void enviarCorreo() {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(txtRemitente.getText()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtDestinatario.getText()));
            message.setSubject(txtAsunto.getText());
            message.setText(txtCuerpo.getText());

            transport.sendMessage(message, message.getAllRecipients());
            JOptionPane.showMessageDialog(this, "Mensaje enviado con éxito");

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(this, "Error al enviar: " + ex.getMessage());
        }
    }

    private void desconectar() {
        try {
            if (transport != null) transport.close();
            btnConectar.setText("Conectar");
            btnEnviar.setEnabled(false);
            bloquearCampos(true);
            JOptionPane.showMessageDialog(this, "Desconectado del servidor");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private void bloquearCampos(boolean estado) {
        txtServidor.setEditable(estado);
        txtPuerto.setEditable(estado);
        txtUsuario.setEditable(estado);
        txtClave.setEditable(estado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteSMTP().setVisible(true));
    }
}