/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

            // On extrait les IDs des objets imbriqués
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

    // 2. Lister les lignes d'une commande avec toutes les jointures
    public List<ligne_commande> listerParCommande(int idCommandeSelectionnee) throws DBException {
        List<ligne_commande> liste = new ArrayList<>();
        
        // Super jointure : Ligne -> Commande ET Ligne -> Produit -> Categorie
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
                    // a. On reconstruit l'objet Commande
                    commande cmd = new commande(
                        rs.getInt("id_commande"),
                        rs.getTimestamp("date_commande").toLocalDateTime(),
                        rs.getString("etat")
                    );

                    // b. On reconstruit l'objet Categorie pour le Produit
                    categorie cat = new categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("libelle")
                    );

                    // c. On reconstruit l'objet Produit
                    produit prod = new produit(
                        rs.getInt("id_produit"),
                        rs.getString("nom"),
                        rs.getDouble("prix_vente"),
                        rs.getInt("stock_actuel"),
                        rs.getInt("seuil_alerte"),
                        cat
                    );

                    // d. Enfin, on crée la Ligne de Commande
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
}
