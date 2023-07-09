/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cliente;
import Modelo.ClsConsultaClientes;
import Vista.frmClients;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Iterator;
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
        this.frm.btnverificar.addActionListener(this);
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
                frm.txtnombre.setForeground(Color.black);
                frm.txtdireccion.setForeground(Color.black);
                frm.txtidentificacion.setEnabled(false);
                frm.btnverificar.setEnabled(false);
                frm.btnIngresar.setEnabled(false);
                frm.btnbuscar.setEnabled(false);
                frm.btnUpdate.setEnabled(true);
                frm.btnEliminar.setEnabled(true);
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
    public void Iniciar(){
        frm.txtid.setVisible(false);
        frm.btnUpdate.setEnabled(false);
        frm.btnEliminar.setEnabled(false);
        frm.btnIngresar.setEnabled(false);
        
        Mostrar();
    }
    String mensaje;
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

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.btnactualizar){
            
            Mostrar();
            Limpiar();
            frm.btnUpdate.setEnabled(false);
            frm.btnEliminar.setEnabled(false);
            frm.txtbuscar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);
            frm.txtidentificacion.setEnabled(true);
            frm.btnverificar.setEnabled(true);
        }
        if(e.getSource()==frm.btnLimpiar){
            
            Mostrar();
            Limpiar();
            
        }
         if(e.getSource()==frm.btnverificar){
             
                if("".equals(frm.txtidentificacion.getText())){
                 mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                 }
               if("Ingrese Identificación Social".equals(frm.txtidentificacion.getText())){
                 mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);


                }else{
                   String cedula = frm.txtidentificacion.getText();
                    if (validarCedula(cedula)) {
                    String provincia = obtenerProvincia(cedula);
                    frm.lblverificado.setText("Cédula Válida. El ciudadano es de " + provincia);
                    frm.lblverificado.setForeground(new Color(0,75,159));
                    frm.btnIngresar.setEnabled(true);

                    } else {
                        frm.lblverificado.setText("Cédula Inválida. Ingrese Cédula Correcta");
                        frm.txtidentificacion.setText("Ingrese Identificación Social");
                        frm.txtidentificacion.setForeground(new Color(204,204,204));
                        frm.lblverificado.setForeground(new Color(151,3,3));
                    }
               }
                
             
            
            
        }
         if(e.getSource()==frm.btnIngresar){
             
            if(Validar()){
                frm.txtbuscar.setEnabled(true);
                
                
                clie.setIdentificacion_fiscal(frm.txtidentificacion.getText());
                if(sqlclie.ExisteCliente(clie)){
                    mensaje = "La Cédula Ingresada Ya Existe. Ingrese otra Cédula";
                    SSMI(mensaje);
                }else{
                    clie.setNombre(frm.txtnombre.getText());
                    clie.setDireccion(frm.txtdireccion.getText());
                    clie.setForma_pago_preferida((String) frm.cmbforma.getSelectedItem());
                    if(sqlclie.Guardar(clie)){

                        mensaje = "Cliente Guardado!!";
                        SSMC(mensaje);
                        frm.btnIngresar.setEnabled(false);
                        Mostrar();
                        Limpiar();
                    }else{
                        mensaje = "No se Guardó la Información";
                        SSMI(mensaje);Limpiar();
                    }
                }
                
            }
                  
                  
        }
         if(e.getSource()==frm.btnUpdate){
            
             if(Validar()){
                 
                clie.setIdentificacion_fiscal(frm.txtidentificacion.getText());
                clie.setId(Integer.parseInt(frm.txtid.getText()));
                clie.setNombre(frm.txtnombre.getText());
                clie.setDireccion(frm.txtdireccion.getText());
                clie.setForma_pago_preferida((String) frm.cmbforma.getSelectedItem());
                if(sqlclie.Modificar(clie)){
                      mensaje ="Se actualizo la informacion";
                      SSMC(mensaje);
                      Limpiar();Mostrar();frm.btnIngresar.setEnabled(false);
                      frm.btnbuscar.setEnabled(true);
                      frm.txtbuscar.setEnabled(true);
                      frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                      frm.txtidentificacion.setEnabled(true);
                      frm.btnverificar.setEnabled(true);
                    }
                    else{
                        mensaje ="No se actualizo la informacion";
                        SSMI(mensaje);
                        Limpiar();frm.btnIngresar.setEnabled(true);
                        frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                        frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                        frm.txtidentificacion.setEnabled(true);
                        frm.btnverificar.setEnabled(true);
                    }
                
                 
                         
                    
             }
         }
         if(e.getSource()==frm.btnEliminar){
       
                 clie.setId(Integer.parseInt(frm.txtid.getText()));  
                 if(sqlclie.Eliminar(clie)){
                     mensaje ="Eliminado";
                     SSMC(mensaje);
                     Mostrar();
                     Limpiar();frm.btnIngresar.setEnabled(false);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                     frm.txtidentificacion.setEnabled(true);
                     frm.btnverificar.setEnabled(true);
                 }else{
                     mensaje="No se elimino"; SSMI(mensaje);Limpiar();frm.btnIngresar.setEnabled(true);
                    frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                    frm.txtidentificacion.setEnabled(true);
                    frm.btnverificar.setEnabled(true);
                 }

         }
         if(e.getSource()==frm.btnbuscar){
              
           if("Buscar cedula del cliente".equals(frm.txtbuscar.getText())){
                mensaje="Ingrese cédula del cliente a Buscar";
                SSMI(mensaje);
           }
           else if("".equals(frm.txtbuscar.getText())){
                mensaje="Ingrese cédula del cliente a Buscar";
                SSMI(mensaje);
           }else{
                clie.setIdentificacion_fiscal(frm.txtbuscar.getText());
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
                        objList= sqlclie.ListarBussqueda(clie.getIdentificacion_fiscal());
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


                                DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                                Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                                if (frm.tbcliente.getColumnCount() >= 7) {
                                   for(int i=4; i<7;i++)
                                   {  
                                       frm.tbcliente.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                   }
                               }
                            }
                            else{
                                mensaje="No encontro información"; 
                                SSMI(mensaje);
                                Limpiar();
                           }

                    }catch (Exception ex){
                        Logger.getLogger(Ctrl_Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                  mensaje="No encontraron Datos";SSMI(mensaje); Limpiar(); frm.txtbuscar.setText("Buscar cedula del cliente");
                }
               
           }
            
         }
    }
    public void Mostrar(){
                // Limpiar la tabla antes de cargar los datos
           DefaultTableModel tabla = (DefaultTableModel) frm.tbcliente.getModel();
           tabla.setRowCount(0);

           String[] columnas = {"ID", "Nombre", "Direccion", "Identificación", "Forma de Pago"};
           Object[] datos = new Object[5];

           List<Cliente> notas;
           Cliente cls;

           try {
               notas = sqlclie.MostrarCliente();

               if (!notas.isEmpty()) {
                   for (int i = 0; i < notas.size(); i++) {
                       cls = notas.get(i);
                       datos[0] = cls.getId();
                       datos[1] = cls.getNombre();
                       datos[2] = cls.getDireccion();
                       datos[3] = cls.getIdentificacion_fiscal();
                       datos[4] = cls.getForma_pago_preferida();

                       tabla.addRow(datos);
                   }

                   frm.tbcliente.setModel(tabla);

                   DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                   Alinear.setHorizontalAlignment(SwingConstants.RIGHT);

                   if (frm.tbcliente.getColumnCount() >= 7) {
                       for (int i = 4; i < 7; i++) {
                           frm.tbcliente.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                       }
                   }
               } else {
                   mensaje = "No se encontraron datos";
                   SSMI(mensaje);
                   Limpiar();
                   frm.txtbuscar.setText("Buscar cedula del cliente");
               }
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_Clientes.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    public void Limpiar()
    {
        
         frm.txtid.setText(null);
         frm.lblverificado.setText(null);
         frm.txtnombre.setText("Ingrese Nombre y Apellidos");
         frm.txtbuscar.setText("Buscar cedula del cliente");
         frm.txtbuscar.setForeground(new Color(204,204,204));
         frm.txtdireccion.setText("Ingrese Direccion");
         frm.txtidentificacion.setText("Ingrese Identificación Social");
         
         frm.txtidentificacion.setForeground(new Color(204,204,204));
         frm.txtdireccion.setForeground(new Color(204,204,204));
         frm.txtnombre.setForeground(new Color(204,204,204));
       
    }
     private boolean Validar(){
          
         
         if("".equals(frm.txtnombre.getText())){
             
             mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);
             return  false;
            
             }
         if("Ingrese Nombre y Apellidos".equals(frm.txtnombre.getText())){
             
             mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);
             return  false;
            
             }
         if("".equals(frm.txtdireccion.getText())){
             mensaje="Debe ingresar datos en el campo direccion";SSMI(mensaje);
            
             return  false;
            
             }
         if("Ingrese Direccion".equals(frm.txtdireccion.getText())){
             mensaje="Debe ingresar datos en el campo direccion";SSMI(mensaje);
            
             return  false;
            
             }
         if("".equals(frm.txtidentificacion.getText())){
             mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);
            
             return  false;
            
             }
          if("Ingrese Identificación Social".equals(frm.txtidentificacion.getText())){
             mensaje="Debe ingresar datos en el campo identificacion";SSMI(mensaje);
            
             return  false;
            
             }
         if(!isNumero(frm.txtidentificacion.getText().trim())) {
		mensaje="Inserte solo Numeros en el campo identificacion";SSMI(mensaje);
                
                    frm.txtidentificacion.setText("Ingrese Identificación Social");
                     return  false;  
                   
          }
         
         

         return true;
     }
      private static boolean isNumero(String datos){
          return datos.matches("[0-9 .]*");}
}
