
package Modelo;

public class ClsLogin {
    // ATRIBUTOS
    
private String usuario;
private String clave;

// CONSTRUCTOR

public ClsLogin(){
    this.clave=clave;
    this.usuario=usuario;
}
// METODOS GETTER Y SETTER

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
