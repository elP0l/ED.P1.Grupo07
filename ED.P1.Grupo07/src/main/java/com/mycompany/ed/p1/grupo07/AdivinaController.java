/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed.p1.grupo07;

import Clases.Pregunta;
import TDA.BinaryTree;
import TDA.NodeBinaryTree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    BinaryTree<Pregunta> bTree = App.game.getDecisionTree();
    @FXML
    String key = "";

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
        
        NodeBinaryTree<Pregunta> node = bTree.getRoot();
        lblQuestion.setText(App.game.mostrarPregunta(node));
        
    }

    @FXML
    private void opIzq(ActionEvent event) throws IOException {
        key+="sí ";
        if(bTree.getRoot().getLeft()==null){
            System.out.println(key);
            switchToPrimary();
        }else{
            bTree = bTree.getRoot().getLeft();
            NodeBinaryTree<Pregunta> node = bTree.getRoot();
            lblQuestion.setText(App.game.mostrarPregunta(node));
        }
    }

    @FXML
    private void opDer(ActionEvent event) throws IOException {
        key+="no ";
        if(bTree.getRoot().getRight()==null){
            System.out.println(key);
            switchToPrimary();
        }else{
            bTree = bTree.getRoot().getRight();
            NodeBinaryTree<Pregunta> node = bTree.getRoot();
            lblQuestion.setText(App.game.mostrarPregunta(node));
        }
    }
    private void switchToPrimary() throws IOException {
        App.setRoot("Portal");
    }
    
    private void concluir() throws IOException {
        App.setRoot("Portal");
    }
    
}
