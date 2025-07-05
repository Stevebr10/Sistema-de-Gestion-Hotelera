/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Metodos {
    Conexion conexion;

    public Metodos() {
        this.conexion = new Conexion();
    }
    
    //AGREGAR REGISTROS: -------------------------------------------------------------------------------------------
     public void agregarRegistroH(Huesped h) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO HUESPEDES (Identificación,Nombre,Apellido,Teléfono,Correo) VALUES(?,?,?,?,?)");
        //ins.setInt(1, h.getIdPersonal());
        ins.setString(1, h.getCedula());
        //ins.setString(1, h.getPasaporte());
        ins.setString(2, h.getNombre());
        ins.setString(3, h.getApellido());
        ins.setString(4, h.getTelefono());
        ins.setString(5, h.getCorreo());
        //ins.setInt(6, h.getHabitacion());
        ins.executeUpdate();
    }
     
    public void agregarRegistroHEx(Huesped h) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO HUESPEDES (Identificación,Nombre,Apellido,Teléfono,Correo) VALUES(?,?,?,?,?)");
        //ins.setString(1, h.getCedula());
         //ins.setInt(1, h.getIdPersonal());
        ins.setString(1, h.getPasaporte());
        ins.setString(2, h.getNombre());
        ins.setString(3, h.getApellido());
        ins.setString(4, h.getTelefono());
        ins.setString(5, h.getCorreo());
        //ins.setInt(6, h.getHabitacion());
        ins.executeUpdate();
    }
    
     public void agregarRegistroPersonal(Personal p) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO PERSONAL (Cédula,Nombre,Apellido,Teléfono,Correo,Rol) VALUES(?,?,?,?,?,?)");
        ins.setString(1, p.getCedula());
        //ins.setString(1, h.getPasaporte());
        ins.setString(2, p.getNombre());
        ins.setString(3, p.getApellido());
        ins.setString(4, p.getTelefono());
        ins.setString(5, p.getCorreo());
        ins.setString(6, p.getRol());
        ins.executeUpdate();
    }
     
    public void AgregarRegistroRe(String idHu, String idHabi, String valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE RESERVAS SET idHuesped='" + idHu + "',idHabitacion='"
                + idHabi + "' WHERE idReserva='" + valor + "'");
        ins.executeUpdate();
    }
    
    public void AgregarRegistroRSRe(String idHu, String idHabi, String valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE RESERVAS SET idHuesped='" + idHu + "',idHabitacion='"
                + idHabi + "' WHERE NOT EXISTS(Select idHuesped from RESERVAS where idHuesped='"+idHu+"') and\n" +
"		NOT EXISTS(Select idHabitacion from RESERVAS where idHuesped='"+idHabi+"')and idReserva='"+valor +"'");
        ins.executeUpdate();
    }
    
    public void agregarNotaVenta(NotaVenta nota) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO NOTAVENTA (Fecha,Identificación,Nombre,Apellido,Detalle,Subtotal,Total) VALUES(?,?,?,?,?,?,?)");
        ins.setString(1, nota.getFecha());
        ins.setString(2, nota.getIdentificacion());
        //ins.setString(1, h.getPasaporte());
        ins.setString(3, nota.getNombre());
        ins.setString(4, nota.getApellido());
        ins.setString(5, nota.getDetalle());
        ins.setDouble(6, nota.getSubtotal());
        ins.setDouble(7, nota.getTotal());
        //ins.setInt(6, h.getHabitacion());
        ins.executeUpdate();
    }
    
    
     public void agregarRegistroCobro(Cobro cobro) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO COBRO (idPersonal,TipoHabitacion,Cantidad,PrecioHabitacion,FechaCobro) VALUES(?,?,?,?,?)");
        ins.setInt(1, cobro.getIdPersonal());
        ins.setString(2, cobro.getTipoHabitacion());
        ins.setInt(3, cobro.getCantidadDias());
        //ins.setString(1, h.getPasaporte());
        ins.setDouble(4, cobro.getPrecioHabi());
        ins.setString(5, cobro.getFecha());
        //ins.setInt(6, h.getHabitacion());
        ins.executeUpdate();
    }
     
    public void agregarSalarioP(Salario salario) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO SALARIOS (idPersonal,Fecha,Nombre,Apellido,Cédula,Rol,Salario) VALUES(?,?,?,?,?,?,?)");
        ins.setInt(1, salario.getIdPersonal());
        ins.setString(2, salario.getFecha());
        ins.setString(3, salario.getNombre());
        ins.setString(4, salario.getApellido());
        ins.setString(5, salario.getCedula());
         ins.setString(6, salario.getRol());
        ins.setDouble(7, salario.getSalario());
        //ins.setInt(6, h.getHabitacion());
        ins.executeUpdate();
    }
    
    public void agregarServicio(Servicios servicio) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO SERVICIOS (idPersonal,Fecha,TipoServicio,TotalPagado) VALUES(?,?,?,?)");
        ins.setInt(1, servicio.getIdPersonal());
        ins.setString(2, servicio.getFecha());
        ins.setString(3, servicio.getTipoServicio());
        ins.setDouble(4, servicio.getTotalPagado());
        //ins.setInt(6, h.getHabitacion());
        ins.executeUpdate();
    }
    
    public void agregarInventario(Inventario inventario) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO INVENTARIO (idHabitacion,idPersonal,Nombre,Cantidad,Estado) VALUES(?,?,?,?,?)");
        ins.setInt(1, inventario.getNumHabitacion());
        ins.setInt(2, inventario.getIdPersonal());
        ins.setString(3, inventario.getNombre());
        ins.setInt(4, inventario.getCantidad());
        ins.setString(5, inventario.getEstado());
        //ins.setInt(6, h.getHabitacion());
        ins.executeUpdate();
    }
    
    public void agregarUsuario(Usuario s) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO USUARIOS (idPersonal,Usuario,Contraseña,Cédula,Nombre,Apellido) VALUES(?,?,?,?,?,?)");
        ins.setInt(1, s.getIdPersonal());
        ins.setString(2, s.getUsuario());
        ins.setString(3, s.getPassword());
        ins.setString(4, s.getCedula());
        ins.setString(5, s.getNombre());
        ins.setString(6, s.getApellido());
        ins.executeUpdate();
    }
    
    public void agregarParametro(Parametro p) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("INSERT INTO PARAMETROS (idPersonal,Nombre,Descripción,Valor) VALUES(?,?,?,?)");
        ins.setInt(1, p.getIdPersonal());
        ins.setString(2, p.getNombre());
        ins.setString(3, p.getDescripcion());
        ins.setDouble(4, p.getValor());
        ins.executeUpdate();
    }
     
    //ACTUALIZAR REGISTROS: ---------------------------------------------------------------------------------------------
     
    public void ActualizarRegistroH(Huesped h, int valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE HUESPEDES SET Teléfono='" + h.getTelefono()
                + "',Correo='" + h.getCorreo()+ /*+ "',Habitación='" + h.getHabitacion()+*/ "' WHERE idHuesped='" + valor + "'");
        ins.executeUpdate();
    }
    
    public void ActualizarRegistroHEx(Huesped h, int valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE HUESPEDES SET Teléfono='" + h.getTelefono()
                + "',Correo='" + h.getCorreo() + /*"',Habitación='" + h.getHabitacion()+ */"' WHERE idHuesped='" + valor + "'");
        ins.executeUpdate();
    }
    
     public void ActualizarRegistroP(Personal p, int valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE PERSONAL SET Teléfono='" + p.getTelefono()
                + "',Correo='" + p.getCorreo() + "',Rol='" + p.getRol()+ "' WHERE idPersonal='" + valor + "'");
        ins.executeUpdate();
    }
     
    public void ActualizarReserva(String valor, String idHu, String idHabi) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE RESERVAS SET idHuesped='" + idHu + "',idHabitacion='" + idHabi + "'WHERE idReserva='" + valor + "'");
        ins.executeUpdate();
    }
    
    public void ActualizarRSRe(String valor, String idHu, String idHabi) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE RESERVAS SET idHuesped='" + idHu + "',idHabitacion='"
                + idHabi + "' WHERE NOT EXISTS(Select idHuesped from RESERVAS where idHuesped='"+idHu+"') and\n" +
"		NOT EXISTS(Select idHabitacion from RESERVAS where idHuesped='"+idHabi+"')and idReserva='"+valor +"'");
        ins.executeUpdate();
    }
    
    public void ActualizarArticulo(Inventario inv, int valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE INVENTARIO SET Nombre='" + inv.getNombre()
                + "',Cantidad='" + inv.getCantidad() + "',Estado='" + inv.getEstado()+ "' WHERE CodigoArticulo='" + valor + "'");
        ins.executeUpdate();
    }
    
    public void ActualizarUsuario(Usuario u, int valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE USUARIOS SET Usuario='" + u.getUsuario()
                + "',Contraseña='" + u.getPassword() + "' WHERE idUsuario='" + valor + "'");
        ins.executeUpdate();
    }
    
     public void ActualizarParametro(Parametro p, int valor) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE PARAMETROS SET Nombre='" + p.getNombre()
                + "',Descripción='" + p.getDescripcion() + "',Valor='" + p.getValor() + "' WHERE idParametro='" + valor + "'");
        ins.executeUpdate();
    }
    
    
    
    //BORRAR REGISTROS: ----------------------------------------------------------------------------------------------
    
    public void borrarRegistroRe(String valor, int x) throws SQLException {                                //x+1
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE RESERVAS SET idReserva='" + (x + 1) + "',idHuesped=" + null + ",idHabitacion=" + null + " WHERE idReserva='" + valor + "'");
        ins.executeUpdate();
    }
    
     public void borrarRegistroRe1(String valor, int x) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE RESERVAS SET idHuesped='" + null + "',idHabitacion=" + null + " WHERE idReserva='" + valor + "'");
        ins.executeUpdate();
    }
    
    
    /*public void borrarRegistroP(int m) throws SQLException {
        PreparedStatement ins = conexion.getConexion().prepareStatement("DELETE FROM PERSONAL WHERE idPersonal='" + m+ "'");
        ins.executeUpdate();
    }*/
    
    public void borrarRegistroP(int valor, int x) throws SQLException {
        String aux = "" + valor;
        PreparedStatement ins = conexion.getConexion().prepareStatement("DELETE FROM PERSONAL WHERE Cédula='" + valor + "'");
        ins.executeUpdate();
    }
    
    public void borrarPersonal(int valor, int x) throws SQLException {
        String aux = "" + valor;
        PreparedStatement ins = conexion.getConexion().prepareStatement("UPDATE PERSONAL SET Rol='Despedido' WHERE idPersonal='" + valor + "'");
        ins.executeUpdate();
    }
    
    public void borrarRegistroU(int valor, int x) throws SQLException {
        String aux = "" + valor;
        PreparedStatement ins = conexion.getConexion().prepareStatement("DELETE FROM USUARIOS WHERE Cédula='" + valor + "'");
        ins.executeUpdate();
    }
}


