package controller;

import DAO.DBUtilisateur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Timer;
import models.utilisateur;
import outils.DBException;
import vue.LoginView;

/**
 * @author Xisclever
 */
public class LoginController {

    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;

        // On cache le message d'alerte au démarrage
        this.view.getLblMessage().setVisible(false);

        // Quand l'utilisateur clique sur "Se connecter"
        this.view.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierConnexion();
            }
        });
    }

    private void verifierConnexion() {
        String login = view.txtLogin.getText();
        String password = new String(view.txtPassword.getPassword());

        // Vérification : champs vides
        if (login.isEmpty() || password.isEmpty()) {
            afficherAlerte("Veuillez remplir tous les champs !",
                    new Color(254, 226, 226), new Color(153, 27, 27));
            return;
        }

        try {
            // Vérification des identifiants en base de données
            utilisateur user = DBUtilisateur.authentifier(login, password);

            if (user != null) {
                // Identifiants corrects : message de bienvenue
                afficherAlerte("Connexion réussie ! Bienvenue " + user.getLogin(),
                        new Color(220, 252, 231), new Color(22, 101, 52));

                // Ouverture du menu principal après 1,5 secondes
                Timer timer = new Timer(1500, (e) -> {
                    view.dispose();
                    vue.MainMenuView menu = new vue.MainMenuView();
                    new controller.DashboardController(menu);
                    menu.setVisible(true);
                });
                timer.setRepeats(false);
                timer.start();

            } else {
                // Identifiants incorrects
                afficherAlerte("Login ou mot de passe incorrect.",
                        new Color(254, 226, 226), new Color(153, 27, 27));
            }

        } catch (DBException | SQLException ex) {
            afficherAlerte("Erreur base de données : " + ex.getMessage(),
                    Color.ORANGE, Color.BLACK);
        }
    }

    // Affiche un message coloré dans le label de la vue
    private void afficherAlerte(String message, Color background, Color foreground) {
        view.getLblMessage().setText(message);
        view.getLblMessage().setBackground(background);
        view.getLblMessage().setForeground(foreground);
        view.getLblMessage().setVisible(true);
    }
}
