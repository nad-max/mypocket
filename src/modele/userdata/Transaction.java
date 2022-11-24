/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.userdata;

import java.util.Date;

/**
 *
 * @author user
 */
public abstract sealed class Transaction permits Depense, Revenu {
    private int idTransac;
    private Date dateTransac;
    private double montantTransac;

    public Transaction(int idTransac, double montantTransac) {
        this.idTransac = idTransac;
        this.montantTransac = montantTransac;
        this.dateTransac = new Date();
    }
   
    
    public int ajouterTransac(){
        return idTransac;
    };
    public Transaction consulterTransac(){
        this.toString();
        return this;
    }
    public void supprimerTransac(int idTransac){}
    
    public int getIdTransac() {
        return idTransac;
    }

    public void setIdTransac(int idTransac) {
        this.idTransac = idTransac;
    }

    public Date getDateTransac() {
        return dateTransac;
    }

    public void setDateTransac(Date dateTransac) {
        this.dateTransac = dateTransac;
    }

    public double getMontantTransac() {
        return montantTransac;
    }

    //Getters + Setters
    public void setMontantTransac(double montantTransac) {    
        this.montantTransac = montantTransac;
    }

    @Override
    public String toString() {
        return "Transaction{" + "idTransac=" + idTransac + ", dateTransac=" + dateTransac.toString() + ", montantTransac=" + montantTransac + '}';
    }

    

    
}

