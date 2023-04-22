/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author juand
 */
public class Usuario {

    //Atributos
    private int idUsuario;
    private String usuario;
    private String clave;
   

    //Contructor vacío
    public Usuario() {
        this.idUsuario = 0;
        this.usuario = "";
        this.clave = "";
    }

    //set and get
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

   

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return clave;
    }

    public void setPassword(String password) {
        this.clave = password;
    }

}
