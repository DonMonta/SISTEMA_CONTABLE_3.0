/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.ClsConsultainventario;
import Modelo.ClsInventario;
import Vista.FrmINVENTARIO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
                  frm.txtnombreproducto.setForeground(Color.black);
                  frm.txtdescripcion.setForeground(Color.black);
                  frm.txtprecioventa.setForeground(Color.black);
                  frm.txtcosto.setForeground(Color.black);
                  frm.txtcantidaddisponible.setForeground(Color.black);
                  frm.btnguardar.setEnabled(false);
                  frm.btnbuscar.setEnabled(false);
                  frm.btnactualizar.setEnabled(true);
                  frm.btneliminar.setEnabled(true);
                  frm.btnmodificar.setEnabled(true);
                  frm.txtnombreproducto.setEnabled(false);
              
            }
        });
        
    }
    
    String mensaje;
    public void Iniciar()
    {
        frm.txtid.setVisible(false);
         frm.btnmodificar.setEnabled(false);
         frm.btneliminar.setEnabled(false);
        Mostrar();
    }
     public void SSMC(String mensaje){
         // Obtener la imagen desde un archivo
             ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/feli.gif"));
            // Crear un panel personalizado con la imagen y el mensaje
           
            JPanel panel = new JPanel();
            JLabel label2 = new JLabel(icono);
            JLabel label = new JLabel(mensaje, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.setLayout(new BorderLayout());
            panel.add(label2, BorderLayout.WEST); // Agregar la imagen en la parte superior del panel
            panel.add(label); // Agregar el mensaje en el centro del panel

            // Mostrar el JOptionPane con el panel personalizado
            JOptionPane.showMessageDialog(null, panel, "Verificación de Usuario", JOptionPane.PLAIN_MESSAGE);
    }
    public void SSMI(String mensaje){
         // Obtener la imagen desde un archivo
             ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/monotriste.gif"));
            // Crear un panel personalizado con la imagen y el mensaje
           
            JPanel panel = new JPanel();
            JLabel label2 = new JLabel(icono);
            JLabel label = new JLabel(mensaje, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.setLayout(new BorderLayout());
            panel.add(label2, BorderLayout.WEST); // Agregar la imagen en la parte superior del panel
            panel.add(label); // Agregar el mensaje en el centro del panel

            // Mostrar el JOptionPane con el panel personalizado
            JOptionPane.showMessageDialog(null, panel, "Verificación de Usuario", JOptionPane.PLAIN_MESSAGE);
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
         frm.txtnombreproducto.setEnabled(true);
        }
        if(e.getSource()==frm.btnborrar){
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
             if(sqlvar.ExisteProducto(var)){
                    mensaje = "El Producto Ingresado Ya Existe. Ingrese otro Producto";
                    SSMI(mensaje);
                }else{
                      
                    var.setDescripcion(frm.txtdescripcion.getText());
                    var.setPrecio_venta(Double.parseDouble(frm.txtprecioventa.getText()));                     
                    var.setCosto(Double.parseDouble(frm.txtcosto.getText()));                     
                    var.setCantidad_disponible(Integer.parseInt(frm.txtcantidaddisponible.getText()));  
                    if(sqlvar.Guardar(var))
                          { 
                              mensaje = "Guardado";
                            SSMC(mensaje); 
                             Limpiar();
                            Mostrar();
                             }
                     else
                     { mensaje = "No se guardó la informacion";Limpiar();SSMI(mensaje);}
                 
             }
                     
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
           {
                mensaje = "Se actualizo la informacion";
                SSMC(mensaje); Limpiar(); Mostrar();
                frm.btnguardar.setEnabled(true); 
                frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                frm.btnmodificar.setEnabled(false);frm.btneliminar.setEnabled(false);
                frm.txtnombreproducto.setEnabled(true);
           }
           else
           {
                mensaje ="No se actualizo la informacion";
                SSMI(mensaje);Limpiar();
                frm.btnguardar.setEnabled(true);
                frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                frm.btnmodificar.setEnabled(false);frm.btneliminar.setEnabled(false);
                frm.txtnombreproducto.setEnabled(true);
           }
       }
     
    
       if(e.getSource()==frm.btneliminar)
       {
          
          var.setId(Integer.parseInt(frm.txtid.getText()));
           if(sqlvar.Eliminar(var))
           { mensaje ="Eliminado";SSMC(mensaje); Limpiar();Mostrar();frm.btnguardar.setEnabled(true);frm.txtnombreproducto.setEnabled(true);
            frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btnmodificar.setEnabled(false);frm.btneliminar.setEnabled(false);}
           else
           { mensaje="No se elimino"; SSMI(mensaje);Limpiar();frm.btnguardar.setEnabled(true);frm.txtnombreproducto.setEnabled(true);
            frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btnmodificar.setEnabled(false);frm.btneliminar.setEnabled(false);}
       }
       if(e.getSource()==frm.btnbuscar)
       {
           if("Buscar Producto".equals(frm.txtbuscar.getText())){
                mensaje="Ingrese producto a Buscar";
                SSMI(mensaje);
            }
            else if("".equals(frm.txtbuscar.getText())){
                 mensaje="Ingrese producto a Buscar";
                 SSMI(mensaje);
            }else{
                   var.setNombre_producto(frm.txtbuscar.getText());
         
                    if(sqlvar.BuscarTienda(var))
                    {

                        String[] columnas ={"ID","Nombre del producto","Descripcion","Precio Venta","Costo","Cantidad disponible"};
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
                              frm.txtbuscar.setText("Buscar Producto");

                                    DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                                    Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                                    if (frm.tbltabla.getColumnCount() >= 7) {
                                       for(int i=4; i<7;i++)
                                       {  
                                           frm.tbltabla.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                       }
                                   }
                           }
                           else
                           {
                               mensaje="No encontro información"; 
                                SSMI(mensaje); Limpiar(); frm.txtbuscar.setText("Buscar Producto");}
                       } catch (Exception ex) {
                           Logger.getLogger(Ctrl_inventario.class.getName()).log(Level.SEVERE, null, ex);
                            }  

                    }
                    else
                {
                  mensaje="No encontraron Datos";SSMI(mensaje); Limpiar(); frm.txtbuscar.setText("Buscar Producto");
                }
            }
        
             
       }

    }   
   private void Mostrar() {
        // Limpiar la tabla antes de cargar los datos
        DefaultTableModel tabla = (DefaultTableModel) frm.tbltabla.getModel();
        tabla.setRowCount(0);

        String[] columnas = {"ID", "Nombre del producto", "Descripcion", "Precio Venta", "Costo", "Cantidad disponible"};
        Object[] datos = new Object[6];

        List<ClsInventario> objList;
        ClsInventario cls;

        try {
            objList = sqlvar.MostrarTienda();

            if (!objList.isEmpty()) {
                for (int i = 0; i < objList.size(); i++) {
                    cls = objList.get(i);
                    datos[0] = cls.getId();
                    datos[1] = cls.getNombre_producto();
                    datos[2] = cls.getDescripcion();
                    datos[3] = cls.getPrecio_venta();
                    datos[4] = cls.getCosto();
                    datos[5] = cls.getCantidad_disponible();
                    tabla.addRow(datos);
                }

                frm.tbltabla.setModel(tabla);

                DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                Alinear.setHorizontalAlignment(SwingConstants.RIGHT);

                if (frm.tbltabla.getColumnCount() >= 7) {
                    for (int i = 4; i < 7; i++) {
                        frm.tbltabla.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                    }
                }
            } else {
                mensaje = "No se encontraron datos";
                SSMI(mensaje);
                Limpiar();
                frm.txtbuscar.setText("Buscar Producto");
            }
        } catch (Exception ex) {
            Logger.getLogger(Ctrl_inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void Limpiar()
    {
        frm.txtid.setText(null);
        frm.txtnombreproducto.setText("Ingrese Producto");
        frm.txtdescripcion.setText("Ingrese Descripcion");
        frm.txtprecioventa.setText("Precio");
        frm.txtcosto.setText("Ingrese costo");
        frm.txtcantidaddisponible.setText("Cantidad");
        frm.txtnombreproducto.setFocusable(true);
        frm.txtcantidaddisponible.setForeground(new Color(204,204,204));
        frm.txtcosto.setForeground(new Color(204,204,204));
        frm.txtdescripcion.setForeground(new Color(204,204,204));
        frm.txtnombreproducto.setForeground(new Color(204,204,204));
        frm.txtprecioventa.setForeground(new Color(204,204,204));
        frm.txtbuscar.setForeground(new Color(204,204,204));



    }
   private boolean Validar(){
        if("".equals(frm.txtnombreproducto.getText())){
             mensaje="Debe ingresar datos en el campo producto";SSMI(mensaje);
            
            return  false;
            
        }
        if("Ingrese Producto".equals(frm.txtnombreproducto.getText())){
             mensaje="Debe ingresar datos en el campo producto";SSMI(mensaje);
            
            return  false;
            
        }
        if("".equals(frm.txtdescripcion.getText())){
             mensaje="Debe ingresar datos en el campo descripcion";SSMI(mensaje);
            
            return  false;
            
        }
        if("Ingrese Descripcion".equals(frm.txtdescripcion.getText())){
             mensaje="Debe ingresar datos en el campo descripcion";SSMI(mensaje);
            
            return  false;
            
        }
        if("".equals(frm.txtprecioventa.getText())){
             mensaje="Debe seleccionar datos en el campo precio";SSMI(mensaje);
           
            return  false;
          
        }
        if("Precio".equals(frm.txtprecioventa.getText())){
             mensaje="Debe seleccionar datos en el campo precio";SSMI(mensaje);
           
            return  false;
          
        }
        if("".equals(frm.txtcosto.getText())){
             mensaje="Debe seleccionar datos en el campo costo";SSMI(mensaje);
           
            return  false;
          
        }
        if("Ingrese costo".equals(frm.txtcosto.getText())){
             mensaje="Debe seleccionar datos en el campo costo";SSMI(mensaje);
           
            return  false;
          
        }
        if("".equals(frm.txtcantidaddisponible.getText())){
             mensaje="Debe seleccionar datos en el campo cantidad";SSMI(mensaje);
           
            return  false;
            
        }
        if("Cantidad".equals(frm.txtcantidaddisponible.getText())){
             mensaje="Debe seleccionar datos en el campo cantidad";SSMI(mensaje);
           
            return  false;
            
        }
        return true;
        
    }
}