/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

import controller.CommandeController;

/**
 *
 * @author agbeb
 */
public class commande_1 extends javax.swing.JPanel implements Rafraichissable{

    /**
     * Creates new form commande
     */
    public commande_1() {
    initComponents();

    // === COULEURS DU THÈME ===
    java.awt.Color bleuFonce  = new java.awt.Color(11, 58, 102);
    java.awt.Color blanc       = new java.awt.Color(255, 255, 255);
    java.awt.Color grisClaire  = new java.awt.Color(243, 244, 246);
    java.awt.Color texteSombre = new java.awt.Color(31, 41, 55);
    java.awt.Color grisLabel   = new java.awt.Color(107, 114, 128);
    java.awt.Color bleuBtn     = new java.awt.Color(13, 79, 139);
    java.awt.Color vert        = new java.awt.Color(22, 163, 74);
    java.awt.Color rouge       = new java.awt.Color(220, 38, 38);
    java.awt.Color bordure     = new java.awt.Color(229, 231, 235);

    // === FOND GÉNÉRAL ===
    this.setBackground(grisClaire);
    jPanel1.setBackground(grisClaire);

    // === TITRE ===
    jLabel1.setText("Gestion des Commandes");
    jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
    jLabel1.setForeground(bleuFonce);

    // === PANEL FORMULAIRE (jPanel2) ===
    jPanel2.setBackground(blanc);
    jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));

    // Labels
    jLabel2.setText("Produit");
    jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel2.setForeground(texteSombre);

    jLabel3.setText("Quantité");
    jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel3.setForeground(texteSombre);

    // ComboBox Produit
    ComboBoxProduit.setBackground(blanc);
    ComboBoxProduit.setForeground(texteSombre);
    ComboBoxProduit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

    // Champ Quantité
    TextFieldQuantité.setBackground(blanc);
    TextFieldQuantité.setForeground(texteSombre);
    TextFieldQuantité.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    TextFieldQuantité.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
    TextFieldQuantité.putClientProperty("JTextField.placeholderText", "0");

    // Bouton Ajouter (Bleu)
    BoutonAjouter.setText("  Ajouter à la commande");
    BoutonAjouter.setBackground(bleuBtn);
    BoutonAjouter.setForeground(blanc);
    BoutonAjouter.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    BoutonAjouter.setOpaque(true);
    BoutonAjouter.setFocusPainted(false);
    BoutonAjouter.setBorderPainted(false);
    BoutonAjouter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    BoutonAjouter.putClientProperty("FlatLaf.style", "");

    // === PANEL TABLEAU (jPanel3) ===
    jPanel3.setBackground(blanc);
    jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));

    // Label Total
    jLabel4.setText("Total : ");
    jLabel4.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    jLabel4.setForeground(bleuFonce);

    // Champ Total (non éditable)
    jTextField1.setEditable(false);
    jTextField1.setBackground(grisClaire);
    jTextField1.setForeground(bleuFonce);
    jTextField1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
    jTextField1.setText("0.00");

    // Bouton Valider (Vert)
    BoutonValiderCommande.setText("  Valider la commande");
    BoutonValiderCommande.setBackground(vert);
    BoutonValiderCommande.setForeground(blanc);
    BoutonValiderCommande.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    BoutonValiderCommande.setOpaque(true);
    BoutonValiderCommande.setFocusPainted(false);
    BoutonValiderCommande.setBorderPainted(false);
    BoutonValiderCommande.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    BoutonValiderCommande.putClientProperty("FlatLaf.style", "");

    // Bouton Annuler (Rouge)
    BoutonAnnulerCommande.setText("  Annuler la commande");
    BoutonAnnulerCommande.setBackground(rouge);
    BoutonAnnulerCommande.setForeground(blanc);
    BoutonAnnulerCommande.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    BoutonAnnulerCommande.setOpaque(true);
    BoutonAnnulerCommande.setFocusPainted(false);
    BoutonAnnulerCommande.setBorderPainted(false);
    BoutonAnnulerCommande.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    BoutonAnnulerCommande.putClientProperty("FlatLaf.style", "");

    // === TABLEAU ===
    TableauCommande.setBackground(blanc);
    TableauCommande.setForeground(texteSombre);
    TableauCommande.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    TableauCommande.setRowHeight(38);
    TableauCommande.setGridColor(new java.awt.Color(243, 244, 246));
    TableauCommande.setSelectionBackground(new java.awt.Color(219, 234, 254));
    TableauCommande.setSelectionForeground(new java.awt.Color(30, 64, 175));
    TableauCommande.setShowHorizontalLines(true);
    TableauCommande.setShowVerticalLines(false);
    TableauCommande.putClientProperty("FlatLaf.style", "");

    // En-tête du tableau
    TableauCommande.getTableHeader().setBackground(bleuFonce);
    TableauCommande.getTableHeader().setForeground(blanc);
    TableauCommande.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    TableauCommande.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 42));

    // ScrollPane
    jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    jScrollPane1.getViewport().setBackground(blanc);

    // === MODÈLE DU TABLEAU ===
    javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(
        new Object[][]{},
        new String[]{"Produit", "Quantité", "Prix unitaire", "Total ligne", "Action"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    TableauCommande.setModel(tableModel);

    // === CHARGEMENT ET ACTIONS ===
    CommandeController.chargerProduits(ComboBoxProduit);

    BoutonAjouter.addActionListener(e -> {
        CommandeController.ajouterLigne(ComboBoxProduit, TextFieldQuantité, TableauCommande, jTextField1);
    });

    BoutonValiderCommande.addActionListener(e -> {
        CommandeController.validerCommande(TableauCommande, jTextField1, ComboBoxProduit);
    });

    BoutonAnnulerCommande.addActionListener(e -> {
        CommandeController.annulerCommande(TableauCommande, jTextField1, ComboBoxProduit);
    });

    // Clic sur colonne "Action" pour retirer une ligne
    TableauCommande.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (TableauCommande.columnAtPoint(e.getPoint()) == 4) {
                CommandeController.retirerLigne(TableauCommande, jTextField1);
            }
        }
    });

    this.revalidate();
    this.repaint();
}
    
    @Override
    public void rafraichir() {
        CommandeController.chargerProduits(ComboBoxProduit);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ComboBoxProduit = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        TextFieldQuantité = new javax.swing.JTextField();
        BoutonAjouter = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableauCommande = new javax.swing.JTable();
        BoutonValiderCommande = new javax.swing.JButton();
        BoutonAnnulerCommande = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gestion des commandes");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jLabel2.setText("Produit ");

        ComboBoxProduit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Quantité");

        TextFieldQuantité.setText("0");

        BoutonAjouter.setBackground(new java.awt.Color(51, 102, 255));
        BoutonAjouter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BoutonAjouter.setForeground(new java.awt.Color(255, 255, 255));
        BoutonAjouter.setText("Ajouter à la commande");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxProduit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(BoutonAjouter))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldQuantité)
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldQuantité, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonAjouter)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        TableauCommande.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Produit", "Quantité", "Prix unitaire", "Total ligne", "Action"
            }
        ));
        jScrollPane1.setViewportView(TableauCommande);

        BoutonValiderCommande.setBackground(new java.awt.Color(51, 255, 51));
        BoutonValiderCommande.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BoutonValiderCommande.setText("Valider la commande");
        BoutonValiderCommande.addActionListener(this::BoutonValiderCommandeActionPerformed);

        BoutonAnnulerCommande.setBackground(new java.awt.Color(255, 51, 51));
        BoutonAnnulerCommande.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BoutonAnnulerCommande.setForeground(new java.awt.Color(242, 242, 242));
        BoutonAnnulerCommande.setText("Annuler la commande");

        jLabel4.setText("Total : ");

        jTextField1.setText("0");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonAnnulerCommande)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BoutonValiderCommande)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BoutonValiderCommande)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BoutonAnnulerCommande)
                        .addComponent(jLabel4)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void BoutonValiderCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonValiderCommandeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoutonValiderCommandeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonAjouter;
    private javax.swing.JButton BoutonAnnulerCommande;
    private javax.swing.JButton BoutonValiderCommande;
    private javax.swing.JComboBox<String> ComboBoxProduit;
    private javax.swing.JTable TableauCommande;
    private javax.swing.JTextField TextFieldQuantité;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
