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
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import modele.userdata.Budget;
import modele.userdata.Categorie;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class NewBudgetController implements Initializable {
    @FXML
    private VBox vBoxBas;

    @FXML
    private VBox vBoxHaut;

    
    @FXML
    private JFXButton btnPlus;

    @FXML
    private JFXButton btnValider;

    @FXML
    private JFXComboBox<Categorie> choixCat;

    @FXML
   private JFXComboBox<Integer> choixDuree;

    @FXML
    private JFXTextField txtMontant;

    @FXML
    private JFXTextField txtNameBudget;
    
    private Budget b;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Pour afficher les noms des categories correctement
        choixCat.setConverter(new StringConverter<Categorie>() {
        @Override
        public String toString(Categorie cat) {
                return cat.getLibCat();
        }

        @Override
        public Categorie fromString(String string) {
            return null;
        }
        });
        
        choixDuree.setConverter(new StringConverter<Integer>() {
        @Override
        public String toString(Integer i) {
            if (i==null) return "Durée en semaine";
            if (i.equals(7)) return "1 semaine";
            else if(i.equals(30)) return "1 mois"; 
            return "";
        }

            @Override
            public Integer fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
        choixCat.getItems().addAll(DBConnection.listCategories); 
        choixDuree.getItems().addAll(7,30);
        
    }    
     @FXML
    void valider(ActionEvent event) throws SQLException {
         
         if( txtNameBudget.getText().equals("" ) || choixDuree.getValue() == null ){
              Alert al = new Alert(Alert.AlertType.WARNING, "S'il vous plait veuillez remplir les champs !");
              al.show();
         }
         else{
             ajouterBudget();
         }
        

    }
    
     @FXML
    void ajouterCatBtn(ActionEvent event) throws SQLException{
        String montant= txtMontant.getText();
        if( montant.equals("") || montant.matches(".*[a-zA-Z]+.*") || choixCat.getValue() == null ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Veuillez verifier les champs !");
                alert.show();
        }else{
            ajouterCat();
            Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
                alertSucc.setTitle("Message");
                alertSucc.setHeaderText("Succés!");
                alertSucc.setContentText("Categorie"+choixCat.getValue().getLibCat()+"ajoutée avec succés");
                alertSucc.show();
           choixCat.setValue(null);
           txtMontant.clear();
        }
    }

    private void ajouterCat() throws SQLException{
        b.setMontantTot(b.getMontantTot()+Double.parseDouble(txtMontant.getText()));
        b.ajouterCat(choixCat.getValue());
        DBConnection.addCatBudg(b);
        System.out.println("Categorie ajoutée avec succès!");
        DBConnection.user.ajouterBudget(b);
    }
    
    private void ajouterBudget() throws SQLException{
        Date d = new Date();
        b = new Budget(txtNameBudget.getText(), d, choixDuree.getValue());      
        DBConnection.addBudget(b);
        System.out.println("ajout avec succés");
        vBoxHaut.setDisable(true);
        vBoxBas.setDisable(false);
        
        
    }
   


}
