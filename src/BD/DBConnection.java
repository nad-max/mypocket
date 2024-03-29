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
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.userdata.Budget;
import modele.userdata.Categorie;
import modele.userdata.Depense;
import modele.userdata.Revenu;
import modele.userdata.SousCategorie;
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
             PreparedStatement ps = conn.prepareStatement("insert into user(nomUser, pw, nom, dateCrt, solde) values (?,?,?,?,?)");
             ps.setString(1, user.getNomUser());
             ps.setString(2, user.getPw());
             ps.setString(3, user.getNom());
             ps.setString(4, user.getDateCrt());
             ps.setDouble(5, user.getSolde());
             ps.executeUpdate();
         }   
         
         public static Utilisateur user = new Utilisateur();
         public static ArrayList<Categorie> listCategories;
         public static ArrayList<Budget> listBudgets; 
         public static ArrayList<String> categoryNames;
         public static ArrayList<Integer> categoryCount;
         //public static Revenu revenu = new Revenu();
         
         
            
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
         ObservableList<Transaction> listTransac;
         listTransac =FXCollections.observableArrayList();
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
         //System.out.print("Affichage de la lite des transac"+listTransac.toString());
         
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
    
     public static void  addBudget(Budget b) throws SQLException{
         SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
         con=DBConnection.getConnexion();
         PreparedStatement ps = con.prepareStatement("insert into budget(nomBudget,dateCreation,duree,idUser ) values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
             ps.setString(1, b.getNomBudget());
             ps.setString(2, date.format(b.getDateCreation()));
             ps.setInt(3, b.getDuree());
             ps.setInt(4, user.getIdUser());
             ps.execute();
             ResultSet generatedKeys = ps.getGeneratedKeys();
             if(generatedKeys.next()){
                 b.setIdBudget(generatedKeys.getInt(1));
                 System.out.println("Budget dans la base"+b.toString());
                 user.ajouterBudget(b);
             }
             
            
     }  
     
     public static void addCatBudg(Budget budg) throws SQLException{
         con=DBConnection.getConnexion();
         
         PreparedStatement ps = con.prepareStatement("""
                                                     INSERT into 
                                                     catbudget(idBudget,categorie) values (?,?)""");
             ps.setInt(1, budg.getIdBudget());
             ps.setString(2, budg.getCategories().get(budg.getCategories().size()-1).getLibCat());
             ps.executeUpdate();
             //ps.setInt(4, user.getIdUser());
             //ps.setString(5, r.getType());
             
             
             PreparedStatement ps1 = con.prepareStatement("update  budget set montantTot = ? where idBudget='"+budg.getIdBudget()+"'");
             ps1.setDouble(1, budg.getMontantTot());
             ps1.executeUpdate();
         
     }
     
     public static void chargerlistCategories(){
         listCategories = new ArrayList<Categorie>();
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
        Categorie logement = new Categorie(4,"Logement");
            //Sous Categories de logement
            SousCategorie reparation = new SousCategorie(40,"Réparation");
            SousCategorie loyer = new SousCategorie(41,"Loyer");
            SousCategorie energie = new SousCategorie(42,"Energie & service publique");
            
            ArrayList<SousCategorie> scatLogement= new ArrayList<SousCategorie>(Arrays.asList(reparation,loyer,energie));
            logement.setSousCat(scatLogement);
            
            Categorie achat = new Categorie(5,"Achats");
            //Sous Categories de logement
            SousCategorie sante = new SousCategorie(50,"Santé");
            SousCategorie vetements = new SousCategorie(51,"Vetements & chaussures");
            SousCategorie electro = new SousCategorie(52,"Electoniques & accessoires");
            
            ArrayList<SousCategorie> scatAchat= new ArrayList<SousCategorie>(Arrays.asList(sante,vetements,electro));
            achat.setSousCat(scatAchat);
            
            
        listCategories = new ArrayList<Categorie>(Arrays.asList(nourritures,transport,loisir,logement,achat));
    }
    
     public static ArrayList<Budget> chargerlistBudgets() throws SQLException, ParseException{ 
        listBudgets = new ArrayList<Budget>();
         SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy :: HH:mm:ss");
         con=DBConnection.getConnexion();
         String req = """
                      SELECT *
                      FROM budget
                      WHERE idUser = '"""+user.getIdUser()+ "'";
	 PreparedStatement ps1;
         PreparedStatement ps2;
	 ResultSet rs1;
         ResultSet rs2;
         ps1=con.prepareStatement(req);
         //ps.setInt(1,user.getIdUser());
 	 rs1=ps1.executeQuery(req);
         
         while (rs1.next()) {
         Budget b = new Budget(rs1.getInt(1), rs1.getString(2), date.parse(rs1.getString(3)), rs1.getInt(4), rs1.getDouble(5));
         //Pour avoir la liste de categories de chaque budget
         String req2 = """
                      SELECT *
                      FROM catbudget
                      WHERE idBudget = '"""+b.getIdBudget()+ "'";
         ps2=con.prepareStatement(req2);
         rs2=ps2.executeQuery(req2);
         while (rs2.next()){
             Categorie cat = new Categorie(rs2.getString(3));
               b.ajouterCat(cat);
         }

 	 listBudgets.add(b); 
    }
         return listBudgets;
     } 
     
     public static void getCategoriesDetails() throws SQLException{
         // recuperer categories name
         chargerlistCategories();
         //test
         for(int i=0;i<listCategories.size();i++){
              System.out.println("Categories "+listCategories.get(i).getLibCat());   
         }
         categoryNames =new ArrayList<String>();
         //listCategories =new ArrayList<Categorie>();
        
         
        for(int i=0; i<DBConnection.listCategories.size(); i++){
           
            categoryNames.add(listCategories.get(i).getLibCat());
           
        }
         
        for(int i=0; i<DBConnection.categoryNames.size(); i++){
            //categoryNames.add(listCategories.get(i).getLibCat());
            System.out.println("name "+categoryNames.get(i));
        }
        
        
        
        // category count list from depense
        categoryCount= new ArrayList<Integer>();
        con=DBConnection.getConnexion();
        PreparedStatement ps;
         ResultSet rs; 
        for (int i = 0; i < categoryNames.size(); i++) {
	 String query =" select sum(montantTransac) from depense where depense.categorie = '"+ categoryNames.get(i) + "' and depense.idUser= '"+user.getIdUser()+ "'";
//	con = DBConnection.getConnexion();
	int cCount = 0;
	ps = con.prepareStatement(query);
	rs=ps.executeQuery();
	while(rs.next()) {
	cCount = cCount + rs.getInt(1);
	}
	categoryCount.add(cCount);
             
        }
        for (int i = 0; i < categoryCount.size(); i++) {
             System.out.println("count "+categoryCount.get(i));
        }
        
     }
     
     
     
}
    
