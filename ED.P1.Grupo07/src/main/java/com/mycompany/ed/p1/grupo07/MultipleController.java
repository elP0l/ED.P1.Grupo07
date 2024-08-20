/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author vecto
 */
public class MultipleController implements Initializable {

    @FXML
    private Label opciones;
    @FXML
    private Button ok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> posiblesRespuestas = App.animalResultado;
        if (posiblesRespuestas != null && !posiblesRespuestas.isEmpty()) {
            StringBuilder mensaje = new StringBuilder("No estoy seguro, pero el animal podría ser:\n");
            for (String respuesta : posiblesRespuestas) {
                mensaje.append("- ").append(respuesta).append("\n");
            }
            opciones.setText(mensaje.toString().trim());
        } else {
            opciones.setText("No se pudo determinar el animal.");
        }
    }  

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("Portal");
    }
    
}
