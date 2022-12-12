/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.userdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 * @author user
 */
public class Budget {
    private int idBudget;
    private String nomBudget;
    private Date dateCreation;
    private int duree;
    private double montantTot;
    private ArrayList<Categorie> categories;

   // const
    public Budget(int idBudget, String nomBudget, Date dateCreation, int duree ,double montantTot) {
        this.idBudget = idBudget;
        this.nomBudget = nomBudget;
        this.dateCreation = dateCreation;
        this.duree=duree;
        this.montantTot = montantTot;
        categories = new ArrayList<Categorie>();
    }

    public Budget(String nomBudget, Date dateCreation, int duree) {
        this.nomBudget = nomBudget;
        this.dateCreation = dateCreation;
        this.duree = duree;
        categories = new ArrayList<Categorie>();
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
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
    
    public void ajouterCat(Categorie cat){
        categories.add(cat);
    }

    public ArrayList<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categorie> categories) {
        this.categories = categories;
    }
    
    public double testDepassement(ArrayList<Transaction> transactions){
        //retourne le montantReel du budget (progression du budget); et -1 si le budget est obsolete;
        System.out.println("lancement test depassement:");
        //Extraire uniquement les transaction "depenses" et de meme categories que le budget
        List<Transaction> deps = transactions.stream()
                .filter(e->{
                    if (e instanceof Depense depense ){
                        for(Categorie cat  :categories){
                            String categTransac = depense.getCategorie().getLibCat();
                            if(cat.getLibCat().equals(categTransac)) return true;
                        }
                    }
                    return false;
                            }).collect(Collectors.toList());
        System.out.println("Depenses filtre: "+deps.toString());
        Date date = new Date();
        long dateNow = date.getTime();
        long dateCreatBudg = dateCreation.getTime();
        long dureeReelBudg = TimeUnit.DAYS.convert(dateNow - dateCreatBudg, TimeUnit.MILLISECONDS);
        System.out.println("nbr jours budget:"+ dureeReelBudg);
        if(dureeReelBudg>duree){
            System.out.println("Ce budget est expire il y a : "+(dureeReelBudg-duree)+" Jours");
            return -1;
        }else{
            //voir si il y a un depassement
            double sommeTransactions=0;
            for(Transaction depense : deps){
                //voir pour chaque depense si on doit ajouter sa somme au budget 
                if(depense.getDateTransac().compareTo(dateCreation)>0){
                    //si la transaction est inclue dans la periode du budget
                    sommeTransactions=sommeTransactions + Math.abs(depense.getMontantTransac());
                    
                }
            }
            return sommeTransactions;
        }


    }
    

    @Override
    public String toString() {
        return "Budget{" + "idBudget=" + idBudget + ", nomBudget=" + nomBudget + ", dateCreation=" + dateCreation + ", duree=" + duree + ", montantTot=" + montantTot + ", categories=" + categories.toString() + '}';
    }
    
    
    
    
    
       
    
}
