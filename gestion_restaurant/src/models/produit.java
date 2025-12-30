/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bossmrpk
 */
public class produit {
    private int id_produit;
    private String nom;
    private double prix_vente;
    private int stock_actuel;
    private int seuil_alerte;
    private categorie categorie;

<<<<<<< HEAD:gestion_restaurant/src/gestion_restaurant/produit.java
    public produit(int id_produit, String nom, double prix_vente, int stock_actuel, int seuil_alerte, categorie categorie) {
=======
    public produit(int id_produit, String nom, double prix_vente, int stock_actuel, int seuil_alerte, categorie id_categorie) {
>>>>>>> 0de7652420a4a94abc7a5c3da8dac392f9065916:gestion_restaurant/src/models/produit.java
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix_vente = prix_vente;
        this.stock_actuel = stock_actuel;
        this.seuil_alerte = seuil_alerte;
<<<<<<< HEAD:gestion_restaurant/src/gestion_restaurant/produit.java
        this.categorie = categorie;
=======
        this.categorie = id_categorie;
>>>>>>> 0de7652420a4a94abc7a5c3da8dac392f9065916:gestion_restaurant/src/models/produit.java
    }
    
    public int getId_produit(){
        return id_produit;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix_vente() {
        return prix_vente;
    }

    public int getStock_actuel() {
        return stock_actuel;
    }

    public int getSeuil_alerte() {
        return seuil_alerte;
    }
    
    public categorie getCategorie() {
        return categorie;
    }
    
    public void setId_produit(int id_produit){
        this.id_produit = id_produit;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix_vente(double prix_vente) {
        this.prix_vente = prix_vente;
    }

    public void setStock_actuel(int stock_actuel) {
        this.stock_actuel = stock_actuel;
    }

    public void setSeuil_alerte(int seuil_alerte) {
        this.seuil_alerte = seuil_alerte;
    }

<<<<<<< HEAD:gestion_restaurant/src/gestion_restaurant/produit.java
    public void setId_categorie(categorie categorie) {
        this.categorie = categorie;
=======
    public void setCategorie(categorie id_categorie) {
        this.categorie = id_categorie;
>>>>>>> 0de7652420a4a94abc7a5c3da8dac392f9065916:gestion_restaurant/src/models/produit.java
    }
    

    
    
    

    
}
