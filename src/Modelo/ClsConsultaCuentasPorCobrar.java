/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author monta
 */
public class ClsConsultaCuentasPorCobrar extends Coneccion{
     public boolean Guardar(ClsCuentasPorCobrar al){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO cuentas_por_cobrar (importe,fecha_vencimiento,fecha_pago,id_factura) "
                    + "values(?,?,?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setDouble(1, al.getImporte());
             ps.setDate(2, new java.sql.Date(al.getFecha_venci().getTime()));
             ps.setDate(3, new java.sql.Date(al.getFecha_pag().getTime()));
             ps.setInt(4, al.getFactura());
          
                      
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
}
