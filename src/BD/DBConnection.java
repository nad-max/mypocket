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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.userdata.Depense;
import modele.userdata.Revenu;
import modele.userdata.Transaction;

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
             PreparedStatement ps = conn.prepareStatement("insert into user(nomUser, pw, nom, dateCrt) values (?,?,?,?)");
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
             SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
             Connection conn= DBConnection.getConnexion();
             
             PreparedStatement ps = conn.prepareStatement("insert into revenu(sourceType,dateTransac,montantTransac,idUser,type) values (?,?,?,?,?)");
             ps.setString(1, r.getSourceType());
             ps.setString(2, date.format(r.getDateTransac()));
             ps.setDouble(3, r.getMontantTransac());
             ps.setInt(4, user.getIdUser());
             ps.setString(5, r.getType());
             ps.executeUpdate();
             
             PreparedStatement ps1 = conn.prepareStatement("update  user set solde = ? where idUser='"+user.getIdUser()+"'");
             ps1.setDouble(1, user.getSolde()+r.getMontantTransac());
             //System.out.println("ajouter revenu solde "+ user.getSolde()+ r.getMontantTransac());
             user.setSolde(user.getSolde()+r.getMontantTransac());
             //System.out.println("ajouter revenu solde "+ user.getSolde());
             ps1.executeUpdate();
         }
         
         //ajouter revenu 
         public static void addDepense(Depense dep) throws SQLException{
             SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
             Connection conn= DBConnection.getConnexion();
             
             PreparedStatement ps = conn.prepareStatement("""
                         INSERT into depense(categorie,souscateg,dateTransac,montantTransac,idUser) 
                         VALUES (?,?,?,?,?)""");
             ps.setString(1, dep.getCategorie().getLibCat());
             ps.setString(2, dep.getSousCat().getLibSousCat());
             ps.setString(3, date.format(dep.getDateTransac()));
             ps.setDouble(4, -dep.getMontantTransac());
             ps.setInt(5, user.getIdUser());
             ps.executeUpdate();
             
             PreparedStatement ps1 = conn.prepareStatement("""
                                                           UPDATE  user 
                                                           SET solde = ? WHERE idUser='"""+user.getIdUser()+"'");
             ps1.setDouble(1, user.getSolde()+dep.getMontantTransac());
             //System.out.println("ajouter revenu solde "+ user.getSolde()+ r.getMontantTransac());
             user.setSolde(user.getSolde()+dep.getMontantTransac());
             //System.out.println("ajouter revenu solde "+ user.getSolde());
             ps1.executeUpdate();
         }
         
         //recuperer date et montant de revenu par utilisateur
     public static ObservableList<Transaction> getMontDate_transac() throws SQLException, ParseException{
         SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
         ObservableList<Transaction> listTransac =FXCollections.observableArrayList();
         Connection conn= DBConnection.getConnexion();
        
         String reqRev = """
                      SELECT dateTransac, montantTransac, type 
                      FROM revenu 
                      WHERE idUser = '"""+user.getIdUser()+ "'";
         
         String reqDep = """
                      SELECT montantTransac, dateTransac, categorie, souscateg
                      FROM depense
                      WHERE idUser = '"""+user.getIdUser()+ "'";
	 PreparedStatement ps1;
         
	 ResultSet rs1;
         ps1=conn.prepareStatement(reqRev);
         //ps.setInt(1,user.getIdUser());
 	 rs1=ps1.executeQuery(reqRev);
         
         while (rs1.next()) {
 	 Transaction rev = new Revenu(date.parse(rs1.getString(1)), rs1.getDouble(2));
 	 listTransac.add(rev);
         }
         //execution de select des depenses
         PreparedStatement ps2;
         
	 ResultSet rs2;
         ps2=conn.prepareStatement(reqDep);
         //ps.setInt(1,user.getIdUser());
 	 rs2=ps2.executeQuery(reqDep);
         while (rs2.next()) {
 	 Transaction dep = new Depense(rs2.getDouble(1), date.parse(rs2.getString(2)), rs2.getString(3), rs2.getString(4));
 	 listTransac.add(dep);
         }
         System.out.print("Affichage de la lite des transac"+listTransac.toString());
         
         return listTransac;
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
    
