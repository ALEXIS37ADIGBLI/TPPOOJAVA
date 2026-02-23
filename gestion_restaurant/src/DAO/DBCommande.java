/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.commande;
import outils.DBConnection;
import outils.DBException;

/**
 *
 * @author wilfried
 */
public class DBCommande {

    public static List<commande> getAll() throws DBException, SQLException {
        List<commande> comds = new ArrayList<>();
        String SQLQuery = "SELECT * FROM commande ORDER BY id_commande";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLQuery);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_commande");
            LocalDateTime date = rs.getObject("date_commande", LocalDateTime.class);
            String etat = rs.getString("etat");

            commande comd = new commande(id, date, etat);
            comds.add(comd);
        }
        con.close();
        return comds;
    }

    public static commande getUser(int id_comd) throws DBException, SQLException {
        String SQLQuery = "SELECT * FROM commande WHERE id_commande=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLQuery);
        ps.setInt(1, id_comd);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id_commande");
            LocalDateTime date = rs.getObject("date_commande", LocalDateTime.class);
            String etat = rs.getString("etat");

            rs.close();
            commande comd = new commande(id, date, etat);
            return comd;

        } else {
            rs.close();
            return null;
        }
    }

    public static void addUser(commande comd) throws DBException, SQLException {
        String SQLQuery = "INSERT INTO commande (date,etat)" + " VALUES(?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLQuery);
        ps.setObject(1, comd.getDate_commande());
        ps.setString(2, comd.getEtat());
        ps.executeUpdate();
    }

    public static void updateUser(commande comd) throws DBException, SQLException {
        String sql = "UPDATE livre SET "
                + "date=?, "
                + "etat=? ,"
                + " WHERE id_commande=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, comd.getDate_commande());
        ps.setString(2, comd.getEtat());
        ps.setInt(3, comd.getId_commande());
        ps.executeUpdate();
    }

    public static void deleteLivre(commande comd) throws DBException, SQLException {
        String SQLQuery = "DELETE FROM commande "
                + " WHERE id_commande=?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLQuery);
        ps.setLong(1, comd.getId_commande());
        ps.executeUpdate();

    }

    // Pour obtenir le nombre de ventes aujourd'hui
    public static int countSalesToday() throws DBException, SQLException {
        String sql = "SELECT COUNT(*) FROM COMMANDE WHERE CAST(date_commande AS DATE) = CURRENT_DATE";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Pour obtenir le revenu total (somme de tous les montants des lignes)
    public static double getTotalRevenue() throws DBException, SQLException {
        String sql = "SELECT SUM(montant_ligne) FROM LIGNE_COMMANDE";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }
    
    public static double getTotatRevenueDay() throws DBException, SQLException {
        String sql = "SELECT SUM(total) FROM commande WHERE DATE(date_commande) = CURDATE() AND etat = 'VALIDÉE'";
        Connection conn = DBConnection.getConnection(); 
              PreparedStatement ps = conn.prepareStatement(sql); 
              ResultSet rs = ps.executeQuery(); 
              if (rs.next()) {
                return rs.getDouble(1);
            }
        return 0.0;

       }
    
//     public static double getTotatRevenuePeriod(java.util.Date debut, java.util.Date fin) throws SQLException, DBException {
//    // La requête est correcte selon votre structure
//    String sql = "SELECT SUM(total) FROM commande WHERE date_commande BETWEEN ? AND ? AND etat = 'VALIDÉE'";
//    
//    try (Connection conn = DBConnection.getConnection();
//         PreparedStatement ps = conn.prepareStatement(sql)) {
//        
//        // On convertit les java.util.Date en java.sql.Timestamp pour la colonne datetime
//        ps.setTimestamp(1, new java.sql.Timestamp(debut.getTime()));
//        ps.setTimestamp(2, new java.sql.Timestamp(fin.getTime()));
//        
//        try (ResultSet rs = ps.executeQuery()) {
//            if (rs.next()) {
//                return rs.getDouble(1); // Retourne le total decimal(12,2)
//            }
//        }
//    }
//    return 0.0;
//}
    
        public static double getTotatRevenuePeriod(int nombreMois) throws SQLException, DBException {
            String sql = "SELECT SUM(total) FROM commande " +
                         "WHERE date_commande >= DATE_SUB(NOW(), INTERVAL ? MONTH) " +
                         "AND etat = 'VALIDÉE'";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, nombreMois);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
    
    }
        
public static void chargerBestVente(DefaultTableModel model, int nombreMois) throws SQLException, DBException {
    model.setRowCount(0);
    System.out.println(">>> chargerBestVente démarré"); // AJOUT
    
    String sql = "SELECT p.id_produit, p.nom, SUM(l.quantite), SUM(l.montant_ligne) " +
                 "FROM ligne_commande l " +
                 "JOIN commande c ON l.id_commande = c.id_commande " +
                 "JOIN produit p ON l.id_produit = p.id_produit " +
                 "WHERE c.date_commande >= DATE_SUB(NOW(), INTERVAL ? MONTH) " +
                 "AND c.etat = 'VALIDÉE' " +
                 "GROUP BY p.id_produit, p.nom " +
                 "ORDER BY SUM(l.quantite) DESC LIMIT 5";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, nombreMois);
        ResultSet rs = ps.executeQuery();
        System.out.println(">>> Requête exécutée"); // AJOUT
        int count = 0;
        while (rs.next()) {
            count++;
            System.out.println(">>> Ligne : " + rs.getString(2)); // AJOUT
           model.addRow(new Object[]{
    rs.getInt(1),          // ID
    rs.getString(2),       // Nom
    rs.getInt(3),          // Quantité
    rs.getDouble(4)        // Revenu -> double au lieu de String formaté
});
        }
        System.out.println(">>> Total : " + count + " lignes"); // AJOUT
    }
}
}
          

        
  
