/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  PenjualanTableModel.java
 * 
 *  Created on Dec 8, 2015, 11:49:11 AM
 */
package com.agung.pos.ui.tableModel;

import com.agung.pos.entity.Penjualan;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class PenjualanTableModel extends AbstractTableModel{

    private List<Penjualan> listPenjualan = new ArrayList<Penjualan>();
    private String[] header = {"ID Penjualan","Tanggal Transaksi","Total Pembayaran"};
    
    public PenjualanTableModel(List<Penjualan> listPenjualan){
        this.listPenjualan = listPenjualan;
    }
    
    @Override
    public int getRowCount() {
        return listPenjualan.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Penjualan p = listPenjualan.get(rowIndex);
        NumberFormat formater = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        switch(columnIndex){
            case 0:return p.getId().substring(0, 5);
            case 1:return p.getTanggalTransaksi();
            case 2:return formater.format(p.getTotalBayar());
                default:return "";
        }
    }
    
}
