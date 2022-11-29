/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class HomeViewController implements Initializable {

    @FXML
    private ComboBox<String> choixTransac;
    
    @FXML
    private ComboBox<String> choixBudg;
    
    @FXML
    private Label txtDate;

    @FXML
    private Label txtSolde;

    @FXML
    private Label txtUser;
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         choixTransac.getItems().addAll("Revenu","Dépense");
         
         //Event handler pour la redirection vers les pages des transactions
         EventHandler<ActionEvent> eventTransac = new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e){
                 if (choixTransac.getValue().equals("Revenu")) {
                    //TODO:Ouvrir l'interface Revenu View
                 }else if(choixTransac.getValue().equals("Dépense")){
                     //TODO:Ouvrir l'interface Depense View
                 }
                 System.out.println(choixTransac.getValue() + " selected");}
         };
         //affecter le event
         choixTransac.setOnAction(eventTransac);
         
         //TODO: faire de meme pour choixBudg (eventHandler et setOnAction
         choixBudg.getItems().addAll("Génerer un budget","Créer manuellement");
         
         
         
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
