/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.produitDAO;
import DAO.CategorieDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import models.categorie;
import models.produit;
import outils.DBException;
import vue.UIProduit;

/**
 *
 * @author wilfried
 */
public class produitController {

    public static void AddProduit(String nom, double prix, int stock, int seuil, String libelle) throws DBException {

        int id_cat = CategorieDAO.getCat(libelle);
//        categorie cat = new categorie(id_cat, libelle);

        try {
            produit p = new produit(nom, prix, stock, seuil, id_cat);
            produitDAO.addproduit(p);
        } catch (DBException | SQLException ex) {
            System.getLogger(UIProduit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public static void UpdateProduit(JTable listeprod, String nom, double prix, int stock, int seuil, String cat_lib) {
        try {
            int id_cat = CategorieDAO.getCat(cat_lib);

            int i = listeprod.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) listeprod.getModel();
            int id_prod = (Integer) model.getValueAt(i, 0);
            produit p = new produit(id_prod, nom, prix, stock, seuil, id_cat);
            produitDAO.updateproduit(p);
        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur: " + ex);
            System.getLogger(produitController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public static void DeleteProduit(JTable listeprod) {
        try {
            int i = listeprod.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) listeprod.getModel();
            int id_prod = (Integer) model.getValueAt(i, 0);
            produitDAO.deleteproduit(id_prod);
        } catch (DBException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur: " + ex);
            System.getLogger(produitController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void remplirTableau(JTable table) throws DBException, SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<produit> dataArray = (ArrayList<produit>) DAO.produitDAO.getAll();
        model.setRowCount(0);
        Object[] rows = new Object[6];

        for (int i = 0; i < dataArray.size(); i++) {
            String lib = CategorieDAO.getlib(dataArray.get(i).getCategorie());
            rows[0] = dataArray.get(i).getId_produit();
            rows[1] = dataArray.get(i).getNom();
//            rows[2] = dataArray.get(i).getCategorie();
            rows[2] = lib;
            rows[3] = dataArray.get(i).getPrix_vente();
            rows[4] = dataArray.get(i).getStock_actuel();
            rows[5] = dataArray.get(i).getSeuil_alerte();
            model.addRow(rows);
        }

    }

    public static void effacerEcran(JTextField nom, JTextField prix, JComboBox cat, JTextField qte, JTextField seuil) {
        nom.setText("");
        prix.setText("");
        qte.setText("");
        seuil.setText("");

        if (cat.getItemCount() > 0) {
            cat.setSelectedIndex(0);
        }
    }

    public static boolean ControlString(JTextField elem) {
        String texte = elem.getText().trim(); // .trim() enlève les espaces inutiles au début et à la fin

        if (texte.isEmpty()) {
            System.out.println("Saisie vide");
            return false;
        }

        /* * 2. Vérifier s'il n'y a que des lettres (et espaces)
     * La regex "^[a-zA-ZÀ-ÿ ]+$" signifie :
     * ^ : début de la chaîne
     * [a-zA-ZÀ-ÿ ] : autorise lettres minuscules, majuscules, accents et espaces
     * + : au moins un caractère
     * $ : fin de la chaîne
         */
//        if (!texte.matches("^[a-zA-ZÀ-ÿ1-9 ]+$")) {
//            System.out.println("il y a presence de chiffre dans votre saisie");
//            return false;
//        }
        return true; // Si on arrive ici, tout est bon
    }

    public static boolean ControlInt(JTextField champ) {
        try {
            int valeur = Integer.parseInt(champ.getText().trim());
            if (valeur < 0) {
                System.out.println(champ.getName() + "ne peut pas etre negative");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println(champ.getName() + "doit être un nombre entier valide.");
            return false;
        }
    }

    public static boolean ControleDouble(JTextField champ) {
        try {
            double valeur = Double.parseDouble(champ.getText().trim().replace(',', '.')); // Gère les virgules
            if (valeur < 0) {
                System.out.println(champ.getName() + "ne peut pas etre negative");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println(champ.getName() + "doit être un nombre entier valide.");
            return false;
        }
    }

    public static boolean ControleAllSaisie(JTextField produitNom, JTextField prix, JTextField stock_actuel, JTextField seuil_alerte, JComboBox categorie) {

        if (produitNom.getText().trim().isEmpty() || prix.getText().trim().isEmpty() || stock_actuel.getText().trim().isEmpty() || seuil_alerte.getText().trim().isEmpty() || categorie.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Vous n'avez pas renseigné une information sur le produit");
            return false;
        }
        if (!produitController.ControlString(produitNom)) {
            JOptionPane.showMessageDialog(null, "Vous avez mal saisie le nom du produit");
            return false;
        }
        if (!produitController.ControleDouble(prix)) {
            JOptionPane.showMessageDialog(null, "Vous avez mal saisie le prix du produit");
            return false;
        }
        if (!produitController.ControlInt(stock_actuel)) {
            JOptionPane.showMessageDialog(null, "Vous avez mal saisie le stock du produit");
            return false;
        }
        if (!produitController.ControlInt(seuil_alerte)) {
            JOptionPane.showMessageDialog(null, "Vous avez mal saisie le seuil du produit");
            return false;
        }

        return true;
    }

    public static void chargerCategories(JComboBox<String> combo) throws DBException, SQLException {
        // 1. On vide la combo box actuelle
        combo.removeAllItems();

        // 2. On ajoute un item par défaut (optionnel)
        combo.addItem("Choisir une catégorie...");

        // 3. Récupération des données via le DAO (à adapter selon ton DAO catégorie)
        // Ici, je suppose que tu as une méthode getAllCategories() qui renvoie une List de String ou d'objets
        List<categorie> categories = DAO.CategorieDAO.lister();

        for (categorie cat : categories) {
            combo.addItem(cat.getLibelle());
        }
    }

    public static void remplirChampt(JTable listeprod, JTextField nom, JTextField prix, JComboBox cat, JTextField stock, JTextField seuil) {
        int i = listeprod.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) listeprod.getModel();
        nom.setText(model.getValueAt(i, 1).toString());
        cat.setSelectedItem(model.getValueAt(i, 2));
        prix.setText(model.getValueAt(i, 3).toString());
        stock.setText(model.getValueAt(i, 4).toString());
        seuil.setText(model.getValueAt(i, 5).toString());
    }
}
