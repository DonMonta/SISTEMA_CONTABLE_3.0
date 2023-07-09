/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Ctrl_Usuario;
import Modelo.ClsConsultaUsuario;
import Modelo.Usuario;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author monta
 */
public class frmRegistrarse extends javax.swing.JFrame {

    /**
     * Creates new form frmRegistrarse
     */
    public frmRegistrarse() {
        initComponents();
        this.setLocationRelativeTo(null);
         txtcorreo.requestFocus();
          if(this.txtcorreo.getText().equals("Ingrese correo")){
            this.txtcorreo.setText("");
            this.txtcorreo.setForeground(Color.black);
        }

        
        if(String.valueOf(this.txtUsuario.getText()).isEmpty()){
            this.txtUsuario.setText("Ingrese usuario");
            this.txtUsuario.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtContraseña.getText()).isEmpty()){
            this.txtContraseña.setText("Ingrese contraseña");
            this.txtContraseña.setForeground(new Color(204,204,204));
        }
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        txtContraseña = new javax.swing.JTextField();
        btnactualizar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnregistrate = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnregistrate1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagen.setBackground(new java.awt.Color(255, 255, 255));
        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_user_100px.png"))); // NOI18N
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 75, 159)));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 160, 140));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Correo:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        txtcorreo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtcorreo.setForeground(new java.awt.Color(204, 204, 204));
        txtcorreo.setText("Ingrese correo");
        txtcorreo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtcorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtcorreoMousePressed(evt);
            }
        });
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });
        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcorreoKeyPressed(evt);
            }
        });
        jPanel1.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 380, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(204, 204, 204));
        txtUsuario.setText("Ingrese usuario");
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUsuarioMousePressed(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 380, 40));

        btnguardar.setBackground(new java.awt.Color(0, 75, 159));
        btnguardar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setText("Guardar");
        btnguardar.setBorder(null);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 101, 40));

        txtContraseña.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(204, 204, 204));
        txtContraseña.setText("Ingrese contraseña");
        txtContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtContraseñaMousePressed(evt);
            }
        });
        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyPressed(evt);
            }
        });
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 380, 40));

        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-actualizar.gif"))); // NOI18N
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 40, 40));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Contraseña:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        btnsalir.setBackground(new java.awt.Color(0, 75, 159));
        btnsalir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("Salir");
        btnsalir.setBorder(null);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 101, 40));

        btnregistrate.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnregistrate.setForeground(new java.awt.Color(0, 75, 159));
        btnregistrate.setText("Clic aquí para Sugerir Clave Segura");
        btnregistrate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrateMouseClicked(evt);
            }
        });
        jPanel1.add(btnregistrate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 75, 159));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 200, 10));

        jLabel4.setBackground(new java.awt.Color(0, 75, 159));
        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-users-48.png"))); // NOI18N
        jLabel4.setText("Regístrate");
        jLabel4.setToolTipText("");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 100));

        jSeparator2.setForeground(new java.awt.Color(0, 75, 159));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 150, 10));

        btnregistrate1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnregistrate1.setForeground(new java.awt.Color(0, 75, 159));
        btnregistrate1.setText("Seleccionar Imagen");
        btnregistrate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrate1MouseClicked(evt);
            }
        });
        jPanel1.add(btnregistrate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcorreoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcorreoMousePressed
        if(this.txtcorreo.getText().equals("Ingrese correo")){
            this.txtcorreo.setText("");
            this.txtcorreo.setForeground(Color.black);
        }

        
        if(String.valueOf(this.txtUsuario.getText()).isEmpty()){
            this.txtUsuario.setText("Ingrese usuario");
            this.txtUsuario.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtContraseña.getText()).isEmpty()){
            this.txtContraseña.setText("Ingrese contraseña");
            this.txtContraseña.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtcorreoMousePressed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void txtUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMousePressed
        if(this.txtUsuario.getText().equals("Ingrese usuario")){
            this.txtUsuario.setText("");
            this.txtUsuario.setForeground(Color.black);
        }

       
        if(String.valueOf(this.txtContraseña.getText()).isEmpty()){
            this.txtContraseña.setText("Ingrese contraseña");
            this.txtContraseña.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtcorreo.getText()).isEmpty()){
            this.txtcorreo.setText("Ingrese correo");
            this.txtcorreo.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtUsuarioMousePressed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtContraseñaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtContraseñaMousePressed
        if(this.txtContraseña.getText().equals("Ingrese contraseña")){
            this.txtContraseña.setText("");
            this.txtContraseña.setForeground(Color.black);
        }

        
        if(String.valueOf(this.txtUsuario.getText()).isEmpty()){
            this.txtUsuario.setText("Ingrese usuario");
            this.txtUsuario.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtcorreo.getText()).isEmpty()){
            this.txtcorreo.setText("Ingrese correo");
            this.txtcorreo.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtContraseñaMousePressed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if("".equals(txtcorreo.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Correo");

        }
        else if("".equals(txtUsuario.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Usuario");

        }
        else if("".equals(txtContraseña.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Contraseña");

        }
        else if("Ingrese usuario".equals(txtUsuario.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Usuario");

        }
        else if("Ingrese contraseña".equals(txtContraseña.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Contraseña");

        }
        else if("Ingrese correo".equals(txtcorreo.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Correo");

        }else{
            Usuario usuario = new Usuario();
            ClsConsultaUsuario consultaUsuario = new ClsConsultaUsuario();
            frmuser registrarse = new frmuser();
            Ctrl_Usuario ctrl = new Ctrl_Usuario(usuario, consultaUsuario, registrarse);
            ctrl.Guardar(txtcorreo.getText(), txtUsuario.getText(), txtContraseña.getText());
            txtcorreo.setText("Ingrese correo");txtUsuario.setText("Ingrese usuario"); txtContraseña.setText("Ingrese contraseña");
            this.txtcorreo.setForeground(new Color(204,204,204));
            this.txtUsuario.setForeground(new Color(204,204,204));
            this.txtContraseña.setForeground(new Color(204,204,204));
            ImageIcon imagenIcono = new ImageIcon(getClass().getResource("/imagenes/icons8_user_100px.png"));
            lblImagen.setIcon(imagenIcono);
            lblImagen.setHorizontalAlignment(JLabel.CENTER);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        frmLogin login = new frmLogin();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
       txtcorreo.setText("Ingrese correo");txtUsuario.setText("Ingrese usuario");
       txtContraseña.setText("Ingrese contraseña");
         ImageIcon imagenIcono = new ImageIcon(getClass().getResource("/imagenes/icons8_user_100px.png"));
            lblImagen.setIcon(imagenIcono);
            lblImagen.setHorizontalAlignment(JLabel.CENTER);
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnregistrateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrateMouseClicked
        Usuario usuario = new Usuario();
        ClsConsultaUsuario consultaUsuario = new ClsConsultaUsuario();
        frmuser registrarse = new frmuser();
        Ctrl_Usuario ctrl = new Ctrl_Usuario(usuario, consultaUsuario, registrarse);
        String sugerenciaClave = ctrl.generarClaveSugerida();
        this.txtContraseña.setForeground(Color.black);
        txtContraseña.setText(sugerenciaClave);
       
    }//GEN-LAST:event_btnregistrateMouseClicked

    private void btnregistrate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrate1MouseClicked
        Usuario usuario = new Usuario();
        ClsConsultaUsuario consultaUsuario = new ClsConsultaUsuario();
        frmuser registrarse = new frmuser();
        Ctrl_Usuario ctrl = new Ctrl_Usuario(usuario, consultaUsuario, registrarse);
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ctrl.Ruta = fileChooser.getSelectedFile().getAbsolutePath();
            Image mImagen = new ImageIcon(ctrl.Ruta).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), 0));
            lblImagen.setIcon(mIcono);
        }
    }//GEN-LAST:event_btnregistrate1MouseClicked

    private void txtcorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Transferir el foco al siguiente componente
             if(String.valueOf(this.txtUsuario.getText()).isEmpty()){
                this.txtUsuario.setText("Ingrese usuario");
                this.txtUsuario.setForeground(new Color(204,204,204));
                txtUsuario.requestFocus();
            }
            
        }
    }//GEN-LAST:event_txtcorreoKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Transferir el foco al siguiente componente
             if(this.txtContraseña.getText().equals("Ingrese contraseña")){
                this.txtContraseña.setText("");
                this.txtContraseña.setForeground(Color.black);
                txtContraseña.requestFocus();
            }
            
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Transferir el foco al siguiente componente
               if("".equals(txtcorreo.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Correo");

                }
                else if("".equals(txtUsuario.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Usuario");

                }
                else if("".equals(txtContraseña.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Contraseña");

                }
                else if("Ingrese usuario".equals(txtUsuario.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Usuario");

                }
                else if("Ingrese contraseña".equals(txtContraseña.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Contraseña");

                }
                else if("Ingrese correo".equals(txtcorreo.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo Correo");

                }else{
                    Usuario usuario = new Usuario();
                    ClsConsultaUsuario consultaUsuario = new ClsConsultaUsuario();
                    frmuser registrarse = new frmuser();
                    Ctrl_Usuario ctrl = new Ctrl_Usuario(usuario, consultaUsuario, registrarse);
                    ctrl.Guardar(txtcorreo.getText(), txtUsuario.getText(), txtContraseña.getText());
                    txtcorreo.setText("Ingrese correo");txtUsuario.setText("Ingrese usuario"); txtContraseña.setText("Ingrese contraseña");
                    this.txtcorreo.setForeground(new Color(204,204,204));
                    this.txtUsuario.setForeground(new Color(204,204,204));
                    this.txtContraseña.setForeground(new Color(204,204,204));
                    ImageIcon imagenIcono = new ImageIcon(getClass().getResource("/imagenes/icons8_user_100px.png"));
                    lblImagen.setIcon(imagenIcono);
                    lblImagen.setHorizontalAlignment(JLabel.CENTER);
                }
            
        }
    }//GEN-LAST:event_txtContraseñaKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegistrarse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnactualizar;
    public javax.swing.JButton btnguardar;
    private javax.swing.JLabel btnregistrate;
    private javax.swing.JLabel btnregistrate1;
    public javax.swing.JButton btnsalir;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel lblImagen;
    public javax.swing.JTextField txtContraseña;
    public javax.swing.JTextField txtUsuario;
    public javax.swing.JTextField txtcorreo;
    // End of variables declaration//GEN-END:variables
}
