/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HP   
 */
public class Conexion {
    /*Connection conexion = null;
    
    String usuario = "sa";
    String contrasena = "2023";
    String db = "Prototipo";
    String ip = "localhost";
    String puerto = "1433";
    
    public Connection obtenerConexion() {
        try{
          
            String cadena = "jdbc:sqlserver://localhost;encrypt=true;integratedSecurity=true;";//+puerto+";"+"databaseName="+db;
            //conexion = DriverManager.getConnection(cadena, usuario, contrasena);
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
        }
        return conexion;
    }
    
    PreparedStatement prepareStatement(String delete_from_HUESPEDES_WHERE_idHuesped) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/
    
    /*Connection con;
    
    public Connection obtenerConexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;encrypt=true;integratedSecurity=true";
            con = DriverManager.getConnection(url);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error de Conexion"+ e);
        }
        return con;
    }*/
    
    /*public static Connection getConexion(){
        String conexionUrl="jdbc:sqlserver://localhost:1433;" 
                + "database=Prototipo;"
                + "user=sa;"
                + "password=2023;"
                + "loginTimeout=30;";
        try{
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }*/
    
    static Connection con  = null;
    public static Connection getConexion(){
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BasePrototipo";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "No se pudo estrablecer la conexion...revisar Driver"+e.getMessage(),"Error de conexion",
                    JOptionPane.ERROR_MESSAGE);
        }
        try{
            con = DriverManager.getConnection(url,"sa","2023");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR"+e.getMessage(),"Error de conexion",
                    JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}
