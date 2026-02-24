package controller;

import DAO.DBCommande;
import DAO.LigneCommandeDAO;
import DAO.produitDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.commande;
import models.ligne_commande;
import models.produit;
import outils.DBConnection;
import outils.DBException;

/**
 * @author obed
 */
public class CommandeController {

    // Liste temporaire des lignes ajoutées avant validation
    private static List<ligne_commande> lignesEnCours = new ArrayList<>();

    /**
     * Charge tous les produits disponibles dans la ComboBox.
     */
    public static void chargerProduits(JComboBox<String> combo) {
        combo.removeAllItems();
        combo.addItem("-- Choisir un produit --");
        try {
            List<produit> produits = produitDAO.getAll();
            for (produit p : produits) {
                // Afficher nom + stock pour informer l'utilisateur
                combo.addItem(p.getNom() + " (stock: " + p.getStock_actuel() + ")");
            }
        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur lors du chargement des produits :\n" + ex.getMessage(),
                    "Erreur BDD", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ajoute une ligne au tableau de commande (sans toucher à la BDD). La BDD
     * sera mise à jour seulement lors de la validation.
     */
    public static void ajouterLigne(JComboBox<String> combo, JTextField champQuantite,
            JTable tableau, JTextField champTotal) {
        // Vérifier qu'un produit est sélectionné
        int idx = combo.getSelectedIndex();
        if (idx <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Veuillez sélectionner un produit.",
                    "Sélection manquante", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Vérifier la quantité
        int quantite;
        try {
            quantite = Integer.parseInt(champQuantite.getText().trim());
            if (quantite <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Veuillez saisir une quantité valide (nombre entier > 0).",
                    "Quantité invalide", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Récupérer le produit sélectionné (idx-1 car on a l'item "-- Choisir --" en position 0)
        try {
            List<produit> produits = produitDAO.getAll();
            produit produitSelectionne = produits.get(idx - 1);

            // Vérifier le stock
            if (produitSelectionne.getStock_actuel() < quantite) {
                JOptionPane.showMessageDialog(null,
                        "Stock insuffisant ! Stock disponible : " + produitSelectionne.getStock_actuel(),
                        "Stock insuffisant", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Vérifier si le produit est déjà dans le tableau → on additionne les quantités
            for (ligne_commande lc : lignesEnCours) {
                if (lc.getProduit().getId_produit() == produitSelectionne.getId_produit()) {
                    int nouvelleQte = lc.getQuantite() + quantite;
                    if (produitSelectionne.getStock_actuel() < nouvelleQte) {
                        JOptionPane.showMessageDialog(null,
                                "Stock insuffisant pour cette quantité totale !",
                                "Stock insuffisant", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    lc.setQuantite(nouvelleQte);
                    lc.setMontant_ligne(nouvelleQte * lc.getPrix_unitaire());
                    rafraichirTableau(tableau, champTotal);
                    champQuantite.setText("1");
                    return;
                }
            }

            // Créer la ligne et l'ajouter à la liste temporaire
            double prixUnitaire = produitSelectionne.getPrix_vente();
            double montant = prixUnitaire * quantite;
            ligne_commande nouvelleLigne = new ligne_commande(0, null, produitSelectionne, quantite, prixUnitaire, montant);
            lignesEnCours.add(nouvelleLigne);

            rafraichirTableau(tableau, champTotal);
            champQuantite.setText("1");

        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur : " + ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Rafraîchit le tableau avec les lignes en cours et recalcule le total.
     */
    public static void rafraichirTableau(JTable tableau, JTextField champTotal) {
        DefaultTableModel model = (DefaultTableModel) tableau.getModel();
        model.setRowCount(0);

        double total = 0;
        for (ligne_commande lc : lignesEnCours) {
            model.addRow(new Object[]{
                lc.getProduit().getNom(),
                lc.getQuantite(),
                String.format("%.2f", lc.getPrix_unitaire()),
                String.format("%.2f", lc.getMontant_ligne()),
                "❌ Retirer"
            });
            total += lc.getMontant_ligne();
        }
        champTotal.setText(String.format("%.2f", total));
    }

    /**
     * Supprime une ligne du tableau (clic sur la ligne sélectionnée).
     */
    public static void retirerLigne(JTable tableau, JTextField champTotal) {
        int row = tableau.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null,
                    "Sélectionnez une ligne à retirer.",
                    "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        lignesEnCours.remove(row);
        rafraichirTableau(tableau, champTotal);
    }

    /**
     * Valide la commande : crée la commande en BDD, insère les lignes, le
     * trigger SQL se charge de décrémenter le stock.
     */
    public static void validerCommande(JTable tableau, JTextField champTotal, JComboBox<String> combo) {
        if (lignesEnCours.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "La commande est vide. Ajoutez au moins un produit.",
                    "Commande vide", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(null,
                "Confirmer la validation de la commande ?",
                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            // 1. Créer la commande et récupérer son ID généré
            String sqlCommande = "INSERT INTO commande (date_commande, etat, total) VALUES (NOW(), 'VALIDÉE', ?)";
            double total = lignesEnCours.stream().mapToDouble(ligne_commande::getMontant_ligne).sum();

            PreparedStatement psCommande = conn.prepareStatement(sqlCommande, Statement.RETURN_GENERATED_KEYS);
            psCommande.setDouble(1, total);
            psCommande.executeUpdate();

            ResultSet keys = psCommande.getGeneratedKeys();
            if (!keys.next()) {
                throw new SQLException("Impossible de récupérer l'ID de la commande.");
            }
            int idCommande = keys.getInt(1);

            // 2. Insérer chaque ligne (le trigger SQL gère la décrémentation du stock)
            String sqlLigne = "INSERT INTO ligne_commande (id_commande, id_produit, quantite, prix_unitaire, montant_ligne) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psLigne = conn.prepareStatement(sqlLigne);

            for (ligne_commande lc : lignesEnCours) {
                psLigne.setInt(1, idCommande);
                psLigne.setInt(2, lc.getProduit().getId_produit());
                psLigne.setInt(3, lc.getQuantite());
                psLigne.setDouble(4, lc.getPrix_unitaire());
                psLigne.setDouble(5, lc.getMontant_ligne());
                psLigne.executeUpdate();
            }

            JOptionPane.showMessageDialog(null,
                    "✅ Commande #" + idCommande + " validée avec succès !\nTotal : " + String.format("%.2f", total) + " FCFA",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);

            // 3. Réinitialiser
            annulerCommande(tableau, champTotal, combo);

        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur lors de la validation :\n" + ex.getMessage(),
                    "Erreur BDD", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Annule la commande en cours : vide le tableau et réinitialise.
     */
    public static void annulerCommande(JTable tableau, JTextField champTotal, JComboBox<String> combo) {
        int confirmation = JOptionPane.showConfirmDialog(null,
                "Annuler la commande en cours ? Toutes les lignes seront supprimées.",
                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        lignesEnCours.clear();
        DefaultTableModel model = (DefaultTableModel) tableau.getModel();
        model.setRowCount(0);
        champTotal.setText("0.00");
        combo.setSelectedIndex(0);
    }
}
