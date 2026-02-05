/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestion_restaurant;

import com.formdev.flatlaf.FlatLightLaf; // Import pour le thème clair moderne
import javax.swing.UIManager;

/**
 * @author Xisclever
 */
public class Gestion_restaurant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Activation de FlatLaf pour un rendu identique à la maquette Figma
        try {
            // FlatLightLaf est parfait pour le style clair et épuré de votre interface
            FlatLightLaf.setup(); 
            
            // Optionnel : Personnalisation pour arrondir les coins des composants (boutons, etc.)
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            
        } catch (Exception e) {
            System.err.println("Échec de l'initialisation de FlatLaf, passage au style par défaut.");
        }

        // Lancement de l'interface graphique
        java.awt.EventQueue.invokeLater(() -> {
            // 1. Initialisation de la vue de connexion
            vue.LoginView loginView = new vue.LoginView();
            
            // 2. Initialisation du contrôleur
            new controller.LoginController(loginView);
            
            // 3. Affichage
            loginView.setVisible(true);
        });
    }
}