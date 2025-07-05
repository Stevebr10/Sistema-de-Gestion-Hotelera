/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Personal extends Persona{
    private String telefono;
    private String correo;
    private String rol;
    //private double salario;

    public Personal(String Cedula, String Pasaporte, String nombre, String Apellido, String telefono, String correo, String rol) {
        super(Cedula, Pasaporte, nombre, Apellido);
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
      //  this.salario = salario;
    }

    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    /*public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }*/

 
}
