/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestion_restaurant;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe principale pour lancer l'application de gestion de restaurant.
 * @author Xisclever
 */
public class Gestion_restaurant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Appliquer l'apparence "Nimbus" ou celle du système pour un rendu moderne
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Si Nimbus n'est pas disponible, on garde le style par défaut
            System.err.println("Impossible de charger le Look and Feel : " + e.getMessage());
        }

        // Lancement de l'interface graphique dans le thread approprié (Event Dispatch Thread)
        java.awt.EventQueue.invokeLater(() -> {
            // 1. Initialisation de la vue de connexion
            vue.LoginView loginView = new vue.LoginView();
            
            // 2. Initialisation du contrôleur (il gère la logique de connexion)
            new controller.LoginController(loginView);
            
            // 3. Affichage de la fenêtre de login
            loginView.setVisible(true);
        });
    }
}