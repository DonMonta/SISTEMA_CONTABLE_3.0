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
public class ClsConsultaGastos extends Coneccion{
     public boolean Guardar(ClsGastos gastos){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO gastos (tipo_gasto,importe,fecha)  "
                    + "values(?,?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, gastos.getTipo_gasto());
             ps.setDouble(2, gastos.getImporte());
             ps.setDate(3, new java.sql.Date(gastos.getFecha().getTime()));
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
     public boolean Modificar(ClsGastos gastos){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="UPDATE gastos SET tipo_gasto=?,importe=?,fecha=? WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, gastos.getTipo_gasto());
             ps.setDouble(2, gastos.getImporte());
             ps.setDate(3, new java.sql.Date(gastos.getFecha().getTime()));
             ps.setInt(4, gastos.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
    }
      public boolean Eliminar(ClsGastos gastos){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="DELETE FROM gastos WHERE id=?";          
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, gastos.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
       public List Buscar()throws Exception{
         ResultSet res;
         List listagastos = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from gastos";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                ClsGastos gastos = new ClsGastos();
                gastos.setId(res.getInt("id"));
                gastos.setTipo_gasto(res.getString("tipo_gasto"));
                gastos.setImporte(res.getDouble("importe"));   
                gastos.setFecha(res.getDate("fecha"));
                listagastos.add(gastos);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listagastos;
     }
   
       public List MostrarClsGastos()throws Exception{
         ResultSet res;
         List listagastos = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from gastos";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                 ClsGastos gastos = new ClsGastos();
                 gastos.setId(res.getInt("id"));
                gastos.setTipo_gasto(res.getString("tipo_gasto"));
                gastos.setImporte(res.getDouble("importe"));   
                gastos.setFecha(res.getDate("fecha"));
                listagastos.add(gastos);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listagastos;
     }
       
    public boolean BuscarClsGastos(ClsGastos gastos){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet res=null;
    String sql="SELECT * FROM gastos WHERE tipo_gasto=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, gastos.getTipo_gasto());
            res=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(res.next())
            {
                gastos.setId(res.getInt("id"));
                gastos.setTipo_gasto(res.getString("tipo_gasto"));
                gastos.setImporte(res.getDouble("importe"));   
                gastos.setFecha(res.getDate("fecha"));
                return true;  
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
   public List ListarBussqueda(String tipo_gasto)throws Exception{
         ResultSet res;
          List listagastos = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="SELECT * FROM gastos WHERE tipo_gasto=?";
         try {
             
             ps=con.prepareStatement(sql);
             ps.setString(1, tipo_gasto);
             res = ps.executeQuery();
             while (res.next()) {                 
               ClsGastos gastos = new ClsGastos();
                gastos.setId(res.getInt("id"));
                gastos.setTipo_gasto(res.getString("tipo_gasto"));
                gastos.setImporte(res.getDouble("importe"));   
                gastos.setFecha(res.getDate("fecha"));
                listagastos.add(gastos);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaGastos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listagastos;
     }
}
