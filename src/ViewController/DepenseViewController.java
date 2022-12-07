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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modele.userdata.Categorie;
import modele.userdata.Depense;
import modele.userdata.SousCategorie;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class DepenseViewController implements Initializable {
    ArrayList<Categorie> listCategories;
    
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
        chargerlistCategories();
        sousCategBox.setDisable(true);
        System.out.println("ComboBox default is: "+categorieBox.getValue());
        
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
        
        categorieBox.getItems().addAll(listCategories);
        
        
        System.out.println("Depense initialized!");
        
         
    }    
    
     // action on btn valider
    @FXML
    void valider(ActionEvent event) throws SQLException {
        
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
    
    private void ajouterDepense() throws SQLException{
              SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
              Date d = new Date();
            
             Depense dep = new Depense(Double.parseDouble(montantField.getText()), d,categorieBox.getValue(), sousCategBox.getValue());
             System.out.println(dep.toString());
             DBConnection.addDepense(dep);
             System.out.println("ajout depense avec succés");
             Stage window = (Stage)btnValider.getScene().getWindow();
             window.close();
            
        }
    
    // action on retour btn
    @FXML
    void retour(ActionEvent event) {
              Stage window = (Stage)btnBack.getScene().getWindow();
              window.close();
            
    }
    
    private void chargerlistCategories(){
        //Creation des categories;
        Categorie nourritures = new Categorie(1,"Nourritures & Boissons");
            //Sous Categories de nourritures
            SousCategorie restaurant = new SousCategorie(11,"Restaurant & Fast Food");
            SousCategorie alimentation = new SousCategorie(12,"Alimentation");
            SousCategorie cafe = new SousCategorie(11,"Bar & Café");
            ArrayList<SousCategorie> scatAlimentation= new ArrayList<SousCategorie>(Arrays.asList(restaurant,alimentation,cafe));
            nourritures.setSousCat(scatAlimentation);
        Categorie transport = new Categorie(2,"Transports");
            //Sous Categories de transport
            SousCategorie taxi = new SousCategorie(21,"Taxi");
            SousCategorie transCommun = new SousCategorie(22,"Transport en commun");
            SousCategorie essence = new SousCategorie(23,"Carburant (Essence, gasoil...)");
            SousCategorie longtrajet = new SousCategorie(24,"Long trajet");
            ArrayList<SousCategorie> scatTransport= new ArrayList<SousCategorie>(Arrays.asList(taxi,transCommun,essence,longtrajet));
            transport.setSousCat(scatTransport);
        Categorie loisir = new Categorie(3,"Loisirs");
            //Sous Categories de loisir
            SousCategorie sport = new SousCategorie(31,"Sport, remise en forme");
            SousCategorie passion = new SousCategorie(32,"Passion, hobbies");
            SousCategorie bienetre = new SousCategorie(33,"Beaute et bien etre");
            SousCategorie livre = new SousCategorie(24,"Livre, audio, abonnement");
            ArrayList<SousCategorie> scatLoisir= new ArrayList<SousCategorie>(Arrays.asList(sport,passion,bienetre,livre));
            loisir.setSousCat(scatLoisir);
        
        listCategories = new ArrayList<Categorie>(Arrays.asList(nourritures,transport,loisir));
    }
    
}
