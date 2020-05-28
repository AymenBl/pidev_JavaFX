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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ghada
 */
public class AcceuilController implements Initializable {

    private BorderPane content;
    private VBox idafficher;
    private AnchorPane rootPane;
    
    private static String view="";
     @FXML
    private BorderPane borderpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(view.equals("")){
       //  view = "animalClient"  ; 
        view="saisonClient";
            
        }
        loadUI(view);
    }    



    @FXML
    private void animal(MouseEvent event) {
       view="animalClient";
        System.out.println("-------------------------------------------------v1 : "+view);
        loadUI("animalClient");
    }
    @FXML
    private void lieu(MouseEvent event) {
        view="lieuClient";
         System.out.println("-------------------------------------------------v1 : "+view);
        loadUI("lieuClient");
    } 
    @FXML
    private void saison(MouseEvent event) {
        view="saisonClient";
         System.out.println("-------------------------------------------------v1 : "+view);
        loadUI("saisonClient");
    }
    
    
    
    private void loadUI(String ui)
    {
         System.out.println("-------------------------------------------------v1 : "+ui);
        Parent root = null;
        try {
            root =FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }

    @FXML
    private void lieu(ContextMenuEvent event) {
    }


   
}
