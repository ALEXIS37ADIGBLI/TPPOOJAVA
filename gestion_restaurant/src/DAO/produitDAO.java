/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import models.produit;
import models.categorie;
import outils.DBConnection;
import outils.DBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author bossmrpk
 */
public class produitDAO {
    public static List<produit> getAll() throws
            DBException,SQLException{
       String sql = "SELECT p.*, c.libelle FROM produit p JOIN categorie c ON p.id_categorie = c.id_categorie ORDER BY p.id_produit";
       List<produit> produit = new ArrayList<>();
       Connection connection ;
       connection = DBConnection.getConnection();
       PreparedStatement ps  = connection.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
           int id_produit =rs.getInt("id_produit");
           String nom = rs.getString("nom");
           double prix_vente = rs.getDouble("prix_vente");
           int stock_actuel = rs.getInt("stock_actuel");
           int seuil_alerte = rs.getInt("seuil_alerte");
           int id_categorie = rs.getInt("id_categorie");
           String libelle = rs.getString("libelle");
           /*Constituer le produit*/
           categorie c  = new categorie(id_categorie,libelle);
           c.setId_categorie(id_categorie);
           produit p = new produit(id_produit, nom, prix_vente, stock_actuel, seuil_alerte, id_categorie);
           p.setId_produit(id_produit);
           p.setNom(nom);
           p.setPrix_vente(prix_vente);
           p.setStock_actuel(stock_actuel);
           p.setSeuil_alerte(seuil_alerte);
           p.setCategorie(id_categorie);
           produit.add(p);
           
       }
       return produit;
       
        
    }
    public static produit get(String produitnom) throws 
            DBException,SQLException{
                String sql = "SELECT p.* FROM produit p JOIN categorie c ON p.id_categorie = c.id_categorie WHERE p.nom = ?";
                Connection connection;
                connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, produitnom);
                ResultSet rs =ps.executeQuery();
                if(rs.next()){
                    int id_produit = rs.getInt("id_produit");
                    String nom = rs.getString("nom");
                    double prix_vente = rs.getDouble("prix_vente");
                    int stock_actuel = rs.getInt("stock_actuel");
                    int seuil_alerte = rs.getInt("seuil_alerte");
                    int id_categorie = rs.getInt("id_categorie");
                    rs.close();
                    produit p = new produit(id_produit, nom, prix_vente, stock_actuel, seuil_alerte, id_categorie);
                   
                    return p;
                    
                }else{
                   rs.close();
                   return null;
                }
            }
    
    public static void addproduit(produit p )
            throws DBException,SQLException{
        String sql = "INSERT INTO produit (nom,prix_vente,stock_actuel,seuil_alerte,id_categorie)"+" VALUES(?,?,?,?,?)";
        Connection connection = DBConnection.getConnection() ;
        PreparedStatement ps  = connection.prepareStatement(sql);
        ps.setString(1, p.getNom());
        ps.setDouble(2,p.getPrix_vente());
        ps.setInt(3,p.getStock_actuel());
        ps.setInt(4,p.getSeuil_alerte());
        ps.setInt(5,p.getCategorie());
        ps.executeUpdate();
    }
    
    public static void updateproduit(produit p ) throws DBException,SQLException{
        String sql = "UPDATE produit SET nom=?, prix_vente=?, stock_actuel=?, seuil_alerte=?, id_categorie=? WHERE id_produit=?";

        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getNom());
        ps.setDouble(2,p.getPrix_vente());
        ps.setInt(3,p.getStock_actuel());
        ps.setInt(4,p.getSeuil_alerte());
        ps.setInt(5,p.getCategorie());
        ps.setInt(6,p.getId_produit());
        ps.executeUpdate();
       
    }
    
    public static void deleteproduit(produit p) throws DBException,SQLException{
        String sql = "DELETE FROM produit "+"WHERE id_produit=?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, p.getId_produit());
        ps.executeUpdate();
    }
    
            
            
    
}
