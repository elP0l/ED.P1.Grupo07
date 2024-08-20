package com.mycompany.ed.p1.grupo07;

import Clases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    
    public static String pathFiles = "src/main/resources/files/";
    public static String pathImages = "src/main/resources/images/";
    public static String nameFileQuestions = "preguntas.txt";
    public static String nameFileAnswers = "respuestas.txt";
    public static Game game = new Game();
    public static List<String> animalResultado;
    public static String anima;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Portal"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public Scene getScene(){
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.cargarArbol("src/main/resources/files/preguntas.txt");
        game.imprimirArbolDecision();
        
        launch();
    }
}
