/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class FXMLsaisonaa implements Initializable {

    
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button btnajoutsaison;
    @FXML
    private TableView<saison> tablesaison;
    @FXML
    private TableColumn<saison, annimal> colanimal;
    @FXML
    private TableColumn<saison, lieu> collieu;
    @FXML
    private TableColumn<saison, String> coldatedebut;
    @FXML
    private TableColumn<saison, String> coldatefin;
    @FXML
    private Button btnsuprimer;
    @FXML
    private Button btnmodif;
    
     @FXML
    private ComboBox<lieu> comlieu;
    @FXML
    private ComboBox<annimal> comanimal;
    
    
    annimalService animal_ser=new annimalService();
    lieuService    lieu_ser=new lieuService();
    saisonService  saison_ser=new saisonService();
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         }
    private void remplirSaison(){
        
        
        List<annimal> LA=animal_ser.afficherAnimal();
        List<lieu> LL=lieu_ser.afficherLieu();
        List<saison> lS=saison_ser.afficherSaison();
        
        //list animal
        
        ObservableList <annimal>list=FXCollections.observableArrayList(LA);
        comanimal.getItems().addAll(list);
        
        
        ObservableList <lieu>list2=FXCollections.observableArrayList(LL);
        comlieu.getItems().addAll(list2);
        
        // TODO
    }    
    
}
