/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author juand
 */
public class ClsProveedor {
    private int id;
    private String nombre;
    private String direccion;
    private String identificacionFiscal;
    private String formaPagoPreferida;

    public ClsProveedor(int id, String nombre, String direccion, String identificacionFiscal, String formaPagoPreferida) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.identificacionFiscal = identificacionFiscal;
        this.formaPagoPreferida = formaPagoPreferida;
    }

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

    public String getIdentificacionFiscal() {
        return identificacionFiscal;
    }

    public void setIdentificacionFiscal(String identificacionFiscal) {
        this.identificacionFiscal = identificacionFiscal;
    }

    public String getFormaPagoPreferida() {
        return formaPagoPreferida;
    }

    public void setFormaPagoPreferida(String formaPagoPreferida) {
        this.formaPagoPreferida = formaPagoPreferida;
    }
}
