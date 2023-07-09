/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.ClsConsultaGastos;
import Modelo.ClsGastos;
import Vista.frmGastos;
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
               
                frm.txtimporte.setForeground(Color.black);
                frm.btnIngresar.setEnabled(false);
                frm.btnbuscar.setEnabled(false);
                frm.btnUpdate.setEnabled(true);
                frm.btnEliminar.setEnabled(true);
            }
        });
    }
    String mensaje;
    public void Iniciar(){
        frm.txtid.setVisible(false);
        frm.btnUpdate.setEnabled(false);
        frm.btnEliminar.setEnabled(false);
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
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.btnactualizar){
            
            Mostrar();
            Limpiar();
            frm.btnUpdate.setEnabled(false);
            frm.btnEliminar.setEnabled(false);
            frm.txtbuscar.setEnabled(true);
            frm.btnIngresar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);
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
                      
                    mensaje = "Guardado";
                    SSMC(mensaje);
                    Mostrar();
                    Limpiar();
                }else{
                     mensaje = "No se guardó la informacion";Limpiar();SSMI(mensaje);
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
                     mensaje = "Se actualizo la informacion";
                     SSMC(mensaje);
                     Limpiar();Mostrar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);
                     frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                 }
                 else{
                     mensaje ="No se actualizo la informacion";
                     SSMI(mensaje);
                     Limpiar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                 }
                         
                    
             }
         }
         if(e.getSource()==frm.btnEliminar){
             if(frm.tbgastos.getSelectedRow()==-1){
                  mensaje ="Debe seleccionar la fila que desea eliminar";
                SSMI(mensaje);
             }
             else{
                 gast.setId(Integer.parseInt(frm.txtid.getText()));  
                 if(sqlgast.Eliminar(gast)){
                    mensaje ="Eliminado";SSMC(mensaje);
                     Mostrar();
                     Limpiar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                 }
             }
         }
         if(e.getSource()==frm.btnbuscar){
             if("Buscar tipo de gastos".equals(frm.txtbuscar.getText())){
                mensaje="Ingrese tipo de gasto a Buscar";
                SSMI(mensaje);
            }
            else if("".equals(frm.txtbuscar.getText())){
                 mensaje="Ingrese tipo de gasto a Buscar";
                 SSMI(mensaje);
            }else{
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
                                  frm.txtbuscar.setText("Buscar tipo de gastos");

                                 DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                                    Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                                    if (frm.tbgastos.getColumnCount() >= 7) {
                                       for(int i=4; i<7;i++)
                                       {  
                                           frm.tbgastos.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                       }
                                   }
                             }
                             else{
                                  mensaje="No encontro información"; 
                                  SSMI(mensaje);
                                 Limpiar();
                            }

                     }catch (Exception ex){
                         Logger.getLogger(ctrl_Gastos.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 else
                {
                  mensaje="No encontraron Datos";SSMI(mensaje); Limpiar(); frm.txtbuscar.setText("Buscar tipo de gastos");
                }
            }
             
         }
    }
    public void Mostrar() {
        // Limpiar la tabla antes de cargar los datos
        DefaultTableModel tabla = (DefaultTableModel) frm.tbgastos.getModel();
        tabla.setRowCount(0);

        String[] columnas = {"ID", "Tipo de Gasto", "Importe", "Fecha"};
        Object[] datos = new Object[4];

        List<ClsGastos> notas;
        ClsGastos cls;

        try {
            notas = sqlgast.MostrarClsGastos();

            if (!notas.isEmpty()) {
                for (int i = 0; i < notas.size(); i++) {
                    cls = notas.get(i);
                    datos[0] = cls.getId();
                    datos[1] = cls.getTipo_gasto();
                    datos[2] = cls.getImporte();
                    datos[3] = cls.getFecha();

                    tabla.addRow(datos);
                }

                frm.tbgastos.setModel(tabla);

                DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                Alinear.setHorizontalAlignment(SwingConstants.RIGHT);

                if (frm.tbgastos.getColumnCount() >= 7) {
                    for (int i = 4; i < 7; i++) {
                        frm.tbgastos.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                    }
                }
            } else {
                mensaje = "No se encontraron datos";
                SSMI(mensaje);
                Limpiar();
                frm.txtbuscar.setText("Buscar tipo de gastos");
            }
        } catch (Exception ex) {
            Logger.getLogger(ctrl_Gastos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Limpiar()
    {
        
         frm.txtid.setText(null);
         frm.txtimporte.setText("Ingrese importe");
         frm.txtbuscar.setText("Buscar tipo de gastos");
         frm.txtimporte.setForeground(new Color(204,204,204));
         frm.txtbuscar.setForeground(new Color(204,204,204));
         frm.jDateChooser1.setDate(null);
        
       
    }
     private boolean Validar(){
         
         if("".equals(frm.txtimporte.getText())){
             
             mensaje="Debe ingresar datos en el campo importe";SSMI(mensaje);
             return  false;
            
             }
          if("Ingrese importe".equals(frm.txtimporte.getText())){
             
             mensaje="Debe ingresar datos en el campo importe";SSMI(mensaje);
             return  false;
            
             }
          if (frm.jDateChooser1.getDate() == null) {
                mensaje = "Debe ingresar datos en el campo fecha";
                SSMI(mensaje);
                return false;
            }
         
         
         

         return true;
     }
     
}
