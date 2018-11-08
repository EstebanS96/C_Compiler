/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador_compilador;

import java.io.File;



/**
 *
 * @author jonat
 */
public class Analizador_Compilador {

    /**
     * @param args the command line arguments
     */
    
    /* Clase que se encarga de crear el archivo Lexer.java, capaz de reconocer las expreiones regulares de C*/
   public static void main(String[] args) {
       //E:\TEC\2017\II Semestre\Compiladores\Proyecto\2\Analizador_Compilador\dist\lib\java-cup-11a.jar
       // -parser analisis_sintactico -symbols Simbolos A_Sintactico.cup
       //java -jar C:\java-cup-11a.jar -parser analisis_sintactico -symbols sym analizador.cup
      String path = "E:\\TEC\\2017\\II Semestre\\Compiladores\\Proyecto\\2\\Analizador_Compilador\\src\\analizador_compilador\\Lexer.flex";  
      generarLexer(path);
      
    }
    public static void generarLexer(String path)
    {
       File file=new File(path);
       jflex.Main.generate(file);
    }
}
