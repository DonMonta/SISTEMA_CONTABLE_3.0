/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class ClsConsultainventario extends Coneccion{
    
    public boolean Guardar(ClsInventario inventario){
    PreparedStatement ps = null;
    Connection con = (Connection)getConexion();
    String sql = "INSERT INTO inventario (nombre_producto, descripcion, precio_venta, costo, cantidad_disponible) "
                + "values (?, ?, ?, ?, ?)";
    try {    
        ps = con.prepareStatement(sql);
        ps.setString(1, inventario.getNombre_producto());
        ps.setString(2, inventario.getDescripcion());
        ps.setDouble(3, inventario.getPrecio_venta());
        ps.setDouble(4, inventario.getCosto());
        ps.setInt(5, inventario.getCantidad_disponible());
        ps.execute();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    finally {
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    public boolean Modificar(ClsInventario inventario){
             PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="UPDATE inventario SET cantidad_disponible=?,costo=?,descripcion=?,nombre_producto=?,precio_venta=? WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, inventario.getCantidad_disponible());
             ps.setDouble(2, inventario.getCosto());
             ps.setString(3, inventario.getDescripcion());
             ps.setString(4, inventario.getNombre_producto());
             ps.setDouble(5, inventario.getPrecio_venta());
            ps.setInt(6, inventario.getId());   
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
    }

    public boolean Eliminar(ClsInventario inventario){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="DELETE FROM inventario WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, inventario.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
  
    public List MostrarTienda()throws Exception{
         ResultSet res;
         List obList = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from inventario";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsInventario obj = new ClsInventario();
                obj.setId(res.getInt("id"));
                obj.setNombre_producto(res.getString("nombre_producto"));
                obj.setDescripcion(res.getString("descripcion"));
                obj.setPrecio_venta(res.getDouble("precio_venta"));
                obj.setCosto(res.getDouble("costo"));
                obj.setCantidad_disponible(res.getInt("cantidad_disponible"));
                obList.add(obj); 
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return obList;
     }
    public boolean BuscarTienda(ClsInventario obj){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet res=null;
    String sql="SELECT * FROM inventario WHERE nombre_producto=?";
            
        try {    
            ps=con.prepareStatement(sql);
            ps.setString(1, obj.getNombre_producto());
            res=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(res.next())
            {
              obj.setId(res.getInt("id"));
                obj.setNombre_producto(res.getString("nombre_producto"));
                obj.setDescripcion(res.getString("descripcion"));
                obj.setPrecio_venta(res.getDouble("precio_venta"));
                obj.setCosto(res.getDouble("costo"));
                obj.setCantidad_disponible(res.getInt("cantidad_disponible"));
                return true;  
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
     public List ListarBussqueda(String nombre)throws Exception{
         ResultSet res;
         List listaMaterias = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="SELECT * FROM inventario WHERE nombre_producto=?";
         try {
             
             ps=con.prepareStatement(sql);
             ps.setString(1, nombre);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsInventario obj = new ClsInventario();
              obj.setId(res.getInt("id"));
                obj.setNombre_producto(res.getString("nombre_producto"));
                obj.setDescripcion(res.getString("descripcion"));
                obj.setPrecio_venta(res.getDouble("precio_venta"));
                obj.setCosto(res.getDouble("costo"));
                obj.setCantidad_disponible(res.getInt("cantidad_disponible"));
                listaMaterias.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultainventario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaMaterias;
     }
    
}