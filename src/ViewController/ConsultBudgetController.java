/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modele.userdata.Budget;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class ConsultBudgetController implements Initializable {
    @FXML
    private Label dureeBudget;

    @FXML
    private JFXComboBox<Budget> BudgetsBox;

    @FXML
    private Label montantBudget;

    
     @FXML
    private JFXButton btnFermer;

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         BudgetsBox.setConverter(new StringConverter<Budget>() {
        @Override
        public String toString(Budget budg) {
                return budg.getNomBudget();
        }

        @Override
        public Budget fromString(String string) {
            return null;
        }

        });
        try {
            BudgetsBox.getItems().addAll(DBConnection.chargerlistBudgets());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultBudgetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ConsultBudgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //EventListener
        BudgetsBox.valueProperty().addListener((obs,oldVal,newVal)->{
            System.out.println(newVal.toString());
            
            //nameBudget.setText(newVal.getNomBudget());
           
            if (newVal.getDuree() == 7 ){
                dureeBudget.setText("1 semaine");
            } 
            else if (newVal.getDuree() == 30 ){
               dureeBudget.setText("1 mois"); 
            }
            
            montantBudget.setText(""+newVal.getMontantTot()+" DT");
                
        });
    }
     @FXML
    void fermer(ActionEvent event) {
      Stage window = (Stage)btnFermer.getScene().getWindow();
              window.close();
    }    
    
}
