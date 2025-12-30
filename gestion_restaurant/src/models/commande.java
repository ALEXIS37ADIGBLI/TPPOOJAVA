/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;

/**
 *
 * @author bossmrpk
 */
public class commande {
    private int id_commande;
    private LocalDateTime date_commande; 
    private String etat ;
    
    public commande(int id_commande, LocalDateTime date_commande,String etat ){
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.etat = etat;
    }

    public int getId_commande() {
        return id_commande;
    }
    
    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public LocalDateTime getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(LocalDateTime date_commande) { 
        this.date_commande = date_commande;
    }
    
    public String getEtat(){
        return etat;
    }
    
    public void  setEtat(String etat){
        this.etat = etat;
    }

}
