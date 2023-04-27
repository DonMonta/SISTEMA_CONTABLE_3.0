/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.ClsProveedor;
import Modelo.ClsConsultaProveedor;
import Vista.frmproveedore;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author juand
 */

public class Ctrl_Proveedor implements ActionListener {
    
  ClsProveedor mat;
    ClsConsultaProveedor sqlmat;
    frmproveedore frm;
    public Ctrl_Proveedor(ClsProveedor mat, ClsConsultaProveedor sqlmat, frmproveedore frm) {
        this.mat = mat;
        this.sqlmat = sqlmat;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener((ActionListener) this);
        this.frm.btnUpdate.addActionListener((ActionListener) this);
        this.frm.btnEliminar.addActionListener((ActionListener) this);
        this.frm.btnBusca.addActionListener((ActionListener) this);  
        this.frm.cmbforma.addActionListener((ActionListener)this);
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
                  frm.txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                  frm.txtDireccion.setText(modelo.getValueAt(fila, 2).toString());
                  frm.cmbforma.setSelectedItem(modelo.getValueAt(fila, 3).toString());
                  frm.txtIdentificacion.setText(modelo.getValueAt(fila, 4).toString());
                  frm.txtIdentificacion.setForeground(Color.black);
                  frm.txtDireccion.setForeground(Color.black);
                  frm.txtNombre.setForeground(Color.black);
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
                 if("".equals(frm.txtNombre.getText())){
                JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo nombre");

                }
                else if("".equals(frm.txtDireccion.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo direccion");



                }
               else if("".equals(frm.txtIdentificacion.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo identificacion");


                }
                else{
                    mat.setNombre(frm.txtNombre.getText());
                    mat.setDireccion(frm.txtDireccion.getText());
                    mat.setFormaPagoPreferida((String) frm.cmbforma.getSelectedItem());
                    mat.setIdentificacionFiscal(frm.txtIdentificacion.getText());
                    if(sqlmat.Guardar(mat))
                    {JOptionPane.showMessageDialog(null, "Proveedor guardado"); Limpiar();
                     Mostrar();}
                    else
                    {JOptionPane.showMessageDialog(null, "No se guardó la informacion del Proveedor");Limpiar();}
                }
                
           
          
       } 
       if(e.getSource()==frm.btnLimpiar)
       {
           Limpiar();
       } 
            
             
            
       
    
     if(e.getSource()==frm.btnUpdate)
       {
                  if("".equals(frm.txtNombre.getText())){
                JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo nombre");

                }
                else if("".equals(frm.txtDireccion.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo direccion");



                }
                else if("".equals(frm.txtIdentificacion.getText())){
                    JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo identificacion");


                }else{
                    mat.setId(Integer.parseInt(frm.txtID.getText()));
                    mat.setNombre(frm.txtNombre.getText());
                    mat.setDireccion(frm.txtDireccion.getText());
                     mat.setFormaPagoPreferida((String) frm.cmbforma.getSelectedItem());
                    mat.setIdentificacionFiscal(frm.txtIdentificacion.getText());

                    if(sqlmat.Modificar(mat))
                    {JOptionPane.showMessageDialog(null, "Se actualizo la informacion del Proveedor"); Limpiar();
                     Mostrar();}
                    else
                    {JOptionPane.showMessageDialog(null, "No se actualizo la informacion del Proveedor");Limpiar();}

                }
                  
                
           
       }
    
       if(e.getSource()==frm.btnEliminar)
       {
          
          mat.setId(Integer.parseInt(frm.txtID.getText()));
           if(sqlmat.Eliminar(mat))
           {JOptionPane.showMessageDialog(null, "Se elimino el Proveedor"); Limpiar();Mostrar();frm.btnIngresar.setEnabled(true);
            frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
            Mostrar();}
           else
           {JOptionPane.showMessageDialog(null, "No se elimino el Proveedor");Limpiar();frm.btnIngresar.setEnabled(true);
            frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);}
       }
       if(e.getSource()==frm.btnBusca)
       {
        
               mat.setNombre(frm.txtBuscar.getText());
         
           if(sqlmat.BuscarProveedor(mat))
           {

            String[] columnas ={"ID","Nombre","Direccion","Identificacion Fiscal","Forma De Pago"};
           Object[] datos = new Object[5];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==5){return true;} else {return false;}}
           };
          
           List objList; ClsProveedor cls;
           try {
                objList= sqlmat.ListarBussqueda(mat.getNombre());
                if(!objList.isEmpty())
                {
                 for (int i = 0; i < objList.size(); i++) {
                    cls = (ClsProveedor) objList.get(i);
                   
                    datos[0] = cls.getId();
                    datos[1] = cls.getNombre();
                    datos[2] = cls.getDireccion();
                    datos[3] = cls.getIdentificacionFiscal();
                    datos[4] = cls.getFormaPagoPreferida();
                    tabla.addRow(datos);
                  }  
                 frm.tbMaterias.setModel(tabla);
                 Limpiar();
                 frm.txtBuscar.setText(null);
               }
               else
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtBuscar.setText(null);}
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                }  
              
               
             }
                 else
                 {
                     JOptionPane.showMessageDialog(null, "No encontro un Proveedor Con ese Nombre"); Limpiar();frm.txtBuscar.setText(null);
                    }
                }
           
    }   
     private void Mostrar()
    {
   String[] columnas ={"ID","Nombre","Direccion","Identificacion Fiscal","Forma De Pago"};
           Object[] datos = new Object[5];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==5){return true;} else {return false;}}
           };
          
           List objList; ClsProveedor cls;
           try {
                objList= sqlmat.MostrarProveedor();
                if(!objList.isEmpty())
                {
                 for (int i = 0; i < objList.size(); i++) {
                    cls = (ClsProveedor) objList.get(i);
                    datos[0] = cls.getId();
                    datos[1] = cls.getNombre();
                    datos[2] = cls.getDireccion();
                    datos[3] = cls.getFormaPagoPreferida();
                    datos[4] = cls.getIdentificacionFiscal();
                    tabla.addRow(datos);
                  }  
                 frm.tbMaterias.setModel(tabla);
               }
               else
               {JOptionPane.showMessageDialog(null, "No encontro información"); }
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
   public void Limpiar()
    {
         frm.txtID.setText(null);
         frm.txtNombre.setText("Ingrese Nombre del Proveedor");
         frm.txtDireccion.setText("Ingrese Direccion");
         frm.txtIdentificacion.setText("Ingrese Identificacion Fiscal");
          frm.txtIdentificacion.setForeground(new Color(204,204,204));
          frm.txtDireccion.setForeground(new Color(204,204,204));
           frm.txtNombre.setForeground(new Color(204,204,204));
    }
  
}