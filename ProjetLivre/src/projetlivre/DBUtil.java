/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kpaka.batazi
 */
public class DBUtil {
    private  static Connection connection;
    private DBUtil() {
        
    }
    public static synchronized Connection getConnection()
            throws DBException,SQLException {
        if(connection!=null){
            return connection;
        }
        else {
            String url="jdbc:mysql://localhost:3306/livreDB";
            String username="root";
            String password="";
            connection=DriverManager.getConnection(url,username,password);
            return connection;
            
        }
    }
    public static synchronized void closeConnection()
            throws DBException, SQLException {
        if(connection!=null){
            connection.close();
        }
    }
    
}
