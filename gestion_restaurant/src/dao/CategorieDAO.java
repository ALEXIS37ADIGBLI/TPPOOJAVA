/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
/**
 *
 * @author obed
 */
import models.categorie;
import outils.DBConnection;
import outils.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {

    
    public void ajouter(categorie cat) throws DBException {
        String sql = "INSERT INTO categorie(libelle) VALUES (?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cat.getLibelle());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erreur ajout catégorie : " + e.getMessage());
        }
    }

    
    public List<categorie> lister() throws DBException {
        List<categorie> liste = new ArrayList<>();
        String sql = "SELECT * FROM categorie";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_categorie");
                String libelle = rs.getString("libelle");

                categorie c = new categorie(id, libelle);
                liste.add(c);
            }

        } catch (SQLException e) {
            throw new DBException("Erreur lecture catégories : " + e.getMessage());
        }

        return liste;
    }
    
    public void modifier(categorie cat) throws DBException {
        String sql = "UPDATE categorie SET libelle=? WHERE id_categorie=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, cat.getLibelle());
            ps.setInt(2, cat.getId_categorie());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erreur modification categorie : " + e.getMessage());
        }
    }
    
     public void supprimer(int idCategorie) throws DBException {
        String sql = "DELETE FROM categorie WHERE id_categorie=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idCategorie);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erreur suppression categorie : " + e.getMessage());
        }
    }
    
}
