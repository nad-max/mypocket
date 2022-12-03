/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ViewController;

import BD.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import java.sql.Date;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.user.Utilisateur;



/**
 * FXML Controller class
 *
 * @author user
 */
public class SignUpViewController {

   @FXML
    private Button btnSignUp;
   @FXML
    private PasswordField txtPas;
   @FXML
    private TextField txtmail;
   @FXML
    private TextField txtNom;
   
   @FXML 
   void ajouterUser(ActionEvent event) throws SQLException, IOException{
       
       String pass=txtPas.getText();
       String email=txtmail.getText();
       String nom=txtNom.getText();
       //verifier si les champs sont vides
       if ((pass.equals("")) || (email.equals("")) || (nom.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Veuillez svp remplir les champs !");
                alert.show();
        }
       else{  //ajouter user
//           Connection conn= DBConnection.getConnexion();
//            PreparedStatement ps;
//            ResultSet rs;
//            String pattern = "yyyy-mm-dd";
//            String today = new SimpleDateFormat(pattern).format(new Date());
//            System.out.println("Today is .......  " + today);
          //String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date d = new Date();
            Utilisateur user = new Utilisateur(nom,email,pass, date.format(d));
            DBConnection.addUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Succès  !");
                alert.setContentText("Ajout fait avec succès !");
                alert.show();
               //retourner au login page
                new LoginPage().start((Stage)btnSignUp.getScene().getWindow());
                
            
            
            
            //ps.setDate(4,today);
            
            
            
            
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
