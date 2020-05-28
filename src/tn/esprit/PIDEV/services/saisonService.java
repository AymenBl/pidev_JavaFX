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
import tn.esprit.PIDEV.entities.saison;
import tn.esprit.PIDEV.utils.MyConnection;

/**
 *
 * @author Aymen
 */
public class saisonService {
         private Connection cnx ;
  
  
  public saisonService(){
      cnx = MyConnection.getInstance().getCnx();
  }
  
  public void ajouterSaison(saison s){ 
      try {
          String req ="INSERT INTO `saison`(`date_debut`, `date_fin`, `idA`, `idL`, `rating`) VALUES ('"+s.getDate_debut()+"','"+s.getDate_fin()+"',"+s.getIdA().getId()+","+s.getIdL().getId()+","+s.getRating()+")";
          System.out.println(req);
          Statement st = cnx.createStatement();
          st.executeUpdate(req);
          System.out.println("ajoute saison  Reussie !");
      } catch (SQLException ex) {
          System.out.println(ex);
      }
      
  }
  
  public List<saison> afficherSaison(){
        List<saison> listS = new ArrayList<>();
          String req = "select * from saison ";
       Statement st ;
       
      try {
          st = cnx.createStatement();
          ResultSet r1 = st.executeQuery(req);
          saison S ;
          lieuService Sl =new  lieuService();
          annimalService Sa =new annimalService();
          while (r1.next()) {              
               S = new saison();
               S.setId(r1.getInt("id"));
               S.setDate_debut(r1.getDate(2).toString());
               S.setDate_fin(r1.getDate(3).toString());
               S.setIdA(Sa.FindAnnimalById(r1.getInt("idA")));
               S.setIdL(Sl.FindlieuById(r1.getInt("idL")));
                 //    System.out.println(S);  
               listS.add(S);
              }
          System.out.println(listS);
          
      } catch (SQLException ex) {
          Logger.getLogger(ex.getMessage());
      }
      return listS;
  }
  
  public void suprimerSaison(int id){
      
       String req = "DELETE FROM `saison` WHERE id = "+id;
       Statement st ;
          try {
          st = cnx.createStatement();
          st.executeUpdate(req);
           System.out.println("supprime saison  Reussie !");
          }catch(SQLException E){
              System.out.println(E.getMessage());
             
          }
      
  }
  
  public void updateSaison(saison L){
       String req = "UPDATE `saison` SET `date_debut`='"+L.getDate_debut()+"',`date_fin`='"+L.getDate_fin()+"',`idA`="+L.getIdA().getId()+",`idL`="+L.getIdL().getId()+",`rating`="+L.getRating()+" WHERE id="+L.getId();
      System.out.println(req);
       Statement st ;
          try {
          st = cnx.createStatement();
          st.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("Update  saison  Reussie !");
          }
      
  }

}
