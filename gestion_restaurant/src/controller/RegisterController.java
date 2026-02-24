package controller;

import DAO.DBUtilisateur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Timer;
import models.utilisateur;
import outils.DBException;
import vue.RegisterView;

/**
 * @author Xisclever
 */
public class RegisterController {

    private RegisterView view;

    public RegisterController(RegisterView view) {
        this.view = view;

        // On cache le message d'alerte au démarrage
        this.view.getLblMessage().setVisible(false);

        // Quand l'utilisateur clique sur "S'inscrire"
        this.view.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enregistrerUtilisateur();
            }
        });
    }

    private void enregistrerUtilisateur() {

        // Récupération des valeurs saisies
        String login = view.txtLogin.getText().trim();
        String password = new String(view.txtPassword.getPassword());

        // Vérification : champs vides
        if (login.isEmpty() || password.isEmpty()) {
            afficherAlerte("Veuillez remplir tous les champs !",
                    new Color(254, 226, 226), new Color(153, 27, 27));
            return;
        }

        // Vérification : mot de passe trop court
        if (password.length() < 4) {
            afficherAlerte("Le mot de passe doit avoir au moins 4 caractères.",
                    new Color(254, 226, 226), new Color(153, 27, 27));
            return;
        }

        try {
            // Création de l'objet utilisateur (id=0, la BDD génère l'id automatiquement)
            utilisateur nouvelUtilisateur = new utilisateur(0, login, password);

            // Envoi en base de données
            DBUtilisateur.addUser(nouvelUtilisateur);

            // Succès : message de confirmation
            afficherAlerte("Compte créé avec succès ! Redirection...",
                    new Color(220, 252, 231), new Color(22, 101, 52));

            // Redirection vers le login après 1,5 secondes
            Timer timer = new Timer(1500, (e) -> {
                view.dispose();
                vue.LoginView loginPage = new vue.LoginView();
                new controller.LoginController(loginPage);
                loginPage.setVisible(true);
            });
            timer.setRepeats(false);
            timer.start();

        } catch (DBException | SQLException ex) {
            // Erreur base de données
            afficherAlerte("Erreur : " + ex.getMessage(),
                    new Color(255, 237, 213), new Color(154, 52, 18));
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
