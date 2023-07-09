/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cliente;
import Modelo.ClsConsultaClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.ClsProveedor;
import Modelo.ClsConsultaProveedor;
import Vista.frmproveedore;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
        this.frm.btnverificar.addActionListener(this);
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
                  frm.txtIdentificacion.setEnabled(false);
                  frm.txtDireccion.setForeground(Color.black);
                  frm.txtNombre.setForeground(Color.black);
                  frm.btnIngresar.setEnabled(false);
                  frm.btnBusca.setEnabled(false);
                  frm.btnUpdate.setEnabled(true);
                  frm.btnEliminar.setEnabled(true);
                  frm.btnverificar.setEnabled(false);
                  frm.lblverificado.setText(null);
              
            }
        });
        
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
    public void Iniciar()
    {
        frm.txtID.setVisible(false);
        frm.btnUpdate.setEnabled(false);
        frm.btnEliminar.setEnabled(false);
        frm.btnIngresar.setEnabled(false);
        Mostrar();
    }
     public  boolean validarCedula(String cedula) {
        // Verificar longitud de la cédula
        if (cedula.length() != 10) {
            return false;
        }

        // Verificar que los primeros dos dígitos sean dígitos válidos
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }

        // Verificar el último dígito (dígito verificador)
        int digitoVerificador = Character.getNumericValue(cedula.charAt(9));
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }
        int residuo = suma % 10;
        int resultado;
        if (residuo == 0) {
            resultado = 0;
        } else {
            resultado = 10 - residuo;
        }

        return resultado == digitoVerificador;
    }

    public  String obtenerProvincia(String cedula) {
        int provincia = Integer.parseInt(cedula.substring(0, 2));

        switch (provincia) {
            case 1:
                return "Azuay";
            case 2:
                return "Bolívar";
            case 3:
                return "Cañar";
            case 4:
                return "Carchi";
            case 5:
                return "Cotopaxi";
            case 6:
                return "Chimborazo";
            case 7:
                return "El Oro";
            case 8:
                return "Esmeraldas";
            case 9:
                return "Guayas";
            case 10:
                return "Imbabura";
            case 11:
                return "Loja";
            case 12:
                return "Los Rios";
            case 13:
                return "Manabí";
            case 14:
                return "Morona Santiago";
            case 15:
                return "Napo";
            case 16:
                return "Pastaza";
            case 17:
                return "Pichincha";
            case 18:
                return "Tungurahua";
            case 19:
                return "Zamora Chinchipe";
            case 20:
                return "Galápagos";
            case 21:
                return "Sucumbios";
            case 22:
                return "Orellana";
            case 23:
                return "Santo Domingo de los Tsáchilas";
            case 24:
                return "Santa Elena";
            case 30:
                return "Número reservado para ecuatorianos registrados en el exterior.";
            default:
                return "Provincia no encontrada";
        }
    }
 String mensaje;
   @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==frm.btnactualizar){
            Mostrar();
            Limpiar();
            frm.btnUpdate.setEnabled(false);
            frm.btnEliminar.setEnabled(false);
            frm.txtBuscar.setEnabled(true);
            frm.btnBusca.setEnabled(true);
            frm.txtIdentificacion.setEnabled(true);
            frm.btnverificar.setEnabled(true);
        }
         if(e.getSource()==frm.btnverificar){
             
                if("".equals(frm.txtIdentificacion.getText())){
                 mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                 }
               if("Ingrese Identificacion Fiscal".equals(frm.txtIdentificacion.getText())){
                 mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                }else{
                   String cedula = frm.txtIdentificacion.getText();
                    if (validarCedula(cedula)) {
                    String provincia = obtenerProvincia(cedula);
                    frm.lblverificado.setText("Cédula Válida. El ciudadano es de " + provincia);
                    frm.lblverificado.setForeground(new Color(0,75,159));
                    frm.btnIngresar.setEnabled(true);

                    } else {
                        frm.lblverificado.setText("Cédula Inválida. Ingrese Cédula Correcta");
                        frm.txtIdentificacion.setText("Ingrese Identificacion Fiscal");
                        frm.txtIdentificacion.setForeground(new Color(204,204,204));
                        frm.lblverificado.setForeground(new Color(151,3,3));
                    }
               }
                
             
            
            
        }
       
       if(e.getSource()==frm.btnIngresar)
       { 
           ClsConsultaClientes clie = new ClsConsultaClientes();
           Cliente clies = new Cliente();
                 if("".equals(frm.txtNombre.getText())){
                        mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);

                }
                else if("Ingrese Nombre del Proveedor".equals(frm.txtNombre.getText())){
                        mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);

                }
                else if("".equals(frm.txtDireccion.getText())){
                   mensaje="Debe ingresar datos en el campo direccion";SSMI(mensaje);

                }
                 else if("Ingrese Direccion".equals(frm.txtDireccion.getText())){
                   mensaje="Debe ingresar datos en el campo direccion";SSMI(mensaje);

                }
               else if("".equals(frm.txtIdentificacion.getText())){
                    mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                }
                else if("Ingrese Identificacion Fiscal".equals(frm.txtIdentificacion.getText())){
                    mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                }
                else{
                    frm.txtBuscar.setEnabled(true);
                    
                    mat.setIdentificacionFiscal(frm.txtIdentificacion.getText());
//                    clies.setIdentificacion_fiscal(frm.txtIdentificacion.getText());
                    if(sqlmat.ExisteProveedor(mat)){
                        mensaje = "La Cédula Ingresada Ya Existe. Ingrese otra Cédula";
                        SSMI(mensaje);
                    }else if(sqlmat.ExisteCliente(frm.txtIdentificacion.getText())){
                        mensaje = "Ya Existe un Cliente Con esa Cédula. Ingrese otra Cédula";
                        SSMI(mensaje);
                    }
                    else{
                        mat.setNombre(frm.txtNombre.getText());
                        mat.setDireccion(frm.txtDireccion.getText());
                        mat.setFormaPagoPreferida((String) frm.cmbforma.getSelectedItem());
                         if(sqlmat.Guardar(mat))
                            {
                                mensaje ="Proveedor guardado"; 
                                SSMC(mensaje);frm.btnIngresar.setEnabled(false);
                                Limpiar();
                                Mostrar();
                            }
                            else
                            {
                                mensaje ="No se guardó la informacion del Proveedor";
                                SSMI(mensaje);Limpiar();
                            }
                    }
                            
                   
                }
                
           
          
       } 
       if(e.getSource()==frm.btnLimpiar)
       {
           Limpiar();
       } 
      
     if(e.getSource()==frm.btnUpdate)
       {
                if("".equals(frm.txtNombre.getText())){
                        mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);

                }
                else if("Ingrese Nombre del Proveedor".equals(frm.txtNombre.getText())){
                        mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);

                }
                else if("".equals(frm.txtDireccion.getText())){
                   mensaje="Debe ingresar datos en el campo direccion";SSMI(mensaje);

                }
                 else if("Ingrese Direccion".equals(frm.txtDireccion.getText())){
                   mensaje="Debe ingresar datos en el campo direccion";SSMI(mensaje);

                }
               else if("".equals(frm.txtIdentificacion.getText())){
                    mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                }
                else if("Ingrese Identificacion Fiscal".equals(frm.txtIdentificacion.getText())){
                    mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                }else{
                    mat.setId(Integer.parseInt(frm.txtID.getText()));
                    mat.setNombre(frm.txtNombre.getText());
                    mat.setDireccion(frm.txtDireccion.getText());
                     mat.setFormaPagoPreferida((String) frm.cmbforma.getSelectedItem());
                    mat.setIdentificacionFiscal(frm.txtIdentificacion.getText());

                    if(sqlmat.Modificar(mat))
                    { 
                        mensaje ="Se actualizo la informacion";
                        SSMC(mensaje); Limpiar();
                        Mostrar();frm.btnIngresar.setEnabled(false);
                        frm.btnBusca.setEnabled(true);
                        frm.txtBuscar.setEnabled(true);
                        frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                        frm.txtIdentificacion.setEnabled(true);
                        frm.btnverificar.setEnabled(true);
                    }
                    else
                    {
                        mensaje ="No se actualizo la informacion";
                        SSMI(mensaje);
                        Limpiar();frm.btnIngresar.setEnabled(true);
                        frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);
                        frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                        frm.txtIdentificacion.setEnabled(true);
                        frm.btnverificar.setEnabled(true);
                    }

                }
                  
                
           
       }
    
       if(e.getSource()==frm.btnEliminar)
       {
          
          mat.setId(Integer.parseInt(frm.txtID.getText()));
           if(sqlmat.Eliminar(mat))
           {
                mensaje ="Eliminado";
                SSMC(mensaje);
                Mostrar();
                Limpiar();frm.btnIngresar.setEnabled(false);
                frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);
                frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                frm.txtIdentificacion.setEnabled(true);
                frm.btnverificar.setEnabled(true);
           }
           else
           {
                mensaje="No se elimino"; SSMI(mensaje);Limpiar();frm.btnIngresar.setEnabled(true);
                frm.btnBusca.setEnabled(true);frm.txtBuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                frm.txtIdentificacion.setEnabled(true);
                frm.btnverificar.setEnabled(true);
           }
       }
       if(e.getSource()==frm.btnBusca)
       {
           if("Buscar Proveedores".equals(frm.txtBuscar.getText())){
                mensaje="Ingrese cédula del Proveedor a Buscar";
                SSMI(mensaje);
           }
           else if("".equals(frm.txtBuscar.getText())){
                mensaje="Ingrese cédula del Proveedor a Buscar";
                SSMI(mensaje);
           }else{
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
                            DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                            Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                            if (frm.tbMaterias.getColumnCount() >= 7) {
                                for(int i=4; i<7;i++)
                                   {  
                                       frm.tbMaterias.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                   }
                            }
                       }
                        
                        else{
                            
                                mensaje="No encontro información"; 
                                SSMI(mensaje);
                                Limpiar();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Ctrl_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                         }  


                }else{
                    mensaje="No encontraron Datos";SSMI(mensaje); Limpiar(); frm.txtBuscar.setText("Buscar Proveedores");
                }
               
           }
        
           
        }
           
    }   
     private void Mostrar()
    {
            // Limpiar la tabla antes de cargar los datos
        DefaultTableModel tabla = (DefaultTableModel) frm.tbMaterias.getModel();
        tabla.setRowCount(0);

        String[] columnas = {"ID", "Nombre", "Direccion", "Identificacion Fiscal", "Forma De Pago"};
        Object[] datos = new Object[5];

        List objList;
        ClsProveedor cls;

        try {
            objList = sqlmat.MostrarProveedor();

            if (!objList.isEmpty()) {
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

                DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                Alinear.setHorizontalAlignment(SwingConstants.RIGHT);

                if (frm.tbMaterias.getColumnCount() >= 7) {
                    for (int i = 4; i < 7; i++) {
                        frm.tbMaterias.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                    }
                }
            } else {
                mensaje = "No se encontraron datos";
                SSMI(mensaje);
                Limpiar();
                frm.txtBuscar.setText("Buscar Proveedores");
            }
        } catch (Exception ex) {
            Logger.getLogger(Ctrl_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   public void Limpiar()
    {
        frm.txtID.setText(null);
        frm.txtNombre.setText("Ingrese Nombre del Proveedor");
        frm.txtBuscar.setText("Buscar Proveedores");
        frm.txtBuscar.setForeground(new Color(204,204,204));
        frm.txtDireccion.setText("Ingrese Direccion");
        frm.txtIdentificacion.setText("Ingrese Identificacion Fiscal");
        frm.txtIdentificacion.setForeground(new Color(204,204,204));
        frm.txtDireccion.setForeground(new Color(204,204,204));
        frm.txtNombre.setForeground(new Color(204,204,204));
        frm.lblverificado.setText(null);
    }
  
}