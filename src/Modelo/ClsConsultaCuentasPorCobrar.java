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
       public List Mostrar()throws Exception{
         ResultSet res;
         List listaCompras = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from cuentas_por_cobrar";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsCuentasPorCobrar obj = new ClsCuentasPorCobrar();
                obj.setId(res.getInt("id"));
                obj.setImporte(res.getDouble("importe"));
                obj.setFecha_venci(res.getDate("fecha_vencimiento"));
                obj.setFecha_pag(res.getDate("fecha_pago"));
                obj.setFactura(res.getInt("id_factura"));
                
                 
                listaCompras.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaCompras;
     } 
       
    public  ClsFactura BuscarFactura(ClsCuentasPorCobrar am){
    ClsFactura al = new ClsFactura();
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet rs=null;
    String sql="SELECT * FROM facturas WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, am.getFactura());
            rs=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(rs.next())
            {
                al.setId(Integer.parseInt(rs.getString("id")));
                al.setNumero(rs.getString("numero_factura"));
                al.setFecha(rs.getDate("fecha"));
                al.setImporte(rs.getDouble("importe_total"));
                al.setCliente(rs.getInt("id_cliente"));
                 al.setProveedor(rs.getInt("id_proveedor "));
                return al;  
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
   
    }
    public List BuscarPList(String fecha)throws Exception{
         ResultSet res;
         List listaMaterias = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="SELECT * FROM cuentas_por_cobrar WHERE fecha_pago=?";
         try {
             
             ps=con.prepareStatement(sql);
             ps.setString(1, fecha);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsCuentasPorCobrar am = new ClsCuentasPorCobrar();
                am.setId(res.getInt("id"));
                am.setImporte(res.getDouble("importe"));
                am.setFecha_venci(res.getDate("fecha_vencimiento"));
                am.setFecha_pag(res.getDate("fecha_pago"));
                am.setFactura(res.getInt("id_factura"));
                listaMaterias.add(am);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaMaterias;
     }
    public boolean BusquedaComple(ClsCuentasPorCobrar al){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet rs=null;
    String sql="SELECT c.id,c.importe,c.fecha_vencimiento,c.fecha_pago,f.numero_factura"
            + " FROM cuentas_por_cobrar c,facturas f WHERE c.fecha_pago=? and f.id=c.id_factura";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setDate(1, new java.sql.Date(al.getFecha_pag().getTime()));
            rs=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(rs.next())
            {
                
               
                al.setId(rs.getInt("id"));
                al.setImporte(rs.getDouble("importe"));
                 
                 try {
                    //convertir las fechas de sql y util
                    String fechas =rs.getString("fecha_vencimiento");
                     String fechas2 =rs.getString("fecha_pago");
                     SimpleDateFormat s=new SimpleDateFormat("yyyy-mm-dd");
                    java.util.Date fecha,fe2;
                    fecha=(java.util.Date)s.parse(fechas);
                    fe2=(java.util.Date)s.parse(fechas2);
                     al.setFecha_venci(fecha);
                     al.setFecha_pag(fe2);
                } catch (ParseException ex) {
                    Logger.getLogger(ClsConsultaCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
                }
                al.setFactura(rs.getInt("id_factura"));
              
               
                return true;  
            }
            return false;
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
