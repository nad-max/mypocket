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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modele.userdata.Budget;
import modele.userdata.Categorie;
import modele.userdata.Depense;
import modele.userdata.SousCategorie;
import modele.userdata.Transaction;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class DepenseViewController implements Initializable {
    
    @FXML
    private JFXButton btnValider;
    
    @FXML
    private JFXButton btnBack;
    
    @FXML
    private JFXComboBox<Categorie> categorieBox;

    @FXML
    private JFXTextField montantField;

    @FXML
    private JFXComboBox<SousCategorie> sousCategBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        sousCategBox.setDisable(true);
        
        //Pour afficher les noms des categories correctement
        categorieBox.setConverter(new StringConverter<Categorie>() {
        @Override
        public String toString(Categorie cat) {
                return cat.getLibCat();
        }

        @Override
        public Categorie fromString(String string) {
            return null;
        }
        });
        
        sousCategBox.setConverter(new StringConverter<SousCategorie>() {
        @Override
        public String toString(SousCategorie cat) {
                return cat.getLibSousCat();
        }

        @Override
        public SousCategorie fromString(String string) {
            return null;
        }
        });
        
        //EventListener
        categorieBox.valueProperty().addListener((obs,oldVal,newVal)->{
            System.out.println(newVal.toString());
            sousCategBox.setDisable(false);
            sousCategBox.getItems().clear();
            sousCategBox.getItems().addAll(newVal.getSousCat());
        });
        
        categorieBox.getItems().addAll(DBConnection.listCategories);
        
        
        System.out.println("Depense initialized!");
        
         
    }    
    
     // action on btn valider
    @FXML
    void valider(ActionEvent event) throws SQLException, ParseException {
        
        String montant= montantField.getText();
        
        if(montant.equals("") || categorieBox.getValue() == null || sousCategBox.getValue() == null){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Veuillez svp remplir les champs !");
                alert.show();
        }
        else if (montant.matches(".*[a-zA-Z]+.*")){
            Alert al = new Alert(Alert.AlertType.ERROR, "S'il vous plait entrez un montant valide !");
            al.show();
            
        }
        else{
              ajouterDepense();
              
              
        }
    }
    
    private void ajouterDepense() throws SQLException, ParseException{
              SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
              Date d = new Date();
            
             Depense dep = new Depense(Double.parseDouble(montantField.getText()), d,categorieBox.getValue(), sousCategBox.getValue());
             ArrayList<Transaction> transactions = new ArrayList<>(DBConnection.getMontDate_transac());
             transactions.add(dep);
             List<Budget> budgetsDepasse = DBConnection.listBudgets.stream().filter(e->{
                  double montantReel = e.testDepassement(transactions);
                 //pour tester si c'est CETTE transaction qui cause le depassement, il faut s'assurer
                 //que la somme des ancienne transactions (avant cette depense) est inferieur au montant totalbudget
                 //autrement dit: que c'est le premier depassement
                 double montantAncienneTransacs;
                 if(e.getCategories().stream().anyMatch(cat-> cat.getLibCat().equals(dep.getCategorie().getLibCat())) ){
                     montantAncienneTransacs = montantReel + dep.getMontantTransac();
                     if(montantAncienneTransacs<0) montantAncienneTransacs=0;
                 }else{
                      montantAncienneTransacs = montantReel;
                 }
                 
                 System.out.println("nomBudget: "+e.getNomBudget()+"; Total: "+e.getMontantTot()+"montantAncienneTransacs: "+montantAncienneTransacs+"; montantReel: "+montantReel);
                 return (montantReel>e.getMontantTot() && montantAncienneTransacs<=e.getMontantTot());
                         }).collect(Collectors.toList());
             System.out.println("Nbr budgets depassés= "+budgetsDepasse.size());
             if(!budgetsDepasse.isEmpty()){
                 System.out.println("Avec cette transaction, vous risquez de depasser un budget!");
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                          alert.setTitle("Depassement du budget! ");
                          alert.setHeaderText("Avec cette transaction, vous risquez de depasser le budget:"+ budgetsDepasse.get(0).getNomBudget()
                          +"! Etes-vous sur de vouloir continuer?");

                             // option != null.
                            Optional<ButtonType> option = alert.showAndWait();

                                 if (option.get() == ButtonType.OK) {
                                 DBConnection.addDepense(dep);
                                 System.out.println("ajout depense (avec depassement avec succés");
                                 } 

             }else{
             System.out.println(dep.toString());
             DBConnection.addDepense(dep);
             System.out.println("ajout depense (sans depassement) avec succés");
             }
             
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
