/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ediso
 */
public class Coneccion {

    //conexion local
   final String bdd="sistema_contable"; 
   final String user="root";
    String pwd="";
    String url="jdbc:mysql://localhost:3309/" + bdd;
    
    Connection conexion;
    
    public Connection getConexion(){
    
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexion=(Connection)DriverManager.getConnection(url,user,pwd);
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        return conexion;
    }
}
