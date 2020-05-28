/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnAnim;
    @FXML
    private Label label;
    @FXML
    private Button btnlieu;
    @FXML
    private Button btnsais;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void animalAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addAninmal.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lieuAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addlieu.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saisonAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addsaison.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
     private void acceuilAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
     private void acceuilActionClient(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
