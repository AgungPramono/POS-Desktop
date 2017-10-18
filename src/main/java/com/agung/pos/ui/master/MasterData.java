/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  MasterData.java
 * 
 *  Created on Dec 8, 2015, 5:11:12 PM
 */
package com.agung.pos.ui.master;

import com.agung.pos.Main;
import com.agung.pos.entity.Produk;
import com.agung.pos.ui.report.RekapProduk;
import com.agung.pos.ui.transaksi.PanelPenjualan;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JTabbedPane;

/**
 *
 * @author agung
 */
public class MasterData extends javax.swing.JPanel {

    private Produk produk;
    private List<Produk> listProduk ;
    public static MasterData panel;
    public static final String PANEL_NAME = "<html><b>Data Master</b></html>";
    private PanelProduk panelProduk = new PanelProduk();
    private PanelDistributor panelDistributor = new PanelDistributor();
    private RekapProduk panelRekapProduk = new RekapProduk();
    //private Integer indexTabs = -1;
    public MasterData() {
        initComponents();
        //setPanelProduk();
        rightPanel.add(panelProduk,"Panel Produk");
        rightPanel.add(panelDistributor, "Panel Distributor");
    }
    
    public static MasterData getPanelProduk(){
        if (panel == null) {
            panel = new MasterData();
        }
        return panel;
    }
    /**
    public JTabbedPane getMainTabedPane(){
        return mainTabbedPane;
    }
    
    private int getComponentIndexByName(String panelName){
        return mainTabbedPane.indexOfTab(panelName);
    }
    
    private void setSelectedPanel(String panelName){
        mainTabbedPane.setSelectedComponent(mainTabbedPane.getComponentAt(getComponentIndexByName(panelName)));
    }
    
    private void setPanelProduk(){
        PanelProduk.getPanelProduk().setName(PanelProduk.PANEL_NAME);
        indexTabs = getComponentIndexByName(PanelProduk.PANEL_NAME);
        if(indexTabs == -1){
            mainTabbedPane.addTab(PanelProduk.PANEL_NAME, PanelProduk.getPanelProduk());
            setSelectedPanel(PanelProduk.PANEL_NAME);
        }else{
            mainTabbedPane.setSelectedIndex(indexTabs);
        }
    }*/
    
    private void setPanelDistributor(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        btnDataProduk = new javax.swing.JButton();
        btnCategory = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        btnRekapProduk = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 153, 153));

        btnDataProduk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDataProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/package.png"))); // NOI18N
        btnDataProduk.setText("Data Produk");
        btnDataProduk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataProduk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDataProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataProdukActionPerformed(evt);
            }
        });

        btnCategory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/user.png"))); // NOI18N
        btnCategory.setText("Data Distributor");
        btnCategory.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnCategory.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryActionPerformed(evt);
            }
        });

        btnKeluar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/exit.png"))); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnRekapProduk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRekapProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/paste.png"))); // NOI18N
        btnRekapProduk.setText("Rekap Produk");
        btnRekapProduk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRekapProduk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRekapProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekapProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDataProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKeluar)
                    .addComponent(btnRekapProduk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        leftPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCategory, btnDataProduk, btnKeluar, btnRekapProduk});

        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnDataProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRekapProduk)
                .addGap(4, 4, 4)
                .addComponent(btnKeluar)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        leftPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCategory, btnDataProduk, btnKeluar, btnRekapProduk});

        splitPane.setLeftComponent(leftPanel);

        rightPanel.setLayout(new java.awt.CardLayout());
        splitPane.setRightComponent(rightPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(splitPane)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDataProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataProdukActionPerformed
        // TODO add your handling code here:

        CardLayout cardLayout = (CardLayout) rightPanel.getLayout();
        cardLayout.show(rightPanel, "Panel Produk");

    }//GEN-LAST:event_btnDataProdukActionPerformed

    private void btnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryActionPerformed
        // TODO add your handling code here:

        CardLayout cardLayout = (CardLayout) rightPanel.getLayout();
        cardLayout.show(rightPanel, "Panel Distributor");

    }//GEN-LAST:event_btnCategoryActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        Main.getMainForm().getMainTabedPane().remove(this);
        panel = null;
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnRekapProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekapProdukActionPerformed
        // TODO add your handling code here:
        rightPanel.add(panelRekapProduk,"rekapProduk");
        CardLayout cardLayout = (CardLayout) rightPanel.getLayout();
        cardLayout.show(rightPanel, "rekapProduk");
    }//GEN-LAST:event_btnRekapProdukActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategory;
    private javax.swing.JButton btnDataProduk;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnRekapProduk;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables
}
