/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Date;
/**
 *
 * @author monta
 */
public class ClsCuentasPorCobrar {
    Date fecha_pag,fecha_venci;
    int id,factura;
    Double importe;

    public Date getFecha_pag() {
        return fecha_pag;
    }

    public void setFecha_pag(Date fecha_pag) {
        this.fecha_pag = fecha_pag;
    }

    public Date getFecha_venci() {
        return fecha_venci;
    }

    public void setFecha_venci(Date fecha_venci) {
        this.fecha_venci = fecha_venci;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
}
