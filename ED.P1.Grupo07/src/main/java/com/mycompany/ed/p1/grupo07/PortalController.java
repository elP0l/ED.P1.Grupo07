/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author vecto
 */
public class PortalController implements Initializable {
    //    @FXML
//    private void switchToSecondary() throws IOException {
//    }
    @FXML
    private Button btAdd;
    @FXML
    private AnchorPane fondo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image gifImage = new Image(getClass().getResourceAsStream("/images/gif2.gif"));
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
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/images/audio2.mp3").toExternalForm()));
        mediaPlayer.play();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15), event -> {
           mediaPlayer.stop();
       }));
       timeline.setCycleCount(1); 
       timeline.play();
}
    @FXML
    private void agregarArchivo() throws IOException{
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivos");
        
        List<File> archivosSeleccionados = fileChooser.showOpenMultipleDialog((Stage) btAdd.getScene().getWindow());

        if (archivosSeleccionados != null && !archivosSeleccionados.isEmpty()) {
            for (File archivo : archivosSeleccionados) {
                try {
                    String nombreArchivo = archivo.getName();
                    
                    if (nombreArchivo.equals("preguntas.txt") || nombreArchivo.equals("questions.txt")){
                        App.nameFileQuestions = nombreArchivo;
                        String archivoPreguntas = App.pathFiles+App.nameFileQuestions;
                        App.game.cargarArbol(archivoPreguntas);
                    }else{
                        App.nameFileAnswers = nombreArchivo;
                        String archivoRespuestas = App.pathFiles+App.nameFileAnswers;
                        App.game.cargarRespuestas(archivoRespuestas);
                    }
                    Path rutaDestino = Path.of(App.pathFiles).resolve(nombreArchivo);
                    
                    Files.copy(archivo.toPath(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Archivo " + nombreArchivo + " guardado en " + rutaDestino);
                } catch (IOException e) {
                    System.err.println("Error al copiar el archivo: " + archivo.getName());
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se seleccionaron archivos.");
        }
        
        
        
        App.setRoot("Limites");
    }
    
    @FXML
    private void adivinar() throws IOException{
        String archivoPreguntas = App.pathFiles+App.nameFileQuestions;
        String archivoRespuestas = App.pathFiles+App.nameFileAnswers;
        App.game.cargarArbol(archivoPreguntas);
        App.game.cargarRespuestas(archivoRespuestas);
        App.setRoot("Limites");
    }
    
}
