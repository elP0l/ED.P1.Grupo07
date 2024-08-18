/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author José Marin
 */
public class Util {
    
    public static Queue<String> leerArchivo(String archivo){
        Queue<String> cola = new LinkedList<>();
        FileReader fR;
        BufferedReader bR;
        try{
            fR = new FileReader(archivo);
            bR = new BufferedReader(fR);
            
            String line;
            while((line=bR.readLine())!=null){
                cola.offer(line.trim());
            }
            
            System.out.println("El archivo ha sido totalmente leido...");
            
        }catch(FileNotFoundException e1){
            System.out.println("El archivo no ha sido encontrado...");
        }catch(IOException e2){
            System.out.println("Ocurrió un error al leer el archivo...");
        }
        
        return cola;
    }
    
}
