/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.user;

import BD.DBConnection;
import java.util.ArrayList;
import modele.userdata.Budget;
import modele.userdata.Transaction;

/**
 *
 * @author user
 */
public class Utilisateur {
    private int idUser;
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
        this.budgets = new ArrayList<Budget>();
        this.transactions = new ArrayList<Transaction>();
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

    public Utilisateur(int idUser, String nom, String nomUser, String pw, String dateCrt, double solde, ArrayList<Budget> budgets, ArrayList<Transaction> transactions) {
        this.budgets = new ArrayList<Budget>();
        this.transactions = new ArrayList<Transaction>();
        this.idUser = idUser;
        this.nom = nom;
        this.nomUser = nomUser;
        this.pw = pw;
        this.dateCrt = dateCrt;
        this.solde = solde;
        this.budgets = budgets;
        this.transactions = transactions;
    }
    

    public Utilisateur() {
        this.budgets = new ArrayList<Budget>();
        this.transactions = new ArrayList<Transaction>();
    }
    
    

    public int getIdUser() {
        return idUser;
    }

    // gettres and settres
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

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
    }
    public void ajouterBudget(Budget budg){
        budgets.add(budg);
    }
    public void clearTransactions(){
        transactions.clear();
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "\nnom=" + nom + ", \nnomUser=" + nomUser + ", \npw=" + pw + ", \ndateCrt=" + dateCrt + ", \nsolde=" + solde + ", \nbudgets=" + budgets + ", \ntransactions=" + transactions + '}';
    }  
}
