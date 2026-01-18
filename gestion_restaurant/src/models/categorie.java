/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bossmrpk
 */
public class categorie {
    private int id_categorie;
    private String libelle;

    public categorie(int id_categorie, String libelle) {
        this.id_categorie = id_categorie;
        this.libelle = libelle;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
}
