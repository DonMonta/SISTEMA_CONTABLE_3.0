/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsuario {
    private Connection conexion;
    
    public ControladorUsuario(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean autenticarUsuario(String usuario, String contrasena) {
        try {
            String query = "SELECT * FROM usuario WHERE usuario = ? AND clave = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Error al autenticar usuario: " + ex.getMessage());
            return false;
        }
    }
}