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
    private Date dateD;
    private Date dateF;
    private double montantTot;
    private ArrayList<Categorie> categories;

   // const
    public Budget(int idBudget, String nomBudget, Date dateD, Date dateF, double montantTot) {
        this.idBudget = idBudget;
        this.nomBudget = nomBudget;
        this.dateD = dateD;
        this.dateF = dateF;
        this.montantTot = montantTot;
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

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
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
