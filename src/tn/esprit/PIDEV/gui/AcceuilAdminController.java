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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ghada
 */
public class AcceuilAdminController implements Initializable {

    @FXML
    private BorderPane borderpane;
    private AnchorPane rootPane;
    private static String view="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("v1 : "+view);
        if(view.equals("")){
         view = "addAninmal"  ; 
        }
     loadUI(view);
     
    }    
     private void loadUI(String ui)
    {
        System.out.println(ui);
        Parent root = null;
        try {
            root =FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        borderpane.setCenter(root);
    }

    @FXML
    private void animal() {
        view="addAninmal";
        loadUI("addAninmal");
    }
     @FXML
    private void lieu() {
        view="addlieu";
        loadUI("addlieu");
    } @FXML
    private void saison() {
        view="addsaison";
        loadUI("addsaison");
    }

    public static void setView(String view) {
        AcceuilAdminController.view = view;
    }

    public static String getView() {
        return view;
    }

    
}
