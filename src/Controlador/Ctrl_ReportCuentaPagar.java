/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClsConsultaFactura;
import Modelo.ClsConsultasCuentasPorPagar;
import Modelo.ClsFactura;
import Modelo.clsCuentasPorPagar;
import Vista.frmcuentasporpagar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author joel
 */
public class Ctrl_ReportCuentaPagar implements ActionListener{
    clsCuentasPorPagar est;
    ClsConsultasCuentasPorPagar sqlest;
    frmcuentasporpagar frm;
    public Ctrl_ReportCuentaPagar(clsCuentasPorPagar est, ClsConsultasCuentasPorPagar sqlest, frmcuentasporpagar frm) {
        this.est = est;
        this.sqlest = sqlest;
        this.frm = frm;
        this.frm.btnagregar.addActionListener((ActionListener) this);
        this.frm.cmbfacturas.addActionListener((ActionListener)this);
        this.frm.btngenerar.addActionListener((ActionListener) this);
        this.frm.btnactualizar.addActionListener((ActionListener) this);
         this.frm.btnbuscar.addActionListener((ActionListener) this);
       
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
                    Logger.getLogger(Ctrl_ReportCuentaPagar.class.getName()).log(Level.SEVERE, null, ex);
                }
              frm.cmbfacturas.setSelectedItem(modelo.getValueAt(fila, 4).toString());
              
              
            }
        });
       
    }
    
    public void Iniciar(){
        
        
        try {
            
            //campos ocultos
           frm.txtid.setVisible(false);
          
            ClsConsultaFactura prod = new ClsConsultaFactura();
            List objList = prod.Mostrar();
             Iterator iterador = objList.iterator();  
            while (iterador.hasNext()) {
                ClsFactura pro = (ClsFactura) iterador.next();
                frm.cmbfacturas.addItem(pro);
            }
        } catch (Exception ex) {
            Logger.getLogger(Ctrl_ReportCuentaPagar.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.btnactualizar){
            Mostrar();
            Limpiar();
            frm.txtbuscar.setEnabled(true);
            frm.txtbuscar.setText("Buscar fechas de Pago");
        }
        if(e.getSource()==frm.cmbfacturas)
        {   
           Mostrar();
         
        }
         if(e.getSource()==frm.btnagregar)
        {  
              if("".equals(frm.txtimporte.getText())){
             JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
            
            
             }
              else if(frm.jDateChooser1.getDate()==null){
                JOptionPane.showMessageDialog(null,"Debe ingresar datos");

              

             }
              else if(frm.jDateChooser2.getDate()==null){
                JOptionPane.showMessageDialog(null,"Debe ingresar datos");

              

             }
              else{
                  frm.txtbuscar.setEnabled(true);
                    ClsFactura fac = (ClsFactura)frm.cmbfacturas.getSelectedItem();
                    est.setImporte(Double.parseDouble(frm.txtimporte.getText()));
                    String fecha,fecha2;
                    java.util.Date date = new java.util.Date();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    fecha=f.format(frm.jDateChooser1.getDate());
                    fecha2=f.format(frm.jDateChooser2.getDate());

                try {       

                    est.setFecha_venci(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
                    est.setFecha_pag(new SimpleDateFormat("yyyy-MM-dd").parse(fecha2));
                } catch (ParseException ex) {
                    Logger.getLogger(Ctrl_ReportCuentaPagar.class.getName()).log(Level.SEVERE, null, ex);
                }
                    est.setFactura(fac.getId()); 


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
    }
    
      public void Limpiar()
    {
        frm.txtid.setText(null);
        frm.jDateChooser1.setDate(null);
        frm.jDateChooser2.setDate(null);
        frm.txtimporte.setText("0000000");
        
    }
    
    public void Mostrar()
    {  
        List notas; 
       clsCuentasPorPagar cls;
       ClsFactura factura;
                 String[] columnas ={"ID","Importe","Fecha Vencimiento","Fecha de Pago","Nº Factura"};
           Object[] datos = new Object[5];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==5){return true;} else {return false;}}
           };
         
         ClsFactura obj =(ClsFactura) frm.cmbfacturas.getSelectedItem();
           try {
              
                notas= sqlest.Mostrar();
                if(!notas.isEmpty())
                {
                    for (int i = 0; i < notas.size(); i++) {

                       cls = (clsCuentasPorPagar) notas.get(i);
                      datos[0] = cls.getId();
                       datos[1]= cls.getImporte();

                       datos[2]= cls.getFecha_venci();
                        datos[3]= cls.getFecha_pag();
                       factura = sqlest.BuscarFactura(cls);
                       datos[4]= factura.getNumero();
                       tabla.addRow(datos);
                     }  
                    frm.tbtransacc.setModel(tabla);


                    DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                    Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                    for(int i=4; i<7;i++)
                    {  frm.tbtransacc.getColumnModel().getColumn(i).setCellRenderer(Alinear);}
                }
                else
                {
                   frm.txtbuscar.setEnabled(false);
                   JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
               
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_ReportCuentaPagar.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
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
  
}
