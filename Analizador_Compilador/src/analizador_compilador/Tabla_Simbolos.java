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
public class Tabla_Simbolos {
    ArrayList <Simbolo> tablaSimbolos;
    
    Tabla_Simbolos(){
        this.tablaSimbolos = new ArrayList();
    }
    
    public boolean agregarSimbolo(Simbolo s){
       if(s.getTipoSimbolo().equals("Funcion")){
           System.out.println(s.getParametros().size());
       }
       tablaSimbolos.add(s);
       return true;
    }
    
    public boolean buscarDeclaracionVariable(Simbolo var){
        for(Simbolo s:tablaSimbolos){
            if(s.getTipoSimbolo().equals("Variable")) {
                if((s.getScope().equals(var.getScope()))&& (s.getNombre().equals(var.getNombre()))){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean buscarDefinicionVariable(Simbolo var){
        for(Simbolo s:tablaSimbolos){
            if(s.getTipoSimbolo().equals("Variable")) {
                if(s.getScope().equals(var.getScope())){
                    if(s.getNombre().equals(var.getNombre())){
                        return true;
                    }
                }else{
                    if((s.getScope().equals("global"))&&(s.getNombre().equals(var.getNombre()))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public String buscarDefinicionFuncion(Simbolo f){
        for(Simbolo s: tablaSimbolos){
            if(s.getNombre().equals(f.getNombre())){
                if(f.getParametros().size()==s.getParametros().size()){
                    int indice = 0;
                    for(Simbolo parametro:s.getParametros()){
                        if(f.getParametros().get(indice).getTipoSimbolo().equals("Variable")){
                            if(!(parametro.getTipo().equals(f.getParametros().get(indice).getTipo()))){
                                return "Error en el tipo de parametro, se esperaba " + parametro.getTipo();
                            }
                        }
                        
                        if(f.getParametros().get(indice).getTipoSimbolo().equals("Numero")){
                            if((parametro.getTipo().equals("char") || (parametro.getTipo().equals("const-char")))){
                               return "Error en el tipo de parametro, se esperaba " + parametro.getTipo();
                            }
                        }
                        
                        if(f.getParametros().get(indice).getTipoSimbolo().equals("Char")){
                            if(!((parametro.getTipo().equals("char") || (parametro.getTipo().equals("const-char"))))){
                               return "Error en el tipo de parametro, se esperaba " + parametro.getTipo();
                            }
                        }
                        
                        indice ++;
                    }
                    return "Valido";
                }else{
                    return "Error en cantidad de parametros";
                }
            }
        }
        return "Función no definida";
    }
    
    public boolean buscarDeclaracionFuncion(Simbolo f){
        for (Simbolo s : tablaSimbolos) {
            if (s.getTipoSimbolo().equals("Funcion")) {
                if (s.getNombre().equals(f.getNombre())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean buscarDeclaracion(Simbolo s){
        if(s.getTipoSimbolo().equals("Funcion")){
            return buscarDeclaracionFuncion(s);
        }else{
            return buscarDeclaracionVariable(s);
        }
    }
    
    public ArrayList<Simbolo>getTablaSimbolos(){
        return this.tablaSimbolos;
    }
    
}
