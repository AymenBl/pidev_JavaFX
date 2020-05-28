/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import tn.esprit.PIDEV.services.JournalService;
import tn.esprit.PIDEV.services.lieuService;
import tn.esprit.PIDEV.services.saisonService;
import tn.esprit.PIDEV.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class StatistiqueController implements Initializable {

   
    @FXML
    private BarChart<String, Integer> barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                System.out.println("statistique");

        this.statistique();
    }    
    private Connection cnx ;
    
    
    private void statistique(){
        
        System.out.println("test");
     cnx = MyConnection.getInstance().getCnx();
     lieuService SL =new lieuService();
       //  LineChart < String, Integer>Linechart = null;
       String req = " SELECT idL , COUNT(idL) FROM `saison` GROUP BY saison.idL";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        Statement st ;
          try {
          st = cnx.createStatement();
          ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(SL.FindlieuById(rs.getInt(1)).getNom(), rs.getInt(2)));
              
            }
            barchart.getData().add(series);
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        
     

 

    }
    
}
