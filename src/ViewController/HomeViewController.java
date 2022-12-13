/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modele.userdata.Budget;
import modele.userdata.Categorie;
import modele.userdata.Depense;
import modele.userdata.Revenu;
import modele.userdata.Transaction;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class HomeViewController implements Initializable {

    /*@FXML
    private ComboBox<String> choixTransac;*/
    
    /*@FXML
    private ComboBox<String> choixBudg;*/
    
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
    private TableView<Transaction> transacTab;
    
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private VBox RhBox;
     @FXML
    private Pane pane;
     @FXML
    private PieChart pieChart;
 
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Charger la liste des categories et les budgets
        DBConnection.chargerlistCategories();
        try {
            DBConnection.chargerlistBudgets();
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         //choixTransac.getItems().addAll("Revenu","Dépense");
         
          //afficher les transactions dans tableView
        try {
            ObservableList<Transaction> list = DBConnection.getMontDate_transac();
            montantCol.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("montantTransac"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,Date>("dateTransac"));
            //colType.setCellValueFactory(new PropertyValueFactory<Transaction,String>("type"));
            transacTab.setItems(list);
            
                    
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
         
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
        
        try {
            //pie chart
            DBConnection.getCategoriesDetails();
           
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
         ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
         
         //adding data 
         for (int j = 0; j < DBConnection.categoryNames.size(); j++) {
	   pieChartData.add(new PieChart.Data(DBConnection.categoryNames.get(j), DBConnection.categoryCount.get(j)));
	  pieChartData.get(j).toString();
           System.out.println( pieChartData.get(j).toString());}
          PieChart pieChart = new PieChart(pieChartData);
          pieChart.setTitle("Dépenses selon catégories");
          
          pieChart.setPrefHeight(200);

          
          pieChart.setPrefWidth(200);

	  pieChart.setPrefSize(200, 200);
	  pieChart.setMinSize(420, 300);
	  pieChart.setMaxSize(420, 300);

          // setting the direction to arrange the data
           pieChart.setClockwise(true);

          // Setting the length of the label line
          pieChart.setLabelLineLength(20);

	 //Setting the labels of the pie chart visible
	pieChart.setLabelsVisible(true);

	// Setting the start angle of the pie chart
	pieChart.setStartAngle(180);
	pieChart.setAnimated(true);
	pieChart.animatedProperty();
      
        pane.getChildren().add(pieChart);
       
        System.out.println("Category chart is generated ");
    }

    void Rafraichir() throws ParseException{
        try {
            //afficher les transactions
            ObservableList<Transaction> list = DBConnection.getMontDate_transac();
            montantCol.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("montantTransac"));
            dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,Date>("dateTransac"));
            transacTab.setItems(list); 
            //set solde
            txtSolde.setText(""+DBConnection.user.getSolde()+" TND");
            
            
            //Raffraichir le combobox des transac
            //choixTransac.setValue("Ajouter une transaction");
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    // click sur deconnexion
     @FXML
    void logout(ActionEvent event) throws IOException {
          new HomeViewController.LoginPage().start((Stage)logoutBtn.getScene().getWindow());
    }
    
    @FXML
    void ajouterDep(ActionEvent event){
         //TODO:Ouvrir l'interface Depense View
                 try {
                      Stage primaryStage = new Stage();
                      Parent root = FXMLLoader.load(getClass().getResource("DepenseView.fxml"));
                      Scene scene = new Scene(root);
                      primaryStage.setTitle("Depense");
                      primaryStage.setScene(scene);
                      //primaryStage.show();
                      primaryStage.showAndWait();
                 } catch (IOException ex) {
                     Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 try {
                Rafraichir();
                } catch (ParseException ex) {
                Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }
    
    @FXML
    void ajouterRev(ActionEvent event){
         try {
                     //TODO:Ouvrir l'interface Revenu View
                      Stage primaryStage = new Stage();
                      Parent root = FXMLLoader.load(getClass().getResource("RevenuView.fxml"));
                      Scene scene = new Scene(root);
                      primaryStage.setTitle("Revenu");
                      primaryStage.setScene(scene);
                      //primaryStage.show();
                      primaryStage.showAndWait();
                      
                     //new HomeViewController.RevenuPage().start((Stage)choixTransac.getScene().getWindow());
                 } catch (IOException ex) {
                     Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                 } 
         try {
                Rafraichir();
            } catch (ParseException ex) {
                Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
     @FXML
    void creerBudget(ActionEvent event){
         try {
                     //TODO:Ouvrir l'interface Revenu View
                      Stage primaryStage = new Stage();
                      Parent root = FXMLLoader.load(getClass().getResource("NewBudget.fxml"));
                      Scene scene = new Scene(root);
                      primaryStage.setTitle("Nouveau Budget");
                      primaryStage.setScene(scene);
                      primaryStage.show();
                     //new HomeViewController.RevenuPage().start((Stage)choixTransac.getScene().getWindow());
                 } catch (IOException ex) {
                     Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }
    
     @FXML
    void genererBudget(ActionEvent event) throws SQLException, ParseException{
        //Genere un budget automatiquement a partir des 30 dernieres transactions;
        Date d = new Date();
        Budget b = new Budget("Budget Généré", d, 30); 
        //b.setCategories();
        ArrayList<Transaction> transactions = new ArrayList<>(DBConnection.getMontDate_transac());
        //tester si il y a suffisamment de transaction
        if(!transactions.isEmpty()){
            double sommeTransac = transactions.stream()
                .limit(30)
                .mapToDouble(t -> {
                    if (t instanceof Depense) 
                    return Math.abs(t.getMontantTransac());
                    return 0; })
                .sum();
            b.setMontantTot(sommeTransac);
            DBConnection.addBudget(b);
            System.out.println("Généré avec succés ID="+b.getIdBudget());
            //ajout des categories
            for(Categorie cat: DBConnection.listCategories){
                b.ajouterCat(cat);
                DBConnection.addCatBudg(b);
                System.out.println("Categorie ajoute= "+cat.getLibCat());
            }
            Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
                    alertSucc.setTitle("Message");
                    alertSucc.setHeaderText("Succés!");
                    alertSucc.setContentText("Budget généré avec succés");
                    alertSucc.show();
            }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Pas assez de transactions !");
                alert.show();
            }
        }
    
     @FXML
    void consulterBudg(ActionEvent event){
         try {
                     //TODO:Ouvrir l'interface Revenu View
                      Stage primaryStage = new Stage();
                      Parent root = FXMLLoader.load(getClass().getResource("ConsultBudget.fxml"));
                      Scene scene = new Scene(root);
                      primaryStage.setTitle("Tous les budgets");
                      primaryStage.setScene(scene);
                      primaryStage.show();
                     //new HomeViewController.RevenuPage().start((Stage)choixTransac.getScene().getWindow());
                 } catch (IOException ex) {
                     Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                 }
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


