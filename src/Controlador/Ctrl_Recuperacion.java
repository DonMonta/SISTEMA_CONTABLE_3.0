/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClsConsultaUsuario;
import Modelo.Usuario;
import Vista.frmLogin;
import Vista.frmRecuperacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author monta
 */
public class Ctrl_Recuperacion{
    Usuario us;
    ClsConsultaUsuario sqlus;

    public Ctrl_Recuperacion(Usuario us, ClsConsultaUsuario sqlus) {
        this.us = us;
        this.sqlus = sqlus;
    }
     private static final String ALGORITHM = "AES";
    String originalValue = "mi_clave_oculta";
    private static String remitente = "soporte.tecnico.2023.dm@gmail.com";
    private static String claveremitente = "zjayvubdpmuinmtr";
    public static String destinatario;
    private String emailTo;
    private String subject;
    private String content;
    
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    String clave;
    public void Iniciar(){
        mProperties = new Properties();
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
    
  
      public boolean Enviar(String user) {
          us.setUsuario(user);
          if(sqlus.RecuperarClave(us)){
                
                destinatario = us.getCorreo();
                clave = us.getPassword();
                emailTo = destinatario;
                subject = "Solicitud de Recuperacion de Clave";
                content = "Tu contraseña es: "+desencriptar(clave);

                 // Simple mail transfer protocol
                mProperties.put("mail.smtp.host", "smtp.gmail.com");
                mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                mProperties.setProperty("mail.smtp.starttls.enable", "true");
                mProperties.setProperty("mail.smtp.port", "587");
                mProperties.setProperty("mail.smtp.user",remitente);
                mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
                mProperties.setProperty("mail.smtp.auth", "true");

                mSession = Session.getDefaultInstance(mProperties);


                try {
                    mCorreo = new MimeMessage(mSession);
                    mCorreo.setFrom(new InternetAddress(remitente));
                    mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                    mCorreo.setSubject(subject);
                    mCorreo.setText(content, "ISO-8859-1", "html");
                    

                } catch (AddressException ex) {
                    Logger.getLogger(Ctrl_Recuperacion.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } catch (MessagingException ex) {
                    Logger.getLogger(Ctrl_Recuperacion.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                sendEmail();
                return true;
              
          }
          return false;
       
    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(remitente, claveremitente);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Ctrl_Recuperacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Ctrl_Recuperacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
