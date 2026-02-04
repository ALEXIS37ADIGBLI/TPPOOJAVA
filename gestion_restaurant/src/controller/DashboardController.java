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
        
        // Initialisation des données dynamiques
        chargerStatistiques();
        verifierAlertes();

        // Ecouteurs pour la navigation (Exemple)
        initNavigation();
    }

    private void chargerStatistiques() {
    try {
        // 1. Nombre total de produits
        int totalProduits = produitDAO.countTotalProducts();
        view.getLblTotalProduits().setText(String.valueOf(totalProduits));

        // 2. Produits en stock faible
        int stockFaible = produitDAO.countLowStock();
        view.getLblStockFaible().setText(String.valueOf(stockFaible));

        // 3. Ventes d'aujourd'hui (APPEL NOUVELLE MÉTHODE)
        int ventes = DAO.DBCommande.countSalesToday();
        view.getLblVentesAujourdhui().setText(String.valueOf(ventes));

        // 4. Revenu total (APPEL NOUVELLE MÉTHODE)
        double revenu = DAO.DBCommande.getTotalRevenue();
        // Formatage en monnaie (ex: 1 250.50 €)
        view.getLblRevenueTotal().setText(String.format("%.2f FCFA", revenu));

    } catch (DBException | SQLException ex) {
        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

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
            view.getPanelAlerte().setVisible(false);
        }
    }

    private void initNavigation() {
        // Exemple : Action sur le bouton "Produits"
        this.view.getBtnProduits().addActionListener(e -> {
            System.out.println("Navigation vers la gestion des produits...");
            // Logique pour changer de vue ou de panel ici
        });
        
        // Action sur le bouton "Déconnexion" (jButton8 dans votre code)
        this.view.getBtnLogout().addActionListener(e -> {
            view.dispose();
            // Logique pour réouvrir le LoginView
        });
    }
}