/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.userdata;

/**
 *
 * @author user
 */
public class SousCategorie {
    private int  idSousCat;
    private String libSousCat;
    
    // constructeur
    public SousCategorie(int idSousCat, String libSousCat) {
        this.idSousCat = idSousCat;
        this.libSousCat = libSousCat;
    }
    
    //getters and setters
    public int getIdSousCat() {
        return idSousCat;
    }

    public void setIdSousCat(int idSousCat) {
        this.idSousCat = idSousCat;
    }

    public String getLibSousCat() {
        return libSousCat;
    }

    public void setLibSousCat(String libSousCat) {
        this.libSousCat = libSousCat;
    }

    @Override
    public String toString() {
        return "SousCategorie{" + "idSousCat=" + idSousCat + ", libSousCat=" + libSousCat + "}\n";
    }
    
}
