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
    
    public static int cantPreguntas;
    private HashMap<String,String> mapaRespuestas = new HashMap<>();
    private BinaryTree<Pregunta> decisionTree;
    
    public void cargarArbol(String file){
        
//        Queue<String> cola = Util.leerArchivo(file);
//        if(cola.isEmpty()){
//            return;
//        }
//        
//        String line = cola.poll();
//        Pregunta p = new Pregunta(line);
//        NodeBinaryTree<Pregunta> nodeRoot = new NodeBinaryTree<>(p);
//        decisionTree = new BinaryTree(nodeRoot);
//        Queue<NodeBinaryTree<Pregunta>> queue = new LinkedList<>();
//        queue.add(nodeRoot);
//
//        while (!cola.isEmpty()) {
//            line = cola.poll();
//            p = new Pregunta(line);
//            NodeBinaryTree<Pregunta> nodeQuestion = new NodeBinaryTree<>(p);
//
//            NodeBinaryTree<Pregunta> currentNode = queue.poll();
//
//            if (currentNode.getLeft() == null) {
//                currentNode.setLeft(new BinaryTree<>(nodeQuestion));
//                queue.add(nodeQuestion);
//            }else if (currentNode.getRight() == null) {
//                currentNode.setRight(new BinaryTree<>(nodeQuestion));
//                queue.add(nodeQuestion);
//            }
//        }
//        
        
          Queue<String> colaPreguntas = Util.leerArchivo(file);
    if (colaPreguntas.isEmpty()) {
        return;
    }

    // Crear la raíz del árbol
    String preguntaRaiz = colaPreguntas.poll();
    Pregunta rootPregunta = new Pregunta(preguntaRaiz);
    NodeBinaryTree<Pregunta> nodoRaiz = new NodeBinaryTree<>(rootPregunta);
    decisionTree = new BinaryTree<>(nodoRaiz);

    // Usar una cola para mantener el seguimiento de los nodos
    Queue<NodeBinaryTree<Pregunta>> colaNodos = new LinkedList<>();
    colaNodos.add(nodoRaiz);

    while (!colaPreguntas.isEmpty()) {
        int nivelSize = colaNodos.size(); // Número de nodos en el nivel actual

        for (int i = 0; i < nivelSize; i++) {
            NodeBinaryTree<Pregunta> currentNode = colaNodos.poll();

            // Crear y añadir el hijo izquierdo
            if (!colaPreguntas.isEmpty()) {
                String preguntaIzquierda = colaPreguntas.poll();
                Pregunta izquierdaPregunta = new Pregunta(preguntaIzquierda);
                NodeBinaryTree<Pregunta> nodoIzquierdo = new NodeBinaryTree<>(izquierdaPregunta);
                currentNode.setLeft(new BinaryTree<>(nodoIzquierdo));
                colaNodos.add(nodoIzquierdo);
            }

            // Crear y añadir el hijo derecho
            if (!colaPreguntas.isEmpty()) {
                String preguntaDerecha = colaPreguntas.poll();
                Pregunta derechaPregunta = new Pregunta(preguntaDerecha);
                NodeBinaryTree<Pregunta> nodoDerecho = new NodeBinaryTree<>(derechaPregunta);
                currentNode.setRight(new BinaryTree<>(nodoDerecho));
                colaNodos.add(nodoDerecho);
            }
        }
    }
        
        
        
   
    }
    
    private void construirArbol(Queue<String> cola, NodeBinaryTree<Pregunta> nodoActual) {
        if (cola.isEmpty()) {
            return;
        }

        // Crear nodos hijos si no están ya creados
        if (nodoActual.getLeft() == null) {
            // Extraer la pregunta para el hijo izquierdo
            String preguntaIzquierda = cola.poll();
            Pregunta izquierdaPregunta = new Pregunta(preguntaIzquierda);
            NodeBinaryTree<Pregunta> nodoIzquierdo = new NodeBinaryTree<>(izquierdaPregunta);

            // Asignar el nodo hijo izquierdo
            nodoActual.setLeft(new BinaryTree<>(nodoIzquierdo));

            // Llamada recursiva para seguir construyendo el árbol en el hijo izquierdo
            construirArbol(cola, nodoIzquierdo);
        }

        if (!cola.isEmpty() && nodoActual.getRight() == null) {
            // Extraer la pregunta para el hijo derecho
            String preguntaDerecha = cola.poll();
            Pregunta derechaPregunta = new Pregunta(preguntaDerecha);
            NodeBinaryTree<Pregunta> nodoDerecho = new NodeBinaryTree<>(derechaPregunta);

            // Asignar el nodo hijo derecho
            nodoActual.setRight(new BinaryTree<>(nodoDerecho));

            // Llamada recursiva para seguir construyendo el árbol en el hijo derecho
            construirArbol(cola, nodoDerecho);
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
