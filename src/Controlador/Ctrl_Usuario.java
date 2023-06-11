/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClsConsultaUsuario;
import Modelo.Usuario;
import Vista.frmMenuInicio;
import Vista.frmRegistrarse;
import Vista.frmuser;
import java.awt.BorderLayout;
import java.util.Base64;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.Security;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JLabel;


import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.modules.synthesis.Voice;


import javax.swing.SwingUtilities;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author juand
 */
public class Ctrl_Usuario implements ActionListener{
    Usuario mat;
    ClsConsultaUsuario sqlmat;
    frmuser frm;
    
    public Ctrl_Usuario(Usuario mat, ClsConsultaUsuario sqlmat, frmuser frm) {
        this.mat = mat;
        this.sqlmat = sqlmat;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener((ActionListener) this);
        this.frm.btnUpdate.addActionListener((ActionListener) this);
        this.frm.btnEliminar.addActionListener((ActionListener) this);
        this.frm.btnBusca.addActionListener((ActionListener) this);  
        this.frm.btnactualizar.addActionListener((ActionListener) this);
        this.frm.btnLimpiar.addActionListener((ActionListener) this);
        this.frm.tbMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
           
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel modelo = (DefaultTableModel) frm.tbMaterias.getModel();
                    
                    
                    int fila=frm.tbMaterias.getSelectedRow();
                    if (fila == -1) {
                        // No se ha seleccionado ninguna fila, maneja el error apropiadamente
                        return;
                    }


                    frm.txtContraseña.setText("");
                    frm.txtBuscar.setEnabled(false);
                    frm.txtID.setText(modelo.getValueAt(fila, 0).toString());
                    frm.txtcorreo.setText(modelo.getValueAt(fila, 1).toString());
                    frm.txtUsuario.setText(modelo.getValueAt(fila, 2).toString());
                    String valorEncriptado = modelo.getValueAt(fila, 3).toString();
                     JLabel imagenLabel = (JLabel) modelo.getValueAt(fila, 4);

                    // Obtiene el icono del JLabel
                    Icon icono = imagenLabel.getIcon();
                    if (icono instanceof ImageIcon) {
                        ImageIcon imagenIcono = (ImageIcon) icono;

                        // Escala la imagen al tamaño deseado y crea un nuevo ImageIcon
                        Image imagen = imagenIcono.getImage().getScaledInstance(frm.lblImagen.getWidth(), frm.lblImagen.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon mIcono = new ImageIcon(imagen);

                        // Establece el ImageIcon en el componente lblImagen
                        frm.lblImagen.setIcon(mIcono);
                    } else {
                        // No se pudo obtener el icono de la imagen, maneja el error apropiadamente
                        System.out.println("Error al obtener el icono de la imagen.");
                    }

                    String desencriptado = desencriptar(valorEncriptado);
                      if (desencriptado != null) {
                          frm.txtContraseña.setText(desencriptado);
                      } else {
                          // No se pudo desencriptar el valor, maneja el error apropiadamente
                          System.out.println("Error al desencriptar el valor.");
                      }

                    frm.txtContraseña.setForeground(Color.black);
                    frm.txtcorreo.setForeground(Color.black);
                    frm.txtUsuario.setForeground(Color.black);
                    frm.txtBuscar.setForeground(Color.black);
                    frm.btnIngresar.setEnabled(false);
                    frm.btnBusca.setEnabled(false);
                    frm.btnUpdate.setEnabled(true);
                    frm.btnEliminar.setEnabled(true);

              }
        });
        
    }
    public static BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage bufferedScaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        bufferedScaledImage.getGraphics().drawImage(scaledImage, 0, 0, null);
        return bufferedScaledImage;
    }
    public void Iniciar()
    {
        frm.txtID.setVisible(false);
         frm.btnUpdate.setEnabled(false);
         frm.btnEliminar.setEnabled(false);
        Mostrar();
    }
    private static final String ALGORITHM = "AES";
    String originalValue = "mi_clave_oculta";
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public  boolean validarCorreo(String correo) {
        boolean esValido = false;

        try {
            InternetAddress internetAddress = new InternetAddress(correo);
            internetAddress.validate();
            esValido = true;

            if (esValido) {
                esValido = existeCorreo(correo);
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error "+ e); 
        }

        return esValido;
    }
     public String generarClaveSugerida() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // Generar al menos un número
        sb.append((char) ('0' + random.nextInt(10)));

        // Generar al menos una letra minúscula
        sb.append((char) ('a' + random.nextInt(26)));

        // Generar al menos una letra mayúscula
        sb.append((char) ('A' + random.nextInt(26)));

        // Generar el resto de caracteres
        for (int i = 0; i < 7; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }

        return sb.toString();
    }
    public boolean validarContraseña(String contraseña) {
        // Verificar la longitud mínima de la contraseña
        if (contraseña.length() < 10) {
            return false;
        }

        boolean tieneNumero = false;
        boolean tieneMinuscula = false;
        boolean tieneMayuscula = false;

        // Verificar cada carácter de la contraseña
        for (char c : contraseña.toCharArray()) {
            if (Character.isDigit(c)) {
                tieneNumero = true;
            } else if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            } else if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }

            // Si se cumplen todos los requisitos, se puede salir del bucle
            if (tieneNumero && tieneMinuscula && tieneMayuscula) {
                break;
            }
        }

        // Verificar si se cumplen todos los requisitos
        return tieneNumero && tieneMinuscula && tieneMayuscula;
    }

    private  boolean existeCorreo(String correo) {
        try {
            String codigoVerificacion = generarCodigoVerificacion();
            String correoPrueba = "soporte.tecnico.2023.dm@gmail.com"; // Correo de prueba
            String dominio = correo.substring(correo.indexOf("@") + 1);
            if("utelvt.edu.ec".equals(dominio)){
                dominio="gmail.com";
                
            }
           
            // Configurar propiedades para la conexión SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp." + dominio);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

            // Configurar un TrustManager personalizado para aceptar todos los certificados SSL
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Obtener una instancia de SSLContext y configurar el TrustManager personalizado
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLContext.setDefault(sslContext);

            // Crear la sesión de correo electrónico utilizando las propiedades y la autenticación
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("soporte.tecnico.2023.dm@gmail.com", "vvjpfuaymmqqybdz");
                }
            });

            // Crear el mensaje de correo electrónico
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoPrueba));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Validación de correo electrónico");
            message.setText("Tu código de verificación es: " + codigoVerificacion);

            // Enviar el mensaje de correo electrónico
            Transport.send(message);
            int intentos = 0;
            String mensaje;

            while (intentos < 3) {
                String codigoIngresado = JOptionPane.showInputDialog(null, "Ingresa el código de verificación enviado al correo " + correo);

                if (codigoIngresado == null) {
                    // El usuario ha cancelado la entrada del código
                    return false;
                }

                if (codigoIngresado.equals(codigoVerificacion)) {
                    mensaje = "Código de verificación correcto. El correo electrónico está validado.";
                    SSMC(mensaje);
                    return true;
                } else {
                    mensaje = "Código de verificación incorrecto. El correo electrónico no está validado.";
                    SSMI(mensaje);
                    intentos++;
                }
            }

            // El usuario ha agotado los intentos permitidos
            mensaje = "Has agotado los intentos permitidos. El correo electrónico no está validado.";
            SSMI(mensaje);

            return false;

            
        } catch (AuthenticationFailedException e) {
            JOptionPane.showMessageDialog(null, "Autenticación fallida. Verifica las credenciales SMTP");
            return false;
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor SMTP. Verifica la configuración de conexión");
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return false;
        }
    }
 private String generarCodigoVerificacion() {
        // Generar un código de verificación aleatorio
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }
   
    public SecretKeySpec generarClave(String llave) {
        try {
            // Generar clave utilizando PBKDF2
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(llave.toCharArray(), "salt".getBytes(StandardCharsets.UTF_8), 65536, 128);
            SecretKeySpec secretKeySpec = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), ALGORITHM);
            return secretKeySpec;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encriptar(String valor) {
        try {
            SecretKeySpec secretKeySpec = generarClave(originalValue);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] valorBytes = valor.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedBytes = cipher.doFinal(valorBytes);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 private JFrame frame;
 public static String Ruta = "";
 
 public void BuscarImg(){
      JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);
        frmRegistrarse registrarse = new frmRegistrarse();
        if (fileChooser.showOpenDialog(registrarse) == JFileChooser.APPROVE_OPTION) {
            Ruta = fileChooser.getSelectedFile().getAbsolutePath();
            Image mImagen = new ImageIcon(Ruta).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(registrarse.lblImagen.getWidth(), registrarse.lblImagen.getHeight(), 0));
            registrarse.lblImagen.setIcon(mIcono);
        }
 }
  public byte[] getImagen(String Ruta) {
        File imagen = new File(Ruta);
        try {
            byte[] icono = new byte[(int) imagen.length()];
            InputStream input = new FileInputStream(imagen);
            input.read(icono);
            return icono;
        } catch (Exception ex) {
            return null;
        }
    }
    public String desencriptar(String valorEncriptado) {
        try {
            SecretKeySpec secretKeySpec = generarClave(originalValue);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] valorEncriptadoBytes = Base64.getDecoder().decode(valorEncriptado);
            byte[] decryptedBytes = cipher.doFinal(valorEncriptadoBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void Voz(String mensaje){
        try {
            // Emitir un sonido para llamar la atención
            Toolkit.getDefaultToolkit().beep();

            // Configurar el sintetizador de voz
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();

            // Iniciar un hilo para mostrar el diálogo
            Thread dialogThread = new Thread(() -> {
                ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/mokey60.gif")); // Reemplaza "/ruta/del/icono.png" con la ruta correcta del icono
                JLabel label = new JLabel(mensaje, icono, JLabel.CENTER);
                 label.setFont(new Font("Arial", Font.BOLD, 16));
                 label.setHorizontalAlignment(JLabel.CENTER);
                 label.setForeground(new Color(255,255,255));
                 // Crear el JFrame y configurarlo
                 frame = new JFrame("Mensaje");
                 frame.setUndecorated(true); // Quitar los bordes del JFrame
                 frame.getContentPane().setBackground(new Color(0,75,159)); // Establecer color de fondo
                 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 frame.addWindowListener(new WindowAdapter() {
                     @Override
                     public void windowClosed(WindowEvent e) {
                         try {
                             // Liberar los recursos del sintetizador cuando se cierra la ventana
                             synthesizer.deallocate();
                         } catch (EngineException | EngineStateError ex) {
                             ex.printStackTrace();
                         }
                     }
                 });

                 // Agregar el JLabel al JFrame
                 frame.getContentPane().add(label);

                int width = Math.max(600, icono.getIconWidth()); // Ajustar el ancho mínimo del JFrame a 600
                int height = Math.min(600, icono.getIconHeight() + mensaje.split("\\n").length * 40); // Ajustar el alto máximo del JFrame a 600
                frame.setSize(width, height);

                 // Centrar el JFrame en la pantalla
                 frame.setLocationRelativeTo(null);

                 // Mostrar el JFrame
                 frame.setVisible(true);
            });

            // Iniciar un hilo para leer el mensaje en voz alta
            Thread voiceThread = new Thread(() -> {
                try {
                    synthesizer.speakPlainText(mensaje, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Iniciar ambos hilos al mismo tiempo
            dialogThread.start();
            voiceThread.start();

            // Esperar a que el hilo de la voz termine
            voiceThread.join();

            // Esperar a que el hilo del diálogo termine
            dialogThread.join();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    frame.dispose(); // Cerrar el JFrame
                }
            }, mensaje.length() * 200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void SSMC(String mensaje){
         // Obtener la imagen desde un archivo
             ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/feli.gif"));
            // Crear un panel personalizado con la imagen y el mensaje
           
            JPanel panel = new JPanel();
            JLabel label2 = new JLabel(icono);
            JLabel label = new JLabel(mensaje, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.setLayout(new BorderLayout());
            panel.add(label2, BorderLayout.WEST); // Agregar la imagen en la parte superior del panel
            panel.add(label); // Agregar el mensaje en el centro del panel

            // Mostrar el JOptionPane con el panel personalizado
            JOptionPane.showMessageDialog(null, panel, "Verificación de Usuario", JOptionPane.PLAIN_MESSAGE);
    }
    public void SSMI(String mensaje){
         // Obtener la imagen desde un archivo
             ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/monotriste.gif"));
            // Crear un panel personalizado con la imagen y el mensaje
           
            JPanel panel = new JPanel();
            JLabel label2 = new JLabel(icono);
            JLabel label = new JLabel(mensaje, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.setLayout(new BorderLayout());
            panel.add(label2, BorderLayout.WEST); // Agregar la imagen en la parte superior del panel
            panel.add(label); // Agregar el mensaje en el centro del panel

            // Mostrar el JOptionPane con el panel personalizado
            JOptionPane.showMessageDialog(null, panel, "Verificación de Usuario", JOptionPane.PLAIN_MESSAGE);
    }
    public void Guardar(String correo,String user, String clave){
         try {
                String mensaje;
                if (!validarContraseña(clave)) {
                    mensaje ="La contraseña debe tener al menos 10 caracteres entre MIN,MAY y números";
                    SSMI(mensaje);
                }else{
                    String encryptedValue = encriptar(clave);
                    mat.setCorreo(correo);
                    mat.setUsuario(user);
                    mat.setPassword(encryptedValue);
                    mat.setImagen(getImagen(Ruta));

                    if(sqlmat.ExisteUsuario(mat)){
                        mensaje = "El Usuario Ingresado Ya Existe. Ingrese otro Usuario";
                        SSMI(mensaje);

                        frm.txtUsuario.setText("Ingrese usuario");
                        frm.txtUsuario.setForeground(new Color(204,204,204));
                    }
                    else if(sqlmat.ExisteCorreoUsuario(mat)){
                        mensaje = "El Correo Ingresado Ya Existe. Ingrese otro Correo";
                        SSMI(mensaje);

                        frm.txtcorreo.setForeground(new Color(204,204,204));
                        frm.txtcorreo.setText("Ingrese correo");
                    }
                    else if(!validarCorreo(correo)){
                        mensaje = "El Correo Ingresado No es Válido";
                        SSMI(mensaje);

                        Limpiar();
                    }
                    else if(sqlmat.Guardar(mat))
                        {
                             mensaje = "Usuario guardado";
                             SSMC(mensaje);

                            Limpiar();
                            Mostrar();
                        }
                    else
                        {
                            mensaje = "No se guardó la informacion del Usuario";
                            SSMI(mensaje);

                        }
                }
                
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     public void Editar(String id,String correo,String user, String clave){
         try {
            String mensaje; 
            String encryptedValue = encriptar(clave);
            mat.setIdUsuario(Integer.parseInt(id));
            mat.setCorreo(correo);
            mat.setUsuario(user);
            mat.setPassword(encryptedValue);
            if(sqlmat.ExisteUsuario(mat)){
                mensaje = "El Usuario Ingresado Ya Existe. Ingrese otro Usuario";
                SSMI(mensaje);
                frm.txtUsuario.setText("Ingrese usuario");
                frm.txtUsuario.setForeground(new Color(204,204,204));
            }
           
            else if(!validarCorreo(correo)){
                mensaje = "El Correo Ingresado No es Válido";
                SSMI(mensaje);
                Limpiar();
            }
            else if(sqlmat.Modificar(mat))
                {mensaje = "Usuario guardado";
                        SSMC(mensaje);
                        Limpiar();
                Mostrar();}
            else
                { mensaje = "No se guardó la informacion del Usuario";
                        SSMI(mensaje);}
         } catch (Exception e) {
              e.printStackTrace();
         }
         
    }



    /**
     *
     * @param e
     */
  
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==frm.btnactualizar){
            Mostrar();
            Limpiar();
         frm.btnUpdate.setEnabled(false);
         frm.btnEliminar.setEnabled(false);
         frm.txtBuscar.setEnabled(true);
         frm.btnIngresar.setEnabled(true);
         frm.btnBusca.setEnabled(true);
          frm.txtBuscar.setText(null);
        }
       
        
        
       
       if(e.getSource()==frm.btnIngresar)
       {
           String mensaje; 
            if("".equals(frm.txtcorreo.getText())){
                
                mensaje="Debe ingresar datos en el campo Correo";
                SSMI(mensaje);

            }
            else if("".equals(frm.txtUsuario.getText())){
                mensaje="Debe ingresar datos en el campo Usuario";
               SSMI(mensaje);

            }
            else if("".equals(frm.txtContraseña.getText())){
                mensaje="Debe ingresar datos en el campo Contraseña";
                 SSMI(mensaje);

            }
            else if("Ingrese usuario".equals(frm.txtUsuario.getText())){
                mensaje="Debe ingresar datos en el campo Usuario";
                 SSMI(mensaje);

            }
            else if("Ingrese contraseña".equals(frm.txtContraseña.getText())){
                mensaje="Debe ingresar datos en el campo Contraseña";
                 SSMI(mensaje);

            }
            else if("Ingrese correo".equals(frm.txtcorreo.getText())){
                mensaje="Debe ingresar datos en el campo Correo";
                SSMI(mensaje);

            }
            else{
                Guardar(frm.txtcorreo.getText(),frm.txtUsuario.getText(),frm.txtContraseña.getText());
               
            }
                
          
        
       }   

     if(e.getSource()==frm.btnUpdate)
       {
            String mensaje; 
               if("".equals(frm.txtcorreo.getText())){
             mensaje="Debe ingresar datos en el campo Correo";
                SSMI(mensaje);

            }
            else if("".equals(frm.txtUsuario.getText())){
                 mensaje="Debe ingresar datos en el campo Usuario";
               SSMI(mensaje);

            }
            else if("".equals(frm.txtContraseña.getText())){
                mensaje="Debe ingresar datos en el campo Contraseña";
                 SSMI(mensaje);

            }
            else if("Ingrese usuario".equals(frm.txtUsuario.getText())){
                mensaje="Debe ingresar datos en el campo Usuario";
                SSMI(mensaje);
            }
            else if("Ingrese contraseña".equals(frm.txtContraseña.getText())){
                mensaje="Debe ingresar datos en el campo Contraseña";
                 SSMI(mensaje);

            }
            else if("Ingrese correo".equals(frm.txtcorreo.getText())){
                 mensaje="Debe ingresar datos en el campo Correo";
                SSMI(mensaje);
            }
            else{
                   Editar(frm.txtID.getText(), frm.txtcorreo.getText(), frm.txtUsuario.getText(),frm.txtContraseña.getText());
            }   
           
       }
    
       if(e.getSource()==frm.btnEliminar)
       {
          String mensaje;
          mat.setIdUsuario(Integer.parseInt(frm.txtID.getText()));
           if(sqlmat.Eliminar(mat))
           {
                mensaje="Se elimino"; SSMC(mensaje);Limpiar();Mostrar();frm.btnIngresar.setEnabled(true);
                frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                Mostrar();
           }
           else
           {
               mensaje="No se elimino"; SSMI(mensaje);Limpiar();frm.btnIngresar.setEnabled(true);
               frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
           }
       }
       if(e.getSource()==frm.btnBusca)
       {
           frm.tbMaterias.setDefaultRenderer(Object.class, new Render());
           String mensaje;
           mat.setUsuario(frm.txtBuscar.getText());
           if(sqlmat.BuscarUsuario(mat)){
               String[] columnas ={"ID","Correo","Usuario","Contraseña","Imagen"};
               Object[] datos = new Object[5];
               DefaultTableModel tabla = new DefaultTableModel(null,columnas){
                   
                  @Override
                  public boolean isCellEditable(int i, int j)
                  { if(i==5){return true;} else {return false;}}
                };
          
                List objList; Usuario cls;
                try {
                     objList= sqlmat.ListarBussqueda(mat.getUsuario());
                     if(!objList.isEmpty())
                     {
                      for (int i = 0; i < objList.size(); i++) {
                         cls = (Usuario) objList.get(i);

                         datos[0] = cls.getIdUsuario();
                         datos[1] = cls.getCorreo();
                         datos[2] = cls.getUsuario();
                         datos[3] = cls.getPassword();
                         byte[] imagen = cls.getImagen();
                         BufferedImage bufferedImage = null;
                         InputStream inputStream = new ByteArrayInputStream(imagen);
                         bufferedImage = ImageIO.read(inputStream);
                         ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(100, 100, 0));
                         datos[4] = new JLabel(mIcono);
                         tabla.addRow(datos);
                       }  
                      frm.tbMaterias.setModel(tabla);
                      Limpiar();
                       frm.txtBuscar.setText("Buscar Por nombre de Usuario");
                      DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                                  Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                                  if (frm.tbMaterias.getColumnCount() >= 7) {
                                     for(int i=4; i<7;i++)
                                     {  
                                         frm.tbMaterias.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                     }
                                 }
                    }
                    else
                    {
                        mensaje="No encontro información";SSMI(mensaje);
                        Limpiar(); frm.txtBuscar.setText("Buscar Por nombre de Usuario");
                    }
                } catch (Exception ex) {
                    
                    Logger.getLogger(Ctrl_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                
                }  


           }else
           {
             mensaje="No encontraron Datos";SSMI(mensaje); Limpiar(); frm.txtBuscar.setText("Buscar Por nombre de Usuario");
           }
       }
           
    }   
     private void Mostrar()
    {
         frm.tbMaterias.setDefaultRenderer(Object.class, new Render());
        String mensaje;
        String[] columnas ={"ID","Correo","Usuario","Contraseña","Imagen"};
           Object[] datos = new Object[5];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==5){return true;} else {return false;}}
           };
          
           List objList; Usuario cls;
           try {
                objList= sqlmat.MostrarUsuario();
                if(!objList.isEmpty())
                {
                 for (int i = 0; i < objList.size(); i++) {
                    cls = (Usuario) objList.get(i);
                    datos[0] = cls.getIdUsuario();
                    datos[1] = cls.getCorreo();
                    datos[2] = cls.getUsuario();
                    datos[3] = cls.getPassword();
                     byte[] imagen = cls.getImagen();
                        BufferedImage bufferedImage = null;
                        InputStream inputStream = new ByteArrayInputStream(imagen);
                        bufferedImage = ImageIO.read(inputStream);
                        ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(100, 100, 0));
                        datos[4] = new JLabel(mIcono);
                    tabla.addRow(datos);
                  }  
                 frm.tbMaterias.setModel(tabla);
                 Limpiar();
                  frm.txtBuscar.setText("Buscar Por nombre de Usuario");
                 
                  DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                             Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                             if (frm.tbMaterias.getColumnCount() >= 7) {
                                for(int i=4; i<7;i++)
                                {  
                                    frm.tbMaterias.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                }
                            }
               }
               else
               {mensaje="No encontro información"; SSMI(mensaje); }
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_Usuario.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
   public void Limpiar()
    {
        frm.txtID.setText(null);
        frm.txtUsuario.setText("Ingrese usuario");
        frm.txtContraseña.setText("Ingrese contraseña");
        frm.txtBuscar.setText("Buscar Por nombre de Usuario");
        frm.txtcorreo.setText("Ingrese correo");
        frm.txtUsuario.setFocusable(true);
        frm.txtContraseña.setForeground(new Color(204,204,204));
        frm.txtUsuario.setForeground(new Color(204,204,204));
        frm.txtcorreo.setForeground(new Color(204,204,204));
        ImageIcon imagenIcono = new ImageIcon(getClass().getResource("/imagenes/icons8_user_100px.png"));
        frm.lblImagen.setIcon(imagenIcono);
        frm.lblImagen.setHorizontalAlignment(JLabel.CENTER);
    }
  
}