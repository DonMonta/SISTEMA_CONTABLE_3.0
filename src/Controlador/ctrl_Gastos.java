/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.ClsConsultaGastos;
import Modelo.ClsGastos;
import Vista.frmGastos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class ctrl_Gastos implements ActionListener{
     ClsGastos gast;
    ClsConsultaGastos sqlgast;
    frmGastos frm;
    public ctrl_Gastos(ClsGastos gast, ClsConsultaGastos sqlgast, frmGastos frm){
        this.gast = gast;
        this.sqlgast = sqlgast;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener(this);
        this.frm.btnUpdate.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnbuscar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.cmbtipo.addActionListener((ActionListener)this);
        this.frm.btnactualizar.addActionListener((ActionListener) this);
        
        this.frm.tbgastos.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                DefaultTableModel modelo = (DefaultTableModel) frm.tbgastos.getModel();
                int fila;
                fila=frm.tbgastos.getSelectedRow();
                frm.txtbuscar.setEnabled(false);
                frm.txtid.setText(modelo.getValueAt(fila, 0).toString());
                frm.cmbtipo.setSelectedItem(modelo.getValueAt(fila, 1).toString());
                frm.txtimporte.setText(modelo.getValueAt(fila, 2).toString());
                               
                String fecha=String.valueOf(modelo.getValueAt(fila, 3));
             
                SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechasa;
                try {
                    fechasa=(java.util.Date) s.parse(fecha);
                    
                    frm.jDateChooser1.setDate(fechasa);
                  
                } catch (ParseException ex) {
                    Logger.getLogger(ctrl_Gastos.class.getName()).log(Level.SEVERE, null, ex);
                } 
               
              
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
                gast.setTipo_gasto((String) frm.cmbtipo.getSelectedItem());
                gast.setImporte(Double.parseDouble(frm.txtimporte.getText()));
                String fecha;
                java.util.Date date = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fecha=f.format(frm.jDateChooser1.getDate());
         
           
                try {       
                
                    gast.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
                } catch (ParseException ex) {
                    Logger.getLogger(ctrl_Gastos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(sqlgast.Guardar(gast)){
                      
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
                 
                gast.setId(Integer.parseInt(frm.txtid.getText())); 
                gast.setTipo_gasto((String) frm.cmbtipo.getSelectedItem());
                gast.setImporte(Double.parseDouble(frm.txtimporte.getText()));
                String fecha;
                java.util.Date date = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fecha=f.format(frm.jDateChooser1.getDate());
         
           
                try {       
                
                    gast.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
                } catch (ParseException ex) {
                    Logger.getLogger(ctrl_Gastos.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
           
                 if(sqlgast.Modificar(gast)){
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
             if(frm.tbgastos.getSelectedRow()==-1){
                 JOptionPane.showMessageDialog(frm, "Debe seleccionar la fila que desea eliminar");
             }
             else{
                 gast.setId(Integer.parseInt(frm.txtid.getText()));  
                 if(sqlgast.Eliminar(gast)){
                     JOptionPane.showMessageDialog(frm, "Eliminado");
                     Mostrar();
                     Limpiar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                 }
             }
         }
         if(e.getSource()==frm.btnbuscar){
             gast.setTipo_gasto(frm.txtbuscar.getText());
             if(sqlgast.BuscarClsGastos(gast)){
                 String[] columnas ={"ID","Tipo de Gasto","Importe","Fecha"};
                 Object[] datos = new Object[4];
                 DefaultTableModel tabla = new DefaultTableModel(null,columnas){
                     @Override
                     public boolean isCellEditable(int i, int j){
                         if(i==4){return true;} else {return false;}
                     }
                 };
                 List objList; ClsGastos cls;
                 try{
                     objList= sqlgast.ListarBussqueda(gast.getTipo_gasto());
                         if(!objList.isEmpty()){
                             for (int i = 0; i < objList.size(); i++){
                                 cls = (ClsGastos) objList.get(i);
                                 datos[0] = cls.getId();
                                 datos[1]= cls.getTipo_gasto();
                                 datos[2]= cls.getImporte();
                                 datos[3]= cls.getFecha();
                                 
                                 tabla.addRow(datos);
                             }
                             
                             frm.tbgastos.setModel(tabla);
                             Limpiar();
                             frm.txtbuscar.setText(null);
                
                 
                             DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                             Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                             for(int i=4; i<7;i++){
                                 frm.tbgastos.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                             }
                         }
                         else{
                             JOptionPane.showMessageDialog(null, "No encontro información"); 
                             Limpiar();frm.txtbuscar.setText(null);
                        }
                     
                 }catch (Exception ex){
                     Logger.getLogger(ctrl_Gastos.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
    }
    public void Mostrar(){
         List notas; 
       ClsGastos cls;
      
            String[] columnas ={"ID","Tipo de Gasto","Importe","Fecha"};
           Object[] datos = new Object[4];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==4){return true;} else {return false;}}
           };
         
        
           try {
              
                notas= sqlgast.MostrarClsGastos();
                if(!notas.isEmpty())
                {
                 for (int i = 0; i < notas.size(); i++) {
                  
                    cls = (ClsGastos) notas.get(i);
                     datos[0] = cls.getId();
                     datos[1]= cls.getTipo_gasto();
                     datos[2]= cls.getImporte();
                     datos[3]= cls.getFecha();
                   
                    tabla.addRow(datos);
                  }  
                 frm.tbgastos.setModel(tabla);
                
                 
                 DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                 Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                 for(int i=4; i<7;i++)
                 {  frm.tbgastos.getColumnModel().getColumn(i).setCellRenderer(Alinear);}
               }
                else
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
               
           } catch (Exception ex) {
               Logger.getLogger(ctrl_Gastos.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
    public void Limpiar()
    {
        
         frm.txtid.setText(null);
         frm.txtimporte.setText("Ingrese importe");
         
        
       
    }
     private boolean Validar(){
         
         if("".equals(frm.txtimporte.getText())){
             
             JOptionPane.showMessageDialog(null,"Debe ingresar datos en el campo importe");
             return  false;
            
             }
         
         
         

         return true;
     }
     
}
