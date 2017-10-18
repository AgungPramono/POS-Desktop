/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukTableModel.java
 * 
 *  Created on Dec 8, 2015, 9:18:35 AM
 */
package com.agung.pos.ui.tableModel;

import com.agung.pos.entity.Produk;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import org.springframework.format.number.NumberFormatter;

/**
 *
 * @author agung
 */
public class ProdukTableModel extends AbstractTableModel{
    
    private List<Produk> listProduk = new ArrayList<Produk>();
    private String[]header = {"Kode produk","Nama Produk","Harga Produk","Jumlah"};
    
    public ProdukTableModel (List<Produk>listProduks){
        this.listProduk = listProduks;
    }
    
    @Override
    public int getRowCount() {
        return listProduk.size();
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
        Produk p = listProduk.get(rowIndex);
        NumberFormat fomater = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        switch(columnIndex){
            case 0:return p.getKodeProduk();
            case 1:return p.getNamaProduk();
            case 2:return fomater.format(p.getHarga());
            case 3:return p.getJumlahProduk();
                default:return "";
        }
    }
    
}
