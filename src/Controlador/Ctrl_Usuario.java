/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.Usuario;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author juand
 */
public class Ctrl_Usuario {
    /*
     public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Coneccion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into usuario values(?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getUsuario());
            consulta.setString(3, objeto.getPassword());
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }
        return respuesta;
    }
     public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        String sql = "select usuario from usuarios where usuario = '" + usuario + "';";
        Statement st;
        try {
            Connection cn = Coneccion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);
        }
        return respuesta;
    }
*/
}
