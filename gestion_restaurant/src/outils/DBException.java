/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package outils;

/**
 *
 * @author Xisclever
 */
public class DBException extends Exception {
    DBException(){}
    
    DBException(Exception e){
        super(e);
    }
    
    public DBException(String message) {
        super(message);
    }
    
}
