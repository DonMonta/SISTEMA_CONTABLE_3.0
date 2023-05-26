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