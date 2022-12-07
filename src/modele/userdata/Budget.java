/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.userdata;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class Budget {
    private int idBudget;
    private String nomBudget;
    private String dateCreation;
    private int duree;
    private double montantTot;
    private ArrayList<Categorie> categories;

   // const
    public Budget(int idBudget, String nomBudget, String dateCreation, int duree ,double montantTot) {
        this.idBudget = idBudget;
        this.nomBudget = nomBudget;
        this.dateCreation = dateCreation;
        this.duree=duree;
        this.montantTot = montantTot;
    }

    public Budget(String nomBudget, String dateCreation, int duree) {
        this.nomBudget = nomBudget;
        this.dateCreation = dateCreation;
        this.duree = duree;
    }
    
    
    
    
    // gettres and settres 

    public int getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }

    public String getNomBudget() {
        return nomBudget;
    }

    public void setNomBudget(String nomBudget) {
        this.nomBudget = nomBudget;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

   

    

    public double getMontantTot() {
        return montantTot;
    }

    public void setMontantTot(double montantTot) {
        this.montantTot = montantTot;
    }
    
    // methodes 
    public void  creerBudget(){
        
    }
     public void genererBudget(Date dateD, Date dateF){
         //getTransaParPeriode()
    }
    public void  supprimerBudget(){
    }
    
    
    
       
    
}
