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
     public boolean Guardar(ClsFactura em){
          PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO facturas (numero_factura,fecha,importe_total,id_cliente,importe_cliente,id_proveedor,importe_proveedor) "
                    + "values(?,?,?,?,?,?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, em.getNumero());
             ps.setDate(2, new java.sql.Date(em.getFecha().getTime()));
             ps.setDouble(3, em.getImporte());
             ps.setInt(4, em.getCliente());
             ps.setDouble(5, em.getImporteclient());
             ps.setInt(6, em.getProveedor());
              ps.setDouble(7, em.getImporteprove());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
     //Metodo Para Editar
    
    public List Mostrar()throws Exception{
         ResultSet res;
         List lista = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql= "SELECT * from facturas";
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
                 obj.setImporteclient(res.getDouble("importe_cliente"));
                 obj.setProveedor(res.getInt("id_proveedor"));
                 obj.setImporteprove(res.getDouble("importe_proveedor"));
                lista.add(obj);
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
        }return lista;
     }
     public  Cliente BuscarCliente(ClsFactura am){
    Cliente cliente = new Cliente();
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet rs=null;
    String sql="SELECT * FROM clientes WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, am.getCliente());
            rs=ps.executeQuery();
            //paso el resultado de la consulta cliente modelo
           if(rs.next())
            {
                cliente.setId(Integer.parseInt(rs.getString("id")));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setIdentificacion_fiscal(rs.getString("identificacion_fiscal"));
                cliente.setForma_pago_preferida(rs.getString("forma_pago_preferida"));
                
                return cliente;  
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
   
    }
    public  ClsProveedor BuscarClsProveedor(ClsFactura am){
        ClsProveedor proveedor = new ClsProveedor();
        PreparedStatement ps =null;
        Connection con= (Connection)getConexion();
        ResultSet rs=null;
        String sql="SELECT * FROM proveedores WHERE id=?";

            try {    
                ps=con.prepareStatement(sql);
                ps.setInt(1, am.getProveedor());
                rs=ps.executeQuery();
                //paso el resultado de la consulta cliente modelo
               if(rs.next())
                {
                    proveedor.setId(Integer.parseInt(rs.getString("id")));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setIdentificacionFiscal(rs.getString("identificacion_fiscal"));
                     proveedor.setFormaPagoPreferida(rs.getString("forma_pago_preferida"));

                    return proveedor;  
                }
                return null;
            } catch (SQLException ex) {
                Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            finally{
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClsConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
   
    }
}
