/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClsConsultaUsuario;
import Modelo.Usuario;
import Vista.FrmUSUARIOS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author juand
 */
public class Ctrl_Usuario implements ActionListener{
    Usuario mat;
    ClsConsultaUsuario sqlmat;
    FrmUSUARIOS frm;
    public Ctrl_Usuario(Usuario mat, ClsConsultaUsuario sqlmat, FrmUSUARIOS frm) {
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
                
               
                  frm.txtBuscar.setEnabled(false);
                  frm.txtID.setText(modelo.getValueAt(fila, 0).toString());
                  frm.txtUsuario.setText(modelo.getValueAt(fila, 1).toString());
              frm.txtContraseña.setText(modelo.getValueAt(fila, 2).toString());
              
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
          mat.setUsuario(frm.txtUsuario.getText());
           mat.setPassword(frm.txtContraseña.getText());
           
           if(sqlmat.Guardar(mat))
           {JOptionPane.showMessageDialog(null, "Usuario guardado"); Limpiar();}
           else
           {JOptionPane.showMessageDialog(null, "No se guardó la informacion del Usuario");Limpiar();}
       }   
            
             
            
       
    
     if(e.getSource()==frm.btnUpdate)
       {
           mat.setIdUsuario(Integer.parseInt(frm.txtID.getText()));
           mat.setUsuario(frm.txtUsuario.getText());
           mat.setPassword(frm.txtContraseña.getText());
           
           if(sqlmat.Modificar(mat))
           {JOptionPane.showMessageDialog(null, "Se actualizo la informacion del Usuario"); Limpiar();}
           else
           {JOptionPane.showMessageDialog(null, "No se actualizo la informacion del Usuario");Limpiar();}
       }
    
       if(e.getSource()==frm.btnEliminar)
       {
          
          mat.setIdUsuario(Integer.parseInt(frm.txtID.getText()));
           if(sqlmat.Eliminar(mat))
           {JOptionPane.showMessageDialog(null, "Se elimino"); Limpiar();Mostrar();frm.btnIngresar.setEnabled(true);
            frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);}
           else
           {JOptionPane.showMessageDialog(null, "No se elimino");Limpiar();frm.btnIngresar.setEnabled(true);
            frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);}
       }
       if(e.getSource()==frm.btnBusca)
       {
        
               mat.setUsuario(frm.txtBuscar.getText());
         
           if(sqlmat.BuscarUsuario(mat))
           {

            String[] columnas ={"ID","USUARIO","CLAVE"};
           Object[] datos = new Object[3];
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
                    datos[1] = cls.getUsuario();
                    datos[2] = cls.getPassword();
                    tabla.addRow(datos);
                  }  
                 frm.tbMaterias.setModel(tabla);
                 Limpiar();
                 frm.txtBuscar.setText(null);
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
   String[] columnas ={"ID","Usuario","Contraseña"};
           Object[] datos = new Object[3];
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
                    datos[1] = cls.getUsuario();
                    datos[2] = cls.getPassword();
                    tabla.addRow(datos);
                  }  
                 frm.tbMaterias.setModel(tabla);
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