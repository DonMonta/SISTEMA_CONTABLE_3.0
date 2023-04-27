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
public class Cls_ConsultaCuentaBancarias extends Coneccion{
     public boolean Guardar(Cuentas_Bancarias cuentas_bancarias){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO cuentas_bancarias (nombre_banco,numero_cuenta,saldo_actual)"
                    + "values(?,?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, cuentas_bancarias.getNombre_banco());
             ps.setString(2, cuentas_bancarias.getNumero_cuenta());
             ps.setDouble(3, cuentas_bancarias.getSaldo_actual());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
     public boolean Modificar(Cuentas_Bancarias cuentas_bancarias){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="UPDATE cuentas_bancarias SET nombre_banco=?,numero_cuenta=?,saldo_actual=? WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, cuentas_bancarias.getNombre_banco());
             ps.setString(2, cuentas_bancarias.getNumero_cuenta());
             ps.setDouble(3, cuentas_bancarias.getSaldo_actual());
             ps.setInt(4, cuentas_bancarias.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
    }
      public boolean Eliminar(Cuentas_Bancarias cuentas_bancarias){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="DELETE FROM cuentas_bancarias WHERE id=?";          
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, cuentas_bancarias.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
       public List Buscar()throws Exception{
         ResultSet res;
         List listacuentasbancarias = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from cuentas_bancarias";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                Cuentas_Bancarias cuentas_bancarias = new Cuentas_Bancarias();
                cuentas_bancarias.setId(res.getInt("id"));
                cuentas_bancarias.setNombre_banco(res.getString("nombre_banco"));
                cuentas_bancarias.setNumero_cuenta(res.getString("numero_cuenta"));   
                cuentas_bancarias.setSaldo_actual(res.getDouble("saldo_actual"));
                listacuentasbancarias.add(cuentas_bancarias);
             }
         } catch (SQLException ex) {
            Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listacuentasbancarias;
     }
   
       public List MostrarCuentas_Bancarias()throws Exception{
         ResultSet res;
         List listacuentasbancarias = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from cuentas_bancarias";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                 Cuentas_Bancarias cuentas_bancarias = new Cuentas_Bancarias();
                cuentas_bancarias.setId(res.getInt("id"));
                cuentas_bancarias.setNombre_banco(res.getString("nombre_banco"));
                cuentas_bancarias.setNumero_cuenta(res.getString("numero_cuenta"));   
                cuentas_bancarias.setSaldo_actual(res.getDouble("saldo_actual"));
                listacuentasbancarias.add(cuentas_bancarias);
             }
         } catch (SQLException ex) {
            Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listacuentasbancarias;
     }
       
    public boolean BuscarCuentas_Bancarias(Cuentas_Bancarias cuentas_bancarias){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet res=null;
    String sql="SELECT * FROM cuentas_bancarias WHERE nombre_banco=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, cuentas_bancarias.getNombre_banco());
            res=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(res.next())
            {
                cuentas_bancarias.setId(res.getInt("id"));
                cuentas_bancarias.setNombre_banco(res.getString("nombre_banco"));
                cuentas_bancarias.setNumero_cuenta(res.getString("numero_cuenta"));   
                cuentas_bancarias.setSaldo_actual(res.getDouble("saldo_actual"));
                return true;  
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
   public List ListarBussqueda(String nombre)throws Exception{
         ResultSet res;
          List listacuentasbancarias = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="SELECT * FROM cuentas_bancarias WHERE nombre_banco=?";
         try {
             
             ps=con.prepareStatement(sql);
             ps.setString(1, nombre);
             res = ps.executeQuery();
             while (res.next()) {                 
               Cuentas_Bancarias cuentas_bancarias = new Cuentas_Bancarias();
               cuentas_bancarias.setId(res.getInt("id"));
                cuentas_bancarias.setNombre_banco(res.getString("nombre_banco"));
                cuentas_bancarias.setNumero_cuenta(res.getString("numero_cuenta"));   
                cuentas_bancarias.setSaldo_actual(res.getDouble("saldo_actual"));
                listacuentasbancarias.add(cuentas_bancarias);
             }
         } catch (SQLException ex) {
            Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cls_ConsultaCuentaBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listacuentasbancarias;
     }
}
