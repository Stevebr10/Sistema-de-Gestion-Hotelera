/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author HP
 */
public class Salario extends Personal {
    private int idPersonal;
    private String fecha;
    private double salario;

    public Salario(int idPersonal, String fecha, String Cedula, String Pasaporte, String nombre, String Apellido, String telefono, String correo, String rol, double salario) {
        super(Cedula, Pasaporte, nombre, Apellido, telefono, correo, rol);
        this.idPersonal = idPersonal;
        this.fecha = fecha;
        this.salario = salario;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    
    
}
