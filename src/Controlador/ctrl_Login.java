/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Clsconsulta_usuario;
import Modelo.Usuario;
import Vista.frmLogin;
import Vista.frmMenuP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author monta
 */
public class ctrl_Login implements ActionListener{

    frmLogin frm;
     Usuario usuario;
     Clsconsulta_usuario clsconsulta_usuario;
     
    public ctrl_Login(frmLogin frm, Usuario usuario, Clsconsulta_usuario clsconsulta_usuario) {
        this.frm = frm;
        this.usuario = usuario;
        this.clsconsulta_usuario = clsconsulta_usuario;
        this.frm.jbtnIngresar.addActionListener((ActionListener) this);
        this.frm.jbtnSalir.addActionListener((ActionListener)this);
    }
    public void iniciar(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.jbtnIngresar){
            
             if (!frm.jtxtUsuario.getText().isEmpty() && !frm.jtxtClave.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Ingrese sus credenciales");
                } 
        }
           
    }
    public void Log(String us, String cla){
            usuario.setUsuario(us);
            usuario.setPassword(cla);
            if (clsconsulta_usuario.Login(usuario)) {
                JOptionPane.showMessageDialog(null, "Login Correcto...");
                frmMenuP menu = new frmMenuP();
               menu.setVisible(true);
                frm.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o Clave Incorrectos");
            }
    }
   
}
