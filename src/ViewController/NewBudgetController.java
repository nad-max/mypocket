/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import modele.userdata.Budget;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class NewBudgetController implements Initializable {
    
    @FXML
    private JFXButton btnPlus;

    @FXML
    private JFXButton btnValider;

    @FXML
    private JFXComboBox<String> choixCat;

    @FXML
   private JFXComboBox<Integer> choixDuree;

    @FXML
    private JFXTextField txtMontant;

    @FXML
    private JFXTextField txtNameBudget;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choixCat.getItems().addAll("Transport","Supermarché", "Divertissement", "Domicile", "Santé et beauté", "Autres");
        choixDuree.getItems().addAll(7,30);
        //choixCat.getValue().equals("Transport")
//         EventHandler<ActionEvent> eventCat = (ActionEvent e) -> { 
//        if( choixCat.getValue().equals("Transport")){
//            choixSousCat.getItems().addAll("Taxi", "Transport publique");
//        } 
//        else if(choixCat.getValue().equals("Supermarché")){
//            choixSousCat.getItems().clear();
//            choixSousCat.getItems().addAll("Nourriture", "Produits Nettoyage", "Vetements", "Autres");
//        }
//        else if(choixCat.getValue().equals("Divertissement")){
//            choixSousCat.getItems().clear();
//            choixSousCat.getItems().addAll("Cinema", "Restaurant", "Livres", "Autres");
//        }
//        else if(choixCat.getValue().equals("Domicile")){
//            choixSousCat.getItems().clear();
//            choixSousCat.getItems().addAll("Electricité", "Loyer", "Internet", "Credit","Carburant", "Eau", "Autres");
//        }
//        else if(choixCat.getValue().equals("Santé et beauté")){
//            choixSousCat.getItems().clear();
//            choixSousCat.getItems().addAll("Médicaments", "Produits cosmétiques", "Consultation", "Autres");
//        }
//        
//         
//         };
        //choixCat.setOnAction(eventCat);
        
        
    }    
     @FXML
    void valider(ActionEvent event) throws SQLException {
         
         if( txtNameBudget.getText().equals("" ) || choixDuree.getValue().equals(0) ){
              Alert al = new Alert(Alert.AlertType.WARNING, "S'il vous plait veuillez remplir les champs !");
              al.show();
         }
         else{
             ajouterBudget();
         }
        

    }

    private void ajouterBudget() throws SQLException{
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
        Date d = new Date();
        Budget b = new Budget(txtNameBudget.getText(), date.format(d), choixDuree.getValue());      
        DBConnection.addBudget(b);
        System.out.println("ajout avec succés");
    }
   
    @FXML
    void plus(ActionEvent event) {
       String montant= txtMontant.getText();
        String cat =choixCat.getValue();
    }


}
