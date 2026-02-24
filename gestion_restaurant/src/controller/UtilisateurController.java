package controller;

import DAO.DBUtilisateur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.utilisateur;
import outils.DBException;

/**
 * author obed
 */
public class UtilisateurController {

    /**
     * Charge tous les utilisateurs depuis la BDD et remplit le tableau.
     */
    public static void remplirTableau(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Vider le tableau avant de recharger

        try {
            List<utilisateur> users = DBUtilisateur.getAll();
            for (utilisateur u : users) {
                Object[] row = {
                    u.getId_utilisateur(),
                    u.getLogin(),
                    "••••••••" // Masquer le mot de passe
                };
                model.addRow(row);
            }
        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur lors du chargement des utilisateurs :\n" + ex.getMessage(),
                    "Erreur BDD", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ouvre une boîte de dialogue pour ajouter un utilisateur, l'insère en BDD,
     * puis rafraîchit le tableau.
     */
    public static void ajouterUtilisateur(JTable table) {
        // Récupérer le login
        String login = JOptionPane.showInputDialog(null,
                "Entrez le login du nouvel utilisateur :",
                "Ajouter un utilisateur", JOptionPane.PLAIN_MESSAGE);

        if (login == null || login.trim().isEmpty()) {
            return; // Annulé ou vide
        }

        // Récupérer le mot de passe
        javax.swing.JPasswordField passwordField = new javax.swing.JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, passwordField,
                "Entrez le mot de passe :", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option != JOptionPane.OK_OPTION) {
            return; // Annulé
        }

        String motDePasse = new String(passwordField.getPassword()).trim();
        if (motDePasse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le mot de passe ne peut pas être vide.",
                    "Erreur de saisie", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            utilisateur nouvelUser = new utilisateur(0, login.trim(), motDePasse);
            DBUtilisateur.addUser(nouvelUser);
            JOptionPane.showMessageDialog(null, "Utilisateur \"" + login + "\" ajouté avec succès.",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);
            remplirTableau(table); // Rafraîchir le tableau
        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur lors de l'ajout de l'utilisateur :\n" + ex.getMessage(),
                    "Erreur BDD", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Supprime l'utilisateur sélectionné dans le tableau.
     */
    public static void supprimerUtilisateur(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null,
                    "Veuillez sélectionner un utilisateur à supprimer.",
                    "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int id = (int) model.getValueAt(selectedRow, 0);
        String login = (String) model.getValueAt(selectedRow, 1);

        int confirmation = JOptionPane.showConfirmDialog(null,
                "Confirmer la suppression de l'utilisateur \"" + login + "\" ?",
                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            utilisateur u = DBUtilisateur.getUser(id);
            if (u != null) {
                DBUtilisateur.deleteUser(u);
                JOptionPane.showMessageDialog(null, "Utilisateur \"" + login + "\" supprimé.",
                        "Succès", JOptionPane.INFORMATION_MESSAGE);
                remplirTableau(table); // Rafraîchir le tableau
            }
        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur lors de la suppression :\n" + ex.getMessage(),
                    "Erreur BDD", JOptionPane.ERROR_MESSAGE);
        }
    }
}
