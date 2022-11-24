/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.userdata;

/**
 *
 * @author user
 */
public final class Revenu extends Transaction {
    public static enum RevSourceType{SALAIRE, LOYER, AFFAIRE, AUTRE}
    private RevSourceType sourceType;
    
    
    public Revenu(int idTransac, double montantTransac,RevSourceType sourceType) {
        super(idTransac, montantTransac);
        this.sourceType = sourceType;
    }

    public RevSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(RevSourceType sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "Revenu{" 
                + "idTransac=" + getIdTransac() + ", dateTransac=" + getDateTransac().toString() + ", montantTransac=" + getMontantTransac() +  //données transaction
                 "sourceType=" + sourceType.name() + "}\n";
    }   
}
