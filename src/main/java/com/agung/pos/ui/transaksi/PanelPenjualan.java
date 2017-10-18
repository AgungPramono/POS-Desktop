/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  PanelPenjualan.java
 * 
 *  Created on Dec 7, 2015, 11:07:59 PM
 */
package com.agung.pos.ui.transaksi;

import com.agung.pos.Main;
import com.agung.pos.entity.Penjualan;
import com.agung.pos.entity.PenjualanDetail;
import com.agung.pos.entity.Produk;
import com.agung.pos.entity.security.Kasir;
import com.agung.pos.ui.util.TableUtil;
import com.agung.pos.ui.util.TextComponentUtils;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import org.joda.time.DateTime;

/**
 *
 * @author agung
 */
public class PanelPenjualan extends javax.swing.JPanel {

    private static PanelPenjualan panel;
    public static final String PANEL_NAME = "<html><b> Form Penjualan</b><html>";
    private Penjualan penjualan;
    private List<PenjualanDetail> penjualanDetail = new ArrayList<PenjualanDetail>();
    private PenjualanDetail detail;
    DateTime sekarang = new DateTime();

    public PanelPenjualan() {
        initComponents();
        //tbPenjualan.setAutoCreateColumnsFromModel(false);
        jdcTanggal.setDate(sekarang.toDate());

        //Kasir kasir = Main.getSecurityService().findByUsername(Main.getKasir().getUserName());
        txtKasir.setText(Main.getKasir().getNamaKasir());

        refreshTable();
        tbPenjualan.getSelectionModel().addListSelectionListener(new TableSelection());

        enableForm(false);
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(true);
        btnBatal.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSimpan.setEnabled(false);
    }

    public static PanelPenjualan getPanelPenjualan() {
        if (panel == null) {
            panel = new PanelPenjualan();
        }
        return panel;
    }

    private void clearForm() {
        txtKodeBarang.setText("");
        lblTotal.setText("Rp.");
        penjualan = null;
        jdcTanggal.setDate(sekarang.toDate());
        penjualanDetail = new ArrayList<PenjualanDetail>();
        tbPenjualan.setModel(new PenjualanDetailTableModel(penjualanDetail));
    }

    private void enableForm(Boolean status) {
        txtKodeBarang.setEnabled(status);
        btnLookupProduk.setEnabled(status);
        tbPenjualan.setEnabled(status);
    }

    private Boolean validateForm() {
        if (penjualanDetail == null || penjualanDetail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Transaksi tidak boleh kosong!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void loadFormToModel() {
        //Kasir kasir = Main.getSecurityService().findByUsername(Main.getKasir().getUserName());
        penjualan.setDaftarPenjualan(penjualanDetail);
        penjualan.setTanggalTransaksi(jdcTanggal.getDate());
        penjualan.setKasir(Main.getKasir());
        BigDecimal total = BigDecimal.ZERO;
        for (PenjualanDetail pd : penjualanDetail) {
            total = total.add(pd.getSubTotal());
            pd.setPenjualan(penjualan);
        }
        penjualan.setTotalBayar(total);
    }

    private void loadModelToForm() {
        penjualanDetail = penjualan.getDaftarPenjualan();
        tbPenjualan.setModel(new PenjualanDetailTableModel(penjualanDetail));
        TableUtil.initColumn(tbPenjualan);
        System.out.println("id = " + penjualan.getId());
        lblTotal.setText("Rp. " + TextComponentUtils.formatNumber(penjualan.getTotalBayar()));
    }

    private void refreshTable() {
        tbPenjualan.setModel(new PenjualanDetailTableModel(penjualanDetail));
        TableUtil.initColumn(tbPenjualan);
    }

    private void refreshTotalLabel() {
        if (penjualanDetail != null && !penjualanDetail.isEmpty()) {
            BigDecimal total = BigDecimal.ZERO;
            for (PenjualanDetail pd : penjualanDetail) {
                total = total.add(pd.getSubTotal());
            }
            lblTotal.setText("Rp. " + TextComponentUtils.formatNumber(total));
        }
    }

    private void minSubTotalLabel() {
        BigDecimal total = BigDecimal.ZERO;
        //inisialisasi Pembayaran detail
        for (PenjualanDetail pd : penjualanDetail) {
            //jumlah bayar
            total = total.subtract(pd.getSubTotal());
        }
        lblTotal.setText("Rp. " + TextComponentUtils.formatNumber(total));
    }

    private void addPenjualanDetail(Produk p) {
        if (p != null) {
            PenjualanDetail pd = new PenjualanDetail();
            pd.setProduk(p);
            pd.setHarga(p.getHarga());
            pd.setQuantity(1);
            if (pd.getSubTotal() != null) {
                pd.setSubTotal(pd.getSubTotal().add(p.getHarga()));
            } else {
                pd.setSubTotal(p.getHarga());
            }
            penjualanDetail.add(pd);
            refreshTable();
            refreshTotalLabel();
        } else {
            JOptionPane.showMessageDialog(this, "Barang tidak ada!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDialogPrint() {
        JDialog dialog = new JDialog(Main.getMainForm(), true);
        dialog.setTitle("Cetak Nota Belanja");
        String id = penjualan.getId();
        JasperPrint jp
                = Main.getReportService().cetakNotaBelanja(id);
        System.out.println("id yg akan dicetak = " + id);
        JRViewer viewer = new JRViewer(jp);
        dialog.add(viewer);
        dialog.add(viewer, BorderLayout.CENTER);

        dialog.pack();
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKasir = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jdcTanggal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        btnLookupProduk = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPenjualan = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnHapusDetail = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 153, 255));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 2, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Kasir");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Tanggal Transaksi");

        txtKasir.setEditable(false);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 1, true));

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/save_as.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/undo.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/exit.png"))); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnCari.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/window_view.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/document_plain_new_1.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/code_edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/document_delete.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKeluar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnCari, btnEdit, btnHapus, btnKeluar, btnSimpan, btnTambah});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar)
                    .addComponent(btnCari)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBatal, btnCari, btnEdit, btnHapus, btnKeluar, btnSimpan, btnTambah});

        jdcTanggal.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Kode Barang");

        txtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBarangActionPerformed(evt);
            }
        });

        btnLookupProduk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLookupProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/add.gif"))); // NOI18N
        btnLookupProduk.setText("cari produk");
        btnLookupProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLookupProdukActionPerformed(evt);
            }
        });

        tbPenjualan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbPenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Kuantitas", "Harga Satuan", "Sub Total"
            }
        ));
        tbPenjualan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPenjualan.setRowHeight(19);
        jScrollPane1.setViewportView(tbPenjualan);

        lblTotal.setFont(new java.awt.Font("Courier New", 1, 36)); // NOI18N
        lblTotal.setText("Rp. 0");

        btnHapusDetail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapusDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/child/delete.gif"))); // NOI18N
        btnHapusDetail.setText("hapus item");
        btnHapusDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLookupProduk)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnHapusDetail)
                                .addGap(38, 38, 38)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLookupProduk)
                        .addComponent(btnHapusDetail))
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtKasir, txtKodeBarang});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapusDetail, btnLookupProduk});

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

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            if (penjualan == null) {
                penjualan = new Penjualan();
            }
            loadFormToModel();
            try {

                Penjualan p = Main.getTransaksiService().cariPenjualanById(penjualan);
                if (p == null) {
                    Main.getTransaksiService().save(penjualan);
                } else {
                    Main.getTransaksiService().saveReturn(penjualan);
                }
                //Main.getTransaksiService().saveReturn(penjualan);
                showDialogPrint();
                clearForm();
                JOptionPane.showMessageDialog(this, "Data Berhasil disimpan!",
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Data gagal disimpan!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        clearForm();
        enableForm(true);
        //pengaturan tombol
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(false);
        btnBatal.setEnabled(true);
        btnEdit.setEnabled(false);
        btnSimpan.setEnabled(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (penjualan != null) {
            enableForm(true);
            btnHapus.setEnabled(false);
            btnTambah.setEnabled(false);
            btnBatal.setEnabled(true);
            btnEdit.setEnabled(false);
            btnSimpan.setEnabled(true);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        
        int resp = JOptionPane.showConfirmDialog(PanelPenjualan.this, "Apakah anda yakin ingin menghapus transaksi ini ?",
                "Konfirmasi",JOptionPane.YES_NO_OPTION);
      
        if (penjualan != null && resp == 0) {
            try {
                Main.getTransaksiService().hapusDataPenjualan(penjualan);
                clearForm();
                penjualan = null;
                refreshTable();
                enableForm(false);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!",
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
                //pengaturan tombol
                btnHapus.setEnabled(false);
                btnTambah.setEnabled(true);
                btnBatal.setEnabled(false);
                btnEdit.setEnabled(false);
                btnSimpan.setEnabled(true);
            } catch (Exception e) {
                //log.error(ex);
                JOptionPane.showMessageDialog(this, "Data masih digunakan tidak bisa dihapus!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            clearForm();
            refreshTable();
            penjualan = null;
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        clearForm();
        enableForm(false);
        //pengaturan tombol
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(true);
        btnBatal.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSimpan.setEnabled(false);

    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        Penjualan p = new LookupPenjualanDialog().showDialog();
        if (p != null) {
            penjualan = Main.getTransaksiService().cariPenjualanById(p);
            loadModelToForm();
            tbPenjualan.setEnabled(true);
            //edit mode
            btnHapus.setEnabled(true);
            btnTambah.setEnabled(false);
            btnBatal.setEnabled(true);
            btnEdit.setEnabled(true);
            btnSimpan.setEnabled(false);
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        Main.getMainForm().getMainTabedPane().remove(this);
        panel = null;
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void txtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBarangActionPerformed
        // TODO add your handling code here:
        String kode = txtKodeBarang.getText();
        try {
            //cari apakah barang ada dalama tblPenjualanDetail
            boolean isProdukInPenjualanDetail = false;
            for (PenjualanDetail pd : penjualanDetail) {
                if (pd.getProduk().getKodeProduk().equals(kode)) {
                    pd.setQuantity(pd.getQuantity() + 1);
                    pd.setSubTotal(
                            pd.getHarga().multiply(
                                    new BigDecimal(pd.getQuantity())));
                    isProdukInPenjualanDetail = true;
                    break;
                }
            }
            if (isProdukInPenjualanDetail) {
                refreshTable();
                refreshTotalLabel();
            } else {
                Produk p = Main.getMasterService().findByKodeProduk(kode);
                if (p != null) {
                    addPenjualanDetail(p);
                } else {
                    JOptionPane.showMessageDialog(this, "Barang tidak ditemukan!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            txtKodeBarang.setText("");
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtKodeBarangActionPerformed

    private void btnLookupProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLookupProdukActionPerformed
        // TODO add your handling code here:
        Produk p = new LookupProdukDialog().showDialog();
        if (p != null) {
            txtKodeBarang.setText(p.getKodeProduk());
            boolean isProdukPenjualanDetail = false;
            for (PenjualanDetail pd : penjualanDetail) {
                if (pd.getProduk().getId().equals(p.getId())) {
                    pd.setQuantity(pd.getQuantity() + 1);
                    pd.setSubTotal(
                            pd.getHarga().multiply(
                                    new BigDecimal(pd.getQuantity())));
                    isProdukPenjualanDetail = true;
                    break;
                }
            }
            if (isProdukPenjualanDetail) {
                refreshTable();
                refreshTotalLabel();
            } else {
                addPenjualanDetail(p);
            }
        }
    }//GEN-LAST:event_btnLookupProdukActionPerformed

    private void btnHapusDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusDetailActionPerformed
        // TODO add your handling code here:
        if (tbPenjualan.getSelectedRow() >= 0 && detail != null) {
            if (detail != null) {
                penjualanDetail.remove(detail);
            }
            //refreshTable(detailsPembayaran);
            minSubTotalLabel();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(Main.getMainForm(),
                    "Tidak ada data dipilih !!", "Terjadi Kesalahan !!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusDetailActionPerformed

    private class PenjualanDetailTableModel extends AbstractTableModel {

        private List<PenjualanDetail> penjualanDetails;
        private String[] header = {"Kode Barang", "Nama Barang", "Harga Satuan",
            "Kuantitas", "Sub Total"};

        PenjualanDetailTableModel(List<PenjualanDetail> salesDetails) {
            this.penjualanDetails = salesDetails;
        }

        @Override
        public int getRowCount() {
            return penjualanDetails.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public String getColumnName(int column) {
            return header[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PenjualanDetail p = penjualanDetails.get(rowIndex);
            NumberFormat formater = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            switch (columnIndex) {
                case 0:
                    return p.getProduk().getKodeProduk();
                case 1:
                    return p.getProduk().getNamaProduk();
                case 2:
                    return formater.format(p.getHarga());
                case 3:
                    return p.getQuantity();
                case 4:
                    return formater.format(p.getSubTotal());
                default:
                    return "";
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 2 || columnIndex == 4) {
                return BigDecimal.class;
            } else if (columnIndex == 3) {
                return Integer.class;
            }
            return String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == 3) {
                return true;
            }
            return false;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            PenjualanDetail p = penjualanDetails.get(rowIndex);
            if (columnIndex == 3) {
                p.setQuantity((Integer) aValue);
                p.setSubTotal(p.getHarga().multiply(
                        new BigDecimal(p.getQuantity())));
                refreshTotalLabel();
            }
        }

    }

    private class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (tbPenjualan.getSelectedRow() >= 0) {
                detail = penjualanDetail.get(tbPenjualan.getSelectedRow());
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusDetail;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnLookupProduk;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdcTanggal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbPenjualan;
    private javax.swing.JTextField txtKasir;
    private javax.swing.JTextField txtKodeBarang;
    // End of variables declaration//GEN-END:variables
}
