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

    private void ajouterBudget() throws SQLException{
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
        Date d = new Date();
        Budget b = new Budget(txtNameBudget.getText(), date.format(d), choixDuree.getValue());      
        DBConnection.addBudget(b);
        System.out.println("ajout avec succés");
    }
   
    @FXML
    void plus(ActionEvent event) {
      // String montant= txtMontant.getText();
        //String cat =choixCat.getValue();
    }


}
