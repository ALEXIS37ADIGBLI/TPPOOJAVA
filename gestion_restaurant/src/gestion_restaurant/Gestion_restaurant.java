package gestion_restaurant;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

/**
 * @author Xisclever
 */
public class Gestion_restaurant {

    public static void main(String[] args) {

        // Activation du thème FlatLaf pour un rendu moderne et épuré
        try {
            FlatLightLaf.setup();

            // Arrondi des coins pour les boutons et composants
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);

        } catch (Exception e) {
            System.err.println("Échec de FlatLaf, passage au style par défaut.");
        }

        // Lancement de l'interface sur le thread graphique
        java.awt.EventQueue.invokeLater(() -> {
            vue.LoginView loginView = new vue.LoginView();
            new controller.LoginController(loginView);
            loginView.setVisible(true);
        });
    }
}