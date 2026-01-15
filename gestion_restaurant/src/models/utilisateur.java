/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bossmrpk
 */
public class utilisateur {
    private int id_utilisateur;
    private String login;
    private String mot_de_passe;

    public utilisateur(int id_utilisateur, String login, String mot_de_passe) {
        this.id_utilisateur = id_utilisateur;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getLogin() {
        return login;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    
    
}
