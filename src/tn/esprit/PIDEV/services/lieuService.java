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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.PIDEV.entities.lieu;
import tn.esprit.PIDEV.utils.MyConnection;

/**
 *
 * @author Aymen
 */
public class lieuService {
     private Connection cnx ;
     

  public lieuService(){
      cnx = MyConnection.getInstance().getCnx();
  }

  public void ajouterLieu(lieu L){
      try {
          String req ="INSERT INTO `lieu`(`description_lieu`, `nom`, `image`) VALUES ('"+L.getDescription_lieu()+"','"+L.getNom()+"','"+L.getImage()+"')";
          System.out.println(req);
          Statement st = cnx.createStatement();
          st.executeUpdate(req);
          System.out.println("ajout effectué avec succès !");
      } catch (SQLException ex) {
          System.out.println(ex);
      }

  }

  public List<lieu> afficherLieu(){
        List<lieu> listL = new ArrayList<>();
          String req = "select * from lieu ";
       Statement st ;

      try {
          st = cnx.createStatement();
          ResultSet r1 = st.executeQuery(req);
          lieu  L ;
          while (r1.next()) {
               L = new lieu();
               L.setId(r1.getInt("id"));
               L.setDescription_lieu(r1.getString(3));
               L.setNom(r1.getString(2));
                 L.setImage(r1.getString("image"));
               ImageView img = new ImageView(new Image("file:/C:/xampp2/htdocs/PIDEV/web/"+r1.getString("image"), 50, 50, true, true));
              // ImageView img1 = new ImageView("file:/C:/xampp2/htdocs/PIDEV/web/"+r1.getString("image"));
               L.setPhoto(img);
               

               listL.add(L);
              }
          System.out.println(listL);

      } catch (SQLException ex) {
          Logger.getLogger(ex.getMessage());
      }
      return listL;
  }

  public void suprimerlieu(int id){

       String req = "DELETE FROM `lieu` WHERE id = "+id;
       Statement st ;
          try {
          st = cnx.createStatement();
          st.executeUpdate(req);
           System.out.println("suppression effectuée avec succès !");
          }catch(SQLException E){
              System.out.println(E.getMessage());

          }

  }

  public void updateLieu(int id, lieu L){
       String req = "UPDATE `lieu` SET `nom`='"+L.getNom()+"',`description_lieu`='"+L.getDescription_lieu()+"',image='"+ L.getImage()+"'WHERE id="+id;
       Statement st ;
          try {
          st = cnx.createStatement();
          st.executeUpdate(req);
          }catch(SQLException E){
              System.out.println(E.getMessage());
               System.out.println("Modification effectué avec succès !");
          }

  }

   public lieu FindlieuById(int id){
         lieu L = new lieu();
       String req = "select * from lieu where id = "+id;
       Statement st ;
          try {
          st = cnx.createStatement();
          ResultSet r1 = st.executeQuery(req);
          while (r1.next()) {
               L.setId(r1.getInt("id"));
               L.setDescription_lieu(r1.getString(3));
               L.setNom(r1.getString(2));
          }
              System.out.println(L);
          }catch(SQLException e){
              System.out.println(e.getMessage());
          }
       return L;
     }


   public LineChart < String, Integer> statistique(){
       LineChart < String, Integer>Linechart = null;
       String req = " SELECT idL , COUNT(idL) FROM `saison` GROUP BY saison.idL";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        Statement st ;
          try {
          st = cnx.createStatement();
          ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(this.FindlieuById(rs.getInt(1)).getNom(), rs.getInt(2)));
            }
            Linechart.getData().add(series);
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
          return Linechart;
   }



}
