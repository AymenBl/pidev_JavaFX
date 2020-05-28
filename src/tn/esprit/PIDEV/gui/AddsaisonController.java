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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.PIDEV.entities.SendMail;
import tn.esprit.PIDEV.entities.annimal;
import tn.esprit.PIDEV.entities.lieu;
import tn.esprit.PIDEV.entities.saison;
import tn.esprit.PIDEV.entities.user;
import tn.esprit.PIDEV.services.JournalService;
import tn.esprit.PIDEV.services.annimalService;
import tn.esprit.PIDEV.services.lieuService;
import tn.esprit.PIDEV.services.saisonService;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class AddsaisonController implements Initializable {

    @FXML
    private DatePicker DateEnd;
    @FXML
    private Button btnSaison;
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
    @FXML
    private Button btnsup;
    @FXML
    private Button btnmodif;
    @FXML
    private DatePicker DateBegi;
    @FXML
    private ComboBox<lieu> ComLiie;
    @FXML
    private ComboBox<annimal> Comanimal;
    
    public static saison SAISONS_UP;

    /**
     * Initializes the controller class.
     */
    
    
        annimalService Sa =new annimalService();
         lieuService SL =new lieuService();
         saisonService Ss =new saisonService();
    @FXML
    private TextField rechercher;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.remplir();
    }    
    JournalService Js =new JournalService();

    @FXML
    private void ajouterSaison(ActionEvent event) {
     lieu L=   ComLiie.getSelectionModel().getSelectedItem();
     annimal a=   Comanimal.getSelectionModel().getSelectedItem();
      LocalDate  d1 = DateBegi.getValue();
       LocalDate  d2 = DateEnd.getValue();
       saison s =new saison();
       s.setDate_debut(d1.toString());
       s.setDate_fin(d2.toString());
       s.setIdA(a);
       s.setIdL(L);
        System.out.println(s);
        Ss.ajouterSaison(s);
        List<user> li = Js.getIdUserbyAnnimal(a);
        
        for (int i = 0; i < li.size(); i++) {
            user get = li.get(i);
             String email=get.getEmail() ;
             String text ="Mr " +get.getUsername() + " , "
                     + "une nouvelle saison de chasse de "+a.getNom_annimal()+" est ouverte ";
             SendMail.sendMail(email ,"Alerte ouverture d'une nouvelle saison de chasse", text);
            
        }
        
       
        remplir();
        
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        saison s = tableview.getSelectionModel().getSelectedItem();
          Optional<ButtonType> option =this.showConfirmation(s.getIdA().getNom_annimal() +" "+s.getIdL().getNom());
        if(option.get() == ButtonType.OK){
        Ss.suprimerSaison(s.getId());
        remplir();
    }
    }
         private Optional<ButtonType> showConfirmation(String Name) {
 
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("supprimer");
      alert.setHeaderText("Voulez-vous vraiment supprimer ce lieu?");
      alert.setContentText(Name);

         Optional<ButtonType> option = alert.showAndWait();
       return option; 
     
      }
    @FXML
    private void updateAction(ActionEvent event) {
        SAISONS_UP= tableview.getSelectionModel().getSelectedItem();
         try {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLUpdateSaison.fxml"));
            Scene sc =new Scene(root);
            stage.setScene(sc);
            stage.setTitle("Modifier Saison");
            stage.show();
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }
         
    }
    
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
       
       ObservableList<lieu> list 
                = FXCollections.observableArrayList(L1);
        ComLiie.getItems().addAll(list);
        
        
       ObservableList<annimal> list1;
        list1 = FXCollections.observableArrayList(L);
        Comanimal.getItems().addAll(list1);
        
       ComLiie.getSelectionModel().select(0);
        Comanimal.getSelectionModel().select(0);
       
    }

    @FXML
    private void rechercher(ActionEvent event) {
          ObservableList data =  tableview.getItems();
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
