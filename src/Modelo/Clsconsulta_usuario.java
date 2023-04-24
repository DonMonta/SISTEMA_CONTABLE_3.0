/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author ytjes
 */
public class Clsconsulta_usuario extends Coneccion{
    
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
}
