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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modele.userdata.Budget;
import modele.userdata.Transaction;

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
    private Label depassText;
    
     @FXML
    private JFXButton btnFermer;
     
    @FXML
    private ProgressBar progBar;
    
    @FXML
    private Label dateText;

    

    
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
            
            dateText.setText(newVal.getDateCreation().toString());
            
            montantBudget.setText(""+newVal.getMontantTot()+" DT");
             try {
                 double depassement = newVal.testDepassement(new ArrayList<Transaction>(DBConnection.getMontDate_transac()));
                 if(depassement == -1){
                     depassText.setText("Budget obsolete!");
                     progBar.setProgress(0);
                 }else if(depassement>newVal.getMontantTot() && depassement>0){
                     depassText.setText("Oui");
                     progBar.setProgress(1);
                 }else{
                     depassText.setText("Non");
                     progBar.setProgress(depassement/newVal.getMontantTot());
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(ConsultBudgetController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ParseException ex) {
                 Logger.getLogger(ConsultBudgetController.class.getName()).log(Level.SEVERE, null, ex);
             }
                
        });
    }
     @FXML
    void fermer(ActionEvent event) {
      Stage window = (Stage)btnFermer.getScene().getWindow();
              window.close();
    }    
    
}
