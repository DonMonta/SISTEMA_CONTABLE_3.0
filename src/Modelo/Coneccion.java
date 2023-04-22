/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Coneccion {
    
    private static Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_basedatos";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";
    
    public static Connection getConexion() {
        try {
            if (conexion == null) {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }
        return conexion;
    }
    
}
