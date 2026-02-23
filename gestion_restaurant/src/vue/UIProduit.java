/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

import DAO.produitDAO;
import models.produit;
import java.util.List;
import controller.produitController;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import outils.DBException;

/**
 *
 * @author wilfried
 */
public class UIProduit extends javax.swing.JPanel implements Rafraichissable {

    /**
     * Creates new form UIProduit
     */
    private List<produit> ListP = null;

    public UIProduit() {
    initComponents();

    // === COULEURS DU THÈME ===
    java.awt.Color bleuFonce  = new java.awt.Color(11, 58, 102);
    java.awt.Color blanc       = new java.awt.Color(255, 255, 255);
    java.awt.Color grisClaire  = new java.awt.Color(243, 244, 246);
    java.awt.Color texteSombre = new java.awt.Color(31, 41, 55);
    java.awt.Color bordure     = new java.awt.Color(229, 231, 235);
    java.awt.Color bleuBtn     = new java.awt.Color(13, 79, 139);
    java.awt.Color vert        = new java.awt.Color(22, 163, 74);
    java.awt.Color rouge       = new java.awt.Color(220, 38, 38);
    java.awt.Color grisBtnN    = new java.awt.Color(107, 114, 128);

    // === FOND GÉNÉRAL ===
    this.setBackground(grisClaire);

    // === TITRE ===
    jLabel1.setText("Gestion des produits");
    jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
    jLabel1.setForeground(bleuFonce);

    // === PANEL FORMULAIRE ===
    jPanel1.setBackground(blanc);
    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));

    // Labels
    jLabel2.setText("Nom du produit");
    jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel2.setForeground(texteSombre);

    jLabel3.setText("Catégorie");
    jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel3.setForeground(texteSombre);

    jLabel4.setText("Prix");
    jLabel4.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel4.setForeground(texteSombre);

    jLabel6.setText("Stock actuel");
    jLabel6.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel6.setForeground(texteSombre);

    jLabel5.setText("Seuil alerte");
    jLabel5.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel5.setForeground(texteSombre);

    // Champs texte
    for (javax.swing.JTextField field : new javax.swing.JTextField[]{produitNom, prix, stock_actuel, seuil_alerte}) {
        field.setBackground(blanc);
        field.setForeground(texteSombre);
        field.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        field.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
    }
    produitNom.putClientProperty("JTextField.placeholderText", "Nom du produit...");
    prix.putClientProperty("JTextField.placeholderText", "0.00");
    stock_actuel.putClientProperty("JTextField.placeholderText", "0");
    seuil_alerte.putClientProperty("JTextField.placeholderText", "0");

    // ComboBox Catégorie
    categorie.setBackground(blanc);
    categorie.setForeground(texteSombre);
    categorie.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

    // Bouton Ajouter (Bleu)
    addBtn.setText("  Ajouter");
    addBtn.setBackground(bleuBtn);
    addBtn.setForeground(blanc);
    addBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    addBtn.setOpaque(true);
    addBtn.setFocusPainted(false);
    addBtn.setBorderPainted(false);
    addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    addBtn.putClientProperty("FlatLaf.style", "");

    // Bouton Modifier (Vert)
    updateBtn.setText("  Modifier");
    updateBtn.setBackground(vert);
    updateBtn.setForeground(blanc);
    updateBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    updateBtn.setOpaque(true);
    updateBtn.setFocusPainted(false);
    updateBtn.setBorderPainted(false);
    updateBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    updateBtn.putClientProperty("FlatLaf.style", "");

    // Bouton Supprimer (Rouge)
    deleteBtn.setText("  Supprimer");
    deleteBtn.setBackground(rouge);
    deleteBtn.setForeground(blanc);
    deleteBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    deleteBtn.setOpaque(true);
    deleteBtn.setFocusPainted(false);
    deleteBtn.setBorderPainted(false);
    deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    deleteBtn.putClientProperty("FlatLaf.style", "");

    // Bouton Effacer (Gris)
    clearBtn.setText("  Effacer");
    clearBtn.setBackground(grisBtnN);
    clearBtn.setForeground(blanc);
    clearBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    clearBtn.setOpaque(true);
    clearBtn.setFocusPainted(false);
    clearBtn.setBorderPainted(false);
    clearBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    clearBtn.putClientProperty("FlatLaf.style", "");

    // === PANEL TABLEAU ===
    jPanel2.setBackground(blanc);
    jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));

    // Tableau
    listeProduit.setBackground(blanc);
    listeProduit.setForeground(texteSombre);
    listeProduit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    listeProduit.setRowHeight(38);
    listeProduit.setGridColor(new java.awt.Color(243, 244, 246));
    listeProduit.setSelectionBackground(new java.awt.Color(219, 234, 254));
    listeProduit.setSelectionForeground(new java.awt.Color(30, 64, 175));
    listeProduit.setShowHorizontalLines(true);
    listeProduit.setShowVerticalLines(false);
    listeProduit.putClientProperty("FlatLaf.style", "");

    // En-tête du tableau
    listeProduit.getTableHeader().setBackground(bleuFonce);
    listeProduit.getTableHeader().setForeground(blanc);
    listeProduit.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    listeProduit.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 42));

    // ScrollPane
    jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    jScrollPane1.getViewport().setBackground(blanc);

    // === CHARGEMENT ===
    rafraichir();

    this.revalidate();
    this.repaint();
}
    
    @Override
    public void rafraichir() {
    try {
        produitController.remplirTableau(listeProduit);
        produitController.chargerCategories(categorie);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        produitNom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        categorie = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        prix = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        stock_actuel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        seuil_alerte = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeProduit = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Gestion des produits");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setText("Nom du produit");

        jLabel3.setText("Categorie");

        categorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieActionPerformed(evt);
            }
        });

        jLabel4.setText("Prix");

        prix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prixActionPerformed(evt);
            }
        });

        jLabel6.setText("Stock actuel");

        stock_actuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stock_actuelActionPerformed(evt);
            }
        });

        jLabel5.setText("Seuil alerte");

        addBtn.setText("Ajouter");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updateBtn.setText("Modifier");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Supprimer");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Effacer");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(produitNom, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                                .addComponent(stock_actuel, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(seuil_alerte)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categorie, javax.swing.GroupLayout.Alignment.LEADING, 0, 386, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(prix)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produitNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categorie)
                    .addComponent(prix))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stock_actuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seuil_alerte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtn)
                    .addComponent(clearBtn))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        listeProduit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        listeProduit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nom", "Categorie", "Prix", "Stock", "Seuil Alerte"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listeProduit.setShowGrid(true);
        listeProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listeProduitMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(listeProduit);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1148, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void prixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prixActionPerformed


    private void stock_actuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stock_actuelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stock_actuelActionPerformed


    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if (!produitController.ControleAllSaisie(produitNom, prix, stock_actuel, seuil_alerte, categorie)) {
            return;
        }

        try {

            produitDAO.Get(produitNom.getText());
            produitController.AddProduit(produitNom.getText(), Double.parseDouble(prix.getText()), Integer.parseInt(stock_actuel.getText()), Integer.parseInt(seuil_alerte.getText()), (String) categorie.getSelectedItem());
            JOptionPane.showMessageDialog(null, produitNom.getText() + " ajoute avec succes.");
            produitController.effacerEcran(produitNom, prix, categorie, stock_actuel, seuil_alerte);
            produitController.remplirTableau(listeProduit);
        } catch (DBException | SQLException ex) {
            System.getLogger(UIProduit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            JOptionPane.showMessageDialog(null, ex);
            produitController.effacerEcran(produitNom, prix, categorie, stock_actuel, seuil_alerte);
        }

    }//GEN-LAST:event_addBtnActionPerformed


    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        try {
            produitController.DeleteProduit(listeProduit);
            produitController.effacerEcran(produitNom, prix, categorie, stock_actuel, seuil_alerte);
            produitController.remplirTableau(listeProduit);
        } catch (DBException | SQLException ex) {
            System.getLogger(UIProduit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            JOptionPane.showMessageDialog(null, ex);

        }

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        produitController.effacerEcran(produitNom, prix, categorie, stock_actuel, seuil_alerte);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void categorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categorieActionPerformed

    private void listeProduitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listeProduitMouseReleased
        // TODO add your handling code here:
        produitController.remplirChampt(listeProduit, produitNom, prix, categorie, stock_actuel, seuil_alerte);
    }//GEN-LAST:event_listeProduitMouseReleased

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        if (!produitController.ControleAllSaisie(produitNom, prix, stock_actuel, seuil_alerte, categorie)) {
            return;
        }

        try {
            produitController.UpdateProduit(listeProduit, produitNom.getText(), Double.parseDouble(prix.getText()), Integer.parseInt(stock_actuel.getText()), Integer.parseInt(seuil_alerte.getText()), (String) categorie.getSelectedItem());
            JOptionPane.showMessageDialog(null, produitNom.getText() + " modifie avec succes.");
            produitController.effacerEcran(produitNom, prix, categorie, stock_actuel, seuil_alerte);
            produitController.remplirTableau(listeProduit);
        } catch (DBException | SQLException ex) {
            System.getLogger(UIProduit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            JOptionPane.showMessageDialog(null, ex);
            produitController.effacerEcran(produitNom, prix, categorie, stock_actuel, seuil_alerte);

        }

    }//GEN-LAST:event_updateBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<String> categorie;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable listeProduit;
    private javax.swing.JTextField prix;
    private javax.swing.JTextField produitNom;
    private javax.swing.JTextField seuil_alerte;
    private javax.swing.JTextField stock_actuel;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
