/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Huesped {
    //private int idPersonal;
    private String telefono;
    private String correo;
    
    private String Cedula;
    private String Pasaporte;
    private String nombre;
    private String Apellido;

    
    //private int habitacion; //----->modificado 5/3/2023

    

   /* public Huesped(String Cedula, String Pasaporte, String nombre, String Apellido, String telefono, String correo ) {
        super(Cedula, Pasaporte, nombre, Apellido);
        this.telefono = telefono;
        this.correo = correo;
        //this.habitacion = habitacion;
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
    }*/

    /*public Huesped(int idPersonal, String Cedula, String Pasaporte, String nombre, String Apellido, String telefono, String correo) {
        super(Cedula, Pasaporte, nombre, Apellido);
        this.idPersonal = idPersonal;
        this.telefono = telefono;
        this.correo = correo;
    }
    

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
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

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        Huesped.con = con;
    }*/

    
    
    
    /*static Connection con  = null;
    public ArrayList getListaIdPersonal(){
        ArrayList listaIdPersonal = new ArrayList();
        Huesped idPersonal = null;
        Statement consulta;
        ResultSet resultado;
        try{
            consulta = con.createStatement();
            resultado = consulta.executeQuery("Select * from PERSONAL");
            while(resultado.next()){
                idPersonal= new Huesped(0, null, null, null, null, null, null);
                idPersonal.setIdPersonal(resultado.getInt("idPersonal"));
                listaIdPersonal.add(idPersonal);
            }
            
        }catch(SQLException e){
            
        }
        return listaIdPersonal;
    }

    @Override
    public String toString() {
        return ""+getIdPersonal();
    }*/

    public Huesped(/*int idPersonal*/ String Cedula, String Pasaporte, String nombre, String Apellido, String telefono, String correo) {
        //this.idPersonal = idPersonal;
        this.telefono = telefono;
        this.correo = correo;
        this.Cedula = Cedula;
        this.Pasaporte = Pasaporte;
        this.nombre = nombre;
        this.Apellido = Apellido;
    }

    public Huesped() {
    }

   /* public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }*/

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

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getPasaporte() {
        return Pasaporte;
    }

    public void setPasaporte(String Pasaporte) {
        this.Pasaporte = Pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    
    

   

  

    
}
