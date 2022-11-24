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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Label lab;

    @FXML
    private Hyperlink nvComte;

    @FXML
    private Hyperlink passOublie;

    @FXML
    private PasswordField txtPW;

    @FXML
    private TextField txtUser;

    @FXML
    void login(ActionEvent event) throws SQLException {
        String usern = txtUser.getText();
        String pass = txtPW.getText();
        //tester si les champs sont vides
        if ((usern.equals("")) || (pass.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Veuillez svp remplir les champs !");
                alert.show();
        }
        else{
            Connection conn= DBConnection.getConnexion();
            PreparedStatement ps;
	    ResultSet rs;
            ps=conn.prepareStatement("select nomUser, pw from user where nomUser= ? and pw= ?");
            ps.setString(1, usern);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("succés !");
                alert.setContentText("login avec succées");
                alert.show();
                //aller page acceuil
                
             }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText("Erreur !");
                alert.setContentText("user name or password incorrest !!");
                alert.show();
                txtUser.setText("");
                txtPW.setText("");
                }
        
    }
    }
    // si on clic sur creer nv compte
    @FXML
    void nvCompte(ActionEvent event){
        
        try {
            new SignUp().start((Stage)btnLogin.getScene().getWindow());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static class SignUp extends Application {

        public SignUp() {
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
           Scene scene = new Scene(root);
            primaryStage.setTitle("Sign Up");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    
    
    
}

    

