/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  DistributorTableModel.java
 * 
 *  Created on Dec 8, 2015, 8:37:00 PM
 */
package com.agung.pos.ui.tableModel;

import com.agung.pos.entity.Distributor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author agung
 */
public class DistributorTableModel extends AbstractTableModel{

    private List<Distributor> listDistributor = new ArrayList<Distributor>();
    private String[]header = {"kode Distributor","Nama Distributor","Alamat Distributor"};
    
    public DistributorTableModel(List<Distributor>listDistributors){
        this.listDistributor = listDistributors;
    }
    
    @Override
    public int getRowCount() {
        return listDistributor.size();
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
        Distributor d = listDistributor.get(rowIndex);
        switch(columnIndex){
            case 0:return d.getKodeDistributor();
            case 1:return d.getNamaDistributor();
            case 2:return d.getAlamatDistributor();
                default:return "";
        }
        
    }
    
}
