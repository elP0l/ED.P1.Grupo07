/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import Clases.Pregunta;
import TDA.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author vecto
 */
public class AdivinaController implements Initializable {

    @FXML
    private Button btYes;
    @FXML
    private Button btNo;
    @FXML
    private AnchorPane fondo;
    @FXML
    private Label lblQuestion;
    public int nivel = 1;
    BinaryTree<Pregunta> bTree = App.game.getDecisionTree();
    String key = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bTree.recorrerPostorden();
        
        Image gifImage = new Image(getClass().getResourceAsStream("/images/gif3.gif"));
        ImageView imageView = new ImageView(gifImage);
        imageView.setFitWidth(fondo.getWidth());
        imageView.setFitHeight(fondo.getHeight());
        imageView.setPreserveRatio(false); 
        fondo.widthProperty().addListener((obs, oldVal, newVal) -> {
        imageView.setFitWidth(newVal.doubleValue());});
        fondo.heightProperty().addListener((obs, oldVal, newVal) -> {
        imageView.setFitHeight(newVal.doubleValue());});
        fondo.getChildren().add(imageView);
        imageView.toBack(); 
        Musica();
        NodeBinaryTree<Pregunta> node = bTree.getRoot();
//        nivel = App.game.cantPreguntas;
        lblQuestion.setText(App.game.mostrarPregunta(node));
        
    }
    public void Musica(){
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/images/audio1.mp3").toExternalForm()));
        mediaPlayer.play();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), event -> {
        mediaPlayer.stop();
        }));
        timeline.setCycleCount(1); 
        timeline.play();
    }
    @FXML
    private void opIzq(ActionEvent event) throws IOException {
        key+="sí ";
        System.out.println(nivel);
        System.out.println(bTree.getRoot().getRight()==null);
        if((bTree.getRoot().getLeft()!=null) && (nivel<Integer.parseInt(LimitesController.num))){
            Musica();
            bTree = bTree.getRoot().getLeft();
            NodeBinaryTree<Pregunta> node = bTree.getRoot();
            lblQuestion.setText(App.game.mostrarPregunta(node));
         } else {
            key = key.trim();
            List<String> posiblesRespuestas = buscarRespuestasPosibles(key);
            App.animalResultado = posiblesRespuestas;
            if (posiblesRespuestas.size() > 1) {
            switchToMultipleOptions();
            } else {
                key = key.trim();
                String nombreAnimal = App.game.getMapaRespuestas().get(key);
                if (nombreAnimal == null) {
                    nombreAnimal = "Animal Desconocido"; 
                }
                Pregunta animalPregunta = new Pregunta(nombreAnimal);
                NodeBinaryTree<Pregunta> animalNode = new NodeBinaryTree<>(animalPregunta);
                bTree.getRoot().setLeft(new BinaryTree<>(animalNode));
                System.out.println("Se ha añadido el animal al árbol: " + nombreAnimal);
                App.anima = nombreAnimal;
                switchToPrimary();
            }
        } 
        nivel++;
    }
        
    
    @FXML
    private void opDer(ActionEvent event) throws IOException {
        key+="no ";
        System.out.println(nivel);
        System.out.println(bTree.getRoot().getRight()==null);
        if((bTree.getRoot().getRight()!=null) && (nivel<Integer.valueOf(LimitesController.num))){
            Musica();
            bTree = bTree.getRoot().getRight();
            NodeBinaryTree<Pregunta> node = bTree.getRoot();
            lblQuestion.setText(App.game.mostrarPregunta(node));
        } else {
            key = key.trim();
            List<String> posiblesRespuestas = buscarRespuestasPosibles(key);
            App.animalResultado = posiblesRespuestas;
            if (posiblesRespuestas.size() > 1) {
            switchToMultipleOptions();
            } else {
                key = key.trim();
                String nombreAnimal = App.game.getMapaRespuestas().get(key);
                if (nombreAnimal == null) {
                    nombreAnimal = "Animal Desconocido"; 
                }
                Pregunta animalPregunta = new Pregunta(nombreAnimal);
                NodeBinaryTree<Pregunta> animalNode = new NodeBinaryTree<>(animalPregunta);
                bTree.getRoot().setLeft(new BinaryTree<>(animalNode));
                System.out.println("Se ha añadido el animal al árbol: " + nombreAnimal);
                App.anima = nombreAnimal;
                switchToPrimary();
            } 
        }
        nivel++;
    }
    private void switchToMultipleOptions() throws IOException {
        App.setRoot("Multiple");
    }
    private List<String> buscarRespuestasPosibles(String pattern) {
        List<String> resultados = new ArrayList<>();
        for (HashMap.Entry<String, String> entry : App.game.getMapaRespuestas().entrySet()) {
            if (entry.getKey().startsWith(pattern)) {
                resultados.add(entry.getValue());
            }
        }
        return resultados;
    }
    private void switchToPrimary() throws IOException {
        App.setRoot("Respuesta");
    }
  

}
