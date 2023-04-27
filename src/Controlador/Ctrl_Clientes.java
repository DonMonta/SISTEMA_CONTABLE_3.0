/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cliente;
import Modelo.ClsConsultaClientes;
import Vista.frmClients;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author monta
 */
public class Ctrl_Clientes implements ActionListener{
     Cliente clie;
    ClsConsultaClientes sqlclie;
    frmClients frm;
    public Ctrl_Clientes(Cliente clie, ClsConsultaClientes sqlclie, frmClients frm){
        this.clie = clie;
        this.sqlclie = sqlclie;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener(this);
        this.frm.btnUpdate.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnbuscar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.cmbforma.addActionListener((ActionListener)this);
        this.frm.btnactualizar.addActionListener((ActionListener) this);
        
        this.frm.tbcliente.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                DefaultTableModel modelo = (DefaultTableModel) frm.tbcliente.getModel();
                int fila;
                fila=frm.tbcliente.getSelectedRow();
                frm.txtbuscar.setEnabled(false);
                frm.txtid.setText(modelo.getValueAt(fila, 0).toString());
                 
                frm.txtnombre.setText(modelo.getValueAt(fila, 1).toString());
                frm.txtdireccion.setText(modelo.getValueAt(fila, 2).toString());
                
                frm.txtidentificacion.setText(modelo.getValueAt(fila, 3).toString());
                frm.cmbforma.setSelectedItem(modelo.getValueAt(fila, 4).toString());
               
               
              
                frm.btnIngresar.setEnabled(false);
                frm.btnbuscar.setEnabled(false);
                frm.btnUpdate.setEnabled(true);
                frm.btnEliminar.setEnabled(true);
            }
        });
    }
    public void Iniciar(){
        frm.txtid.setVisible(false);
        frm.btnUpdate.setEnabled(false);
        frm.btnEliminar.setEnabled(false);
        Mostrar();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.btnactualizar){
            
            Mostrar();
            Limpiar();
            frm.btnUpdate.setEnabled(false);
            frm.btnEliminar.setEnabled(false);
            frm.txtbuscar.setEnabled(true);
            frm.btnIngresar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);
            frm.txtbuscar.setText(null);
        }
        if(e.getSource()==frm.btnLimpiar){
            
            Mostrar();
            Limpiar();
            
        }
         if(e.getSource()==frm.btnIngresar){
             
            if(Validar()){
                frm.txtbuscar.setEnabled(true);
                
                clie.setNombre(frm.txtnombre.getText());
                clie.setDireccion(frm.txtdireccion.getText());
                clie.setIdentificacion_fiscal(frm.txtidentificacion.getText());
                clie.setForma_pago_preferida((String) frm.cmbforma.getSelectedItem());
                if(sqlclie.Guardar(clie)){
                      
                    JOptionPane.showMessageDialog(null, "Guardado");
                    Mostrar();
                    Limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "No se guardó la informacion");Limpiar();
                }
            }
                  
                  
        }
         if(e.getSource()==frm.btnUpdate){
             if(Validar()){
                 
                 
                 clie.setId(Integer.parseInt(frm.txtid.getText()));
                 clie.setNombre(frm.txtnombre.getText());
                 clie.setDireccion(frm.txtdireccion.getText());
                 clie.setIdentificacion_fiscal(frm.txtidentificacion.getText());
                 clie.setForma_pago_preferida((String) frm.cmbforma.getSelectedItem());
                 
           
                 if(sqlclie.Modificar(clie)){
                     JOptionPane.showMessageDialog(null, "Se actualizo la informacion");
                     Limpiar();Mostrar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);
                     frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "No se actualizo la informacion");
                     Limpiar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                 }
                         
                    
             }
         }
         if(e.getSource()==frm.btnEliminar){
             if(frm.tbcliente.getSelectedRow()==-1){
                 JOptionPane.showMessageDialog(frm, "Debe seleccionar la fila que desea eliminar");
             }
             else{
                 clie.setId(Integer.parseInt(frm.txtid.getText()));  
                 if(sqlclie.Eliminar(clie)){
                     JOptionPane.showMessageDialog(frm, "Eliminado");
                     Mostrar();
                     Limpiar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                 }
             }
         }
         if(e.getSource()==frm.btnbuscar){
             clie.setNombre(frm.txtbuscar.getText());
             if(sqlclie.BuscarCliente(clie)){
                 String[] columnas ={"ID","Nombre","Direccion","Identificación","Forma de Pago"};
                 Object[] datos = new Object[5];
                 DefaultTableModel tabla = new DefaultTableModel(null,columnas){
                     @Override
                     public boolean isCellEditable(int i, int j){
                         if(i==5){return true;} else {return false;}
                     }
                 };
                 List objList; Cliente cls;
                 try{
                     objList= sqlclie.ListarBussqueda(clie.getNombre());
                         if(!objList.isEmpty()){
                             for (int i = 0; i < objList.size(); i++){
                                 cls = (Cliente) objList.get(i);
                                 datos[0] = cls.getId();
                                 datos[1]= cls.getNombre();
                                 datos[2]= cls.getDireccion();
                                 datos[3]= cls.getIdentificacion_fiscal();
                                 datos[4]= cls.getForma_pago_preferida();
                                 
                                 tabla.addRow(datos);
                             }
                             
                             frm.tbcliente.setModel(tabla);
                             Limpiar();
                             frm.txtbuscar.setText(null);
                
                 
                             DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                             Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                             for(int i=4; i<7;i++){
                                 frm.tbcliente.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                             }
                         }
                         else{
                             JOptionPane.showMessageDialog(null, "No encontro información"); 
                             Limpiar();frm.txtbuscar.setText(null);
                        }
                     
                 }catch (Exception ex){
                     Logger.getLogger(Ctrl_Clientes.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
    }
    public void Mostrar(){
         List notas; 
       Cliente cls;
      
            String[] columnas ={"ID","Nombre","Direccion","Identificación","Forma de Pago"};
           Object[] datos = new Object[5];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==5){return true;} else {return false;}}
           };
         
        
           try {
              
                notas= sqlclie.MostrarCliente();
                if(!notas.isEmpty())
                {
                 for (int i = 0; i < notas.size(); i++) {
                  
                    cls = (Cliente) notas.get(i);
                     datos[0] = cls.getId();
                     datos[1]= cls.getNombre();
                     datos[2]= cls.getDireccion();
                     datos[3]= cls.getIdentificacion_fiscal();
                     datos[4]= cls.getForma_pago_preferida();
                   
                    tabla.addRow(datos);
                  }  
                 frm.tbcliente.setModel(tabla);
                
                 
                 DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                 Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                 for(int i=4; i<7;i++)
                 {  frm.tbcliente.getColumnModel().getColumn(i).setCellRenderer(Alinear);}
               }
                else
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
               
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_Clientes.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
    public void Limpiar()
    {
        
         frm.txtid.setText(null);
         frm.txtnombre.setText("Ingrese Nombre y Apellidos");
         frm.txtdireccion.setText("Ingrese Direccion");
         frm.txtidentificacion.setText("Ingrese Identificación Social");
        
       
    }
     private boolean Validar(){
         
         if("".equals(frm.txtnombre.getText())){
             
             JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo nombre");
             return  false;
            
             }
         if("".equals(frm.txtdireccion.getText())){
             JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo direccion");
            
             return  false;
            
             }
         if("".equals(frm.txtidentificacion.getText())){
             JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo identificacion");
            
             return  false;
            
             }
         if(!isNumero(frm.txtidentificacion.getText().trim())) {
		JOptionPane.showMessageDialog(null,"Inserte solo Numeros en el campo identificacion");
                
                    frm.txtidentificacion.setText("Ingrese Identificación Social");
                     return  false;  
                   
          }
         
         

         return true;
     }
      private static boolean isNumero(String datos){
          return datos.matches("[0-9 .]*");}
}
