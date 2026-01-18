/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import business.Livre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kpaka.batazi
 */
public class LivreDB {
    public static List<Livre> getAll() throws
            DBException,SQLException {
        String sql="SELECT * FROM Livre ORDER BY LivreID";
        List<Livre> livres=new ArrayList<>();
        Connection connection;
        connection=DBUtil.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            int livreID=rs.getInt("LivreID");
            String code=rs.getString("Code");
            String description=rs.getString("Description");
            double prix=rs.getDouble("Prix");
            /*Constituer le livre*/
            Livre l=new Livre();
            l.setId(livreID);
            l.setCode(code);
            l.setDescription(description);
            l.setPrix(prix);
            livres.add(l);
        }
        return livres;
    }
    public static Livre get(String livrecode)
            throws DBException,SQLException{
        String sql="SELECT * FROM Livre WHERE code=?";
        Connection connection=DBUtil.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, livrecode);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            long livreID=rs.getLong("livreID");
            String description=rs.getString("Description");
            double prix=rs.getDouble("Prix");
            //String code=rs.getString("Code");
            
            rs.close();
            Livre l=new Livre();
            l.setCode(livrecode);
            l.setDescription(description);
            l.setId(livreID);
            l.setPrix(prix);
            return l;
        } else {
            rs.close();
            return null;
        }
        
        
    }
    public static void addLivre(Livre l)
            throws DBException, SQLException {
        String sql="INSERT INTO Livre (Code,Description,Prix)"
                +" VALUES(?,?,?)";
        Connection connection=DBUtil.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,l.getCode());
        ps.setString(2,l.getDescription());
        ps.setDouble(3, l.getPrix());
        ps.executeUpdate();
        
                
                
    }
        public static void updateLivre(Livre l)
            throws DBException, SQLException {
        String sql="UPDATE Livre SET "
                +"Code=?, "
                +"Description=? ,"
                +"Prix=? "
                +" WHERE LivreID=?";
        Connection connection=DBUtil.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,l.getCode());
        ps.setString(2,l.getDescription());
        ps.setDouble(3, l.getPrix());
        ps.setLong(4, l.getId());
        ps.executeUpdate();        
                
    }
          public static void deleteLivre(Livre l)
            throws DBException, SQLException {
        String sql="DELETE FROM Livre "
                +" WHERE LivreID=?";
        Connection connection=DBUtil.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setLong(1, l.getId());
        ps.executeUpdate();        
                
    }
}
