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
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.PIDEV.entities.annimal;
import tn.esprit.PIDEV.entities.user;
import tn.esprit.PIDEV.utils.MyConnection;

/**
 *
 * @author Aymen
 */
public class JournalService {
       private Connection cnx ;
   UserService us =new UserService();
  
  public JournalService(){
      cnx = MyConnection.getInstance().getCnx();
     
      
  }
  public List<user> getIdUserbyAnnimal(annimal A){ 
      List<user> listU = new ArrayList<>();
         String req = "SELECT `user_id` FROM `journal` WHERE  animal_id="+A.getId();
       Statement st ;
       
      try {
          st = cnx.createStatement();
          ResultSet r1 = st.executeQuery(req);
          while (r1.next()) {              
              user  u = new user();
              u = us.findUserbyId(r1.getInt(1));
              listU.add(u);
              }
          System.out.println(listU);
          System.out.println("get Id user by annimal  Reussie !");
      } catch (SQLException ex) {
          Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
      }
       return listU;
      
  }
 
  
}
