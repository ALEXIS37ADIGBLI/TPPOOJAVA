package controller;

import DAO.produitDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import outils.DBException;
import vue.MainMenuView;

/**
 * @author Xisclever
 */
public class DashboardController {

    private MainMenuView view;

    public DashboardController(MainMenuView view) {
        this.view = view;

        // Chargement des statistiques et vérification des alertes au démarrage
        chargerStatistiques();
        verifierAlertes();

        // Configuration des boutons de navigation
        initNavigation();
    }

    // Récupère les données depuis la BDD et les affiche dans les cartes du dashboard
    private void chargerStatistiques() {
        try {
            int totalProduits = produitDAO.countTotalProducts();
            view.getLblTotalProduits().setText(String.valueOf(totalProduits));

            int stockFaible = produitDAO.countLowStock();
            view.getLblStockFaible().setText(String.valueOf(stockFaible));

            int ventes = DAO.DBCommande.countSalesToday();
            view.getLblVentesAujourdhui().setText(String.valueOf(ventes));

            double revenu = DAO.DBCommande.getTotalRevenue();
            view.getLblRevenueTotal().setText(String.format("%.2f FCFA", revenu));

        } catch (DBException | SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Affiche ou cache le panneau d'alerte selon le nombre de produits en stock faible
    private void verifierAlertes() {
        try {
            int stockFaible = produitDAO.countLowStock();

            if (stockFaible > 0) {
                view.getPanelAlerte().setVisible(true);
                view.getLblMessageAlerte().setText("<html><b>⚠ Alerte de Stock</b><br/>"
                    + stockFaible + " produit(s) sont en dessous du seuil. Vérifiez les stocks.</html>");
            } else {
                view.getPanelAlerte().setVisible(false);
            }

        } catch (DBException | SQLException ex) {
            // En cas d'erreur, on cache simplement l'alerte
            view.getPanelAlerte().setVisible(false);
        }
    }

    // Configure les actions des boutons de navigation du menu
    private void initNavigation() {

        // Bouton Déconnexion : ferme le menu et retourne au login
        this.view.getBtnLogout().addActionListener(e -> {
            view.dispose();
            vue.LoginView loginPage = new vue.LoginView();
            new controller.LoginController(loginPage);
            loginPage.setVisible(true);
        });
    }
}