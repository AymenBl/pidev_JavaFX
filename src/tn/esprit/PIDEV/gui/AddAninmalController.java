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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.PIDEV.entities.annimal;
import tn.esprit.PIDEV.services.annimalService;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class AddAninmalController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdescription;
    @FXML
    private Button btnajoutanim;
    @FXML
    private TableView<annimal> tableview;
     @FXML
    private TableColumn<annimal, String> desc;
    @FXML
    private TableColumn<annimal, String> nom;
    @FXML
    private Button btnsup;
    @FXML
    private Button btnmodif;
   
    
    annimalService sa =new annimalService();
    public static annimal annimal_Mod;
    @FXML
    private TableColumn<annimal, ImageView> img;
    @FXML
        private Button btnimage;
    @FXML
    private Label verif_nom;
    @FXML
    private Label verif_desc;
    
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
     private void ajouterAnimal(){
         annimal a =new annimal();
         if (((!txtnom.getText().isEmpty()))&&(!txtdescription.getText().isEmpty())&&(txtnom.getLength() > 2) && 
                 
           imageUp    && (txtnom.getText().matches("[0-9]*")==false)){
         a.setNom_annimal(txtnom.getText());
         a.setDescription(txtdescription.getText());
         a.setImage("photo/"+btnimage.getText());
         sa.ajouterAnimal(a);
         txtnom.clear();
         txtdescription.clear();
       
         btnimage.setText("ajouter image");
         this.repmlireTableV();
         
         
          
                
                verif_desc.setVisible(false);
                verif_nom.setVisible(false);
               
         }else{
            System.out.println("ERRER!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Remplir les champs correctement   !");
            alert.showAndWait();
            
   
         }   
         
     }
     @FXML
    private void deleteAction(ActionEvent event){
          if(tableview.getSelectionModel().isEmpty()){
         this.showAlert();
       }else{
        annimal a = tableview.getSelectionModel().getSelectedItem();
          Optional<ButtonType> option =this.showConfirmation(a.getNom_annimal());
        if(option.get() == ButtonType.OK){
        sa.suprimerAnimal(a.getId());
        this.repmlireTableV();
        }
          }
        
    } 
      private Optional<ButtonType> showConfirmation(String Name) {
 
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("supprimers");
      alert.setHeaderText("Voulez-vous vraiment supprimer cet animal?");
      alert.setContentText(Name);

         Optional<ButtonType> option = alert.showAndWait();
       return option; 
     
      }
       @FXML
    private void verifier_nomanimal(KeyEvent event) {
      
        if (txtnom.getText().isEmpty()) {
            verif_nom.setText("champ vide!");
            verif_nom.setTextFill(javafx.scene.paint.Color.RED);
            verif_nom.setVisible(true);
        } else if (txtnom.getText().matches("[1-9]*")) {
            verif_nom.setText("sasir un nom sans chiffres !");
            verif_nom.setTextFill(Color.RED);
           verif_nom.setVisible(true);

        } else if (txtnom.getText().length() < 2) {
            verif_nom.setText("Nom d'animal incorect nom < 3 !");
            verif_nom.setTextFill(Color.RED);
           verif_nom.setVisible(true);} 
        else {
            verif_nom.setText("Champ remplie!");
           verif_nom.setTextFill(Color.GREEN);
            verif_nom.setVisible(true);

        }
    }
    
    
    
    @FXML
    private void verifier_desanimal(KeyEvent event) {
       
        if (txtdescription.getText().isEmpty()) {
            verif_desc.setText("champ vide!");
            verif_desc.setTextFill(Color.RED);
            verif_desc.setVisible(true);
        } else if ( txtdescription.getText().matches("[1-9]*")) {
           verif_desc.setText("sasir un nom sans chiffres !");
            verif_desc.setTextFill(Color.RED);
           verif_desc.setVisible(true);

        } else {
            verif_desc.setText("Champ remplie!");
           verif_desc.setTextFill(Color.GREEN);
            verif_desc.setVisible(true);

        }
    }
     
    private Optional<ButtonType> showAlert() {
 
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Alert ");
      alert.setHeaderText("veuillez seleclioner un animal ");
      
     // alert.setContentText(Name);

         Optional<ButtonType> option = alert.showAndWait();
       return option; 
   }
      
      @FXML
    private void updateAction(ActionEvent event) {
          if(tableview.getSelectionModel().isEmpty()){
         this.showAlert();
       }else{
      annimal_Mod= tableview.getSelectionModel().getSelectedItem();
      try {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLUpdeteAnnimal.fxml"));
            Scene sc =new Scene(root);
            stage.setScene(sc);
            stage.setTitle("Modifier animal");
            Stage stage1 = (Stage) btnmodif.getScene().getWindow();
          //  stage1.hide();
            stage.show();
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }
          }
      
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.repmlireTableV();
    }
   boolean imageUp=false;    

    @FXML
    private void ajouterimage(ActionEvent event) {
         Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String path = "C:/xampp2/htdocs/PIDEV/web/photo";
        btnimage.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath()); 
                imageUp=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }
    

