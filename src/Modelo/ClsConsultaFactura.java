/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author monta
 */
public class ClsConsultaFactura extends Coneccion{
    public List Mostrar()throws Exception{
         ResultSet res;
         List listaCompras = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from facturas";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsFactura obj = new ClsFactura();
                obj.setId(res.getInt("id"));
                obj.setNumero(res.getString("numero_factura"));
                obj.setFecha(res.getDate("fecha"));
                obj.setImporte(res.getDouble("importe_total"));
                obj.setCliente(res.getInt("id_cliente")); 
                obj.setProveedor(res.getInt("id_proveedor"));
                listaCompras.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaCompras;
     } 
}
