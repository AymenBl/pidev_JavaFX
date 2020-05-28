 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.PIDEV.entities.annimal;
import tn.esprit.PIDEV.utils.MyConnection;

/**
 *
 * @author Aymen
 */
public class annimalService {
private Connection cnx ;
  
  
  public annimalService(){
      cnx = MyConnection.getInstance().getCnx();
  }
  
  public void ajouterAnimal(annimal A){ 
      try {
          String req ="INSERT INTO `annimal`(`description`, `nom_annimal`, `image`) VALUES ('"+A.getDescription()+"','"+A.getNom_annimal()+"','"+A.getImage()+"')";
          System.out.println(req);
          Statement st = cnx.createStatement();
          st.executeUpdate(req);
          System.out.println("Animal ajouté !");
      } catch (SQLException ex) {
          System.out.println(ex);
      }
      
  }
  
  public List<annimal> afficherAnimal(){
        List<annimal> listA = new ArrayList<>();
          String req = "select * from annimal ";
       Statement st ;
       
      try {
          st = cnx.createStatement();
          ResultSet r1 = st.executeQuery(req);
          annimal  A ;
          while (r1.next()) {              
               A = new annimal();
               A.setId(r1.getInt("id"));
               A.setDescription(r1.getString(2));
               A.setNom_annimal(r1.getString(3));
               A.setImage(r1.getString("image"));
               ImageView img = new ImageView(new Image("file:/C:/xampp2/htdocs/PIDEV/web/"+r1.getString("image"), 50, 50, true, true));
              // ImageView img1 = new ImageView("file:/C:/xampp2/htdocs/PIDEV/web/"+r1.getString("image"));
               A.setPhoto(img);
               listA.add(A);
              }
          System.out.println(listA);
          
      } catch (SQLException ex) {
          Logger.getLogger(ex.getMessage());
      }
      return listA;
  }
  
  public void suprimerAnimal(int id){
      
       String req = "DELETE FROM `annimal` WHERE id = "+id;
       Statement st ;
          try {
          st = cnx.createStatement();
          st.executeUpdate(req);
           System.out.println("suppression effectué avec succès !");
          }catch(SQLException E){
              System.out.println(E.getMessage());
             
          }
      
  }
  
  public void updateAnimal(int id, annimal a){
       String req = "UPDATE `annimal` SET `description`='"+a.getDescription()+"',`nom_annimal`='"+a.getNom_annimal()+"',`image`='"+a.getImage()+"' WHERE id="+id;
       Statement st ;
          try {
          st = cnx.createStatement();
          st.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("Update Annimal  Reussie !");
          }
      
  }
   public annimal FindAnnimalById(int id){
         annimal A = new annimal();
       String req = "select * from annimal where id = "+id;
       Statement st ;
          try {
          st = cnx.createStatement();
          ResultSet r1 = st.executeQuery(req);
          while (r1.next()) {    
             A.setId(r1.getInt("id"));
               A.setDescription(r1.getString(2));
               A.setNom_annimal(r1.getString(3));
               A.setImage(r1.getString("image"));
          }
              System.out.println(A);
          }catch(SQLException e){
              System.out.println(e.getMessage());
          }
       return A;
     }

  
  
}
