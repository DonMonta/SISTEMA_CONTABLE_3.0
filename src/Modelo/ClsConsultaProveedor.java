/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juand
 */
public class ClsConsultaProveedor extends Coneccion {
    public boolean ExisteProveedor(ClsProveedor obj){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet res=null;
    String sql="SELECT * FROM proveedores WHERE identificacion_fiscal=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, obj.getIdentificacionFiscal());
            res=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(res.next())
            {
                 obj.setId(res.getInt("id"));
                obj.setDireccion(res.getString("direccion"));
                obj.setFormaPagoPreferida(res.getString("forma_pago_preferida"));
                obj.setIdentificacionFiscal(res.getString("identificacion_fiscal"));
                return true;  
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
      public boolean ExisteCliente(String identificacion){
        PreparedStatement ps =null;
        Connection con= (Connection)getConexion();
        ResultSet res=null;
        String sql="SELECT * FROM clientes WHERE identificacion_fiscal=?";

            try {    
                ps=con.prepareStatement(sql);
                 ps.setString(1, identificacion);
                res=ps.executeQuery();
                //paso el resultado de la consulta al modelo
               if(res.next())
                {
                    Cliente cliente = new Cliente();
                    cliente.setId(res.getInt("id"));
                    cliente.setNombre(res.getString("nombre"));
                    cliente.setDireccion(res.getString("direccion"));   
                    cliente.setIdentificacion_fiscal(res.getString("identificacion_fiscal"));
                    cliente.setForma_pago_preferida(res.getString("forma_pago_preferida"));
                    return true;  
                }
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            finally{
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }

    public boolean Guardar(ClsProveedor proveedor){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO proveedores (nombre,direccion,forma_pago_preferida,identificacion_fiscal)  "
                    + "values(?,?,?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, proveedor.getNombre());
             ps.setString(2, proveedor.getDireccion());
             ps.setString(3, proveedor.getFormaPagoPreferida());
             ps.setString(4, proveedor.getIdentificacionFiscal());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
     public boolean Modificar(ClsProveedor user){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="UPDATE proveedores SET nombre=?,direccion=?,forma_pago_preferida=?,identificacion_fiscal=? WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
             ps.setString(2, user.getDireccion());
             ps.setString(3, user.getFormaPagoPreferida());
             ps.setString(4, user.getIdentificacionFiscal());
             ps.setInt(5, user.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
    }
      public boolean Eliminar(ClsProveedor al){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="DELETE FROM proveedores WHERE id=?";          
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, al.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
       public List Buscar()throws Exception{
         ResultSet res;
         List listaProveedores = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from proveedores";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsProveedor mat = new ClsProveedor();
                mat.setId(res.getInt("id"));
                mat.setNombre(res.getString("nombre"));
                mat.setIdentificacionFiscal(res.getString("identificacion_fiscal"));   
                mat.setIdentificacionFiscal(res.getString("forma_pago_preferida"));        
                listaProveedores.add(mat);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaProveedores;
     }
   
      
       public List MostrarProveedor()throws Exception{
         ResultSet res;
         List obList = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from proveedores";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsProveedor obj = new ClsProveedor();
                obj.setId(res.getInt("id"));
                obj.setNombre(res.getString("nombre"));
                obj.setDireccion(res.getString("direccion"));
                obj.setFormaPagoPreferida(res.getString("forma_pago_preferida"));
                obj.setIdentificacionFiscal(res.getString("identificacion_fiscal"));
                obList.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return obList;
     }
        public boolean BuscarProveedor(ClsProveedor obj){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet res=null;
    String sql="SELECT * FROM proveedores WHERE identificacion_fiscal=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, obj.getIdentificacionFiscal());
            res=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(res.next())
            {
                 obj.setId(res.getInt("id"));
                obj.setDireccion(res.getString("direccion"));
                obj.setFormaPagoPreferida(res.getString("forma_pago_preferida"));
                obj.setIdentificacionFiscal(res.getString("identificacion_fiscal"));
                return true;  
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
   public List ListarBussqueda(String nombre)throws Exception{
         ResultSet res;
         List listaMaterias = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="SELECT * FROM proveedores WHERE identificacion_fiscal=?";
         try {
             
             ps=con.prepareStatement(sql);
             ps.setString(1, nombre);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsProveedor obj = new ClsProveedor();
               obj.setId(res.getInt("id"));
                obj.setNombre(res.getString("nombre"));
                obj.setDireccion(res.getString("direccion"));
                obj.setFormaPagoPreferida(res.getString("forma_pago_preferida"));
                obj.setIdentificacionFiscal(res.getString("identificacion_fiscal"));
                listaMaterias.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaMaterias;
     }
}
