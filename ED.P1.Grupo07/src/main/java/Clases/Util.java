/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author José Marin
 */
public class Util {
    
    public static Stack<String> leerArchivo(String archivo){
        Stack<String> pila = new Stack<>();
        FileReader fR;
        BufferedReader bR;
        try{
            fR = new FileReader(archivo);
            bR = new BufferedReader(fR);
            
            String line;
            while((line=bR.readLine())!=null){
                pila.push(line.trim());
            }
            
            System.out.println("El archivo ha sido totalmente leido...");
            
        }catch(FileNotFoundException e1){
            System.out.println("El archivo no ha sido encontrado...");
        }catch(IOException e2){
            System.out.println("Ocurrió un error al leer el archivo...");
        }
        
        return pila;
    }
    
}
