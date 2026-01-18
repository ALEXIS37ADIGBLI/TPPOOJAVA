/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import models.utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import outils.DBConnection;
import outils.DBException;

/**
 *
 * @author wilfried
 */
public class DBUtilisateur {
    
    public static List<utilisateur> getAll() throws DBException, SQLException{
        List<utilisateur> users = new ArrayList<>();
        String SQLQuery = "SELECT * FROM utilisateur ORDER BY id_utilisateur";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLQuery);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id_utilisateur");
            String login = rs.getString("login");
            String pw = rs.getString("mot_de_passe");
            
            utilisateur user = new utilisateur(id, login, pw);
            users.add(user);  
        }
        con.close();
        return users;
    }
    
   public static utilisateur getUser(int user_id) throws DBException, SQLException{
       String SQLQuery = "SELECT * FROM utilisateur WHERE id_utilisateur=?";
       Connection con = DBConnection.getConnection();
       PreparedStatement ps = con.prepareStatement(SQLQuery);
       ps.setInt(1, user_id);
       ResultSet rs = ps.executeQuery();
       
       if(rs.next()){
            int id = rs.getInt("id_utilisateur");
            String login = rs.getString("login");
            String pw = rs.getString("mot_de_passe");
            
            rs.close();
            utilisateur user = new utilisateur(id, login, pw); 
            return user;
            
        } else {
           rs.close();
           return null;
       }
   }
    
    public static void addUser(utilisateur user) throws DBException, SQLException{
        String SQLQuery="INSERT INTO utilisateur (login,mot_de_passe)"+" VALUES(?,?)";
        Connection con =DBConnection.getConnection();
        PreparedStatement ps= con.prepareStatement(SQLQuery);
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getMot_de_passe());
        ps.executeUpdate();
    }
    
     public static void updateUser(utilisateur user) throws DBException, SQLException {
        String sql="UPDATE utilisateur SET "
                +"login=?, "
                +"mot_de_passe=? ,"
                +" WHERE id_utilisateur=?";
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,user.getLogin());
        ps.setString(2,user.getMot_de_passe());
        ps.setDouble(3,user.getId_utilisateur());
        ps.executeUpdate();               
    }
     
    public static void deleteLivre(utilisateur user) throws DBException, SQLException {
        String SQLQuery="DELETE FROM utilisateur "
                +" WHERE id_utilisateur=?";
        
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(SQLQuery);
        ps.setLong(1, user.getId_utilisateur());
        ps.executeUpdate();  
         
    }
   
}
