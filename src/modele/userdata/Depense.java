/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.userdata;

/**
 *
 * @author user
 */
public final class Depense extends Transaction{
    private Categorie categorie;
    private SousCategorie sousCat;
    
    public Depense(int idTransac, double montantTransac, Categorie categorie, SousCategorie sousCat) {
        super(idTransac, - montantTransac); // signe (-) pour signaler que c'est une depense
        this.categorie = categorie;
        this.sousCat = sousCat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public SousCategorie getSousCat() {
        return sousCat;
    }

    public void setSousCat(SousCategorie sousCat) {
        this.sousCat = sousCat;
    }

    @Override
    public String toString() {
        return "Depense{" 
                + "idTransac=" + getIdTransac() + ", dateTransac=" + getDateTransac().toString() + ", montantTransac=" + getMontantTransac() +  //données transaction
                "categorie=" + categorie + ", sousCat=" + sousCat + "}\n";
    }   
}
