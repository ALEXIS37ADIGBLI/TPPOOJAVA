/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;

import business.Livre;
import dao.DBException;
import dao.DBUtil;
import dao.LivreDB;
import java.util.List;

/**
 *
 * @author kpaka.batazi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DBException {
        // TODO code application logic here
        //Affichage du mot de bienvenue
        Console.displayNewLine();
        Console.display("Bienvenu dans la Bibliothèqe");
        
        displayMenu();
        
        String action="";
        while (!action.equalsIgnoreCase("exit")) {
            // get the input from the user
            action = Console.getString("Entrer une commande: ");
            Console.displayNewLine();

            if (action.equalsIgnoreCase("list")) {
                displayAllLivres();
            } else if (action.equalsIgnoreCase("add")) {
                addLivre();
            } else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) {
                deleteLivre();
            } else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
                Console.display("Bye.\n");
            } else {
                Console.display("Error! Not a valid command.\n");
            }
        }

    }

    public static void displayMenu() {
        Console.display("MENU");
        Console.display("list -Afficher tous les livres");
        Console.display("add -Ajouter un livre");
        Console.display("del -Supprimer un livre");
        Console.display("help -Aide");
        Console.display("exit -Sortir du programme!");
    }
    
    public static void displayAllLivres() {
        Console.display("LIVRE LIST");

        List<Livre> livres = null;
        try {
            livres = LivreDB.getAll();
        } catch (DBException e) {
            Console.display(e + "\n");
        }
        
        if (livres == null) {
            Console.display("Erreur! Impossible de récuperer les livres.\n");
        } else {
            Livre l;
            StringBuilder sb = new StringBuilder();
            for (Livre livre : livres) {
                l = livre;
                sb.append(StringUtil.padWithSpaces(
                        l.getCode(), 12));
                sb.append(StringUtil.padWithSpaces(
                        l.getDescription(), 34));
                sb.append(l.getPrix());
                sb.append("\n");
            }
            Console.display(sb.toString());
        }
    }
    
    public static void addLivre() {
        String code = Console.getString("Entrer le code du livre: ");
        String description = Console.getString("Entrer la description du livre: ");
        double price = Console.getDouble("Entrer le prix: ");

        Livre livre = new Livre();
        livre.setCode(code);
        livre.setDescription(description);
        livre.setPrix(price);
            
        try {
            LivreDB.addLivre(livre);
            Console.display(livre.getDescription()
                    + " a été ajouté à la BD.\n");
        } catch (DBException e) {
            Console.display("Erreur! Impossible d'ajouter un livre.");
            Console.display(e + "\n");
        }
    }
    
    public static void deleteLivre() throws DBException {
        String code = Console.getString("Entrer le code du Livre: ");

        Livre livre;
        try {
            livre = LivreDB.get(code);
            if (livre == null) {
                throw new Exception("Livre non trouvé.");
            }
        } catch (Exception e) {
            Console.display("Error! Impossible de supprimer le livre.");
            Console.display(e + "\n");
            return;
        }
        
        LivreDB.deleteLivre(livre);
        
        Console.display(livre.getDescription() + " Est supprimé de la BD.\n");
    }
    
    public static void exit() {
        try {
            DBUtil.closeConnection();
        } catch (DBException e) {
            Console.display("Erreur! Impossible de fermer la connection.");
            Console.display(e + "\n");
        }
        System.out.println("Bye.\n");
        System.exit(0);
    }
}
