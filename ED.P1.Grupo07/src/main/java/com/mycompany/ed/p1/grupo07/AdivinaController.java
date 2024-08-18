/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button btAdd;
    @FXML
    private AnchorPane fondo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/images/audio1.mp3").toExternalForm()));
       mediaPlayer.play();
       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), event -> {
           mediaPlayer.stop();
       }));
       timeline.setCycleCount(1); 
       timeline.play();
}

    @FXML
    private void agregarArchivo(ActionEvent event) {
    }

    @FXML
    private void adivinar(ActionEvent event) {
    }
    private void switchToPrimary() throws IOException {
        App.setRoot("Portal");
    }
    
    
}
