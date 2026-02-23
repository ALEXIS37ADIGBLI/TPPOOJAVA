/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

import DAO.MouvementStockDAO;
import DAO.produitDAO;
import models.produit;
import java.awt.HeadlessException;
import models.mouvement_stock;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;

import outils.DBConnection;
import outils.DBException;

/**
 *
 * @author bossmrpk
 */

public class Mouvement_stockPanel extends javax.swing.JPanel {
    private boolean alertVisible = false;
    produitDAO produitDAO = new produitDAO();
    List<produit> listeproduit;
    
    /**
     * Creates new form Mouvement_stockPanel
     */
    public Mouvement_stockPanel() throws DBException, SQLException {
    initComponents();

    // === COULEURS DU THÈME ===
    java.awt.Color bleuFonce  = new java.awt.Color(11, 58, 102);
    java.awt.Color blanc      = new java.awt.Color(255, 255, 255);
    java.awt.Color grisClaire = new java.awt.Color(243, 244, 246);
    java.awt.Color texteSombre= new java.awt.Color(31, 41, 55);
    java.awt.Color bleuBtn    = new java.awt.Color(13, 79, 139);
    java.awt.Color rouge      = new java.awt.Color(220, 38, 38);
    java.awt.Color grisBtnN   = new java.awt.Color(107, 114, 128);
    java.awt.Color bordure    = new java.awt.Color(229, 231, 235);

    // === FOND GÉNÉRAL ===
    this.setBackground(grisClaire);
    jPanel1.setBackground(grisClaire);

    // === TITRE ===
    jLabel1.setText("⚙  Mouvement de stock");
    jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
    jLabel1.setForeground(bleuFonce);

    // === PANEL FORMULAIRE ===
    formulaire.setBackground(blanc);
    formulaire.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(bordure, 1),
        javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15)
    ));

    // Labels
    jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel3.setForeground(texteSombre);
    jLabel3.setText("Produit");

    jLabel4.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel4.setForeground(texteSombre);
    jLabel4.setText("Type");

    jLabel6.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel6.setForeground(texteSombre);
    jLabel6.setText("Quantité");

    jLabel7.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    jLabel7.setForeground(texteSombre);
    jLabel7.setText("Motif");

    // ComboBoxes
    produitBox.setBackground(blanc);
    produitBox.setForeground(texteSombre);
    produitBox.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

    typeBox.setBackground(blanc);
    typeBox.setForeground(texteSombre);
    typeBox.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

    // Champ Motif
    textMotif.setBackground(blanc);
    textMotif.setForeground(texteSombre);
    textMotif.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    textMotif.setBorder(javax.swing.BorderFactory.createLineBorder(
        new java.awt.Color(209, 213, 219), 1));
    textMotif.putClientProperty("JTextField.placeholderText", "Raison du mouvement...");

    // Bouton Enregistrer
    BouttonEnregistrer.setText("✚  Enregistrer");
    BouttonEnregistrer.setBackground(bleuBtn);
    BouttonEnregistrer.setForeground(blanc);
    BouttonEnregistrer.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    BouttonEnregistrer.setOpaque(true);
    BouttonEnregistrer.setFocusPainted(false);
    BouttonEnregistrer.setBorderPainted(false);
    BouttonEnregistrer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    BouttonEnregistrer.putClientProperty("FlatLaf.style", "");

    // Bouton Nettoyer
    BouttonNettoye.setText("↺  Nettoyer");
    BouttonNettoye.setBackground(grisBtnN);
    BouttonNettoye.setForeground(blanc);
    BouttonNettoye.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    BouttonNettoye.setOpaque(true);
    BouttonNettoye.setFocusPainted(false);
    BouttonNettoye.setBorderPainted(false);
    BouttonNettoye.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    BouttonNettoye.putClientProperty("FlatLaf.style", "");
    // Brancher Nettoyer (pas de listener dans initComponents)
    BouttonNettoye.addActionListener(e -> {
        textMotif.setText("");
        QuantitéSpinner.setValue(0);
        if (produitBox.getItemCount() > 0) produitBox.setSelectedIndex(0);
        typeBox.setSelectedIndex(0);
    });

    // === PANEL HISTORIQUE ===
    Historique.setBackground(blanc);
    Historique.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(bordure, 1),
        javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));

    // Bouton Seuil
    BouttonSeuil.setText("⚠  Seuil d'alerte");
    BouttonSeuil.setBackground(rouge);
    BouttonSeuil.setForeground(blanc);
    BouttonSeuil.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    BouttonSeuil.setOpaque(true);
    BouttonSeuil.setFocusPainted(false);
    BouttonSeuil.setBorderPainted(false);
    BouttonSeuil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    BouttonSeuil.putClientProperty("FlatLaf.style", "");

    // === LABEL ALERTE — auto-resize selon contenu ===
    LabelSeuilAlert.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    LabelSeuilAlert.setForeground(new java.awt.Color(153, 27, 27));
    LabelSeuilAlert.setBackground(new java.awt.Color(254, 226, 226));
    LabelSeuilAlert.setOpaque(true);
    LabelSeuilAlert.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 165, 165), 1),
        javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)
    ));
    // Auto-resize : on écoute les changements de texte pour ajuster la hauteur
    LabelSeuilAlert.addPropertyChangeListener("text", evt -> {
        LabelSeuilAlert.revalidate();
        // Recalcule la taille préférée selon le contenu HTML
        java.awt.Dimension pref = LabelSeuilAlert.getPreferredSize();
        LabelSeuilAlert.setPreferredSize(
            new java.awt.Dimension(pref.width, pref.height + 16)
        );
        Historique.revalidate();
        Historique.repaint();
    });

    // === TABLEAU HISTORIQUE ===
    // Modèle propre sans lignes null
    DefaultTableModel modelHisto = new DefaultTableModel(
        new Object[]{"ID", "Produit", "Type", "Quantité", "Date", "Motif"}, 0
    ) {
        Class[] types = new Class[]{
            Integer.class, String.class, String.class,
            Integer.class, Object.class, String.class
        };
        @Override public Class getColumnClass(int c) { return types[c]; }
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    Tablehisto.setModel(modelHisto);

    Tablehisto.setBackground(blanc);
    Tablehisto.setForeground(texteSombre);
    Tablehisto.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    Tablehisto.setRowHeight(38);
    Tablehisto.setGridColor(new java.awt.Color(243, 244, 246));
    Tablehisto.setSelectionBackground(new java.awt.Color(219, 234, 254));
    Tablehisto.setSelectionForeground(new java.awt.Color(30, 64, 175));
    Tablehisto.setShowHorizontalLines(true);
    Tablehisto.setShowVerticalLines(false);
    Tablehisto.putClientProperty("FlatLaf.style", "");

    Tablehisto.getTableHeader().setBackground(bleuFonce);
    Tablehisto.getTableHeader().setForeground(blanc);
    Tablehisto.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
    Tablehisto.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 42));

    jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    jScrollPane1.getViewport().setBackground(blanc);

    // === CHARGEMENT ===
    LabelSeuilAlert.setVisible(false);
    jSeparator2.setVisible(false);
    chargerproduitBox();
    chargerHistorique();

    this.revalidate();
    this.repaint();
}
    public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
        javax.swing.JFrame frame = new javax.swing.JFrame("Test du Panel");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        try {
            frame.setContentPane(new Mouvement_stockPanel());
        } catch (DBException ex) {
            System.getLogger(Mouvement_stockPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(Mouvement_stockPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    });
    

    
   
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        formulaire = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        produitBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        typeBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        QuantitéSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        textMotif = new javax.swing.JTextField();
        BouttonEnregistrer = new javax.swing.JButton();
        BouttonNettoye = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Historique = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tablehisto = new javax.swing.JTable();
        BouttonSeuil = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        LabelSeuilAlert = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        formulaire.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulaire du mouvement de stock"));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel3.setText("Produit");

        produitBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        produitBox.addActionListener(this::produitBoxActionPerformed);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel4.setText("Type ");

        typeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENTRÉE", "SORTIE", " " }));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel6.setText("Quantité");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel7.setText("Motif");

        BouttonEnregistrer.setText("Enregistrer le Stock");
        BouttonEnregistrer.addActionListener(this::BouttonEnregistrerActionPerformed);

        BouttonNettoye.setText("Nettoyé");

        javax.swing.GroupLayout formulaireLayout = new javax.swing.GroupLayout(formulaire);
        formulaire.setLayout(formulaireLayout);
        formulaireLayout.setHorizontalGroup(
            formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formulaireLayout.createSequentialGroup()
                .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(formulaireLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(produitBox, 0, 159, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(QuantitéSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formulaireLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(148, 148, 148))
                            .addComponent(textMotif))))
                .addContainerGap())
            .addGroup(formulaireLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BouttonEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BouttonNettoye, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formulaireLayout.setVerticalGroup(
            formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formulaireLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formulaireLayout.createSequentialGroup()
                        .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addComponent(produitBox, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addGroup(formulaireLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addGroup(formulaireLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(textMotif, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                            .addGroup(formulaireLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(QuantitéSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                            .addGroup(formulaireLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(typeBox, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(formulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BouttonEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BouttonNettoye, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jLabel1.setText("Mouvement de stock");

        Historique.setBorder(javax.swing.BorderFactory.createTitledBorder("Historique de stock"));

        Tablehisto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Produit", "Type", "Quantité", "Date", "Motif"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tablehisto);

        BouttonSeuil.setText("seuil d’alerte");
        BouttonSeuil.addActionListener(this::BouttonSeuilActionPerformed);

        javax.swing.GroupLayout HistoriqueLayout = new javax.swing.GroupLayout(Historique);
        Historique.setLayout(HistoriqueLayout);
        HistoriqueLayout.setHorizontalGroup(
            HistoriqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoriqueLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BouttonSeuil, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(LabelSeuilAlert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HistoriqueLayout.setVerticalGroup(
            HistoriqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HistoriqueLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(BouttonSeuil, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelSeuilAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(formulaire, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Historique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(formulaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Historique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void produitBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produitBoxActionPerformed
          
    }//GEN-LAST:event_produitBoxActionPerformed

    private void BouttonEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BouttonEnregistrerActionPerformed
        try {
            int id_produit = getProduitSelectionneId();
            String type  = typeBox.getSelectedItem().toString();
            int quantite = (int)QuantitéSpinner.getValue();
            String motif  = textMotif.getText();
            
            
        if (quantite <=0){
                    JOptionPane.showMessageDialog(this,
                "La quantité ne doit pas être inférieur ou égale a  0",
                    "erreur",
                    JOptionPane.INFORMATION_MESSAGE);
                    return;
        }
        if (motif.isEmpty()|| !motif.matches("[a-zA-ZÀ-ÿ\\s]+")){
                 JOptionPane.showMessageDialog(this,
                "Veuillez entrer le motif",
                    "erreur",
                    JOptionPane.INFORMATION_MESSAGE);
                 return;
        }
        if(produitBox.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this,
                "Veuillez sélectionner le produit",
                    "erreur",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        mouvement_stock m = new mouvement_stock(
                0,
                id_produit,
                type,
                quantite,
                java.time.LocalDateTime.now(), 
                motif
                
        );
        
        MouvementStockDAO dao = new MouvementStockDAO();
        dao.ajouter(m);
        chargerHistorique();

        textMotif.setText("");
        QuantitéSpinner.setValue(0);
        produitBox.setSelectedIndex(0);
        typeBox.setSelectedIndex(0);
        
        
        JOptionPane.showMessageDialog(this, "Mouvement enregistré avec succès !");
        
        } catch (Exception e) {
            e.printStackTrace(); // Très important : tu verras la vraie cause dans la console
            JOptionPane.showMessageDialog(this, e.getMessage());
}

    }//GEN-LAST:event_BouttonEnregistrerActionPerformed

    private void BouttonSeuilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BouttonSeuilActionPerformed
              
        try{
            if(!alertVisible){
              int count  = produitDAO.countLowStock();
              List<produit> listeseuil = produitDAO.getProduitAlert();
              StringBuilder message = new StringBuilder();
              message.append("⚠ Produits en alerte : ").append(count).append("\n\n");
              for (produit p : listeseuil){
                    message.append("- ")
                   .append(p.getNom())
                   .append(" : ")
                   .append(p.getStock_actuel())
                   .append(" unités (Seuil : ")
                   .append(p.getSeuil_alerte())
                   .append(")\n");
              }
              LabelSeuilAlert.setText("<html>" + message.toString().replace("\n", "<br>") + "</html>");
              if (count == 0) {
                LabelSeuilAlert.setText("Aucun produit en alerte ");
            }
            LabelSeuilAlert.setVisible(true);
            jSeparator2.setVisible(true);
            BouttonSeuil.setText("Masquer les alertes");

            alertVisible = true;
            }else{ LabelSeuilAlert.setVisible(false);
            jSeparator2.setVisible(false);
            BouttonSeuil.setText("Seuil d’alerte");

            alertVisible = false;
            }
            revalidate();
            repaint();

              
             } catch (SQLException | DBException e) {
                 JOptionPane.showMessageDialog(this,
                "Erreur lors du chargement des alertes !");
             }
              
    }//GEN-LAST:event_BouttonSeuilActionPerformed
    public int getProduitSelectionneId() throws DBException, SQLException{
            String nomChoisi = produitBox.getSelectedItem().toString();
            produit p = produitDAO.get(nomChoisi);
           return p.getId_produit();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BouttonEnregistrer;
    private javax.swing.JButton BouttonNettoye;
    private javax.swing.JButton BouttonSeuil;
    private javax.swing.JPanel Historique;
    private javax.swing.JLabel LabelSeuilAlert;
    private javax.swing.JSpinner QuantitéSpinner;
    private javax.swing.JTable Tablehisto;
    private javax.swing.JPanel formulaire;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> produitBox;
    private javax.swing.JTextField textMotif;
    private javax.swing.JComboBox<String> typeBox;
    // End of variables declaration//GEN-END:variables

    private void chargerproduitBox() throws DBException, SQLException {
        produitBox.removeAllItems();
        listeproduit = produitDAO.getAll();
        for(produit p : listeproduit){
           produitBox.addItem(p.getNom());
        }
        
    }

private void chargerHistorique() {
    try {
        MouvementStockDAO dao = new MouvementStockDAO();
        List<mouvement_stock> mouvements = dao.listerTout(); 

        DefaultTableModel model = (DefaultTableModel) Tablehisto.getModel();
        model.setRowCount(0);

        for (mouvement_stock m : mouvements) {

            String nomProduit = "";

            for (produit p : listeproduit) {
                if (p.getId_produit() == m.getId_produit()) {
                    nomProduit = p.getNom();
                    break;
                }
            }

            Object[] row;
            row = new Object[]{
                m.getId_mouvement(),
                nomProduit,
                m.getType_mouvement(),
                m.getQuantite(),
                m.getDate_mouvement(),
                m.getMotif()
            };

            model.addRow(row);
        }

    } catch (DBException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
                "Erreur lors du chargement de l'historique !");
    }
}
}
