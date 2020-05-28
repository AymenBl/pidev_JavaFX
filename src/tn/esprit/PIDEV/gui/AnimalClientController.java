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
import javafx.scene.image.ImageView;
import tn.esprit.PIDEV.entities.annimal;
import tn.esprit.PIDEV.entities.saison;
import tn.esprit.PIDEV.services.annimalService;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class AnimalClientController implements Initializable {

   annimalService sa =new annimalService();
    @FXML
    private TableColumn<annimal, ImageView> img;
     @FXML
    private TableView<annimal> tableview;
     @FXML
    private TableColumn<annimal, String> desc;
    @FXML
    private TableColumn<annimal, String> nom;
    @FXML
    private TextField rechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        repmlireTableV();
    }    
    
      private void repmlireTableV(){
   
         List<annimal> L =  sa.afficherAnimal();
         System.out.println(L);
         ObservableList <annimal> L1 = FXCollections.observableArrayList(L);      
       nom.setCellValueFactory(new PropertyValueFactory<>("nom_annimal"));
       desc.setCellValueFactory(new PropertyValueFactory<>("description"));
     
        img.setCellValueFactory(new PropertyValueFactory<>("photo"));
       
       tableview.setItems(L1);
       
     }

    @FXML
    private void rechercher(ActionEvent event) {
         ObservableList data =  tableview.getItems();
         rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
               tableview.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<annimal> subentries = FXCollections.observableArrayList();
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
      
    

