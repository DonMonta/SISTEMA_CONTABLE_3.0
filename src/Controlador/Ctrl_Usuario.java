/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClsConsultaUsuario;
import Modelo.Usuario;
import Vista.frmRegistrarse;
import Vista.frmuser;
import java.util.Base64;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.nio.charset.StandardCharsets;
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
import java.util.Properties;
import java.util.Random;
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
                  int fila;
              
                  fila=frm.tbMaterias.getSelectedRow();
                
                  frm.txtContraseña.setText("");
                  frm.txtBuscar.setEnabled(false);
                  frm.txtID.setText(modelo.getValueAt(fila, 0).toString());
                  frm.txtcorreo.setText(modelo.getValueAt(fila, 1).toString());
                  frm.txtUsuario.setText(modelo.getValueAt(fila, 2).toString());
                  String valorEncriptado = modelo.getValueAt(fila, 3).toString();
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
                  frm.btnIngresar.setEnabled(false);
                  frm.btnBusca.setEnabled(false);
                  frm.btnUpdate.setEnabled(true);
                  frm.btnEliminar.setEnabled(true);
              
            }
        });
        
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

    private  boolean existeCorreo(String correo) {
        try {
            String codigoVerificacion = generarCodigoVerificacion();
            String correoPrueba = "soporte.tecnico.2023.dm@gmail.com"; // Correo de prueba
            String dominio = correo.substring(correo.indexOf("@") + 1);

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
                    return new PasswordAuthentication("soporte.tecnico.2023.dm@gmail.com", "zjayvubdpmuinmtr");
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
             // Solicitar al usuario que ingrese el código de verificación
            String codigoIngresado = JOptionPane.showInputDialog(null, "Ingresa el código de verificación enviado al correo " + correo );

            while (codigoIngresado != null) {
                if (codigoIngresado.equals(codigoVerificacion)) {
                    JOptionPane.showMessageDialog(null, "Código de verificación correcto. El correo electrónico está validado.");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Código de verificación incorrecto. El correo electrónico no está validado.");
                    codigoIngresado = JOptionPane.showInputDialog(null, "Ingresa el código de verificación enviado al correo electrónico");
                }
            }

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
    public void Guardar(String correo,String user, String clave){
        String encryptedValue = encriptar(clave);
        mat.setCorreo(correo);
        mat.setUsuario(user);
        mat.setPassword(encryptedValue);
        if(sqlmat.ExisteUsuario(mat)){
            JOptionPane.showMessageDialog(null, "El Usuario Ingresado Ya Existe. Ingrese otro Usuario"); 
            frm.txtUsuario.setText("Ingrese usuario");
            frm.txtUsuario.setForeground(new Color(204,204,204));
        }
        else if(sqlmat.ExisteCorreoUsuario(mat)){
            JOptionPane.showMessageDialog(null, "El Correo Ingresado Ya Existe. Ingrese otro Correo");
            frm.txtcorreo.setForeground(new Color(204,204,204));
            frm.txtcorreo.setText("Ingrese correo");
        }
        else if(!validarCorreo(correo)){
            JOptionPane.showMessageDialog(null, "El Correo Ingresado No es Válido"); Limpiar();
        }
        else if(sqlmat.Guardar(mat))
            {JOptionPane.showMessageDialog(null, "Usuario guardado"); Limpiar();
            Mostrar();}
        else
            {JOptionPane.showMessageDialog(null, "No se guardó la informacion del Usuario");}
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
                if("".equals(frm.txtcorreo.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Correo");

                    

                }
                else if("".equals(frm.txtUsuario.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Usuario");

                    

                }
                else if("".equals(frm.txtContraseña.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Contraseña");

                    

                }
                else{
                    Guardar(frm.txtcorreo.getText(),frm.txtUsuario.getText(),frm.txtContraseña.getText());
                    Limpiar();
                }
                
          
        
       }   

     if(e.getSource()==frm.btnUpdate)
       {
                if("".equals(frm.txtUsuario.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Usuario");

                    

                }
                else if("".equals(frm.txtContraseña.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Contraseña");

                    

                }
                else{
                     String encryptedValue = encriptar(frm.txtContraseña.getText());
                    mat.setIdUsuario(Integer.parseInt(frm.txtID.getText()));
                    mat.setCorreo(frm.txtcorreo.getText());
                    mat.setUsuario(frm.txtUsuario.getText());
                    mat.setPassword(encryptedValue);

                    if(sqlmat.Modificar(mat))
                    {JOptionPane.showMessageDialog(null, "Se actualizo la informacion del Usuario"); Limpiar(); Mostrar();}
                    else
                    {JOptionPane.showMessageDialog(null, "No se actualizo la informacion del Usuario");Limpiar();}

                }
                
           
       }
    
       if(e.getSource()==frm.btnEliminar)
       {
          
          mat.setIdUsuario(Integer.parseInt(frm.txtID.getText()));
           if(sqlmat.Eliminar(mat))
           {JOptionPane.showMessageDialog(null, "Se elimino"); Limpiar();Mostrar();frm.btnIngresar.setEnabled(true);
            frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
            Mostrar();}
           else
           {JOptionPane.showMessageDialog(null, "No se elimino");Limpiar();frm.btnIngresar.setEnabled(true);
            frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);}
       }
       if(e.getSource()==frm.btnBusca)
       {
        
               mat.setUsuario(frm.txtBuscar.getText());
         
           if(sqlmat.BuscarUsuario(mat))
           {

            String[] columnas ={"ID","Correo","Usuario","Contraseña"};
           Object[] datos = new Object[4];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==6){return true;} else {return false;}}
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
                    tabla.addRow(datos);
                  }  
                 frm.tbMaterias.setModel(tabla);
                 Limpiar();
                 frm.txtBuscar.setText(null);
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
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtBuscar.setText(null);}
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }  
              
               
             }
                 else
                 {
                     JOptionPane.showMessageDialog(null, "No encontro una Tienda Con ese Nombre"); Limpiar();frm.txtBuscar.setText(null);
                    }
                }
           
    }   
     private void Mostrar()
    {
   String[] columnas ={"ID","Correo","Usuario","Contraseña"};
           Object[] datos = new Object[4];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==3){return true;} else {return false;}}
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
                    tabla.addRow(datos);
                  }  
                 frm.tbMaterias.setModel(tabla);
                 Limpiar();
                 frm.txtBuscar.setText(null);
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
               {JOptionPane.showMessageDialog(null, "No encontro información"); }
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_Usuario.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
   public void Limpiar()
    {
        frm.txtID.setText(null);
        frm.txtUsuario.setText(null);
        frm.txtContraseña.setText(null);
        frm.txtUsuario.setFocusable(true);
        frm.txtContraseña.setForeground(new Color(204,204,204));
         frm.txtUsuario.setForeground(new Color(204,204,204));
    }
   private boolean Validar(){
        if("".equals(frm.txtUsuario.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
            return  false;
            
        }
        if("".equals(frm.txtContraseña.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
            return  false;
          
        }
        return false;
    }
}