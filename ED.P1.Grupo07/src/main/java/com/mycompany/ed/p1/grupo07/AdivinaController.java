/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import Clases.Pregunta;
import TDA.*;
import java.io.IOException;
import java.net.URL;
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
    @FXML
    private Label lblSimbol;
    @FXML
    public int nivel = 1;
    @FXML
    BinaryTree<Pregunta> bTree = App.game.getDecisionTree();
    @FXML
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
            String nombreAnimal = App.game.getMapaRespuestas().get(key);
            if (nombreAnimal == null) {
                nombreAnimal = "Animal Desconocido"; 
            }
            Pregunta animalPregunta = new Pregunta(nombreAnimal);
            NodeBinaryTree<Pregunta> animalNode = new NodeBinaryTree<>(animalPregunta);
            bTree.getRoot().setLeft(new BinaryTree<>(animalNode));
            System.out.println("Se ha añadido el animal al árbol: " + nombreAnimal);
            App.animalResultado = nombreAnimal;
            switchToPrimary();
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
        }else{
           key = key.trim();
            String nombreAnimal = App.game.getMapaRespuestas().get(key);
            if (nombreAnimal == null) {
                nombreAnimal = "Animal Desconocido"; 
            }
            Pregunta animalPregunta = new Pregunta(nombreAnimal);
            NodeBinaryTree<Pregunta> animalNode = new NodeBinaryTree<>(animalPregunta);
            bTree.getRoot().setLeft(new BinaryTree<>(animalNode));
            System.out.println("Se ha añadido el animal al árbol: " + nombreAnimal);
            App.animalResultado = nombreAnimal;
            switchToPrimary();
        }
        nivel++;
    }
    private void switchToPrimary() throws IOException {
        App.setRoot("Respuesta");
    }
    
    private void concluir() throws IOException {
        App.setRoot("Portal");
    }

}
