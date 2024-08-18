package com.mycompany.ed.p1.grupo07;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PortalController {

//    @FXML
//    private void switchToSecondary() throws IOException {
//    }
    @FXML
    private Button btAdd;
    @FXML
    private AnchorPane fondo;
    @FXML
    private void agregarArchivo() throws IOException{
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Archivos de Texto", "*.txt");
        fileChooser.getExtensionFilters().add(filtro);

        File archivoSeleccionado = fileChooser.showOpenDialog((Stage) btAdd.getScene().getWindow());

        if (archivoSeleccionado != null) {
            Path destino = Paths.get(App.pathFiles, archivoSeleccionado.getName());
            try {
                Files.copy(archivoSeleccionado.toPath(), destino);
                System.out.println("Archivo guardado en: " + destino.toString());
            } catch (IOException e) {
                System.err.println("Error al guardar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
        
        App.setRoot("Adivina");
    }
    
    @FXML
    private void adivinar() throws IOException{
        String archivoPreguntas = App.pathFiles+App.nameFileQuestions;
        String archivoRespuestas = App.pathFiles+App.nameFileAnswers;
        App.game.cargarArbol(archivoPreguntas);
        App.game.cargarRespuestas(archivoRespuestas);
        App.setRoot("Adivina");
    }
    
}
