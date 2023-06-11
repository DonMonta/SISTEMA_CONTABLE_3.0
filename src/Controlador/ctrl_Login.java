/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClsConsultaUsuario;
import Modelo.Usuario;
import Vista.frmLogin;
import Vista.frmMenuP;
import Vista.frmRecuperacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author monta
 */
public class ctrl_Login{

     Usuario usuario;
     ClsConsultaUsuario clsconsulta_usuario;
     
     private JFrame frame;
    public ctrl_Login(Usuario usuario, ClsConsultaUsuario clsconsulta_usuario) {
        this.usuario = usuario;
        this.clsconsulta_usuario = clsconsulta_usuario;
    }
     private static final String ALGORITHM = "AES";
    String originalValue = "mi_clave_oculta";
    private void iniciarHilos(String mensaje) {
        try {
                System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
                Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
                synthesizer.allocate();
                synthesizer.resume();
                Thread dialogThread = new Thread(() -> {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/mokey60.gif")); // Reemplaza "/ruta/del/icono.png" con la ruta correcta del icono
                        JLabel label = new JLabel(mensaje, JLabel.CENTER);
                        JLabel label2 = new JLabel(icono);
                        label.setFont(new Font("Arial", Font.BOLD, 16));
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label2.setHorizontalAlignment(SwingConstants.LEFT);
                        label.setForeground(new Color(255, 255, 255));

                        // Crear el JFrame y configurarlo
                        frame = new JFrame("Mensaje");
        //                frame.setUndecorated(true); // Quitar los bordes del JFrame
                        frame.getContentPane().setBackground(new Color(0, 75, 159)); // Establecer color de fondo
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

                        // Crear un panel para contener el JLabel y el botón
                        JPanel panel = new JPanel();
                        panel.setBackground(new Color(0, 75, 159));
                        panel.setLayout(new BorderLayout());
                        // Crear un panel para contener el JLabel
                        JPanel labelPanel = new JPanel();
                        labelPanel.setBackground(new Color(0, 75, 159));
                        labelPanel.add(label);
                        JPanel labelPanel2 = new JPanel();
                        labelPanel2.setBackground(new Color(0, 75, 159));
                        labelPanel2.add(label2);
                        panel.add(labelPanel, BorderLayout.CENTER);
                        panel.add(labelPanel2, BorderLayout.WEST);



                            // Crear el botón para cerrar el formulario
                            JButton closeButton = new JButton("Aceptar");
                            closeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (clsconsulta_usuario.Login(usuario)) {
                                        frmMenuP menu = new frmMenuP();
                                        menu.setVisible(true);
                                        menu.userlbl.setText(frmLogin.user);
                                        frame.dispose(); // Cerrar el JFrame
                                    }
                                    else{
                                        frame.dispose(); 
                                    }

                                }
                            });
                           // Establecer el tamaño preferido del botón
                            closeButton.setPreferredSize(new Dimension(100, 30)); // Ajusta el ancho y alto según tus necesidades

                            panel.add(closeButton, BorderLayout.SOUTH);


                        frame.getContentPane().add(panel);

                        int width = Math.max(600, icono.getIconWidth()); // Ajustar el ancho mínimo del JFrame a 600
                        int height = Math.min(600, icono.getIconHeight() + mensaje.split("\\n").length * 40); // Ajustar el alto máximo del JFrame a 600
                        frame.setSize(width, height); // Calcular la altura proporcional al ancho
                        label.setPreferredSize(new Dimension(width, height));
                        frame.pack();

                        // Centrar el JFrame en la pantalla
                        frame.setLocationRelativeTo(null);

                        // Mostrar el JFrame
                        SwingUtilities.invokeLater(() -> frame.setVisible(true));
            });

            Thread voiceThread = new Thread(() -> {
                try {
                    synthesizer.speakPlainText(mensaje, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Iniciar los hilos
            dialogThread.start();
            voiceThread.start();

            // Esperar a que ambos hilos terminen
            try {
                dialogThread.join();
                voiceThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
        }
         
}

    public void Voz(String mensaje){
         try {
            // Emitir un sonido para llamar la atención
            Toolkit.getDefaultToolkit().beep();
             iniciarHilos(mensaje);
           

           
        } catch (Exception e) {
            e.printStackTrace();
        }
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
             ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/mono65.gif"));
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
    public boolean Log(String us, String cla){
        String Mensaje;
        
        usuario.setUsuario(us);
        
        if(clsconsulta_usuario.ExisteUsuario(usuario)){
            String encryptedValue = encriptar(cla);
            usuario.setPassword(encryptedValue);
             if (clsconsulta_usuario.Login(usuario)) {
           
                Mensaje = "Bienvenido " + us;
                SSMC(Mensaje);
                frmMenuP menu = new frmMenuP();
                menu.setVisible(true);
                try {
                    byte[] imagen = usuario.getImagen();
                    BufferedImage bufferedImage = null;
                    InputStream inputStream = new ByteArrayInputStream(imagen);
                    bufferedImage = ImageIO.read(inputStream);
                    ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(menu.imglbl.getWidth(), menu.imglbl.getHeight(), Image.SCALE_SMOOTH));
                    menu.imglbl.setIcon(mIcono);
                    menu.userlbl.setText(us);
                    usuario.setPassword("");
                } catch (Exception e) {
                    e.printStackTrace();
                }



                return true;
            } else {
               Mensaje = "Usuario o Clave Incorrectos";
               SSMI(Mensaje);
                return false;
            }
        }else{
            Mensaje = "El Usuario No Existe. Ingresa Usuario Válido o Crea uno";
               SSMI(Mensaje);
                return false;
        }
       
    }
              
}
