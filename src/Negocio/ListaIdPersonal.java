/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListaIdPersonal {
    ArrayList<Huesped> listaIdPersonal;

    public ListaIdPersonal() {
        this.listaIdPersonal = new ArrayList();
    }
    
    public void agregarIdPersonal(Huesped p){
        listaIdPersonal.add(p);
    }
    
    
}
