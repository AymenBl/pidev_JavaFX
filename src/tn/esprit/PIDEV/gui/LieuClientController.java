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
import tn.esprit.PIDEV.entities.lieu;
import tn.esprit.PIDEV.services.lieuService;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class LieuClientController implements Initializable {

    @FXML
    private TableView<lieu> tablelieux;
    @FXML
    private TableColumn<lieu,String> nom;
    @FXML
    private TableColumn<lieu,String> desc;
    @FXML
    private TextField rechercher;
    @FXML
    private TableColumn<lieu, ImageView> img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.remplirtablieux();
    }    
      lieuService SL =new lieuService();
     private void remplirtablieux(){
        List <lieu> L =SL.afficherLieu();
        ObservableList <lieu> Lox;
        Lox = FXCollections.observableArrayList(L);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       desc.setCellValueFactory(new PropertyValueFactory<>("description_lieu"));
       img.setCellValueFactory(new PropertyValueFactory<>("photo"));
       
       tablelieux.setItems(Lox);
        }

    @FXML
    private void rechercher(ActionEvent event) {
       
         ObservableList data = tablelieux.getItems();
         rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
              tablelieux.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<lieu> subentries = FXCollections.observableArrayList();
            long count = tablelieux.getColumns().stream().count();
            for (int i = 0; i < tablelieux.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tablelieux.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(tablelieux.getItems().get(i));
                        break;
                    }
                }
            }
            tablelieux.setItems(subentries);
        });
        
    }
    }
    

