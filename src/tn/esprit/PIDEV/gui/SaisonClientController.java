/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
public class SaisonClientController implements Initializable {

    
    @FXML
    private TextField rechercher;
    
    annimalService Sa =new annimalService();
         lieuService SL =new lieuService();
         saisonService Ss =new saisonService();
    @FXML
    private TableView<saison> tableview;
     @FXML
    private TableColumn<saison, annimal>  animal;
    @FXML
    private TableColumn<saison, lieu>  lieu;
    @FXML
    private TableColumn<saison, String>  datedeb;
    @FXML
    private TableColumn<saison, String>  datefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        remplir();
    }    
/*
    @FXML
    private void rechercher(KeyEvent event) {
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

*/
      private void remplir(){
         List<annimal> L =  Sa.afficherAnimal();
            List <lieu> L1 = SL.afficherLieu();
            List<saison> L2= Ss.afficherSaison();
      
       ObservableList <saison> LO = FXCollections.observableArrayList(L2);      
       animal.setCellValueFactory(new PropertyValueFactory<>("idA"));
       lieu.setCellValueFactory(new PropertyValueFactory<>("idL"));
       datedeb.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
       datefin.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
       
       tableview.setItems(LO);
       
      
       
    }

        

    @FXML
    private void rechercher(ActionEvent event) {
         ObservableList data = tableview.getItems();
         rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
              tableview.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<saison> subentries = FXCollections.observableArrayList();
            long count = tableview.getColumns().stream().count();
            for (int i = 0; i < tableview.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tableview.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(tableview.getItems().get(i));
                        break;
                    }
                }
            }
            tableview.setItems(subentries);
        });
        
    }
    }
    

    

   

