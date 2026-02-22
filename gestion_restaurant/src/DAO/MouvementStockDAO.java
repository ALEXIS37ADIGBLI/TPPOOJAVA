package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.mouvement_stock;
import models.produit;
import outils.DBConnection;
import outils.DBException;

/**
 *
 * @author Xisclever
 */
public class MouvementStockDAO {
    
        private produitDAO produitDAO; 
        
        

    public MouvementStockDAO() {
        produitDAO = new produitDAO(); 
    }

   public void ajouter(mouvement_stock m) throws DBException {

    String insertQuery = "INSERT INTO MOUVEMENT_STOCK (id_produit, type_mouvement, quantite, date_mouvement, motif) VALUES (?, ?, ?, ?, ?)";
    String updateStockQuery = "UPDATE produit SET stock_actuel = ? WHERE id_produit = ?";

    try (Connection conn = DBConnection.getConnection()) {

        conn.setAutoCommit(false); 

        if (m.getQuantite() <= 0) {
            throw new DBException("La quantité doit être supérieure à 0 !");
        }

       produit p = produitDAO.getById(m.getId_produit(), conn);
        if (p == null) {
            throw new DBException("Produit introuvable");
        }

        int stockActuel = p.getStock_actuel();
        int nouveauStock;

        if (m.getType_mouvement().equals("ENTRÉE")) {
            nouveauStock = stockActuel + m.getQuantite();

        } else if (m.getType_mouvement().equals("SORTIE")) {

            if (m.getQuantite() > stockActuel) {
                throw new DBException("Stock insuffisant !");
            }

            nouveauStock = stockActuel - m.getQuantite();

        } else {
            throw new DBException("Type de mouvement invalide !");
        }


        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setInt(1, m.getId_produit());
            pstmt.setString(2, m.getType_mouvement());
            pstmt.setInt(3, m.getQuantite());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(m.getDate_mouvement()));
            pstmt.setString(5, m.getMotif());
            pstmt.executeUpdate();
        }


        try (PreparedStatement pstmt = conn.prepareStatement(updateStockQuery)) {
            pstmt.setInt(1, nouveauStock);
            pstmt.setInt(2, m.getId_produit());
            pstmt.executeUpdate();
        }

        conn.commit();

    } catch (Exception e) {
        throw new DBException("Erreur lors de l'ajout du mouvement : " + e.getMessage());
    }
}

    public List<mouvement_stock> listerTout() throws DBException {
        List<mouvement_stock> liste = new ArrayList<>();
        String query = "SELECT * FROM MOUVEMENT_STOCK ORDER BY date_mouvement DESC";

        try (Connection conn = DBConnection.getConnection();
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

    public void modifier(mouvement_stock m) throws DBException {
        String query = "UPDATE MOUVEMENT_STOCK SET id_produit = ?, type_mouvement = ?, quantite = ?, motif = ? WHERE id_mouvement = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, m.getId_produit());
            pstmt.setString(2, m.getType_mouvement());
            pstmt.setInt(3, m.getQuantite());
            pstmt.setString(4, m.getMotif());
            pstmt.setInt(5, m.getId_mouvement());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erreur lors de la modification du mouvement : " + e.getMessage());
        }
    }

    public void supprimer(int idMouvement) throws DBException {
        String query = "DELETE FROM MOUVEMENT_STOCK WHERE id_mouvement = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idMouvement);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erreur lors de la suppression du mouvement : " + e.getMessage());
        }
    }

    public mouvement_stock chercherParId(int idMouvement) throws DBException {
        String query = "SELECT * FROM MOUVEMENT_STOCK WHERE id_mouvement = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idMouvement);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new mouvement_stock(
                        rs.getInt("id_mouvement"),
                        rs.getInt("id_produit"),
                        rs.getString("type_mouvement"),
                        rs.getInt("quantite"),
                        rs.getTimestamp("date_mouvement").toLocalDateTime(),
                        rs.getString("motif")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DBException("Erreur lors de la recherche du mouvement : " + e.getMessage());
        }
        return null;
    }

    public List<mouvement_stock> listerParProduit(int idProduit) throws DBException {
        List<mouvement_stock> liste = new ArrayList<>();
        String query = "SELECT * FROM MOUVEMENT_STOCK WHERE id_produit = ? ORDER BY date_mouvement DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idProduit);

            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            throw new DBException("Erreur lors du chargement des mouvements par produit : " + e.getMessage());
        }
        return liste;
    }
}