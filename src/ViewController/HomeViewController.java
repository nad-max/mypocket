/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.userdata.Revenu;
import modele.userdata.Transaction;

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
    
    @FXML
    private TableColumn<Transaction, Date> dateCol;

    @FXML
    private TableColumn<Transaction, Double> montantCol;
    
    @FXML
    private TableColumn<Transaction,String> colType; 
    
    @FXML
    private TableView<Transaction> transacTab;
    
    @FXML
    private Hyperlink logoutBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         choixTransac.getItems().addAll("Revenu","Dépense");
    
          //afficher les transactions dans tableView
        try {
            ObservableList<Transaction> list = DBConnection.getMontDate_transac();
            montantCol.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("montantTransac"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,Date>("dateTransac"));
            //colType.setCellValueFactory(new PropertyValueFactory<Transaction,String>("type"));
            transacTab.setItems(list);
            //same for depense
                    
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // comboBox
         EventHandler<ActionEvent> eventTransac = (ActionEvent e) -> {    
             if (choixTransac.getValue().equals("Revenu")) {
                 try {
                     //TODO:Ouvrir l'interface Revenu View
                      Stage primaryStage = new Stage();
                      Parent root = FXMLLoader.load(getClass().getResource("RevenuView.fxml"));
                      Scene scene = new Scene(root);
                      primaryStage.setTitle("Revenu");
                      primaryStage.setScene(scene);
                      primaryStage.show();
                      
                     //new HomeViewController.RevenuPage().start((Stage)choixTransac.getScene().getWindow());
                 } catch (IOException ex) {
                     Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
             }else if(choixTransac.getValue().equals("Dépense")){
                 //TODO:Ouvrir l'interface Depense View
                 try {
                      Stage primaryStage = new Stage();
                      Parent root = FXMLLoader.load(getClass().getResource("DepenseView.fxml"));
                      Scene scene = new Scene(root);
                      primaryStage.setTitle("Depense");
                      primaryStage.setScene(scene);
                      primaryStage.show();
                 } catch (IOException ex) {
                     Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             System.out.println(choixTransac.getValue() + " selected");
         };
          //affecter le event
         choixTransac.setOnAction(eventTransac);
                
         //TODO: faire de meme pour choixBudg (eventHandler et setOnAction)
         choixBudg.getItems().addAll("Génerer un budget","Créer manuellement");
         
         
         // top Hbox
         //date 
         SimpleDateFormat date = new SimpleDateFormat("E dd MMM yyyy");
          Date d = new Date();
         txtDate.setText(date.format(d));
         // user name
         txtUser.setText("Bonjour, "+DBConnection.user.getNom());
         //solde
         txtSolde.setText(""+DBConnection.user.getSolde()+" TND");
         System.out.println(DBConnection.user.getSolde());
         
        
    }
    //click sur table des transactions
    @FXML
    void Rafraichir(MouseEvent event) throws ParseException{
        try {
            //afficher les transactions
            ObservableList<Transaction> list = DBConnection.getMontDate_transac();
            montantCol.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("montantTransac"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,Date>("dateTransac"));
            transacTab.setItems(list); 
            //set solde
            txtSolde.setText(""+DBConnection.user.getSolde()+" TND");
            //System.out.println("home wiew test "+DBConnection.user.getSolde());
            
            //Raffraichir le combobox des transac
            choixTransac.setValue("Ajouter une transaction");
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    // click sur deconnexion
     @FXML
    void logout(ActionEvent event) throws IOException {
          new HomeViewController.LoginPage().start((Stage)logoutBtn.getScene().getWindow());
    }
    
     private static class LoginPage extends Application {

        @Override
        public void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }

    }
    
}


