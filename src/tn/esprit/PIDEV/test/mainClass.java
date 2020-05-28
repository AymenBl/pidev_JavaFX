/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.test;


import tn.esprit.PIDEV.services.annimalService;
import tn.esprit.PIDEV.utils.MyConnection;
import tn.esprit.PIDEV.entities.annimal;
import tn.esprit.PIDEV.entities.lieu;
import tn.esprit.PIDEV.entities.saison;
import tn.esprit.PIDEV.services.saisonService;



/**
 *
 * @author DELL
 */
public class mainClass {
    
    public static void main(String[] args) {
        annimal A =new annimal();
        
        A.setDescription("test java");
        A.setNom_annimal("java");
        A.setImage("Image Java");
        A.setId(20);
        
        annimalService Sa =new annimalService();
        
      //  Sa.ajouterAnimal(A);
    //  Sa.afficherAnimal();
   //   Sa.suprimerAnimal(19);
      Sa.updateAnimal(17, A);
      
        saison S =new saison();
        S.setId(16);
        S.setDate_debut("2020-04-29");
        S.setDate_fin("2020-05-30");
        S.setIdA(A);
     //   S.setIdL(new lieu(6, "", "description_lieu"));
        
        saisonService  Ss =new saisonService();
     //   Ss.updateSaison(S);
     
        
    }
    
}
