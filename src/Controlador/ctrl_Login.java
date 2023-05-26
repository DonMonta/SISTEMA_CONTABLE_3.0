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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author monta
 */
public class ctrl_Login{

     Usuario usuario;
     ClsConsultaUsuario clsconsulta_usuario;
     
     
    public ctrl_Login(Usuario usuario, ClsConsultaUsuario clsconsulta_usuario) {
        this.usuario = usuario;
        this.clsconsulta_usuario = clsconsulta_usuario;
    }
     private static final String ALGORITHM = "AES";
    String originalValue = "mi_clave_oculta";
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
    public boolean Log(String us, String cla){
        String encryptedValue = encriptar(cla);
        usuario.setUsuario(us);
        usuario.setPassword(encryptedValue);
        if (clsconsulta_usuario.Login(usuario)) {
            JOptionPane.showMessageDialog(null, "Bienvenido Usuario " + us);
            frmMenuP menu = new frmMenuP();
            menu.setVisible(true);
            menu.userlbl.setText(us);

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o Clave Incorrectos");
            return false;
        }
    }
              
}
