/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import models.produit;
import models.categorie;
import outils.DbConnection;
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
       String sql = "SELECT * FROM produit ORDER BY id_produit";
       List<produit> produit = new ArrayList<>();
       Connection connection ;
       connection = DbConnection.getConnection();
       PreparedStatement ps  = connection.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
           int id_produit =rs.getInt("id_produit");
           String nom = rs.getString("nom");
           double prix_vente = rs.getDouble("prix_vente");
           int stock_actuel = rs.getInt("stock_actuel");
           int seuil_alerte = rs.getInt("seuil_alerte");
           /*Constituer le produit*/
           produit p = new produit();
           
       }
       return produit;
       
        
    }
            
            
    
}
