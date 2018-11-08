/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador_compilador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author jonat
 */




public class Archivo {
    /**
     * @return the file_write
     */
    public FileWriter getFile_write() {
        return file_write;
    }


    public void setFile_write(String path) throws IOException {
       this.file_write = new FileWriter(path);
    }

    /**
     * @return the print_writer
     */
    public PrintWriter getPrint_writer() {
        return print_writer;
    }

    
    public void setPrint_writer() {
        this.print_writer = new PrintWriter(file_write);
    }

    /**
     * @return the file_reader
     */
    public FileReader getFile_reader() {
        return file_reader;
    }

    /**
     * @param path the file_reader to set
     * @throws java.io.FileNotFoundException
     */
    public void setFile_reader(String path) throws FileNotFoundException {
        this.file_reader = new FileReader(path);
    }

    /**
     * @return the buffer_reader
     */
    public BufferedReader getBuffer_reader() {
        return buffer_reader;
    }

    public void setBuffer_reader() {
        this.buffer_reader = new BufferedReader(this.file_reader);
    }
  
    public void abrirArchivo(File archivo) throws FileNotFoundException{
        this.setFile_reader(archivo.getAbsolutePath());
        this.setBuffer_reader();
    }
    
    public void cerrarArchivo() throws IOException{
        this.file_reader.close(); 
    }
    
    public String ajustarString(String info){
        while(info.length()<31){
            info = info + " ";
        }
        return info+"|";
    }
    
    public void escribir(String linea){
        print_writer.println(linea);
    }

    public void crearArchivo(Lista_Tokens lista, String path) throws IOException {
        String archivo = path;

        setFile_write(archivo);
        setPrint_writer();
        try {
            escribir("Token" + "                          |" + "Tipo" + "                           |" + "Línea");
            escribir("---------------------------------------------------------------------");
            String info_token = "";
            for (Token_Encontrado e : lista.getLista_encontrados()) {
                info_token = info_token + ajustarString(e.getContenido()) + ajustarString(e.getTipo().toString());
                for (ArrayList<Integer> sublista : e.getLineas_aparicion()) {
                    for (Integer j : sublista) {
                        if (sublista.get(1) == 1) {
                            info_token = info_token + sublista.get(0) + " - ";
                            break;
                        } else {
                            info_token = info_token + sublista.get(0) + "(" + sublista.get(1) + ")" + " - ";
                            break;
                        }
                    }

                }
                escribir(info_token);
                info_token = "";
            }
            file_write.close();
        } catch (IOException e) {
        }
    }
    /**
     * @return the archivo
     */
    private FileReader file_reader;
    private BufferedReader buffer_reader;
    private FileWriter file_write;
    private PrintWriter print_writer;
    
    
}
