/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import TDA.BinaryTree;
import TDA.NodeBinaryTree;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author José Marin
 */
public class Game {
    
    private HashMap<String,String> mapaRespuestas = new HashMap<>();
    private BinaryTree<Pregunta> decisionTree;
    
    public void cargarArbol(String file){
        
        Queue<String> cola = Util.leerArchivo(file);
        if(cola.isEmpty()){
            return;
        }
        
        String line = cola.poll();
        Pregunta p = new Pregunta(line);
        NodeBinaryTree<Pregunta> nodeRoot = new NodeBinaryTree<>(p);
        decisionTree = new BinaryTree(nodeRoot);
        Queue<NodeBinaryTree<Pregunta>> queue = new LinkedList<>();
        queue.add(nodeRoot);

        while (!cola.isEmpty()) {
            line = cola.poll();
            p = new Pregunta(line);
            NodeBinaryTree<Pregunta> nodeQuestion = new NodeBinaryTree<>(p);

            NodeBinaryTree<Pregunta> currentNode = queue.poll();

            if (currentNode.getLeft() == null) {
                currentNode.setLeft(new BinaryTree<>(nodeQuestion));
                queue.add(nodeQuestion);
            }else if (currentNode.getRight() == null) {
                currentNode.setRight(new BinaryTree<>(nodeQuestion));
                queue.add(nodeQuestion);
            }
        }
        
    }
    
    public void cargarRespuestas(String file){
        
        Queue<String> cola = Util.leerArchivo(file);
        if(cola.isEmpty()){
            return;
        }
        String line = "";
        while(!cola.isEmpty()){
            line = cola.poll();
            int pos = line.indexOf(" ");
            String value = line.substring(0,pos);
            String key = line.substring(pos+1);
            mapaRespuestas.put(key, value);    
        }
        
    }
    
    public String mostrarPregunta(NodeBinaryTree<Pregunta> node){
        Pregunta p = node.getContent();
        return p.getPregunta().substring(1);
    }

    public HashMap<String, String> getMapaRespuestas() {
        return mapaRespuestas;
    }

    public BinaryTree<Pregunta> getDecisionTree() {
        return decisionTree;
    }
    
}
