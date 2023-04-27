/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author monta
 */
public class Cuentas_Bancarias {
    int id ;
    String nombre_banco,numero_cuenta;
    Double saldo_actual;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_banco() {
        return nombre_banco;
    }

    public void setNombre_banco(String nombre_banco) {
        this.nombre_banco = nombre_banco;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(Double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }
    @Override
    public String toString() {
        return getNombre_banco()+" "+ getNumero_cuenta();
    }
}
