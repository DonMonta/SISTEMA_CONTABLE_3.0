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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




import Vista.FrmUSUARIOS;


/**
 *
 * @author juand
 */
public class ClsConsultaUsuario extends Coneccion {
    public boolean Guardar(Usuario tienda){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO usuario (user,clave) "
                    + "values(?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, tienda.getUsuario());
             ps.setString(2, tienda.getPassword());
            ps.execute();
            return true;
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
     public boolean Modificar(Usuario user){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="UPDATE usuario SET user=?,clave=? WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
            
             ps.setString(1, user.getUsuario());
             ps.setString(2, user.getPassword());
             ps.setInt(3, user.getIdUsuario());
            ps.execute();
            return true;
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
      public boolean Eliminar(Usuario al){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="DELETE FROM usuario WHERE id=?";          
        try {    
            ps=con.prepareStatement(sql);
            ps.setInt(1, al.getIdUsuario());
            ps.execute();
            return true;
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
       public List Buscar()throws Exception{
         ResultSet res;
         List listaUsuarios = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from usuario";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                Usuario mat = new Usuario();
                mat.setIdUsuario(res.getInt("id"));
                mat.setUsuario(res.getString("user"));
                mat.setPassword(res.getString("clave"));        
                listaUsuarios.add(mat);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaUsuarios;
     }
public List Mostrar()throws Exception{
         ResultSet res;
         List listaCompras = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from usuario";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                Usuario obj = new Usuario();
                obj.setIdUsuario(res.getInt("id"));
                obj.setUsuario(res.getNString("user"));
                obj.setPassword(res.getNString("clave"));
                
                 
                listaCompras.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaCompras;
     }
public List MostrarUsuario()throws Exception{
         ResultSet res;
         List obList = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="select * from usuario";
         try {
             ps=con.prepareStatement(sql);
             res = ps.executeQuery();
             while (res.next()) {                 
                Usuario obj = new Usuario();
                obj.setIdUsuario(res.getInt("id"));
                obj.setUsuario(res.getString("user"));
                obj.setPassword(res.getString("clave"));
                
                obList.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return obList;
     }
public boolean BuscarUsuario(Usuario obj){
    PreparedStatement ps =null;
    Connection con= (Connection)getConexion();
    ResultSet res=null;
    String sql="SELECT * FROM usuario WHERE user=?";
            
        try {    
            ps=con.prepareStatement(sql);
             ps.setString(1, obj.getUsuario());
            res=ps.executeQuery();
            //paso el resultado de la consulta al modelo
           if(res.next())
            {
                 obj.setIdUsuario(res.getInt("id"));
                obj.setPassword(res.getString("user"));
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
public List ListarBussqueda(String nombre)throws Exception{
         ResultSet res;
         List listaMaterias = new ArrayList();
         PreparedStatement ps =null;
         Connection con= (Connection)getConexion();
         String sql="SELECT * FROM usuario WHERE user=?";
         try {
             
             ps=con.prepareStatement(sql);
             ps.setString(1, nombre);
             res = ps.executeQuery();
             while (res.next()) {                 
                Usuario obj = new Usuario();
               obj.setIdUsuario(res.getInt("id"));
                obj.setUsuario(res.getString("user"));
                obj.setPassword(res.getString("clave"));
                listaMaterias.add(obj);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClsConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }return listaMaterias;
     }
}
