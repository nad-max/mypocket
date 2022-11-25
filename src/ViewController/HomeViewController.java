/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class HomeViewController implements Initializable {

    @FXML
    private ChoiceBox<String> choixTran;
     @FXML
    private Label txtDate;

    @FXML
    private Label txtSolde;

    @FXML
    private Label txtUser;
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         choixTran.getItems().addAll("Revenu","Dépense");
         SimpleDateFormat date = new SimpleDateFormat("E dd MMM yyyy");
          Date d = new Date();
         txtDate.setText(date.format(d));
         
         txtUser.setText("Bonjour, "+DBConnection.user.getNom());
       
         txtSolde.setText(""+DBConnection.user.getSolde()+" TND");
         System.out.println(DBConnection.user.getNom());
         
         System.out.println(txtUser.getText()+"!!!");
    }
    
    
    /**
     * Initializes the controller class.
     */
    
    
}
