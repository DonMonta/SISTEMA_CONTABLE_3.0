/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cls_ConsultaCuentaBancarias;
import Modelo.Cuentas_Bancarias;
import Vista.frmCuentasBancarias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Ctrl_CuentasBancarias implements ActionListener{
     Cuentas_Bancarias clie;
    Cls_ConsultaCuentaBancarias sqlclie;
    frmCuentasBancarias frm;
    public Ctrl_CuentasBancarias(Cuentas_Bancarias clie, Cls_ConsultaCuentaBancarias sqlclie, frmCuentasBancarias frm){
        this.clie = clie;
        this.sqlclie = sqlclie;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener(this);
        this.frm.btnUpdate.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnbuscar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnactualizar.addActionListener((ActionListener) this);
        
        this.frm.tbcuenta.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                DefaultTableModel modelo = (DefaultTableModel) frm.tbcuenta.getModel();
                int fila;
                fila=frm.tbcuenta.getSelectedRow();
                frm.txtbuscar.setEnabled(false);
                frm.txtid.setText(modelo.getValueAt(fila, 0).toString());
                 
                frm.txtnombre.setText(modelo.getValueAt(fila, 1).toString());
                frm.txtnumero.setText(modelo.getValueAt(fila, 2).toString());
                
                frm.txtsaldo.setText(modelo.getValueAt(fila, 3).toString());
               
               
              
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
                
                clie.setNombre_banco(frm.txtnombre.getText());
                clie.setNumero_cuenta(frm.txtnumero.getText());
                clie.setSaldo_actual(Double.parseDouble(frm.txtsaldo.getText()));
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
                 clie.setNombre_banco(frm.txtnombre.getText());
                clie.setNumero_cuenta(frm.txtnumero.getText());
                clie.setSaldo_actual(Double.parseDouble(frm.txtsaldo.getText()));
                 
           
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
             if(frm.tbcuenta.getSelectedRow()==-1){
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
             clie.setNombre_banco(frm.txtbuscar.getText());
             if(sqlclie.BuscarCuentas_Bancarias(clie)){
                 String[] columnas ={"ID","Banco","Numero de Cuenta","Saldo"};
                 Object[] datos = new Object[4];
                 DefaultTableModel tabla = new DefaultTableModel(null,columnas){
                     @Override
                     public boolean isCellEditable(int i, int j){
                         if(i==4){return true;} else {return false;}
                     }
                 };
                 List objList; Cuentas_Bancarias cls;
                 try{
                     objList= sqlclie.ListarBussqueda(clie.getNombre_banco());
                         if(!objList.isEmpty()){
                             for (int i = 0; i < objList.size(); i++){
                                 cls = (Cuentas_Bancarias) objList.get(i);
                                 datos[0] = cls.getId();
                                 datos[1]= cls.getNombre_banco();
                                 datos[2]= cls.getNumero_cuenta();
                                 datos[3]= cls.getSaldo_actual();
                                 
                                 tabla.addRow(datos);
                             }
                             
                             frm.tbcuenta.setModel(tabla);
                             Limpiar();
                             frm.txtbuscar.setText(null);
                
                 
                             DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                             Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                             for(int i=4; i<7;i++){
                                 frm.tbcuenta.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                             }
                         }
                         else{
                             JOptionPane.showMessageDialog(null, "No encontro información"); 
                             Limpiar();frm.txtbuscar.setText(null);
                        }
                     
                 }catch (Exception ex){
                     Logger.getLogger(Ctrl_CuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
    }
    public void Mostrar(){
         List notas; 
       Cuentas_Bancarias cls;
      
            String[] columnas ={"ID","Banco","Numero de Cuenta","Saldo"};
           Object[] datos = new Object[4];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==4){return true;} else {return false;}}
           };
         
        
           try {
              
                notas= sqlclie.MostrarCuentas_Bancarias();
                if(!notas.isEmpty())
                {
                 for (int i = 0; i < notas.size(); i++) {
                  
                    cls = (Cuentas_Bancarias) notas.get(i);
                     datos[0] = cls.getId();
                     datos[1]= cls.getNombre_banco();
                     datos[2]= cls.getNumero_cuenta();
                     datos[3]= cls.getSaldo_actual();
                   
                    tabla.addRow(datos);
                  }  
                 frm.tbcuenta.setModel(tabla);
                
                 
                 DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                 Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                 for(int i=4; i<7;i++)
                 {  frm.tbcuenta.getColumnModel().getColumn(i).setCellRenderer(Alinear);}
               }
                else
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
               
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_CuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
    public void Limpiar()
    {
        
         frm.txtid.setText(null);
         frm.txtnombre.setText("Ingrese Nombre del Banco");
         frm.txtnumero.setText("Ingrese Numero de Cuenta");
         frm.txtsaldo.setText("Ingrese Saldo");
        
       
    }
     private boolean Validar(){
         
         if("".equals(frm.txtnombre.getText())){
             
             JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo nombre");
             return  false;
            
             }
         if("".equals(frm.txtnumero.getText())){
             JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo numero");
            
             return  false;
            
             }
         if("".equals(frm.txtsaldo.getText())){
             JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo saldo");
            
             return  false;
            
             }
         if(!isNumero(frm.txtsaldo.getText().trim())) {
		JOptionPane.showMessageDialog(null,"Inserte solo Numeros en el campo saldo");
                
                    frm.txtsaldo.setText("Ingrese Identificación Social");
                     return  false;  
                   
          }
         
         

         return true;
     }
      private static boolean isNumero(String datos){
          return datos.matches("[0-9 .]*");}
}
