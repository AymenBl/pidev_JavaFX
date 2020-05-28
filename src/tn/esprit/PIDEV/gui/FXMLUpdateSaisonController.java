/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.PIDEV.entities.annimal;
import tn.esprit.PIDEV.entities.lieu;
import tn.esprit.PIDEV.entities.saison;
import tn.esprit.PIDEV.services.annimalService;
import tn.esprit.PIDEV.services.lieuService;
import tn.esprit.PIDEV.services.saisonService;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class FXMLUpdateSaisonController implements Initializable {

    @FXML
    private Button btnSaison;
    @FXML
    private DatePicker DateBegi;
    @FXML
    private DatePicker DateEnd;
    @FXML
    private ComboBox<lieu> ComLiie;
    @FXML
    private ComboBox<annimal> Comanimal;
    
      annimalService Sa =new annimalService();
         lieuService SL =new lieuService();
         saisonService Ss =new saisonService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     remplir();
    }    

    @FXML
    private void updateSaison(ActionEvent event) {
        try {
            lieu L=   ComLiie.getSelectionModel().getSelectedItem();
            annimal a=   Comanimal.getSelectionModel().getSelectedItem();
            LocalDate  d1 = DateBegi.getValue();
            LocalDate  d2 = DateEnd.getValue();
            saison s =new saison();
            s.setDate_debut(d1.toString());
            s.setDate_fin(d2.toString());
            s.setIdA(a);
            s.setIdL(L);
            System.out.println("test "+s);
            s.setId(AddsaisonController.SAISONS_UP.getId());
            Ss.updateSaison(s);
            
            Stage stage=new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml"));
                    Scene sc =new Scene(root);
                    stage.setScene(sc);
                   // stage.setTitle("ajouter  lieu");
                    stage.show();
                    AcceuilAdminController.setView("addsaison");
                    System.out.println("up li "+AcceuilAdminController.getView());
            stage.show();
            Stage stage1 = (Stage) btnSaison.getScene().getWindow();
            stage1.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateSaisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void remplir(){
         List<annimal> L =  Sa.afficherAnimal();
            List <lieu> L1 =SL.afficherLieu();
            //    List<saison> L2= Ss.afficherSaison();
      
      
       
       ObservableList<lieu> list 
                = FXCollections.observableArrayList(L1);
        ComLiie.getItems().addAll(list);
        
        
       ObservableList<annimal> list1;
        list1 = FXCollections.observableArrayList(L);
        Comanimal.getItems().addAll(list1);
        
       ComLiie.getSelectionModel().select(AddsaisonController.SAISONS_UP.getIdL());
        Comanimal.getSelectionModel().select(AddsaisonController.SAISONS_UP.getIdA());
        
        DateBegi.setValue(LocalDate.parse(AddsaisonController.SAISONS_UP.getDate_debut()));
        DateEnd.setValue(LocalDate.parse(AddsaisonController.SAISONS_UP.getDate_fin()));
       
    }
}
