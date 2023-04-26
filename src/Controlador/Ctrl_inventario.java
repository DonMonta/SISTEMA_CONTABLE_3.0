/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.ClsConsultainventario;
import Modelo.ClsGastos;
import Modelo.ClsInventario;
import Vista.FrmGASTO;
import Vista.FrmINVENTARIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class Ctrl_inventario implements ActionListener {
    ClsInventario var;
    ClsConsultainventario sqlvar;
    FrmINVENTARIO frm;
    
     public Ctrl_inventario (ClsInventario var, ClsConsultainventario sqlvar, FrmINVENTARIO frm) {
        this.var = var;
        this.sqlvar = sqlvar;
        this.frm = frm;
        this.frm.btnguardar.addActionListener((ActionListener) this);
        this.frm.btneliminar.addActionListener((ActionListener) this);
        this.frm.btnactualizar.addActionListener((ActionListener) this);
        this.frm.btnborrar.addActionListener((ActionListener) this); 
        this.frm.btnbuscar.addActionListener((ActionListener) this);
        this.frm.btnmodificar.addActionListener ((ActionListener) this);
        
        this.frm.tbltabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              DefaultTableModel modelo = (DefaultTableModel) frm.tbltabla.getModel();
              int fila;
              
              fila=frm.tbltabla.getSelectedRow();
                
               
                  frm.txtbuscar.setEnabled(false);
                  frm.txtid.setText(modelo.getValueAt(fila, 0).toString());
                  frm.txtnombreproducto.setText(modelo.getValueAt(fila, 1).toString());
               frm.txtdescripcion.setText(modelo.getValueAt(fila, 2).toString());
                 frm.txtprecioventa.setText(modelo.getValueAt(fila, 3).toString());
                frm.txtcosto.setText(modelo.getValueAt(fila, 4).toString());
                frm.txtcantidaddisponible.setText(modelo.getValueAt(fila, 5).toString());
                frm.btnguardar.setEnabled(false);
                frm.btnbuscar.setEnabled(false);
                 frm.btnactualizar.setEnabled(true);
                  frm.btneliminar.setEnabled(true);
              
            }
        });
        
    }
    
    
    public void Iniciar()
    {
        frm.txtid.setVisible(false);
         frm.btnmodificar.setEnabled(true);
         frm.btneliminar.setEnabled(false);
        Mostrar();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==frm.btnactualizar){
            Mostrar();
            Limpiar();
         frm.btnmodificar.setEnabled(false);
         frm.btneliminar.setEnabled(false);
         frm.txtbuscar.setEnabled(true);
         frm.btnguardar.setEnabled(true);
         frm.btnbuscar.setEnabled(true);
          frm.txtbuscar.setText(null);
        }
       
       if(e.getSource()==frm.btnguardar)
       {
          if(Validar()){
             frm.txtbuscar.setEnabled(true);
                    var.setNombre_producto(frm.txtnombreproducto.getText());       
                    var.setDescripcion(frm.txtdescripcion.getText());
                    var.setPrecio_venta(Double.parseDouble(frm.txtprecioventa.getText()));                     
                    var.setCosto(Double.parseDouble(frm.txtcosto.getText()));                     
                    var.setCantidad_disponible(Integer.parseInt(frm.txtcantidaddisponible.getText()));  
                    if(sqlvar.Guardar(var))
                          {JOptionPane.showMessageDialog(null, "Guardado"); 
                             Limpiar();
                            Mostrar();
                             }
                     else
                     {JOptionPane.showMessageDialog(null, "No se guardó la informacion");Limpiar();} 
          }
            
             
            
       }   
    
     if(e.getSource()==frm.btnmodificar)
       {
           var.setId(Integer.parseInt(frm.txtid.getText()));
           var.setNombre_producto(frm.txtnombreproducto.getText());
           var.setDescripcion(frm.txtdescripcion.getText());
           var.setPrecio_venta(Double.parseDouble(frm.txtprecioventa.getText()));
           var.setCosto(Double.parseDouble(frm.txtcosto.getText()));
           var.setCantidad_disponible(Integer.parseInt(frm.txtcantidaddisponible.getText()));
           if(sqlvar.Modificar(var))
           {JOptionPane.showMessageDialog(null, "Se actualizo la informacion del Proveedor"); Limpiar(); Mostrar();}
           else
           {JOptionPane.showMessageDialog(null, "No se actualizo la informacion del Proveedor");Limpiar();}
       }
     
    
       if(e.getSource()==frm.btneliminar)
       {
          
          var.setId(Integer.parseInt(frm.txtid.getText()));
           if(sqlvar.Eliminar(var))
           {JOptionPane.showMessageDialog(null, "Se elimino"); Limpiar();Mostrar();frm.btnguardar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btnmodificar.setEnabled(false);frm.btneliminar.setEnabled(false);}
           else
           {JOptionPane.showMessageDialog(null, "No se elimino");Limpiar();frm.btnguardar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btnmodificar.setEnabled(false);frm.btneliminar.setEnabled(false);}
       }
       if(e.getSource()==frm.btnbuscar)
       {
        
               var.setNombre_producto(frm.txtbuscar.getText());
         
           if(sqlvar.BuscarTienda(var))
           {

            String[] columnas ={"id,nombre_producto","descripcion","precio_venta","costo","cantidad_disponible"};
           Object[] datos = new Object[6];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==6){return true;} else {return false;}}
           };
          
           List objList; ClsInventario cls;
           try {
                objList= sqlvar.ListarBussqueda(var.getNombre_producto());
                if(!objList.isEmpty())
                {
                 for (int i = 0; i < objList.size(); i++) {
                    cls = (ClsInventario) objList.get(i);
                   
                    datos[0] = cls.getId();
                    datos[1] = cls.getNombre_producto();
                    datos[2] = cls.getDescripcion();
                    datos[3] = cls.getPrecio_venta();
                    datos[4] = cls.getCosto();
                    datos[5] = cls.getCantidad_disponible();
                    tabla.addRow(datos);
                  }  
                 frm.tbltabla.setModel(tabla);
                 Limpiar();
                 frm.txtbuscar.setText(null);
               }
               else
               {JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_inventario.class.getName()).log(Level.SEVERE, null, ex);
                }  
               
           }
       }

    }   
    private void Mostrar()
    {
   String[] columnas ={"id","nombre_producto","descripcion","precio_venta","costo","cantidad_disponible"};
           Object[] datos = new Object[6];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==6){return true;} else {return false;}}
           };
          
           List objList; ClsInventario cls;
           try {
                objList= sqlvar.MostrarTienda();
                if(!objList.isEmpty())
                {
                 for (int i = 0; i < objList.size(); i++) {
                    cls = (ClsInventario) objList.get(i);
                   datos[0] = cls.getId();
                    datos[1] = cls.getNombre_producto();
                    datos[2] = cls.getDescripcion();
                    datos[3] = cls.getPrecio_venta();
                    datos[4] = cls.getCosto();
                    datos[5] = cls.getCantidad_disponible();
                    tabla.addRow(datos);
                  }  
                 frm.tbltabla.setModel(tabla);
               }
               else
               {JOptionPane.showMessageDialog(null, "No encontro información"); }
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_inventario.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }
   public void Limpiar()
    {
        frm.txtid.setText(null);
        frm.txtnombreproducto.setText(null);
        frm.txtdescripcion.setText(null);
        frm.txtprecioventa.setText(null);
        frm.txtcosto.setText(null);
        frm.txtcantidaddisponible.setText(null);
        frm.txtnombreproducto.setFocusable(true);
    }
   private boolean Validar(){
        if("".equals(frm.txtnombreproducto.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar tipo datos");
            
            return  false;
            
        }
        if("".equals(frm.txtdescripcion.getText())){
            JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
            return  false;
            
        }
        if("".equals(frm.txtprecioventa.getText())){
            JOptionPane.showMessageDialog(null,"Debe seleccionar datos");
           
            return  false;
          
   }
        if("".equals(frm.txtcosto.getText())){
            JOptionPane.showMessageDialog(null,"Debe seleccionar datos");
           
            return  false;
          
   }
        if("".equals(frm.txtcantidaddisponible.getText())){
            JOptionPane.showMessageDialog(null,"Debe seleccionar datos");
           
            return  false;
            
   }
        return true;
        
}
}