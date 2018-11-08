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
public class Simbolo {

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    
    Simbolo(String ts, String s, String tipo, String nombre,ArrayList<Simbolo> lista, int lin, int col){
        this.nombre = nombre;
        this.scope =s;
        this.tipo = tipo;
        this.tipoSimbolo = ts;
        this.parametros = lista;
        this.linea = lin;
        this.columna = col;
        
    }
    
    Simbolo(String ts, String s, String tipo, String nombre, int lin, int col){
        this.nombre = nombre;
        this.scope =s;
        this.tipo = tipo;
        this.tipoSimbolo = ts;
        this.linea = lin;
        this.columna = col;
    }
    /**
     * @return the parametros
     */
    public ArrayList<Simbolo> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(ArrayList<Simbolo> parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the tipoSimbolo
     */
    public String getTipoSimbolo() {
        return tipoSimbolo;
    }

    /**
     * @param tipoSimbolo the tipoSimbolo to set
     */
    public void setTipoSimbolo(String tipoSimbolo) {
        this.tipoSimbolo = tipoSimbolo;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the tipo
     */
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void addParametro(Simbolo p){
        this.parametros.add(p);
    }
    
    private String tipoSimbolo;
    private String scope;
    private String tipo;
    private String nombre;
    private int linea;
    private int columna;
    private ArrayList<Simbolo> parametros;
    
}
