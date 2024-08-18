/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author José Marin
 */
public class LimitesController implements Initializable {
    
    @FXML
    private Label lblPiense;
    @FXML
    private Label lblListo;
    @FXML
    private Label lblPregunta;
    @FXML
    private AnchorPane root;
    @FXML
    public static String num;
    @FXML
    TextField txtField = new TextField();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Thread(() -> {
            escribirTexto(lblPiense, "Piense en un animal...", 120);
            esperarHastaTerminar(lblPiense, "Piense en un animal...");

            escribirTexto(lblPregunta, "¿Cuántas preguntas desea que se le haga?", 120);
            esperarHastaTerminar(lblPregunta, "¿Cuántas preguntas desea que se le haga?");

            Platform.runLater(() -> {
                txtField.setLayoutX(151);
                txtField.setLayoutY(274);
                txtField.setAlignment(Pos.CENTER);
                txtField.setPrefWidth(206);
                txtField.setPrefHeight(26);
                txtField.setFont(Font.font("Arial", 16));
                txtField.setStyle("-fx-text-fill: #ca170f; -fx-background-color: lightgray;");
                root.getChildren().add(txtField);
            });

            escribirTexto(lblListo, "Listo?", 120);
            esperarHastaTerminar(lblListo, "Listo?");

            Platform.runLater(() -> {
                Button boton = new Button("Let's go");
                boton.setLayoutX(222);
                boton.setLayoutY(335);
                boton.setPrefWidth(71);
                boton.setPrefHeight(39);
                boton.setStyle("-fx-text-fill: white; -fx-background-color: #ca170f;");
                boton.setOnAction(event -> {
                    try {
                        num = txtField.getText();
                        if(num!=null && !num.trim().isEmpty()){
                            System.out.println(num);
                            App.setRoot("Adivina");
                        }else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Campo faltante");
                            alert.setHeaderText(null);
                            alert.setContentText("Ingrese el número de preguntas");
                            alert.showAndWait();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                root.getChildren().add(boton);
            });

        }).start();
    }    

    @FXML
    private void escribirTexto(Label label, String texto, long intervalo) {
        Thread thread = new Thread(() -> {
            StringBuilder textoParcial = new StringBuilder();
            for (char c : texto.toCharArray()) {
                textoParcial.append(c);

                // Actualizar el texto del Label en el hilo de la interfaz gráfica
                Platform.runLater(() -> label.setText(textoParcial.toString()));

                try {
                    Thread.sleep(intervalo); // Pausa entre caracteres
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true); // Para que el hilo se cierre cuando se cierre la aplicación
        thread.start();

        try {
            thread.join(); // Esperar a que este hilo termine antes de continuar con el siguiente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void esperarHastaTerminar(Label label, String textoEsperado) {
        // Espera a que el Label contenga todo el texto antes de continuar
        while (!label.getText().equals(textoEsperado)) {
            try {
                Thread.sleep(50); // Pausa breve para evitar uso excesivo de CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
