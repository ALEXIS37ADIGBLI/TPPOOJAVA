/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_restaurant;

/**
 *
 * @author bossmrpk
 */
public class commande {
    private int id_commande;
    private int  date_commande;
    private EtatCommande etat;
    
    public commande(int id_commande, int date_commande,EtatCommande etat ){
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.etat = EtatCommande.EN_COURS;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(int date_commande) {
        this.date_commande = date_commande;
    }
    
    public EtatCommande getEtat(){
        return etat;
    }
    
    public void  setEtat(EtatCommande etat){
        this.etat = etat;
    }

}
