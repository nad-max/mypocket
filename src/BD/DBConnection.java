/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.user.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.userdata.Revenu;

/**
 *
 * @author nada
 */
public class DBConnection {
    
    public static Connection con;
	   
	    
	    public static Connection getConnexion() throws SQLException{  
	    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Chargement de driver JDBC
            con = DriverManager.getConnection("jdbc:mysql://localhost/pocket", "root", "");
//            System.out.println("Connection is established" );  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection failed" ); 
            
        }
	    	return con;
            }
            
            
         public static void addUser(Utilisateur user) throws SQLException{
             Connection conn= DBConnection.getConnexion();
             PreparedStatement ps = conn.prepareStatement("insert into user values (?,?,?,?)");
             ps.setString(1, user.getNomUser());
             ps.setString(2, user.getPw());
             ps.setString(3, user.getNom());
             ps.setString(4, user.getDateCrt());
             ps.executeUpdate();
         }   
         
         public static Utilisateur user = new Utilisateur();
         public static Revenu revenu = new Revenu();
         
         
            
         public static Utilisateur getUser(String userN, String pw) throws SQLException{
        
                con = DBConnection.getConnexion();
                //Utilisateur u = new Utilisateur();
                
                String req = "select idUser, nom, solde from user where nomUser = '"+userN+"' and pw = '"+pw+"'";
		PreparedStatement ps = con.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    user.setIdUser(rs.getInt(1));
                    user.setNom(rs.getString(2));
                    user.setSolde(rs.getDouble(3));
                    //u.setPw(rs.getString(2));
                    //u.setNomUser(rs.getString(3));
                    System.out.println(user.getNom());
                    System.out.println(user.getSolde());
                    System.out.println(user.getIdUser());
                }
          return user;
    }
         
     //ajouter revenu 
         public static void addRevenu(Revenu r) throws SQLException{
             Connection conn= DBConnection.getConnexion();
             PreparedStatement ps = conn.prepareStatement("insert into revenu(sourceType,dateTransac,montantTransac,idUser) values (?,?,?,?)");
             ps.setString(1, r.getSourceType());
             ps.setString(2, r.getDateTransac());
             ps.setDouble(3, r.getMontantTransac());
             ps.setInt(4, user.getIdUser());
             ps.executeUpdate();
            
         }
         //recuperer date et montant de revenu par utilisateur
     public static ObservableList<Revenu> getMontDate_rev() throws SQLException{
         ObservableList<Revenu> listRevenu =FXCollections.observableArrayList();
         Connection conn= DBConnection.getConnexion();
        
         String req = "select dateTransac, montantTransac  from revenu where idUser = '"+user.getIdUser()+"'";
	 PreparedStatement ps;
         
	 ResultSet rs;
         ps=conn.prepareStatement(req);
         //ps.setInt(1,user.getIdUser());
 	 rs=ps.executeQuery(req);
         while (rs.next()) {
 	 Revenu p = new Revenu(rs.getString(1), rs.getDouble(2));
 	 listRevenu.add(p);
         }
         
         return listRevenu;
     }   
     
     //recuperer somme de revenu d'un user
     public static double getSumRevenu() throws SQLException{
         con = DBConnection.getConnexion();
         double total=0.0;
         String req = "select SUM(montantTransac) from revenu where idUser = '"+user.getIdUser()+"'";
		PreparedStatement ps = con.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    total+=rs.getDouble(1);
                }
                return total;
     
     }
     
//     public static void setSoldeUser() throws SQLException{
//         con = DBConnection.getConnexion();
//         PreparedStatement ps = con.prepareStatement("update  user  set solde = solde + '"+getSumRevenu()+"' where idUser = '"+user.getIdUser()+"'");
//         ps.executeUpdate();
//         //same pour depense ps et update
//     }
         
         
      
            
}
    
