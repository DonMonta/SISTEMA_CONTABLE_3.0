/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.ClsConsultaCuentasPorCobrar;
import Modelo.ClsCuentasPorCobrar;
import Vista.frmcuentasporcobrar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class Ctrl_ReportCuentaCobrar implements ActionListener{
    ClsCuentasPorCobrar est;
    ClsConsultaCuentasPorCobrar sqlest;
    frmcuentasporcobrar frm;

    public Ctrl_ReportCuentaCobrar(ClsCuentasPorCobrar est, ClsConsultaCuentasPorCobrar sqlest, frmcuentasporcobrar frm) {
        this.est = est;
        this.sqlest = sqlest;
        this.frm = frm;
        this.frm.btngenerar.addActionListener((ActionListener) this);
        this.frm.btngenerar.addActionListener((ActionListener) this);
        this.frm.imagen.addMouseListener((MouseListener) this); 
       
        this.frm.tbtransacc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              DefaultTableModel modelo = (DefaultTableModel) frm.tbtransacc.getModel();
              int fila;
              
              fila=frm.tbtransacc.getSelectedRow();
                
              frm.txtbuscar.setEnabled(false);
              frm.txtid.setText(modelo.getValueAt(fila, 0).toString());
              frm.txtimporte.setText(modelo.getValueAt(fila, 1).toString());
             String fecha=String.valueOf(modelo.getValueAt(fila, 2));
             String fecha2=String.valueOf(modelo.getValueAt(fila, 3));
             
              SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
              java.util.Date fechasa,fehca2;
                try {
                    fechasa=(java.util.Date) s.parse(fecha);
                    fehca2=(java.util.Date) s.parse(fecha2);
                    frm.jDateChooser1.setDate(fechasa);
                  
                } catch (ParseException ex) {
                    Logger.getLogger(Ctrl_ReportCuentaCobrar.class.getName()).log(Level.SEVERE, null, ex);
                }
              frm.cmbfacturas.setSelectedItem(modelo.getValueAt(fila, 4).toString());
              
              
             
              
               frm.btngenerar.setEnabled(false);
                
              
              
            }
        });
       
    }
     @Override
     
    public void actionPerformed(ActionEvent e)
    {
        /*
       if(e.getSource()==frm.btnactualizar){
            Mostrar();
            Limpiar();
         frm.btneditar.setEnabled(false);
         frm.btneliminar.setEnabled(false);
         frm.txtbuscar.setEnabled(true);
         frm.btnguardar.setEnabled(true);
         frm.btnbuscar.setEnabled(true);
          frm.txtbuscar.setText(null);
        }
       if(e.getSource()==frm.cmbtransacc)
       {   
           Mostrar();
         
       }
        if(e.getSource()==frm.btnguardar)
       {  
           if(Validar()){
               frm.txtbuscar.setEnabled(true);
                ClsTipo_Transaccion mat = (ClsTipo_Transaccion)frm.cmbtransacc.getSelectedItem();
                 String fecha;
           java.util.Date date = new java.util.Date();
           SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
           fecha=f.format(frm.jfecha.getDate());
         
           
            try {       
                
                est.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
            } catch (ParseException ex) {
                Logger.getLogger(CtrlTransaccion.class.getName()).log(Level.SEVERE, null, ex);
            }
                est.setTipo_transaccion(mat.getId());
                 est.setDescripcion(frm.txtdescricion.getText());     
           
          
           if(sqlest.Guardar(est))
           {
               JOptionPane.showMessageDialog(null, "Guardado");
                Mostrar();
                Limpiar();
           }
           else
           {JOptionPane.showMessageDialog(null, "No se guardó la informacion");Limpiar();}
           }
          
           
       }
         if(e.getSource()==frm.btneditar)
       {
           if(Validar()){
             ClsTipo_Transaccion mat = (ClsTipo_Transaccion)frm.cmbtransacc.getSelectedItem();
           est.setId(Integer.parseInt(frm.txtid.getText()));
                 String fecha;
           java.util.Date date = new java.util.Date();
           SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
           fecha=f.format(frm.jfecha.getDate());
         
           
            try {       
                
                est.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
            } catch (ParseException ex) {
                Logger.getLogger(CtrlTransaccion.class.getName()).log(Level.SEVERE, null, ex);
            }
                est.setTipo_transaccion(mat.getId());
                 est.setDescripcion(frm.txtdescricion.getText()); 
           
           if(sqlest.Modificar(est))
           {JOptionPane.showMessageDialog(null, "Se actualizo la informacion"); Limpiar();Mostrar();frm.btnguardar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btneditar.setEnabled(false);frm.btneliminar.setEnabled(false);}
           else
           {JOptionPane.showMessageDialog(null, "No se actualizo la informacion");Limpiar();frm.btnguardar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btneditar.setEnabled(false);frm.btneliminar.setEnabled(false);}
           }
           
       }
       if(e.getSource()==frm.btneliminar)
       {   
           if(frm.tbtransacc.getSelectedRow()==-1)
           { JOptionPane.showMessageDialog(frm, "Debe seleccionar la fila que desea eliminar");}
           else{    
               est.setId(Integer.parseInt(frm.txtid.getText()));  
               if(sqlest.Eliminar(est))
               { JOptionPane.showMessageDialog(frm, "Eliminado");
                  Mostrar();
                  Limpiar();frm.btnguardar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btneditar.setEnabled(false);frm.btneliminar.setEnabled(false);
               }
           }
       }
       if(e.getSource()==frm.btnbuscar){
                if(ValidarBusca()){ 
                try {
                   
        est.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(frm.txtbuscar.getText()));
//            
               } catch (ParseException ex) {
                   Logger.getLogger(CtrlTransaccion.class.getName()).log(Level.SEVERE, null, ex);
               }
//            
               
            if(sqlest.BusquedaComple(est)){
                
                ClsTipo_Transaccion tipo_Transaccion;
                 String[] columnas ={"ID","Fecha","Tipo de Transaccion","Descripcion"};
           Object[] datos = new Object[4];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==4){return true;} else {return false;}}
           };
          List objList; ClsTransaccion cls;
          ClsTipo_Transaccion obj =(ClsTipo_Transaccion) frm.cmbtransacc.getSelectedItem();
           try {
              
                objList= sqlest.BuscarPList(frm.txtbuscar.getText());
                if(!objList.isEmpty())
                {
                 for (int i = 0; i < objList.size(); i++) {
                  
                    cls = (ClsTransaccion) objList.get(i);
                    datos[0] = cls.getId();
                    datos[1]= cls.getFecha();
                   
                    datos[3]= cls.getDescripcion();
                    tipo_Transaccion = sqlest.BuscarTienda(cls);
                    datos[2]= "N:Compra:"+tipo_Transaccion.getCompra()+" N:Venta:"+tipo_Transaccion.getVenta()+" N:Dev:"+tipo_Transaccion.getDevolucion();
                    tabla.addRow(datos);
                  }  
                 frm.tbtransacc.setModel(tabla);
                  Limpiar();
                 frm.txtbuscar.setText(null);
                
                 
                 DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                 Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                 for(int i=4; i<7;i++)
                 {  frm.tbtransacc.getColumnModel().getColumn(i).setCellRenderer(Alinear);}
               }
                else
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
               
           } catch (Exception ex) {
               Logger.getLogger(CtrlTransaccion.class.getName()).log(Level.SEVERE, null, ex);
           }  
            } 
          
           }
       }
    }
 
  public void Mostrar()
    {  
        List notas; 
       ClsTransaccion cls;
       ClsTipo_Transaccion tipo_Transaccion;
                 String[] columnas ={"ID","Fecha","Tipo de Transaccion","Descripcion"};
           Object[] datos = new Object[4];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==4){return true;} else {return false;}}
           };
         
         ClsTipo_Transaccion obj =(ClsTipo_Transaccion) frm.cmbtransacc.getSelectedItem();
           try {
              
                notas= sqlest.Mostrar();
                if(!notas.isEmpty())
                {
                 for (int i = 0; i < notas.size(); i++) {
                  
                    cls = (ClsTransaccion) notas.get(i);
                   datos[0] = cls.getId();
                    datos[1]= cls.getFecha();
                   
                    datos[3]= cls.getDescripcion();
                    tipo_Transaccion = sqlest.BuscarTienda(cls);
                    datos[2]= "N:Compra:"+tipo_Transaccion.getCompra()+" N:Venta:"+tipo_Transaccion.getVenta()+" N:Dev:"+tipo_Transaccion.getDevolucion();
                    tabla.addRow(datos);
                  }  
                 frm.tbtransacc.setModel(tabla);
                
                 
                 DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                 Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                 for(int i=4; i<7;i++)
                 {  frm.tbtransacc.getColumnModel().getColumn(i).setCellRenderer(Alinear);}
               }
                else
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
               
           } catch (Exception ex) {
               Logger.getLogger(CtrlTransaccion.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }  
   public void Limpiar()
    {
        frm.txtid.setText(null);
        frm.jfecha.setDate(null);
        frm.txtdescricion.setText(null);
       
        
    }
     private boolean Validar(){
       
        if("".equals(frm.txtdescricion.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
            return  false;
            
        }
       if(frm.jfecha.getDate()==null){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
            return  false;
            
        }
      
         

        return true;
    }/*
      private boolean ValidarBusca(){
        if("".equals(frm.txtbuscar.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
            return  false;
            
        }
         if(!FormatoFecha(frm.txtbuscar.getText().trim())) {
                    JOptionPane.showMessageDialog(null,"Inserte solo Formato yyyy-MM-dd el campo Buscar incluyendo -");
                
                        frm.txtbuscar.setText(null);
                        return  false; 
                        
           }
        

        return true;
    }
  
  private static boolean FormatoFecha(String datos){
          return datos.matches("[0-9-/]*");
      }
*/

 }  
}
