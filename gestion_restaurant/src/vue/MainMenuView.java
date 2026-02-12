/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vue;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Xisclever
 */
public class MainMenuView extends javax.swing.JFrame {

    /**
     * Creates new form MainMenuView
     */
    public MainMenuView() {
        initComponents();

        // 1. MAXIMISER ET NETTOYER
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        jPanel3.removeAll(); // On enlève tout ce que NetBeans a mis en vrac
        jPanel3.setLayout(new java.awt.CardLayout());

        // 2. RECONSTRUCTION DE LA PAGE DASHBOARD (Propre et alignée)
        javax.swing.JPanel dashboardPage = new javax.swing.JPanel();
        dashboardPage.setLayout(new java.awt.BorderLayout(0, 25)); // Espace entre le haut et le bas
        dashboardPage.setBackground(new java.awt.Color(243, 244, 246));
        dashboardPage.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // On regroupe le titre et les cartes de stats au centre
        javax.swing.JPanel centerPanel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 15));
        centerPanel.setOpaque(false);
        jLabel11.setText("Dashboard Overview"); // On réutilise ton label
        centerPanel.add(jLabel11, java.awt.BorderLayout.NORTH);
        centerPanel.add(jPanel4, java.awt.BorderLayout.CENTER); // Tes 4 cartes de stats

        dashboardPage.add(centerPanel, java.awt.BorderLayout.CENTER);
        dashboardPage.add(jPanel9, java.awt.BorderLayout.SOUTH); // L'alerte jaune tout en bas

        // 3. AJOUT DES PAGES AU CARDLAYOUT
        jPanel3.add(dashboardPage, "cardDashboard");
        commande_1 produitPage = new commande_1();
        jPanel3.add(produitPage, "cardCommande");

        CategoriePanel categoriepage = new CategoriePanel();
        jPanel3.add(categoriepage, "cardCategorie");

        Mouvement_stockPanel mvstockPage = new Mouvement_stockPanel();
        jPanel3.add(mvstockPage, "cardMv");

        Utilisateurs userPage = new Utilisateurs();
        jPanel3.add(userPage, "cardUser");

        // 4. CONFIGURATION DES BOUTONS DU MENU (Design & Icônes)
        int iconSize = 18;
        java.awt.Color menuTextColor = new java.awt.Color(75, 85, 99);
        javax.swing.border.Border margin = javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 0);

        // Bouton Dashboard
        setupMenuButton(jButton1, "images/layout-dashboard.svg", iconSize, true, margin);
        jButton1.addActionListener(e -> {
            ((java.awt.CardLayout) jPanel3.getLayout()).show(jPanel3, "cardDashboard");
        });

        // Bouton Produits
        setupMenuButton(jButton2, "images/package.svg", iconSize, false, margin);
        // L'action est déjà gérée par NetBeans dans jButton2ActionPerformed

        // Bouton Catégories
        jButton3.setText("Catégories");
        setupMenuButton(jButton3, "images/folder-open.svg", iconSize, false, margin);

        // Bouton Stocks
        jButton4.setText("Stocks");
        setupMenuButton(jButton4, "images/warehouse.svg", iconSize, false, margin);

        // Bouton commande
        jButton4.setText("Stocks");
        setupMenuButton(jButton5, "images/shopping-cart.svg", iconSize, false, margin);

        // Bouton commande
        jButton4.setText("Stocks");
        setupMenuButton(jButton6, "images/chart-column.svg", iconSize, false, margin);

        // Bouton commande
        jButton4.setText("Stocks");
        setupMenuButton(jButton7, "images/users.svg", iconSize, false, margin);

        // Bouton Logout
        jButton8.setText("Déconnexion");
        setupMenuButton(jButton8, "images/log-out.svg", iconSize, false, margin);

        // 5. FINALISATION
        java.awt.CardLayout cl = (java.awt.CardLayout) jPanel3.getLayout();
        cl.show(jPanel3, "cardDashboard"); // On force l'affichage du dashboard

        jPanel3.revalidate();
        jPanel3.repaint();
    }

    // Petite méthode utilitaire pour éviter de répéter le code des icônes
    private void setupMenuButton(javax.swing.JButton btn, String iconPath, int size, boolean isActive, javax.swing.border.Border margin) {
        com.formdev.flatlaf.extras.FlatSVGIcon icon = new com.formdev.flatlaf.extras.FlatSVGIcon(iconPath, size, size);
        if (!isActive) {
            icon.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> new java.awt.Color(75, 85, 99)));
        } else {
            icon.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> java.awt.Color.WHITE));
        }
        btn.setIcon(icon);
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn.setIconTextGap(15);
        btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(btn.getBorder(), margin));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        com.formdev.flatlaf.extras.FlatSVGIcon icon16 = new com.formdev.flatlaf.extras.FlatSVGIcon("images/package.svg", 50, 50);
        icon16.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> new java.awt.Color(59, 130, 246)));
        jLabel16.setIcon(icon16);
        jLabel16.setText("");
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        com.formdev.flatlaf.extras.FlatSVGIcon icon17 = new com.formdev.flatlaf.extras.FlatSVGIcon("images/triangle-alert.svg", 50, 50);
        icon17.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> new java.awt.Color(239, 68, 68)));
        jLabel17.setIcon(icon17);
        jLabel17.setText("");
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        com.formdev.flatlaf.extras.FlatSVGIcon icon20 = new com.formdev.flatlaf.extras.FlatSVGIcon("images/shopping-cart.svg", 50, 50);
        icon20.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> new java.awt.Color(34, 197, 94)));
        jLabel20.setIcon(icon20);
        jLabel20.setText("");
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        com.formdev.flatlaf.extras.FlatSVGIcon icon23 = new com.formdev.flatlaf.extras.FlatSVGIcon("images/dollar-sign.svg", 50, 50); icon23.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> new java.awt.Color(245, 158, 11))); jLabel23.setIcon(icon23); jLabel23.setText("");
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(75, 85, 99));
        jButton2.setText("Produits");
        jButton2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(11, 58, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gestion de restaurant");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Système de gestion de restaurant");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 847, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(229, 231, 235));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 10, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 0));
        jPanel1.setLayout(new java.awt.GridLayout(10, 0, 0, 10));

        jButton1.setBackground(new java.awt.Color(13, 79, 139));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(75, 85, 99));
        jButton9.setText("Produits");
        jButton9.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(75, 85, 99));
        jButton3.setText("Catégorie");
        jButton3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(75, 85, 99));
        jButton4.setText("Sotcks");
        jButton4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(75, 85, 99));
        jButton5.setText("Commande");
        jButton5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(75, 85, 99));
        jButton6.setText("Statistiques");
        jButton6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jPanel1.add(jButton6);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(75, 85, 99));
        jButton7.setText("Utilisateurs");
        jButton7.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(75, 85, 99));
        jButton8.setText("Se deconnecter");
        jButton8.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jPanel1.add(jButton8);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(new java.awt.Color(243, 244, 246));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel11.setText("Dashboard");
        jPanel3.add(jLabel11, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(245, 246, 248));
        jPanel4.setLayout(new java.awt.GridLayout(3, 2, 40, 40));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(107, 114, 128));
        jLabel12.setText("Total produits");
        jPanel6.add(jLabel12, java.awt.BorderLayout.NORTH);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(31, 41, 55));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("0");
        jPanel6.add(jLabel13, java.awt.BorderLayout.CENTER);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setIconTextGap(0);
        jLabel16.setName(""); // NOI18N
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel6.add(jLabel16, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(107, 114, 128));
        jLabel14.setText("Produit en stock faible");
        jPanel7.add(jLabel14, java.awt.BorderLayout.NORTH);

        jLabel15.setBackground(new java.awt.Color(31, 41, 55));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(31, 41, 55));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("0");
        jPanel7.add(jLabel15, java.awt.BorderLayout.CENTER);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel17.setIconTextGap(0);
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel7.add(jLabel17, java.awt.BorderLayout.LINE_END);

        jPanel4.add(jPanel7);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(107, 114, 128));
        jLabel18.setText("Vente d'aujourd'hui");
        jPanel5.add(jLabel18, java.awt.BorderLayout.NORTH);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(31, 41, 55));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("0");
        jPanel5.add(jLabel19, java.awt.BorderLayout.CENTER);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel20.setIconTextGap(0);
        jLabel20.setOpaque(true);
        jLabel20.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.add(jLabel20, java.awt.BorderLayout.LINE_END);

        jPanel4.add(jPanel5);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 28)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(107, 114, 128));
        jLabel21.setText("Revenue total");
        jPanel8.add(jLabel21, java.awt.BorderLayout.NORTH);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(31, 41, 55));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("0");
        jPanel8.add(jLabel22, java.awt.BorderLayout.CENTER);

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setForeground(new java.awt.Color(244, 180, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel23.setIconTextGap(0);
        jLabel23.setOpaque(true);
        jLabel23.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel8.add(jLabel23, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 244, 206));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 180, 0)));
        jPanel9.setPreferredSize(new java.awt.Dimension(0, 80));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel24.setForeground(new java.awt.Color(244, 180, 0));
        jLabel24.setText("<html><b>⚠ Low Stock Alert</b><br/>2 product(s) are running low on stock.</html>");
        jLabel24.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jPanel9.add(jLabel24, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(jPanel9);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CardLayout cl = (CardLayout) jPanel3.getLayout();
        cl.show(jPanel3, "cardCategorie");
        setActiveButton(jButton3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setActiveButton(jButton1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //CardLayout cl = (CardLayout) jPanel3.getLayout();
        //cl.show(jPanel3, "Commande");
        //setActiveButton(jButton9);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CardLayout cl = (CardLayout) jPanel3.getLayout();
        cl.show(jPanel3, "cardMv");
        setActiveButton(jButton4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CardLayout cl = (CardLayout) jPanel3.getLayout();
        cl.show(jPanel3, "cardCommande");
        setActiveButton(jButton5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        CardLayout cl = (CardLayout) jPanel3.getLayout();
        cl.show(jPanel3, "cardUser");
        setActiveButton(jButton7);
    }//GEN-LAST:event_jButton7ActionPerformed

    // Remplace tes getters actuels par ceux-ci dans MainMenuView.java
    public JLabel getLblTotalProduits() {
        return jLabel13;
    }

    public JLabel getLblStockFaible() {
        return jLabel15;
    }

    public javax.swing.JPanel getPanelAlerte() {
        return jPanel9;
    }

    public JLabel getLblMessageAlerte() {
        return jLabel24;
    }

    public JButton getBtnProduits() {
        return jButton2;
    }

    public JButton getBtnLogout() {
        return jButton8;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenuView().setVisible(true);
            }
        });
    }

    private void setActiveButton(javax.swing.JButton activeBtn) {
        // 1. Liste de tous tes boutons de menu
        javax.swing.JButton[] buttons = {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton9};

        // 2. Couleurs
        java.awt.Color activeBack = new java.awt.Color(13, 79, 139); // Bleu foncé
        java.awt.Color activeText = java.awt.Color.WHITE;
        java.awt.Color idleBack = java.awt.Color.WHITE;
        java.awt.Color idleText = new java.awt.Color(75, 85, 99);   // Gris

        for (javax.swing.JButton btn : buttons) {
            if (btn == activeBtn) {
                // Style du bouton cliqué
                btn.setBackground(activeBack);
                btn.setForeground(activeText);
                // On change aussi l'icône en blanc (on réutilise ta logique setup)
                updateIconColor(btn, true);
            } else {
                // Style des autres boutons
                btn.setBackground(idleBack);
                btn.setForeground(idleText);
                updateIconColor(btn, false);
            }
        }
    }

// Petite méthode pour changer la couleur de l'icône dynamiquement
    private void updateIconColor(javax.swing.JButton btn, boolean isActive) {
        if (btn.getIcon() instanceof com.formdev.flatlaf.extras.FlatSVGIcon) {
            com.formdev.flatlaf.extras.FlatSVGIcon icon = (com.formdev.flatlaf.extras.FlatSVGIcon) btn.getIcon();
            if (isActive) {
                icon.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> java.awt.Color.WHITE));
            } else {
                icon.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> new java.awt.Color(75, 85, 99)));
            }
            btn.repaint();
        }
    }

    public JLabel getLblVentesAujourdhui() {
        return jLabel19; // Le label au centre de jPanel5
    }

    public JLabel getLblRevenueTotal() {
        return jLabel22; // Le label au centre de jPanel8
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
