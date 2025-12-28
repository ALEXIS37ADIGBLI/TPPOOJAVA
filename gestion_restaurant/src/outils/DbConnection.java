/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package outils;

import java.sql.*;

/**
 *
 * @author Xisclever
 */
public class DbConnection {
    
    private static Connection connection= null;
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3307/gestion_restaurant";
    private static final String USER = "root";
    private static final String PASSWORD = "will";
    
    private DbConnection(){}
    public static Connection getConnection() throws DBException {
        try{
            //verifie si la connection n'existe pas encore ou est ferme
            if (connection == null || connection.isClosed()){
            
                // Chargement du pilote JDBC Mysql
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                //creation de la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            
        } catch (ClassNotFoundException e){
            throw new DBException("Error : MySQL driver not found (mysql-connector).");
        } catch (SQLException e){
            throw new DBException("SQL Network connexion Error : "+e.getMessage());
        }
        return connection;
    }  
    
    public static void closeConnection() throws DBException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DBException("SQL Network connexion Error : "+e.getMessage());
        }
    }
}
