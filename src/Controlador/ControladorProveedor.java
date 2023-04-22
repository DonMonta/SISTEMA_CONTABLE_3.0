/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author juand
 */
import Modelo.ClsProveedor;
import Modelo.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorProveedor {
    private final String tabla = "proveedores";
    private Connection conexion = Coneccion.getConexion();

    public void agregarProveedor(ClsProveedor proveedor) {
        String sql = "INSERT INTO " + tabla + " (nombre, direccion, identificacion_fiscal, forma_pago_preferida) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getIdentificacionFiscal());
            statement.setString(4, proveedor.getFormaPagoPreferida());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    public void actualizarProveedor(ClsProveedor proveedor) {
        String sql = "UPDATE " + tabla + " SET nombre = ?, direccion = ?, identificacion_fiscal = ?, forma_pago_preferida = ? WHERE id = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getIdentificacionFiscal());
            statement.setString(4, proveedor.getFormaPagoPreferida());
            statement.setInt(5, proveedor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    public void eliminarProveedor(int id) {
        String sql = "DELETE FROM " + tabla + " WHERE id = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(12, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }
}