/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador_compilador;

import java.util.ArrayList;

/**
 *
 * @author jonat
 */
public class Token_Encontrado {

  /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the tipo
     */
 
    Token_Encontrado(){
        this.lineas_aparicion = new ArrayList();
        this.contenido = "";
    }
    
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the cantidad
     */


    /**
     * @return the lineas
     */
    public ArrayList<ArrayList<Integer>> getLineas_aparicion() {
        return lineas_aparicion;
    }

    /**
     * @param lineas the lineas to set
     */
    public void setLineas_aparicion(ArrayList<ArrayList<Integer>> lineas) {
        this.lineas_aparicion = lineas;
    }
    private String tipo;
    private String contenido;
    private ArrayList<ArrayList<Integer>> lineas_aparicion;
  
}
