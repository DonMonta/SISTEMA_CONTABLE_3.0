/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author monta
 */
public class Cliente {
    int id;
    String nombre,direccion,identificacion_fiscal,forma_pago_preferida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdentificacion_fiscal() {
        return identificacion_fiscal;
    }

    public void setIdentificacion_fiscal(String identificacion_fiscal) {
        this.identificacion_fiscal = identificacion_fiscal;
    }

    public String getForma_pago_preferida() {
        return forma_pago_preferida;
    }

    public void setForma_pago_preferida(String forma_pago_preferida) {
        this.forma_pago_preferida = forma_pago_preferida;
    }
    @Override
    public String toString() {
        return getNombre();
    }
}
