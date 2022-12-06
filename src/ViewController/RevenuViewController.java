/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.userdata.RevType;
import modele.userdata.Revenu;
import modele.userdata.Transaction;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class RevenuViewController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnValider;

    @FXML
    private ComboBox<String> srcChoice;

    @FXML
    private TextField txtMontant;
//     @FXML
//    private VBox window;
      
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        srcChoice.getItems().addAll(RevType.AFFAIRE.name(),RevType.LOYER.name(),RevType.SALAIRE.name(), RevType.AUTRE.name());
        
        
        
        
        
        
    }   
    // action on btn valider
    @FXML
    void valider(ActionEvent event) throws SQLException {
        
        String montant= txtMontant.getText();
        
        if(montant.equals("") || srcChoice.getValue().equals("Source du revenu")){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Veuillez svp remplir les champs !");
                alert.show();
        }
        else if (montant.matches(".*[a-zA-Z]+.*")){
            System.out.println("type invalide");
            Alert al = new Alert(AlertType.ERROR, "S'il vous plait entrez un numéro !");
            al.show();
            
        }
        else{
              ajouterRevenu();
              
              
        }
    }
    
    
    private void ajouterRevenu() throws SQLException{
              SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
              Date d = new Date();
            
             Revenu r = new Revenu(srcChoice.getValue(), date.format(d), Double.parseDouble(txtMontant.getText()));
             DBConnection.addRevenu(r);
             System.out.println("ajout avec succés");
             Stage window = (Stage)btnValider.getScene().getWindow();
             window.close();
            
        }
    
    // action on retour btn
    @FXML
    void retour(ActionEvent event) {
              Stage window = (Stage)btnBack.getScene().getWindow();
              window.close();
            
    }
    
    
    
    
    
    
    
}
