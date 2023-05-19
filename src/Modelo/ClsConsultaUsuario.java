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



/**
 *
 * @author juand
 */
public class ClsConsultaUsuario extends Coneccion {
     public boolean Login(Usuario obj){
        PreparedStatement ps =null;
        Connection con= (Connection)getConexion();
        ResultSet res=null;
        String sql="SELECT * FROM usuario WHERE user=? and clave=?";

            try {    
                ps=con.prepareStatement(sql);
                 ps.setString(1, obj.getUsuario());
                 ps.setString(2, obj.getPassword());
                res=ps.executeQuery();
                //paso el resultado de la consulta al modelo
               if(res.next())
                {
                     obj.setIdUsuario(res.getInt("id"));
                     obj.setCorreo(res.getString("correo"));
                     obj.setUsuario(res.getString("user"));
                     obj.setPassword(res.getString("clave"));
                    return true;  
                }
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(Clsconsulta_usuario.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            finally{
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clsconsulta_usuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }
    public boolean Guardar(Usuario user){
            PreparedStatement ps =null;
            Connection con= (Connection)getConexion();
            String sql="INSERT INTO usuario (correo,user,clave) "
                    + "values(?,?,?)";
            
        try {    
            ps=con.prepareStatement(sql);
            ps.setString(1, user.getCorreo());
            ps.setString(2, user.getUsuario());
            ps.setString(3, user.getPassword());
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
            String sql="UPDATE usuario SET correo=?,user=?,clave=? WHERE id=?";
            
        try {    
            ps=con.prepareStatement(sql);
            
            ps.setString(1, user.getCorreo());
            ps.setString(2, user.getUsuario());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdUsuario());
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
                mat.setCorreo(res.getString("correo"));
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
                obj.setCorreo(res.getString("correo"));
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
                 obj.setCorreo(res.getString("correo"));
                 obj.setUsuario(res.getString("user"));
                 obj.setPassword(res.getString("clave"));
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
                obj.setCorreo(res.getString("correo"));
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
