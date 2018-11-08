/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador_compilador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/*

*/

/**
 *
 * @author jonat
 */
public class Analizador {

    /**
     * @return the lista_Semanticos
     */
    public ArrayList<Error_Semantico> getLista_Semanticos() {
        return lista_Semanticos;
    }

    /**
     * @param lista_Semanticos the lista_Semanticos to set
     */
    public void setLista_Semanticos(ArrayList<Error_Semantico> lista_Semanticos) {
        this.lista_Semanticos = lista_Semanticos;
    }
    /**
     * @return the archivo
     */
    public Archivo getArchivo() {
        return fuente;
    }
    /**
     * @param archivo the archivo to set
     * @return 
     * @throws java.io.FileNotFoundException
     */
    public boolean cargarArchivo(File archivo) throws FileNotFoundException, IOException {
        try{
            this.fuente = new Archivo();
            this.fuente.abrirArchivo(archivo);
            return true;
        }
        catch (Exception e) {
            return false;
        } 
        
        
    }
        
    public boolean analizarArchivo() throws IOException {
        Reader lector = fuente.getBuffer_reader();
        lexer = new Lexer(lector);
        Sintactico parser = new Sintactico(lexer);
       
        try {
           
            parser.parse();
            System.out.println("Finalizado");
        } catch (Exception e) {
            
        }
     
        
        this.lista_Tokens = lexer.getLista_Tokens();
        this.lista_Sintacticos = parser.getLista_Errores();
        this.TS = parser.getTS();
        this.lista_Semanticos = parser.getListaSemanticos();
        fuente.cerrarArchivo();
        return true;
    }
    
    public boolean generarArchivoTokens(String path) throws IOException{      
        this.fuente.crearArchivo(lista_Tokens,path);
        return true;
    }
    public Lista_Tokens getListaTokens(){
        return this.lista_Tokens;
    }
    
    public ArrayList<Error_Sintactico> getListaErroresS(){
        return lista_Sintacticos;
    }
    
    public Tabla_Simbolos getTS(){
        return this.TS;
    }
    
    private Archivo fuente;
    private Lexer lexer;
    private Lista_Tokens lista_Tokens;
    private ArrayList<Error_Sintactico> lista_Sintacticos;
    private Tabla_Simbolos TS;
    private ArrayList<Error_Semantico> lista_Semanticos;
}

