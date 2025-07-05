/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author HP
 */
public class Inventario {
    private int NumHabitacion;
    private int idPersonal;
    private String Nombre;
    private int Cantidad;
    private String Estado;

    public Inventario(int NumHabitacion, int idPersonal, String Nombre, int Cantidad, String Estado) {
        this.NumHabitacion = NumHabitacion;
        this.idPersonal = idPersonal;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
        this.Estado = Estado;
    }

    public int getNumHabitacion() {
        return NumHabitacion;
    }

    public void setNumHabitacion(int NumHabitacion) {
        this.NumHabitacion = NumHabitacion;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
}
