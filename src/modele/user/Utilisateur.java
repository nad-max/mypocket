/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.user;

import java.util.ArrayList;
import java.sql.Date;
import modele.userdata.Budget;
import modele.userdata.Transaction;

/**
 *
 * @author user
 */
public class Utilisateur {
    private String nom;
    private String nomUser;
    private String pw; //mot de passe
    private String dateCrt;
    private double solde;
    private ArrayList<Budget> budgets;
    private ArrayList<Transaction> transactions;
    
    // constructeur 

    public Utilisateur(String nom, String nomUser, String pw, String dateCrt) {
        this.nom = nom;
        this.nomUser = nomUser;
        this.pw = pw;
        this.dateCrt = dateCrt;
    }
    

    public Utilisateur(String nom, String nomUser, String pw, String dateCrt, double solde) {
        this.nom = nom;
        this.nomUser = nomUser;
        this.pw = pw;
        this.dateCrt = dateCrt;
        this.solde = solde;
        this.budgets = new ArrayList<Budget>();
        this.transactions = new ArrayList<Transaction>();
    }
    
    // gettres and settres 

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getDateCrt() {
        return dateCrt;
    }

    public void setDateCrt(String dateCrt) {
        this.dateCrt = dateCrt;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public void ajouterTransac(Transaction tran){
        transactions.add(tran);
        this.solde= solde + tran.getMontantTransac(); 
    }
    public void ajouterBudget(Budget budg){
        budgets.add(budg);
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "\nnom=" + nom + ", \nnomUser=" + nomUser + ", \npw=" + pw + ", \ndateCrt=" + dateCrt + ", \nsolde=" + solde + ", \nbudgets=" + budgets + ", \ntransactions=" + transactions + '}';
    }
    
    
    
    
     
    
    
}
