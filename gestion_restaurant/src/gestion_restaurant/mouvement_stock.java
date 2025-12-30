/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_restaurant;

/**
 *
 * @author bossmrpk
 */
public class mouvement_stock {
    private int id_mouvement;
    private int id_produit;
    private mouvement_type_stock type_mouvement;
    private int quantite;
    private int date_mouvement;
    private String motif;

    public mouvement_stock(int id_mouvement, int id_produit, mouvement_type_stock type_mouvement, int quantite, int date_mouvement, String motif) {
        this.id_mouvement = id_mouvement;
        this.id_produit = id_produit;
        this.type_mouvement = type_mouvement;
        this.quantite = quantite;
        this.date_mouvement = date_mouvement;
        this.motif = motif;
    }
    
    public mouvement_type_stock getType_mouvement(){
        return type_mouvement;
    }
    public void setType_mouvement(mouvement_type_stock type_mouvement){
        this.type_mouvement = type_mouvement;
    }

    public int getId_mouvement() {
        return id_mouvement;
    }

    public void setId_mouvement(int id_mouvement) {
        this.id_mouvement = id_mouvement;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getDate_mouvement() {
        return date_mouvement;
    }

    public void setDate_mouvement(int date_mouvement) {
        this.date_mouvement = date_mouvement;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
    
}
