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
        
        Queue<String> colaPreguntas = Util.leerArchivo(file);
        if (colaPreguntas.isEmpty()) {
            return;
        }
        Queue<BinaryTree<Pregunta>> colaTrees = new LinkedList<>();
        //Raiz
        NodeBinaryTree<Pregunta> nodoP = new NodeBinaryTree<>(new Pregunta(colaPreguntas.poll()));
        BinaryTree<Pregunta> root = new BinaryTree<>(nodoP);
        colaTrees.offer(root);
        while (!colaPreguntas.isEmpty()){
            int iteraciones = colaTrees.size();
            nodoP = new NodeBinaryTree<>(new Pregunta(colaPreguntas.poll()));
            for(int i=0;i<iteraciones;i++){
                BinaryTree<Pregunta> arbol = colaTrees.poll();
                //HIJOS IZQ
                BinaryTree<Pregunta> subTreeLeft = new BinaryTree<>(nodoP);
                arbol.getRoot().setLeft(subTreeLeft);
                colaTrees.add(subTreeLeft);
                //HIJOS DER
                BinaryTree<Pregunta> subTreeRight = new BinaryTree<>(nodoP);
                arbol.getRoot().setRight(subTreeRight);
                colaTrees.add(subTreeRight);
            }
        }
        decisionTree = root;
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
    public String obtenerNombreAnimal(String clave) {
        return mapaRespuestas.get(clave.trim());
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
    public void imprimirArbol(NodeBinaryTree<Pregunta> root, int nivel) {
        if (root == null) {
            return;
        }

        // Imprime el nivel y la pregunta del nodo actual
        System.out.println("Nivel " + nivel + ": " + root.getContent().getPregunta());

        // Llamada recursiva para el subárbol izquierdo
        imprimirArbol(root.getLeft() != null ? root.getLeft().getRoot() : null, nivel + 1);

        // Llamada recursiva para el subárbol derecho
        imprimirArbol(root.getRight() != null ? root.getRight().getRoot() : null, nivel + 1);
    }

    // Método para imprimir todo el árbol de decisiones
    public void imprimirArbolDecision() {
        imprimirArbol(decisionTree.getRoot(), 0);  // Nivel 0 para la raíz
    }
    
}
    

