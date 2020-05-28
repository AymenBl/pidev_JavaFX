/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.PIDEV.entities.lieu;
import tn.esprit.PIDEV.services.lieuService;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class UpdatelieuController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdesc;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      lieu a =AddlieuController.lieuox;
      txtnom.setText(a.getNom());
      txtdesc.setText(a.getDescription_lieu());
      btnimg.setText(a.getImage());
      System.out.println("test : " +a.getImage().toString());
    }    
   
    
    lieuService SL=new lieuService();
    @FXML
    private void modifierlieu() {
      
            Optional<ButtonType> option =this.showConfirmation();
            
            if(option.get() == ButtonType.OK ){
                
                try {
                    lieu a =new lieu();
                    a.setNom(txtnom.getText());
                    a.setDescription_lieu(txtdesc.getText());
                    a.setImage("photo/"+btnimg.getText());
                   
                    
                    SL.updateLieu(AddlieuController.lieuox.getId(), a);
                     System.out.println("test : " +a.getImage().toString());
                       System.out.println(a);
                    Stage stage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml"));
                    Scene sc =new Scene(root);
                    stage.setScene(sc);
                   // stage.setTitle("ajouter  lieu");
                    stage.show();
                    AcceuilAdminController.setView("addlieu");
                    System.out.println("up li "+AcceuilAdminController.getView());
                    Stage stage1 = (Stage) btnmodif.getScene().getWindow();
                    stage1.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            
            }
    
    
}

   private Optional<ButtonType> showConfirmation() {
 
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Modifier");
      alert.setHeaderText("Voulez-vous vraiment mettre à jour ce lieu?");
      alert.setContentText(AddlieuController.lieuox.getNom());

          Optional<ButtonType> option = alert.showAndWait();
       return option; 
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
}