/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author vecto
 */
public class RespuestaController implements Initializable {

    @FXML
    private AnchorPane fond;
    @FXML
    private Button correct;
    @FXML
    private Button incorrect;
    @FXML
    private Label resp;
    @FXML
    private ImageView image;
    private String resultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String nombreAnimal = App.anima;
        if (nombreAnimal != null) {
            resp.setText(nombreAnimal);
        } else {
            resp.setText("No se pudo determinar el animal.");
        }
    
    }    
    
     public void mostrarNombreAnimal(String nombreAnimal) {
        resp.setText(nombreAnimal);
    }
}

