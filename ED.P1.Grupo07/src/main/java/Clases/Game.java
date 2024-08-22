/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import TDA.BinaryTree;
import TDA.NodeBinaryTree;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Grupo 7
 */
public class Game {
    
    public static int cantPreguntas;
    private HashMap<String,String> mapaRespuestas = new HashMap<>();
    private BinaryTree<Pregunta> decisionTree;
    
    public void cargarArbol(String file) {
        Queue<String> colaPreguntas = Util.leerArchivo(file);
        if (colaPreguntas.isEmpty()) {
            return;
        }

        Queue<BinaryTree<Pregunta>> colaTrees = new LinkedList<>();

        // Raiz (nivel 0, k=0)
        NodeBinaryTree<Pregunta> nodoP = new NodeBinaryTree<>(new Pregunta(colaPreguntas.poll()));
        BinaryTree<Pregunta> root = new BinaryTree<>(nodoP);
        colaTrees.offer(root);

        int nivel = 1; // Inicia desde el nivel 1

        while (!colaPreguntas.isEmpty()) {
            int iteraciones = (int) Math.pow(2, nivel); // 2^k, donde k = nivel actual

            // Crear los nodos de la pregunta actual
            NodeBinaryTree<Pregunta>[] nodosActuales = new NodeBinaryTree[iteraciones];
            String preguntaActual = colaPreguntas.poll();

            for (int i = 0; i < iteraciones; i++) {
                nodosActuales[i] = new NodeBinaryTree<>(new Pregunta(preguntaActual));
            }

            for (int i = 0; i < iteraciones / 2; i++) {
                BinaryTree<Pregunta> arbol = colaTrees.poll();

                // Asignar los nodos como hijos izquierdo y derecho
                BinaryTree<Pregunta> subTreeLeft = new BinaryTree<>(nodosActuales[2 * i]);
                arbol.getRoot().setLeft(subTreeLeft);
                colaTrees.offer(subTreeLeft);

                BinaryTree<Pregunta> subTreeRight = new BinaryTree<>(nodosActuales[2 * i + 1]);
                arbol.getRoot().setRight(subTreeRight);
                colaTrees.offer(subTreeRight);
            }

            nivel++;
        }

        decisionTree = root;
    }

    
    public String obtenerNombreAnimal(String clave) {
        return mapaRespuestas.get(clave.trim());
    }
    
    public void cargarRespuestas(String file){
        decisionTree.insertarRespuestas(file);
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
        imprimirArbolDecision();
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
    
    public String encontrarAnimal(List<String> respuestas) {
        NodeBinaryTree<Pregunta> nodoActual = decisionTree.getRoot();

        for (String respuesta : respuestas) {
            if (respuesta.equals("sí")) {
                if (nodoActual.getLeft() != null) {
                    nodoActual = nodoActual.getLeft().getRoot();
                } else {
                    return "No se encontró el animal, llegó a un nodo sin hijos en el lado izquierdo.";
                }
            } else if (respuesta.equals("no")) {
                if (nodoActual.getRight() != null) {
                    nodoActual = nodoActual.getRight().getRoot();
                } else {
                    return "No se encontró el animal, llegó a un nodo sin hijos en el lado derecho.";
                }
            } else {
                return "Respuesta inválida. Use 'sí' o 'no'.";
            }
        }

        // El contenido del nodo actual debería ser el animal
        Pregunta preguntaActual = nodoActual.getContent();
        return preguntaActual.getPregunta();
    }
}
    

