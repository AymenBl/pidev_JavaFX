/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import tn.esprit.PIDEV.entities.lieu;
import tn.esprit.PIDEV.services.lieuService;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class AddlieuController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdesc;
    @FXML
    private Button abtntjout;
    @FXML
    private TableView<lieu> tablelieux;
     @FXML
    private TableColumn<lieu, String> desc;
       @FXML
    private TableColumn<lieu, String> nom;
       @FXML
    private TableColumn<lieu, ImageView> img;
    @FXML
    private Button btnsup;
    @FXML
    private Button btnmodif;

    /**
     * Initializes the controller class.
     */
    public static lieu lieuox;
    lieuService SL =new lieuService();
    @FXML
    private ImageView fximage;
    @FXML
    private Button btnstat;
    @FXML
    private Label verif_nom;
    @FXML
    private Label verif_desc;
    @FXML
    private Button btnimg;
  
    
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
     private void ajouterlieu(){
         lieu l =new lieu();
          if (((!txtnom.getText().isEmpty()))&&(!txtdesc.getText().isEmpty())&&(txtnom.getLength() > 2) && 
                 
            (txtnom.getText().matches("[0-9]*")==false)){
         
         
         l.setNom(txtnom.getText());
         l.setDescription_lieu(txtdesc.getText());
         l.setImage("photo/"+btnimg.getText());
         SL.ajouterLieu(l);
         txtnom.clear();
         txtdesc.clear();
         remplirtablieux();
         
         
     }}
    
    @FXML
   
    private void deleteAction(ActionEvent event){
       lieu l = tablelieux.getSelectionModel().getSelectedItem();
          Optional<ButtonType> option =this.showConfirmation(l.getNom());
        if(option.get() == ButtonType.OK){
        SL.suprimerlieu(l.getId());
        remplirtablieux();
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
      lieuox= tablelieux.getSelectionModel().getSelectedItem();
      try {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("updatelieu.fxml"));
            Scene sc =new Scene(root);
            stage.setScene(sc);
            stage.setTitle("Modifier lieu");
                        stage.show();
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }
      
    }
      
       @FXML
      private void statistique(ActionEvent event) {
      
      try {
          System.out.println("statistique");
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("statistique.fxml"));
            Scene sc =new Scene(root);
            stage.setScene(sc);
            stage.setTitle("statistique lieu");
         
            stage.show();
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }
      
    }
     @FXML 
    private void verifier_lieu_nom(KeyEvent event) {
       
        if (txtnom.getText().isEmpty()) {
            verif_nom.setText("champ vide!");
            verif_nom.setTextFill(Color.RED);
           verif_nom.setVisible(true);
        } else if ( txtnom.getText().matches("[1-9]*")) {
           verif_nom.setText("sasir un nom sans chiffres !");
           verif_nom.setTextFill(Color.RED);
          verif_nom.setVisible(true);

        } else {
           verif_nom.setText("Champ remplie!");
           verif_nom.setTextFill(Color.GREEN);
            verif_nom.setVisible(true);

        }
    }
    @FXML
    private void verifier_lieu_desc() {
       
        if (txtdesc.getText().isEmpty()) {
            verif_desc.setText("champ vide!");
            verif_desc.setTextFill(Color.RED);
          verif_desc.setVisible(true);
        
        } else {
           verif_desc.setText("Champ remplie!");
           verif_desc.setTextFill(Color.GREEN);
            verif_desc.setVisible(true);

        }
    }
     @FXML
    private void ajouterimage(ActionEvent event) {
         Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String path = "C:/xampp2/htdocs/PIDEV/web/photo";
        btnimg.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath()); 
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirtablieux();
        btnstat.setGraphic(fximage);
    }    

    
    
}
