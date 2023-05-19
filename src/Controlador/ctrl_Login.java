/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClsConsultaUsuario;
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
     ClsConsultaUsuario clsconsulta_usuario;
     
     
    public ctrl_Login(frmLogin frm, Usuario usuario, ClsConsultaUsuario clsconsulta_usuario) {
        this.frm = frm;
        this.usuario = usuario;
        this.clsconsulta_usuario = clsconsulta_usuario;
        this.frm.jbtnIngresar.addActionListener((ActionListener) this);
        this.frm.btnver.addActionListener((ActionListener) this);
        this.frm.btnocultar.addActionListener((ActionListener) this);
        this.frm.jbtnSalir.addActionListener((ActionListener)this);
    }
    public void iniciar(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.btnocultar){
            frm.txtpassword.setEchoChar('*');
            frm.btnocultar.setVisible(false);
             
        }
        if(e.getSource()==frm.btnver){
            frm.txtpassword.setEchoChar((char) 0);
             frm.btnver.setVisible(true);
        }
        if(e.getSource()==frm.jbtnIngresar){
           
             if(Log(frm.jtxtUsuario.getText(),frm.txtpassword.getText())){
              frm.dispose();
            }
        }
           
    }
    public boolean Log(String us, String cla){
            if("".equals(frm.jtxtUsuario.getText())){
                JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo usuario"); 
            }
            else if("".equals(frm.txtpassword.getText())){
                JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo clave");
            }else{
                
                    usuario.setUsuario(us);
                usuario.setPassword(cla);
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
            
         return false;
            
    }
    
}
