/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author HP
 */
public class Usuario extends Persona {
    private int idPersonal;
    private String Usuario;
    private String Password;

    public Usuario(int idPersonal, String Usuario, String Password, String Cedula, String Pasaporte, String nombre, String Apellido) {
        super(Cedula, Pasaporte, nombre, Apellido);
        this.idPersonal = idPersonal;
        this.Usuario = Usuario;
        this.Password = Password;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    
}
