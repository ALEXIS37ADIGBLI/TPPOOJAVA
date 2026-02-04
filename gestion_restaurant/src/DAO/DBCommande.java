/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.commande;
import outils.DBConnection;
import outils.DBException;

/**
 *
 * @author wilfried
 */
public class DBCommande {
     public static List<commande> getAll() throws DBException, SQLException{
        List<commande> comds = new ArrayList<>();
        String SQLQuery = "SELECT * FROM commande ORDER BY id_commande";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLQuery);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id_commande");
            LocalDateTime date = rs.getObject("date_commande", LocalDateTime.class);
            String etat = rs.getString("etat");
            
            commande comd = new commande(id, date, etat);
            comds.add(comd);  
        }
        con.close();
        return comds;
    }
    
   public static commande getUser(int id_comd) throws DBException, SQLException{
       String SQLQuery = "SELECT * FROM commande WHERE id_commande=?";
       Connection con = DBConnection.getConnection();
       PreparedStatement ps = con.prepareStatement(SQLQuery);
       ps.setInt(1, id_comd);
       ResultSet rs = ps.executeQuery();
       
       if(rs.next()){
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
    
    public static void addUser(commande comd) throws DBException, SQLException{
        String SQLQuery="INSERT INTO commande (date,etat)"+" VALUES(?,?)";
        Connection con =DBConnection.getConnection();
        PreparedStatement ps= con.prepareStatement(SQLQuery);
        ps.setObject(1, comd.getDate_commande());
        ps.setString(2, comd.getEtat());
        ps.executeUpdate();
    }
    
     public static void updateUser(commande comd) throws DBException, SQLException {
        String sql="UPDATE livre SET "
                +"date=?, "
                +"etat=? ,"
                +" WHERE id_commande=?";
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1,comd.getDate_commande());
        ps.setString(2,comd.getEtat());
        ps.setInt(3,comd.getId_commande());
        ps.executeUpdate();               
    }
     
    public static void deleteLivre(commande comd) throws DBException, SQLException {
        String SQLQuery="DELETE FROM commande "
                +" WHERE id_commande=?";
        
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(SQLQuery);
        ps.setLong(1, comd.getId_commande());
        ps.executeUpdate();  
         
    }
    

    // Pour obtenir le nombre de ventes aujourd'hui
    public static int countSalesToday() throws DBException, SQLException {
        String sql = "SELECT COUNT(*) FROM COMMANDE WHERE CAST(date_commande AS DATE) = CURRENT_DATE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // Pour obtenir le revenu total (somme de tous les montants des lignes)
    public static double getTotalRevenue() throws DBException, SQLException {
        String sql = "SELECT SUM(montant_ligne) FROM LIGNE_COMMANDE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }

}
