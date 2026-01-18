package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.categorie;
import models.ligne_commande;
import models.commande;
import models.produit;
import outils.DbConnection;
import outils.DBException;

/**
 *
 * @author Xisclever
 */
public class LigneCommandeDAO {

    public void ajouter(ligne_commande lc) throws DBException {
        String query = "INSERT INTO LIGNE_COMMANDE (id_commande, id_produit, quantite, prix_unitaire, montant_ligne) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, lc.getCommande().getId_commande());
            pstmt.setInt(2, lc.getProduit().getId_produit());
            pstmt.setInt(3, lc.getQuantite());
            pstmt.setDouble(4, lc.getPrix_unitaire());
            pstmt.setDouble(5, lc.getMontant_ligne());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erreur lors de l'ajout de la ligne : " + e.getMessage());
        }
    }

    public List<ligne_commande> listerParCommande(int idCommandeSelectionnee) throws DBException {
        List<ligne_commande> liste = new ArrayList<>();
        
        String query = "SELECT lc.*, " +
                       "cmd.date_commande, cmd.etat, " +
                       "p.nom, p.prix_vente, p.stock_actuel, p.seuil_alerte, " +
                       "cat.id_categorie, cat.libelle " +
                       "FROM LIGNE_COMMANDE lc " +
                       "JOIN COMMANDE cmd ON lc.id_commande = cmd.id_commande " +
                       "JOIN PRODUIT p ON lc.id_produit = p.id_produit " +
                       "JOIN CATEGORIE cat ON p.id_categorie = cat.id_categorie " +
                       "WHERE lc.id_commande = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idCommandeSelectionnee);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    commande cmd = new commande(
                        rs.getInt("id_commande"),
                        rs.getTimestamp("date_commande").toLocalDateTime(),
                        rs.getString("etat")
                    );

                    categorie cat = new categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("libelle")
                    );

                    produit prod = new produit(
                        rs.getInt("id_produit"),
                        rs.getString("nom"),
                        rs.getDouble("prix_vente"),
                        rs.getInt("stock_actuel"),
                        rs.getInt("seuil_alerte"),
                        cat
                    );

                    ligne_commande lc = new ligne_commande(
                        rs.getInt("id_ligne"),
                        cmd,
                        prod,
                        rs.getInt("quantite"),
                        rs.getDouble("prix_unitaire"),
                        rs.getDouble("montant_ligne")
                    );
                    
                    liste.add(lc);
                }
            }
        } catch (SQLException e) {
            throw new DBException("Erreur lors du chargement des lignes de commande : " + e.getMessage());
        }
        return liste;
    }

    public void modifier(ligne_commande lc) throws DBException {
        String query = "UPDATE LIGNE_COMMANDE SET id_commande = ?, id_produit = ?, quantite = ?, prix_unitaire = ?, montant_ligne = ? WHERE id_ligne = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, lc.getCommande().getId_commande());
            pstmt.setInt(2, lc.getProduit().getId_produit());
            pstmt.setInt(3, lc.getQuantite());
            pstmt.setDouble(4, lc.getPrix_unitaire());
            pstmt.setDouble(5, lc.getMontant_ligne());
            pstmt.setInt(6, lc.getId_ligne());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erreur lors de la modification de la ligne : " + e.getMessage());
        }
    }

    public void supprimer(int idLigne) throws DBException {
        String query = "DELETE FROM LIGNE_COMMANDE WHERE id_ligne = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idLigne);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erreur lors de la suppression de la ligne : " + e.getMessage());
        }
    }

    public ligne_commande chercherParId(int idLigne) throws DBException {
        String query = "SELECT lc.*, " +
                       "cmd.date_commande, cmd.etat, " +
                       "p.nom, p.prix_vente, p.stock_actuel, p.seuil_alerte, " +
                       "cat.id_categorie, cat.libelle " +
                       "FROM LIGNE_COMMANDE lc " +
                       "JOIN COMMANDE cmd ON lc.id_commande = cmd.id_commande " +
                       "JOIN PRODUIT p ON lc.id_produit = p.id_produit " +
                       "JOIN CATEGORIE cat ON p.id_categorie = cat.id_categorie " +
                       "WHERE lc.id_ligne = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idLigne);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    commande cmd = new commande(
                        rs.getInt("id_commande"),
                        rs.getTimestamp("date_commande").toLocalDateTime(),
                        rs.getString("etat")
                    );

                    categorie cat = new categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("libelle")
                    );

                    produit prod = new produit(
                        rs.getInt("id_produit"),
                        rs.getString("nom"),
                        rs.getDouble("prix_vente"),
                        rs.getInt("stock_actuel"),
                        rs.getInt("seuil_alerte"),
                        cat
                    );

                    return new ligne_commande(
                        rs.getInt("id_ligne"),
                        cmd,
                        prod,
                        rs.getInt("quantite"),
                        rs.getDouble("prix_unitaire"),
                        rs.getDouble("montant_ligne")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DBException("Erreur lors de la recherche de la ligne : " + e.getMessage());
        }
        return null;
    }
}