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
                frm.txtidentificacion.setForeground(Color.black);
                frm.btnIngresar.setEnabled(false);
                frm.btnbuscar.setEnabled(false);
                frm.btnUpdate.setEnabled(true);
                frm.btnEliminar.setEnabled(true);
                frm.lblverificado.setText(null);
            }
        });
    }
    public void Iniciar(){
        frm.txtid.setVisible(false);
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
                return "Chimborazo";
            case 6:
                return "Cotopaxi";
            case 7:
                return "El Oro";
            case 8:
                return "Esmeraldas";
            case 9:
                return "Galápagos";
            case 10:
                return "Guayas";
            case 11:
                return "Imbabura";
            case 12:
                return "Loja";
            case 13:
                return "Los Ríos";
            case 14:
                return "Manabí";
            case 15:
                return "Morona Santiago";
            case 16:
                return "Napo";
            case 17:
                return "Orellana";
            case 18:
                return "Pastaza";
            case 19:
                return "Pichincha";
            case 20:
                return "Santa Elena";
            case 21:
                return "Santo Domingo de los Tsáchilas";
            case 22:
                return "Sucumbíos";
            case 23:
                return "Tungurahua";
            case 24:
                return "Zamora Chinchipe";
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
            frm.btnIngresar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);
        }
        if(e.getSource()==frm.btnLimpiar){
            
            Mostrar();
            Limpiar();
            
        }
         if(e.getSource()==frm.btnverificar){
            if(Validar()){
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
                
                clie.setNombre(frm.txtnombre.getText());
                clie.setDireccion(frm.txtdireccion.getText());
                clie.setIdentificacion_fiscal(frm.txtidentificacion.getText());
                clie.setForma_pago_preferida((String) frm.cmbforma.getSelectedItem());
                if(sqlclie.Guardar(clie)){
                      
                    JOptionPane.showMessageDialog(null, "Guardado");
                    frm.btnIngresar.setEnabled(false);
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
                     Limpiar();Mostrar();frm.btnIngresar.setEnabled(false);
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
                     Limpiar();frm.btnIngresar.setEnabled(false);
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
                             JOptionPane.showMessageDialog(null, "No encontro información"); 
                             Limpiar();
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
                 if (frm.tbcliente.getColumnCount() >= 7) {
                                for(int i=4; i<7;i++)
                                {  
                                    frm.tbcliente.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                }
                            }
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
         frm.lblverificado.setText(null);
         frm.txtnombre.setText("Ingrese Nombre y Apellidos");
         frm.txtbuscar.setText("Buscar  Nombre y Apellidos del cliente");
         frm.txtdireccion.setText("Ingrese Direccion");
         frm.txtidentificacion.setText("Ingrese Identificación Social");
         frm.txtbuscar.setForeground(new Color(204,204,204));
         frm.txtidentificacion.setForeground(new Color(204,204,204));
         frm.txtdireccion.setForeground(new Color(204,204,204));
         frm.txtnombre.setForeground(new Color(204,204,204));
       
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
