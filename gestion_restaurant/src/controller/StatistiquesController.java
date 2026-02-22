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
import javax.swing.JOptionPane;
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
//    public static  void ChiffrePeriode(JLabel chiffrePeriod, JDateChooser debut, JDateChooser fin) throws SQLException, DBException{
//        try {
//        double chiffre = DBCommande.getTotatRevenuePeriod(debut.getDate(), fin.getDate());    
//        chiffrePeriod.setText(Short.toString((short) chiffre)+" Euros");
//     } catch (DBException | SQLException ex) {
//            System.getLogger(UIProduit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
//            JOptionPane.showMessageDialog(null, ex);
//    }
//    }
//    
    
}

