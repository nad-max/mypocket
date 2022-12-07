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
public final class Revenu extends Transaction {
    //public static enum RevSourceType{SALAIRE, LOYER, AFFAIRE, AUTRE}
    
    private String sourceType;
    private int idUser;
    private String  type;
    
    
    
    
    public Revenu(int idTransac, double montantTransac,String sourceType) {
        super(idTransac, montantTransac);
        this.type = "+";
        this.sourceType = sourceType;
        
    }
     
    public Revenu(String sourceType, Date dateTransac, double montantTransac) {
        super(dateTransac, montantTransac);
        this.type = "+";
        this.sourceType = sourceType;
    }

    public String getType() {
        return type;
    }
    
    
    
    
    public Revenu(Date dateTransac, double montantTransac) {
        super(dateTransac, montantTransac);
        this.type = "+";
        
    }


    

    public Revenu() {
        this.type = "+";
    }

   
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    
    
    

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "Revenu{" 
                + "idTransac=" + getIdTransac() + ", dateTransac=" + getDateTransac().toString() + ", montantTransac=" + getMontantTransac() +  //données transaction
                 "sourceType=" + getSourceType()+ "}\n";
    }   
}
