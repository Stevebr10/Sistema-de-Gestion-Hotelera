/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author HP
 */
public class Cobro {
    private int idPersonal;
    private String tipoHabitacion;
    private int cantidadDias;
    private double precioHabi;
    private String fecha;

    public Cobro(int idPersonal, String tipoHabitacion, int cantidadDias, double precioHabi, String fecha) {
        this.idPersonal = idPersonal;
        this.tipoHabitacion = tipoHabitacion;
        this.cantidadDias = cantidadDias;
        this.precioHabi = precioHabi;
        this.fecha = fecha;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public double getPrecioHabi() {
        return precioHabi;
    }

    public void setPrecioHabi(double precioHabi) {
        this.precioHabi = precioHabi;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

   
   

}
