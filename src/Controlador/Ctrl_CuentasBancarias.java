/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cls_ConsultaCuentaBancarias;
import Modelo.Cuentas_Bancarias;
import Vista.frmCuenta_Bancaria;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class Ctrl_CuentasBancarias implements ActionListener{
     Cuentas_Bancarias clie;
    Cls_ConsultaCuentaBancarias sqlclie;
    frmCuenta_Bancaria frm;
    public Ctrl_CuentasBancarias(Cuentas_Bancarias clie, Cls_ConsultaCuentaBancarias sqlclie, frmCuenta_Bancaria frm){
        this.clie = clie;
        this.sqlclie = sqlclie;
        this.frm = frm;
        this.frm.btnIngresar.addActionListener(this);
        this.frm.btnverificar.addActionListener(this);
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
                frm.txtsaldo.setForeground(Color.black);
               
                frm.txtnombre.setForeground(Color.black);
                frm.txtnumero.setEnabled(false);
                frm.txtnombre.setEnabled(false);
                frm.btnverificar.setEnabled(false);
                frm.btnIngresar.setEnabled(false);
                frm.btnbuscar.setEnabled(false);
                frm.btnUpdate.setEnabled(true);
                frm.btnEliminar.setEnabled(true);
            }
        });
    }
     String mensaje;
   private static final Map<String, String> BANCOS = new HashMap<>();

    static {
        BANCOS.put("220", "Banco Pichincha");
        BANCOS.put("300", "Banco del Pacífico");
        BANCOS.put("302", "Banco de Guayaquil");
        BANCOS.put("350", "Produbanco");
        BANCOS.put("320", "Banco Internacional");
        // Agrega más bancos según sea necesario
    }

    public static boolean validarNumeroCuenta(String numeroCuenta) {
        String numeroCuentaLimpiado = numeroCuenta.replaceAll("\\s", "").replaceAll("-", "");

        if (numeroCuentaLimpiado.length() != 10) {
            return false;
        }

        String primerosDigitos = numeroCuentaLimpiado.substring(0, 3);

        if (!BANCOS.containsKey(primerosDigitos)) {
            return false;
        }

        String nombreBanco = obtenerNombreBanco(numeroCuenta);

        if (nombreBanco.equals("Banco Pichincha")) {
            // Reglas de validación para el Banco Pichincha
            if (!validarReglasBancoPichincha(numeroCuentaLimpiado)) {
                return false;
            }
        } else if (nombreBanco.equals("Banco del Pacífico")) {
            // Reglas de validación para el Banco del Pacífico
            if (!validarReglasBancoDelPacifico(numeroCuentaLimpiado)) {
                return false;
            }
        } else if (nombreBanco.equals("Banco de Guayaquil")) {
            // Reglas de validación para el Banco de Guayaquil
            if (!validarReglasBancoDeGuayaquil(numeroCuentaLimpiado)) {
                return false;
            }
        } else if (nombreBanco.equals("Produbanco")) {
            // Reglas de validación para Produbanco
            if (!validarReglasProdubanco(numeroCuentaLimpiado)) {
                return false;
            }
        } else if (nombreBanco.equals("Banco Internacional")) {
            // Reglas de validación para Banco Internacional
            if (!validarReglasBancoInternacional(numeroCuentaLimpiado)) {
                return false;
            }
        }
        // Agrega más bloques "else if" para los demás bancos según sea necesario

        return true; // Por defecto, se considera válido si pasa las verificaciones anteriores
    }

    public static String obtenerNombreBanco(String numeroCuenta) {
        String primerosDigitos = numeroCuenta.substring(0, 3);
        return BANCOS.getOrDefault(primerosDigitos, "Desconocido");
    }

    private static boolean validarReglasBancoPichincha(String numeroCuenta) {
        // Reglas de validación para el Banco Pichincha
        // Ejemplo de regla: El dígito en la posición 4 debe ser igual a 5
        int cuartoDigito = Character.getNumericValue(numeroCuenta.charAt(3));
        return cuartoDigito == 5;
    }

    private static boolean validarReglasBancoDelPacifico(String numeroCuenta) {
        // Reglas de validación para el Banco del Pacífico
        // Ejemplo de regla: El dígito en la posición 6 debe ser igual a 1 o 2
        int sextoDigito = Character.getNumericValue(numeroCuenta.charAt(5));
        return sextoDigito == 1 || sextoDigito == 2;
    }

    private static boolean validarReglasBancoDeGuayaquil(String numeroCuenta) {
        // Reglas de validación para el Banco de Guayaquil
        // Ejemplo de regla: El dígito en la posición 8 debe ser igual a 2
        int octavoDigito = Character.getNumericValue(numeroCuenta.charAt(7));
        return octavoDigito == 2;
    }

    private static boolean validarReglasProdubanco(String numeroCuenta) {
        // Reglas de validación para Produbanco
        // Ejemplo de regla: El dígito en la posición 2 debe ser igual a 1 o 5
        int segundoDigito = Character.getNumericValue(numeroCuenta.charAt(1));
        return segundoDigito == 1 || segundoDigito == 5;
    }

    private static boolean validarReglasBancoInternacional(String numeroCuenta) {
        // Reglas de validación para Banco Internacional
        // Ejemplo de regla: El dígito en la posición 5 debe ser igual a 3
        int quintoDigito = Character.getNumericValue(numeroCuenta.charAt(4));
        return quintoDigito == 3;
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
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.btnactualizar){
            
            Mostrar();
            Limpiar();
            frm.btnUpdate.setEnabled(false);
            frm.btnEliminar.setEnabled(false);
            frm.txtbuscar.setEnabled(true);
            frm.btnbuscar.setEnabled(true);
            frm.txtbuscar.setText(null);
            frm.btnverificar.setEnabled(true);
            frm.txtnumero.setEnabled(true);
        }
        if(e.getSource()==frm.btnLimpiar){
            
            Mostrar();
            Limpiar();
            
        }
        if(e.getSource()==frm.btnverificar){
                if("".equals(frm.txtnumero.getText())){
                mensaje="Debe ingresar datos en el campo numero";SSMI(mensaje);


                }
            if("Ingrese Numero de Cuenta".equals(frm.txtnumero.getText())){
               mensaje="Debe ingresar datos en el campo numero";SSMI(mensaje);


                }
            else{
                String numero = frm.txtnumero.getText();
                if (validarNumeroCuenta(numero)) {
                String Banco = obtenerNombreBanco(numero);
                frm.lblverificado.setText("Número de Cuenta Válido. El numero de Cuenta es de " + Banco);
                frm.txtnombre.setText(Banco);
                frm.lblverificado.setForeground(new Color(0,75,159));
                frm.btnIngresar.setEnabled(true);
                

                } else {
                    frm.lblverificado.setText("Número de Cuenta Inválido. Ingrese Número de Cuenta Correcto");
                    frm.txtnumero.setText("Ingrese Numero de Cuenta");
                    frm.txtnumero.setForeground(new Color(204,204,204));
                    frm.lblverificado.setForeground(new Color(151,3,3));
                }
            }
                
            
            
            
        }
         if(e.getSource()==frm.btnIngresar){
             
            if(Validar()){
                frm.txtbuscar.setEnabled(true);
                
               
                clie.setNumero_cuenta(frm.txtnumero.getText());
                if(sqlclie.ExisteCuentas_Bancarias(clie)){
                    mensaje = "El Número de Cuenta Ingresado Ya Existe. Ingrese otro Número";
                    SSMI(mensaje);
                }else{
                    clie.setNombre_banco(frm.txtnombre.getText());
                    clie.setSaldo_actual(Double.valueOf(frm.txtsaldo.getText()));
                    if(sqlclie.Guardar(clie)){

                        mensaje = "Guardado";
                        SSMC(mensaje);
                        Mostrar();
                        Limpiar();
                    }else{
                         mensaje = "No se guardó la informacion";Limpiar();SSMI(mensaje);
                    }
                }
                
            }
                  
                  
        }
         if(e.getSource()==frm.btnUpdate){
             if(Validar()){
                 
                 
                 clie.setId(Integer.parseInt(frm.txtid.getText()));
                 clie.setNombre_banco(frm.txtnombre.getText());
                clie.setNumero_cuenta(frm.txtnumero.getText());
                clie.setSaldo_actual(Double.valueOf(frm.txtsaldo.getText()));
                 
           
                 if(sqlclie.Modificar(clie)){
                     mensaje = "Se actualizo la informacion";
                     SSMC(mensaje);
                     Limpiar();Mostrar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);
                     frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                     frm.txtnumero.setEnabled(true);
                     frm.btnverificar.setEnabled(true);
                 }
                 else{
                     mensaje ="No se actualizo la informacion";
                     SSMI(mensaje);
                     Limpiar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                     frm.txtnumero.setEnabled(true);
                     frm.btnverificar.setEnabled(true);
                 }
                         
                    
             }
         }
         if(e.getSource()==frm.btnEliminar){
             if(frm.tbcuenta.getSelectedRow()==-1){
                mensaje ="Debe seleccionar la fila que desea eliminar";
                SSMI(mensaje);
             }
             else{
                 clie.setId(Integer.parseInt(frm.txtid.getText()));  
                 if(sqlclie.Eliminar(clie)){
                    mensaje ="Eliminado";SSMC(mensaje);
                     Mostrar();
                     Limpiar();frm.btnIngresar.setEnabled(true);
                     frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);
                     frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                     frm.txtnumero.setEnabled(true);
                     frm.btnverificar.setEnabled(true);
                 }
                 else{
                     mensaje="No se elimino"; SSMI(mensaje);Limpiar();frm.btnIngresar.setEnabled(true);
                    frm.btnbuscar.setEnabled(true);frm.txtbuscar.setEnabled(true);frm.btnUpdate.setEnabled(false);frm.btnEliminar.setEnabled(false);
                    frm.txtnumero.setEnabled(true);
                    frm.btnverificar.setEnabled(true);
                 }
             }
         }
         if(e.getSource()==frm.btnbuscar){
              if("Buscar Numero de Cuenta".equals(frm.txtbuscar.getText())){
                mensaje="Ingrese número de cuenta a Buscar";
                SSMI(mensaje);
            }
            else if("".equals(frm.txtbuscar.getText())){
                 mensaje="Ingrese número de cuenta a Buscar";
                 SSMI(mensaje);
            }else{
                    clie.setNumero_cuenta(frm.txtbuscar.getText());
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
                         objList= sqlclie.ListarBussqueda(clie.getNumero_cuenta());
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
                                 frm.txtbuscar.setText("Buscar Numero de Cuenta");

                                    DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                                    Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                                    if (frm.tbcuenta.getColumnCount() >= 7) {
                                       for(int i=4; i<7;i++)
                                       {  
                                           frm.tbcuenta.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                                       }
                                   }
                                }
                                else{
                                    mensaje="No encontro información"; 
                                    SSMI(mensaje);
                                    Limpiar();
                               }

                     }catch (Exception ex){
                         Logger.getLogger(Ctrl_CuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 else
                {
                  mensaje="No encontraron Datos";SSMI(mensaje); Limpiar(); frm.txtbuscar.setText("Buscar Numero de Cuenta");
                }
            }
             
         }
    }
   public void Mostrar() {
       
        // Limpiar la tabla antes de cargar los datos
        DefaultTableModel tabla = (DefaultTableModel) frm.tbcuenta.getModel();
        tabla.setRowCount(0);

        String[] columnas = {"ID", "Banco", "Numero de Cuenta", "Saldo"};
        Object[] datos = new Object[4];

        List<Cuentas_Bancarias> notas;
        Cuentas_Bancarias cls;

        try {
            notas = sqlclie.MostrarCuentas_Bancarias();

            if (!notas.isEmpty()) {
                for (int i = 0; i < notas.size(); i++) {
                    cls = notas.get(i);
                    datos[0] = cls.getId();
                    datos[1] = cls.getNombre_banco();
                    datos[2] = cls.getNumero_cuenta();
                    datos[3] = cls.getSaldo_actual();

                    tabla.addRow(datos);
                }

                frm.tbcuenta.setModel(tabla);

                DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                Alinear.setHorizontalAlignment(SwingConstants.RIGHT);

                if (frm.tbcuenta.getColumnCount() >= 7) {
                    for (int i = 4; i < 7; i++) {
                        frm.tbcuenta.getColumnModel().getColumn(i).setCellRenderer(Alinear);
                    }
                }
            } else {
                mensaje = "No se encontraron datos";
                SSMI(mensaje);
                Limpiar();
                frm.txtbuscar.setText("Buscar Numero de Cuenta");
            }
        } catch (Exception ex) {
            Logger.getLogger(Ctrl_CuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Limpiar()
    {
        
         frm.txtid.setText(null);
         frm.txtnombre.setText(null);
         frm.txtnumero.setText("Ingrese Numero de Cuenta");
         frm.txtsaldo.setText("Ingrese Saldo");
         frm.lblverificado.setText(null);
         frm.txtbuscar.setText("Buscar Numero de Cuenta");
         frm.txtbuscar.setForeground(new Color(204,204,204));
        frm.txtsaldo.setForeground(new Color(204,204,204));
        frm.txtnumero.setForeground(new Color(204,204,204));
         frm.txtnombre.setForeground(new Color(204,204,204));
         frm.txtsaldo.setForeground(new Color(204,204,204));
       
    }
     private boolean Validar(){
         
         if("".equals(frm.txtnombre.getText())){
             
             mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);
             return  false;
            
             }
         if("Ingrese Nombre del Banco".equals(frm.txtnombre.getText())){
             
             mensaje="Debe ingresar datos en el campo nombre";SSMI(mensaje);
             return  false;
            
             }
         if("".equals(frm.txtnumero.getText())){
             mensaje="Debe ingresar datos en el campo numero";SSMI(mensaje);
            
             return  false;
            
             }
         if("Ingrese Numero de Cuenta".equals(frm.txtnumero.getText())){
            mensaje="Debe ingresar datos en el campo numero";SSMI(mensaje);
            
             return  false;
            
             }
         if("".equals(frm.txtsaldo.getText())){
            mensaje="Debe ingresar datos en el campo saldo";SSMI(mensaje);
            
             return  false;
            
             }
          if("Ingrese Saldo".equals(frm.txtsaldo.getText())){
             mensaje="Debe ingresar datos en el campo saldo";SSMI(mensaje);
            
             return  false;
            
             }
         if(!isNumero(frm.txtsaldo.getText().trim())) {
		mensaje="Inserte solo Numeros en el campo saldo";SSMI(mensaje);
                
                    frm.txtsaldo.setText("Ingrese Identificación Social");
                     return  false;  
                   
          }
          if(!isNumero(frm.txtnumero.getText().trim())) {
		mensaje="Inserte solo Numeros en el campo cuenta";SSMI(mensaje);
                
                    frm.txtnumero.setText("Ingrese Numero de Cuenta");
                     return  false;  
                   
          }
         
         

         return true;
     }
      private static boolean isNumero(String datos){
          return datos.matches("[0-9 .]*");}
}
