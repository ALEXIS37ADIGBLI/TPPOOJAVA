/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bossmrpk
 */
public class ligne_commande {
    private int id_ligne;
    private commande commande;
    private produit produit;
    private int quantite;
    private double prix_unitaire;
    private double montant_ligne;

    public ligne_commande(int id_ligne, commande id_commande, produit id_produit, int quantite, double prix_unitaire, double montant_ligne) {
        this.id_ligne = id_ligne;
        this.commande = id_commande;
        this.produit = id_produit;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;
        this.montant_ligne = montant_ligne;
    }

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public commande getCommande() {
        return commande;
    }

    public void setCommande(commande id_commande) {
        this.commande = id_commande;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit id_produit) {
        this.produit = id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public double getMontant_ligne() {
        return montant_ligne;
    }

    public void setMontant_ligne(double montant_ligne) {
        this.montant_ligne = montant_ligne;
    }
    
    
}
