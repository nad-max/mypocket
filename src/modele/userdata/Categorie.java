/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.userdata;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Categorie {
    private int  idCat;
    private String libCat;
    private ArrayList<SousCategorie> sousCat;
    
    
    //constructeur
    public Categorie(int idCat, String libCat, ArrayList<SousCategorie> sousCat) {
        this.idCat = idCat;
        this.libCat = libCat;
        this.sousCat = sousCat;
    }

    public Categorie(int idCat, String libCat) {
        this.idCat = idCat;
        this.libCat = libCat;
        sousCat = new ArrayList<SousCategorie>();
    }
    
    //getters and settres 

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getLibCat() {
        return libCat;
    }

    public void setLibCat(String libCat) {
        this.libCat = libCat;
    }

    public ArrayList<SousCategorie> getSousCat() {
        return sousCat;
    }

    public void setSousCat(ArrayList<SousCategorie> sousCat) {
        this.sousCat = sousCat;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idCat=" + idCat + ", libCat=" + libCat + ", sousCat=" + sousCat + "}\n";
    }
    
}
