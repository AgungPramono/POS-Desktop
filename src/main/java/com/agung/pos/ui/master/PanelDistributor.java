/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  PanelDistributor.java
 * 
 *  Created on Dec 8, 2015, 5:11:37 PM
 */
package com.agung.pos.ui.master;

import com.agung.pos.Main;
import com.agung.pos.entity.Distributor;
import com.agung.pos.ui.tableModel.DistributorTableModel;
import com.agung.pos.ui.util.ComponentUtils;
import com.agung.pos.ui.util.TableUtil;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author agung
 */
public class PanelDistributor extends javax.swing.JPanel {

    private Distributor distributor;
    private List<Distributor> lisDistributor;

    public PanelDistributor() {
        initComponents();

        refreshTable();
        tblDistributor.getSelectionModel().addListSelectionListener(new TableSelection());

        enableForm(false);

        //pengaturan tombol ketika panel dibuka
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(true);
        btnBatal.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSimpan.setEnabled(false);
    }

    private void refreshTable() {
        lisDistributor = Main.getMasterService().cariSemuaDistributor();
        if (lisDistributor != null && !lisDistributor.isEmpty()) {
            tblDistributor.setModel(new DistributorTableModel(lisDistributor));
            TableUtil.initColumn(tblDistributor);
        } else {
            tblDistributor.setModel(new DistributorTableModel(new ArrayList<Distributor>()));
            TableUtil.initColumn(tblDistributor);
        }
    }

    private void loadFormToModel() {
        if (distributor == null) {
            distributor = new Distributor();
        }
        distributor.setKodeDistributor(txtKodeDistributor.getText());
        distributor.setNamaDistributor(txtNamaDistributor.getText());
        distributor.setAlamatDistributor(txtAlamatDistributor.getText());
    }

    private void loadModelToForm() {
        txtKodeDistributor.setText(distributor.getKodeDistributor());
        txtAlamatDistributor.setText(distributor.getAlamatDistributor());
        txtNamaDistributor.setText(distributor.getNamaDistributor());
    }

    private Boolean validateForm() {
        if (txtKodeDistributor.getText().trim().length() > 0
                && txtNamaDistributor.getText().trim().length() > 0
                && txtAlamatDistributor.getText().trim().length() > 0) {
            return true;
        }
        return false;
    }

    private void enableForm(boolean status) {
        txtKodeDistributor.setEnabled(status);
        txtAlamatDistributor.setEnabled(status);
        txtNamaDistributor.setEnabled(status);
    }

    private void clearForm() {
        txtKodeDistributor.setText("");
        txtNamaDistributor.setText("");
        txtAlamatDistributor.setText("");
        distributor = null;
        tblDistributor.getSelectionModel().clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        menuEdit = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKodeDistributor = new javax.swing.JTextField();
        txtNamaDistributor = new javax.swing.JTextField();
        txtAlamatDistributor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDistributor = new javax.swing.JTable();

        popupMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popupMenuMouseClicked(evt);
            }
        });

        menuEdit.setText("jMenuItem1");
        popupMenu.add(menuEdit);

        setBackground(new java.awt.Color(255, 102, 51));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 0), 1, true), "DATA DISTRIBUTOR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Kode Distributor");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nama Distributor");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Alamat Distributor");

        txtKodeDistributor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKodeDistributorFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKodeDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtAlamatDistributor))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNamaDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKodeDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAlamatDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, txtAlamatDistributor, txtKodeDistributor, txtNamaDistributor});

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 51), 1, true));

        btnTambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/document_plain_new_1.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/save_as.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/document_delete.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/undo.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Cari");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/code_edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatal)
                .addGap(42, 42, 42)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnEdit, btnHapus, btnSimpan, btnTambah});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSimpan)
                        .addComponent(btnHapus)
                        .addComponent(btnBatal)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBatal, btnEdit, btnHapus, btnSimpan, btnTambah, jLabel4, txtSearch});

        tblDistributor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblDistributor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Distributor", "Nama Distributor", "Alamat Distributor"
            }
        ));
        tblDistributor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblDistributor.setRowHeight(18);
        jScrollPane1.setViewportView(tblDistributor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        clearForm();
        enableForm(true);
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(false);
        btnBatal.setEnabled(true);
        btnEdit.setEnabled(false);
        btnSimpan.setEnabled(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (distributor != null) {
            enableForm(true);
            txtKodeDistributor.setEnabled(false);
            btnHapus.setEnabled(false);
            btnTambah.setEnabled(false);
            btnBatal.setEnabled(true);
            btnEdit.setEnabled(false);
            btnSimpan.setEnabled(true);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        Integer resp = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini ?",
                "Konfirmasi",JOptionPane.YES_NO_OPTION);
        try {
            if (tblDistributor.getSelectedRow() >= 0
                    && distributor != null) {
                if(resp == 0){
                    Main.getMasterService().hapusDistributor(distributor);
                    refreshTable();
                    btnHapus.setEnabled(false);
                    btnTambah.setEnabled(true);
                    btnBatal.setEnabled(false);
                    btnEdit.setEnabled(false);
                    btnSimpan.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "Data Berhasil dihapus..");
                }else{
                    distributor = null;
                    clearForm();
                }
            } else {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Tidak ada data yang ingin di Hapus !!",
                        "Terjadi Kesalahan !!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
            if (validateForm()) {
                loadFormToModel();
                Main.getMasterService().simpanDistributor(distributor);
                refreshTable();
                clearForm();
                enableForm(false);
                btnHapus.setEnabled(false);
                btnTambah.setEnabled(true);
                btnBatal.setEnabled(false);
                btnEdit.setEnabled(false);
                btnSimpan.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Data berhasil Disimpan ...");
            }else {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                        "Semua Kolom bertanda * harus di isi !!",
                        "Terjadi Kesalahan !!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        clearForm();
        enableForm(false);
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(true);
        btnBatal.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSimpan.setEnabled(false);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        for(int i=0; i<tblDistributor.getRowCount(); i++){
            if(tblDistributor.getValueAt(i, 0).toString().startsWith(txtSearch.getText())){
                //select baris yang ditemukan
                tblDistributor.getSelectionModel().setSelectionInterval(i, i);
                //scroll ke baris tersebut kalau ada di bawah atau bagian atas 
                ComponentUtils.scrollToRect(tblDistributor, i);
                break;
            }
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtKodeDistributorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKodeDistributorFocusLost
        // TODO add your handling code here:
        Distributor d = Main.getMasterService().findByKodeDistributor(txtKodeDistributor.getText());
        if(d != null && d.getKodeDistributor()
                .equalsIgnoreCase(txtKodeDistributor.getText())){
            JOptionPane.showMessageDialog(this, "Maaf, Kode yang anda masukkan sudah digunakan !!!",
                    "Terjadi Kesalahan",JOptionPane.ERROR_MESSAGE);
            txtKodeDistributor.setText("");
            txtKodeDistributor.requestFocus();
        }else{
            txtNamaDistributor.requestFocus();
        }
    }//GEN-LAST:event_txtKodeDistributorFocusLost

    private void popupMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popupMenuMouseClicked
        // TODO add your handling code here:
        if(evt.getButton() == MouseEvent.BUTTON3){
            popupMenu.show((Component) evt.getSource(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_popupMenuMouseClicked

    private class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (tblDistributor.getSelectedRow() >= 0) {
                distributor = lisDistributor.get(tblDistributor.getSelectedRow());
                distributor = Main.getMasterService().findByIdDistributor(distributor.getId());
                loadModelToForm();
                btnHapus.setEnabled(true);
                btnTambah.setEnabled(false);
                btnBatal.setEnabled(true);
                btnEdit.setEnabled(true);
                btnSimpan.setEnabled(false);
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuEdit;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JTable tblDistributor;
    private javax.swing.JTextField txtAlamatDistributor;
    private javax.swing.JTextField txtKodeDistributor;
    private javax.swing.JTextField txtNamaDistributor;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
