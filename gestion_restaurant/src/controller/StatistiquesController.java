/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.LigneCommandeDAO;
import DAO.DBCommande;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import outils.DBConnection;
import models.produit;
import models.categorie;
import outils.DBConnection;
import outils.DBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vue.UIProduit;

/**
 *
 * @author wilfried
 */
public class StatistiquesController {
    
    public static void ChiffreDay(JLabel chiffreDay) throws DBException, SQLException{
        double chiffre = DBCommande.getTotatRevenueDay();
        chiffreDay.setText(Short.toString((short) chiffre)+" Euros");
    }
//    
   public static void ChiffrePeriode(JLabel chiffrePeriod, JComboBox combo, JTable tBest) throws SQLException, DBException {
    String period = (String) combo.getSelectedItem();
    System.out.println("Période sélectionnée : " + period); // AJOUT
    if (period == null) return;
    int mois = switch(period) {
        case "1 Mois" -> 1;
        case "3 Mois" -> 3;
        case "6 Mois" -> 6;
        case "1 An" -> 12;
        default -> 0;
    };
    System.out.println("Nombre de mois : " + mois); // AJOUT
    double chiffre = DBCommande.getTotatRevenuePeriod(mois);
    chiffrePeriod.setText(String.format("%.2f Euros", chiffre));
        System.out.println("Nombre de mois 1: " + mois); // AJOUT

    DefaultTableModel model = (DefaultTableModel) tBest.getModel();
        System.out.println("Nombre de mois 2: " + mois); // AJOUT

    DBCommande.chargerBestVente(model, mois);
}
   
   public static void remplirTableauAlertes(JTable table) throws DBException, SQLException {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    ArrayList<produit> dataArray = (ArrayList<produit>) DAO.produitDAO.getAlertes();
    
    model.setRowCount(0);
    Object[] rows = new Object[4]; // 4 au lieu de 3 !
    for (int i = 0; i < dataArray.size(); i++) {
        rows[0] = dataArray.get(i).getId_produit();
        rows[1] = dataArray.get(i).getNom();
        rows[2] = dataArray.get(i).getStock_actuel();
        rows[3] = dataArray.get(i).getSeuil_alerte(); // index 3 existe maintenant
        model.addRow(rows);
    }
}
    
    
}

