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
 * @author monta
 */
public class ClsConsultaClientes extends Coneccion{
    public boolean ExisteCliente(Cliente cliente){
        PreparedStatement ps =null;
        Connection con= (Connection)getConexion();
        ResultSet res=null;
        String sql="SELECT * FROM clientes WHERE identificacion_fiscal=?";

            try {    
                ps=con.prepareStatement(sql);
                 ps.setString(1, cliente.getIdentificacion_fiscal());
                res=ps.executeQuery();
                //paso el resultado de la consulta al modelo
               if(res.next())
                {
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
     public boolean Guardar(Cliente cliente){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO clientes (nombre,direccion,identificacion_fiscal,forma_pago_preferida)  "
                    + "values(?,?,?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, cliente.getNombre());
             ps.setString(2, cliente.getDireccion());
             ps.setString(3, cliente.getIdentificacion_fiscal());
             ps.setString(4, cliente.getForma_pago_preferida());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
     public boolean Modificar(Cliente cliente){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="UPDATE clientes SET nombre=?,direccion=?,identificacion_fiscal=?,forma_pago_preferida=? WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, cliente.getNombre());
             ps.setString(2, cliente.getDireccion());
             ps.setString(3, cliente.getIdentificacion_fiscal());
             ps.setString(4, cliente.getForma_pago_preferida());
             ps.setInt(5, cliente.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
    }
      public boolean Eliminar(Cliente cliente){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="DELETE FROM clientes WHERE id=?";          
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
       public List Buscar()throws Exception{
         ResultSet res;
         List listaLcientes = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from clientes";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("id"));
                cliente.setNombre(res.getString("nombre"));
                cliente.setDireccion(res.getString("direccion"));   
                cliente.setIdentificacion_fiscal(res.getString("identificacion_fiscal"));
                cliente.setForma_pago_preferida(res.getString("forma_pago_preferida"));
                listaLcientes.add(cliente);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaLcientes;
     }
   
       public List MostrarCliente()throws Exception{
         ResultSet res;
         List listaLcientes = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from clientes";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                 Cliente cliente = new Cliente();
                cliente.setId(res.getInt("id"));
                cliente.setNombre(res.getString("nombre"));
                cliente.setDireccion(res.getString("direccion"));   
                cliente.setIdentificacion_fiscal(res.getString("identificacion_fiscal"));
                cliente.setForma_pago_preferida(res.getString("forma_pago_preferida"));
                listaLcientes.add(cliente);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaLcientes;
     }
       
    public boolean BuscarCliente(Cliente cliente){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet res=null;
    String sql="SELECT * FROM clientes WHERE identificacion_fiscal=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, cliente.getIdentificacion_fiscal());
            res=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(res.next())
            {
                cliente.setId(res.getInt("id"));
                cliente.setNombre(res.getString("nombre"));
                cliente.setDireccion(res.getString("direccion"));   
                cliente.setIdentificacion_fiscal(res.getString("identificacion_fiscal"));
                cliente.setForma_pago_preferida(res.getString("forma_pago_preferida"));
                return true;  
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
   public List ListarBussqueda(String nombre)throws Exception{
         ResultSet res;
          List listaLcientes = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="SELECT * FROM clientes WHERE identificacion_fiscal=?";
         try {
             
             ps=con.prepareStatement(sql);
             ps.setString(1, nombre);
             res = ps.executeQuery();
             while (res.next()) {                 
               Cliente cliente = new Cliente();
                cliente.setId(res.getInt("id"));
                cliente.setNombre(res.getString("nombre"));
                cliente.setDireccion(res.getString("direccion"));   
                cliente.setIdentificacion_fiscal(res.getString("identificacion_fiscal"));
                cliente.setForma_pago_preferida(res.getString("forma_pago_preferida"));
                listaLcientes.add(cliente);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaLcientes;
     }
}
