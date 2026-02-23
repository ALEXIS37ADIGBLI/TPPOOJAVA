/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

import controller.StatistiquesController;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import outils.DBException;

/**
 *
 * @author wilfried
 */
public class Statistics extends javax.swing.JPanel implements Rafraichissable{

    /**
     * Creates new form statistics
     */
    public Statistics() throws DBException, SQLException {
        initComponents();

        // === COULEURS DU THÈME ===
        java.awt.Color bleuFonce = new java.awt.Color(11, 58, 102);
        java.awt.Color blanc = new java.awt.Color(255, 255, 255);
        java.awt.Color grisClaire = new java.awt.Color(243, 244, 246);
        java.awt.Color texteSombre = new java.awt.Color(31, 41, 55);
        java.awt.Color bordure = new java.awt.Color(229, 231, 235);
        java.awt.Color grisLabel = new java.awt.Color(107, 114, 128);
        java.awt.Color vert = new java.awt.Color(22, 163, 74);
        java.awt.Color rougeAlerte = new java.awt.Color(185, 28, 28);

        // === FOND GÉNÉRAL ===
        this.setBackground(grisClaire);

        // === TITRE ===
        jLabel1.setText("Statistiques");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        jLabel1.setForeground(bleuFonce);

        // === PANEL PÉRIODE ===
        periode.setBackground(blanc);
        periode.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
        jLabel2.setText("Période");
        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jLabel2.setForeground(texteSombre);
        period.setBackground(blanc);
        period.setForeground(texteSombre);
        period.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

        // === PANEL CHIFFRE DU JOUR ===
        revenuday.setBackground(blanc);
        revenuday.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
        jLabel3.setText("Chiffre d'affaire du jour");
        jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jLabel3.setForeground(grisLabel);
        ChiffreAffaireDay.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 36));
        ChiffreAffaireDay.setForeground(vert);

        // === PANEL CHIFFRE PÉRIODIQUE ===
        revenuPeriod.setBackground(blanc);
        revenuPeriod.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
        jLabel6.setText("Chiffre d'affaire périodique");
        jLabel6.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jLabel6.setForeground(grisLabel);
        ChiffreAffairePeriod.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 36));
        ChiffreAffairePeriod.setForeground(bleuFonce);

        // === PANEL MEILLEURES VENTES ===
        meilleurVentes.setBackground(blanc);
        meilleurVentes.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
        meilleurVente.setText("Meilleures ventes");
        meilleurVente.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        meilleurVente.setForeground(bleuFonce);

        // Modèle propre pour listbest — VIDE, sans lignes null
        DefaultTableModel modelBest = new DefaultTableModel(
                new Object[]{"ID", "Nom", "Quantité vendue", "Prix Total"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        listbest.setModel(modelBest);
        listbest.setColumnSelectionAllowed(false);
        listbest.setBackground(blanc);
        listbest.setForeground(texteSombre);
        listbest.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        listbest.setRowHeight(38);
        listbest.setGridColor(new java.awt.Color(243, 244, 246));
        listbest.setSelectionBackground(new java.awt.Color(219, 234, 254));
        listbest.setSelectionForeground(new java.awt.Color(30, 64, 175));
        listbest.setShowHorizontalLines(true);
        listbest.setShowVerticalLines(false);
        listbest.putClientProperty("FlatLaf.style", "");
        listbest.getTableHeader().setBackground(bleuFonce);
        listbest.getTableHeader().setForeground(blanc);
        listbest.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        listbest.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 42));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane2.getViewport().setBackground(blanc);

        // === PANEL PRODUITS SOUS SEUIL ===
        prodSeuil.setBackground(blanc);
        prodSeuil.setBorder(javax.swing.BorderFactory.createLineBorder(bordure, 1));
        jLabel4.setText("Produits sous seuil");
        jLabel4.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        jLabel4.setForeground(new java.awt.Color(153, 27, 27));

        // Modèle propre pour tabSeuil — VIDE, sans lignes null
        DefaultTableModel modelSeuil = new DefaultTableModel(
                new Object[]{"ID", "Nom", "Stock Actuel", "Seuil Alerte"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tabSeuil.setModel(modelSeuil);
        tabSeuil.setBackground(blanc);
        tabSeuil.setForeground(texteSombre);
        tabSeuil.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        tabSeuil.setRowHeight(38);
        tabSeuil.setGridColor(new java.awt.Color(243, 244, 246));
        tabSeuil.setSelectionBackground(new java.awt.Color(254, 226, 226));
        tabSeuil.setSelectionForeground(new java.awt.Color(153, 27, 27));
        tabSeuil.setShowHorizontalLines(true);
        tabSeuil.setShowVerticalLines(false);
        tabSeuil.putClientProperty("FlatLaf.style", "");
        tabSeuil.getTableHeader().setBackground(rougeAlerte);
        tabSeuil.getTableHeader().setForeground(blanc);
        tabSeuil.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tabSeuil.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 42));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane1.getViewport().setBackground(blanc);

        // === CHARGEMENT DES DONNÉES ===
        // Les modèles sont propres et vides, le chargement va y injecter les données
        StatistiquesController.remplirTableauAlertes(tabSeuil);
        StatistiquesController.ChiffreDay(ChiffreAffaireDay);
        StatistiquesController.ChiffrePeriode(ChiffreAffairePeriod, period, listbest);

        // === ÉCOUTEUR PÉRIODE ===
        period.addActionListener(e -> {
            try {
                StatistiquesController.ChiffrePeriode(ChiffreAffairePeriod, period, listbest);
            } catch (SQLException | DBException ex) {
                System.getLogger(Statistics.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });

        this.revalidate();
        this.repaint();
    }
    
     @Override
    public void rafraichir() {
        try {
            DefaultTableModel modelSeuil = new DefaultTableModel(
                new Object[]{"ID", "Nom", "Stock Actuel", "Seuil Alerte"}, 0
            ) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
            };
            tabSeuil.setModel(modelSeuil);

            DefaultTableModel modelBest = new DefaultTableModel(
                new Object[]{"ID", "Nom", "Quantité vendue", "Prix Total"}, 0
            ) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
            };
            listbest.setModel(modelBest);

            StatistiquesController.remplirTableauAlertes(tabSeuil);
            StatistiquesController.ChiffreDay(ChiffreAffaireDay);
            StatistiquesController.ChiffrePeriode(ChiffreAffairePeriod, period, listbest);
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
        periode = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        period = new javax.swing.JComboBox<>();
        revenuday = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ChiffreAffaireDay = new javax.swing.JLabel();
        revenuPeriod = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ChiffreAffairePeriod = new javax.swing.JLabel();
        meilleurVentes = new javax.swing.JPanel();
        meilleurVente = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        listbest = new javax.swing.JTable();
        prodSeuil = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabSeuil = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setText("Statistiques");

        periode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Periode");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        period.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0 Mois", "1 Mois", "3 Mois", "6 Mois", "1 An" }));

        javax.swing.GroupLayout periodeLayout = new javax.swing.GroupLayout(periode);
        periode.setLayout(periodeLayout);
        periodeLayout.setHorizontalGroup(
            periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(periodeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(periodeLayout.createSequentialGroup()
                        .addGroup(periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(period, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        periodeLayout.setVerticalGroup(
            periodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(periodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(period, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        revenuday.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setText("Chiffre d'affaire du jour");

        ChiffreAffaireDay.setFont(new java.awt.Font("sansserif", 3, 36)); // NOI18N
        ChiffreAffaireDay.setText("jLabel5");

        javax.swing.GroupLayout revenudayLayout = new javax.swing.GroupLayout(revenuday);
        revenuday.setLayout(revenudayLayout);
        revenudayLayout.setHorizontalGroup(
            revenudayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenudayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(revenudayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChiffreAffaireDay)
                    .addComponent(jLabel3))
                .addContainerGap(317, Short.MAX_VALUE))
        );
        revenudayLayout.setVerticalGroup(
            revenudayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenudayLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ChiffreAffaireDay)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        revenuPeriod.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setText("Chiffre d'affaire periodique");

        ChiffreAffairePeriod.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        ChiffreAffairePeriod.setText("jLabel7");

        javax.swing.GroupLayout revenuPeriodLayout = new javax.swing.GroupLayout(revenuPeriod);
        revenuPeriod.setLayout(revenuPeriodLayout);
        revenuPeriodLayout.setHorizontalGroup(
            revenuPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuPeriodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(revenuPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(ChiffreAffairePeriod))
                .addContainerGap(326, Short.MAX_VALUE))
        );
        revenuPeriodLayout.setVerticalGroup(
            revenuPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuPeriodLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(ChiffreAffairePeriod)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        meilleurVentes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        meilleurVente.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        meilleurVente.setText("Meilleur ventes");

        listbest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nom", "Quantite vendu", "Prix Totale"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listbest.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(listbest);
        listbest.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout meilleurVentesLayout = new javax.swing.GroupLayout(meilleurVentes);
        meilleurVentes.setLayout(meilleurVentesLayout);
        meilleurVentesLayout.setHorizontalGroup(
            meilleurVentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meilleurVentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meilleurVentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(meilleurVentesLayout.createSequentialGroup()
                        .addComponent(meilleurVente)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        meilleurVentesLayout.setVerticalGroup(
            meilleurVentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meilleurVentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meilleurVente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        prodSeuil.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Produits sous seuil");

        tabSeuil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nom", "Stock Actuel", "Seuil Actuel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabSeuil);

        javax.swing.GroupLayout prodSeuilLayout = new javax.swing.GroupLayout(prodSeuil);
        prodSeuil.setLayout(prodSeuilLayout);
        prodSeuilLayout.setHorizontalGroup(
            prodSeuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prodSeuilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(prodSeuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(prodSeuilLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        prodSeuilLayout.setVerticalGroup(
            prodSeuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prodSeuilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(prodSeuil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(meilleurVentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(periode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(revenuday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(revenuPeriod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(periode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(revenuday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenuPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(meilleurVentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(prodSeuil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChiffreAffaireDay;
    private javax.swing.JLabel ChiffreAffairePeriod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable listbest;
    private javax.swing.JLabel meilleurVente;
    private javax.swing.JPanel meilleurVentes;
    private javax.swing.JComboBox<String> period;
    private javax.swing.JPanel periode;
    private javax.swing.JPanel prodSeuil;
    private javax.swing.JPanel revenuPeriod;
    private javax.swing.JPanel revenuday;
    private javax.swing.JTable tabSeuil;
    // End of variables declaration//GEN-END:variables

//    private void actualiserRevenuPeriodique() {
//    java.util.Date d1 = dateDebut.getDate();
//    java.util.Date d2 = dateFin.getDate();
//    if (d1 != null && d2 != null) {
//        try {
//            double chiffre = StatistiquesController.ChiffrePeriode(d1, d2);
//            labelPeriodRevenue.setText(String.format("%.2f $", chiffre));
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public void rechargerDonnees() {
//    try {
//        StatistiquesController.remplirTableauAlertes(tabSeuil);
//        StatistiquesController.ChiffreDay(ChiffreAffaireDay);
//        StatistiquesController.ChiffrePeriode(ChiffreAffairePeriod, period, listbest);
//    } catch (DBException | SQLException e) {
//        e.printStackTrace();
//    }
//}
//}
}
