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
public class Lista_Tokens {

    /**
     * @return the lista_encontrados
     */
    Lista_Tokens(){
        lista_encontrados = new ArrayList();
    }
    public Token_Encontrado extraer(String contenido){
        for(Token_Encontrado token: lista_encontrados){
            if(token.getContenido().equals(contenido)){
                return token;
            }
        }
        return null;
    }
    
    public boolean estaToken(String contenido){
        boolean estado = false;
        for(Token_Encontrado token: lista_encontrados){   
            if(token.getContenido().equals(contenido)){
                estado = true;
                break;
            }
        }
        return estado;
    }
    
    public void aumentarCantidadLinea(int linea, String contenido){
        Token_Encontrado token = extraer(contenido);
        for(ArrayList<Integer> lista:token.getLineas_aparicion()){
            if(lista.get(0)==linea){
                lista.set(1,lista.get(1)+1);
            }
        }
    }
    
    public void agregarNuevoToken(Token token){
        Token_Encontrado nuevo = new Token_Encontrado();
        nuevo.setContenido(token.getLexema());
        nuevo.setTipo(token.getTipo());
        lista_encontrados.add(nuevo);
    }
    
    public boolean estaLineaToken(int linea, String contenido){
        boolean estado = false;
        Token_Encontrado token = extraer(contenido);
        if(0!=token.getLineas_aparicion().size()){
           for(ArrayList<Integer> sublista: token.getLineas_aparicion()){
               if(sublista.get(0)==linea){
                   estado = true;
                   break;
               }
           } 
        }
        return estado;
    }
    
    public void crearNuevaLinea(int linea,String contenido){
        Token_Encontrado token = extraer(contenido);
        ArrayList<Integer> lista = new ArrayList();
        lista.add(linea);
        lista.add(1);
        token.getLineas_aparicion().add(lista);
    }
    
    public ArrayList<Token_Encontrado> getLista_encontrados() {
        return lista_encontrados;
    }

    /**
     * @param lista_encontrados the lista_encontrados to set
     */
    public void setLista_encontrados(ArrayList<Token_Encontrado> lista_encontrados) {
        this.lista_encontrados = lista_encontrados;
    }
     
    private ArrayList<Token_Encontrado> lista_encontrados; 
}
