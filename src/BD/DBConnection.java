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
            
         public static Utilisateur getUser(String userN, String pw) throws SQLException{
        
                con = DBConnection.getConnexion();
                //Utilisateur u = new Utilisateur();
                
                String req = "select nom, solde from user where nomUser = '"+userN+"' and pw = '"+pw+"'";
		PreparedStatement ps = con.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    user.setNom(rs.getString(1));
                    user.setSolde(rs.getDouble(2));
                    //u.setPw(rs.getString(2));
                    //u.setNomUser(rs.getString(3));
                    System.out.println(user.getNom());
                    System.out.println(user.getSolde());
                }
          return user;
    }
    public static Utilisateur user = new Utilisateur();     
            
}
    
