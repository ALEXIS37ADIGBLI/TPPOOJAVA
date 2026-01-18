/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.mouvement_stock;
import outils.DbConnection;
import outils.DBException;
/**
 *
 * @author Xisclever
 */
public class MouvementStockDAO {
    // 1. Ajouter un mouvement (Entrée ou Sortie)
    public void ajouter(mouvement_stock m) throws DBException {
        String query = "INSERT INTO MOUVEMENT_STOCK (id_produit, type_mouvement, quantite, motif) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, m.getId_produit());
            pstmt.setString(2, m.getType_mouvement());
            pstmt.setInt(3, m.getQuantite());
            pstmt.setString(4, m.getMotif());

            pstmt.executeUpdate();
            
            
        } catch (SQLException e) {
            throw new DBException("Erreur lors de l'ajout du mouvement : " + e.getMessage());
        }
    }

    // 2. Lister l'historique des mouvements
    public List<mouvement_stock> listerTout() throws DBException {
        List<mouvement_stock> liste = new ArrayList<>();
        String query = "SELECT * FROM MOUVEMENT_STOCK ORDER BY date_mouvement DESC";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                mouvement_stock mouvement = new mouvement_stock(
                    rs.getInt("id_mouvement"),
                    rs.getInt("id_produit"),
                    rs.getString("type_mouvement"),
                    rs.getInt("quantite"),
                    rs.getTimestamp("date_mouvement").toLocalDateTime(),
                    rs.getString("motif")
                );
                
                liste.add(mouvement);
            }
        } catch (SQLException e) {
            throw new DBException("Erreur lors de la récupération des mouvements : " + e.getMessage());
        }
        return liste;
    }
}
