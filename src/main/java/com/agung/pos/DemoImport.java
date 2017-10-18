/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  DemoImport.java
 * 
 *  Created on Dec 27, 2015, 10:00:20 AM
 */
package com.agung.pos;

import com.agung.pos.entity.Produk;
import com.agung.pos.ui.importer.HasilImportProduk;
import com.agung.pos.ui.importer.ImportError;
import com.agung.pos.ui.importer.ProdukImport;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author agung
 */
public class DemoImport {
    public static void main(String[]args) throws FileNotFoundException{
        Main.initContext();
        File file = new File("src/main/resources/data/data.csv");
        
        HasilImportProduk hasil = ProdukImport.proses(file);
        
        System.out.println("====== hasil import sukses =============");
        for(Produk p:hasil.getSucces()){
            System.out.println("kode "+p.getKodeProduk());
            System.out.println("nama "+p.getNamaProduk());
            System.out.println("harga"+p.getHarga());
            System.out.println("jumlah"+p.getJumlahProduk());
            //Main.getMasterService().simpanProduk(p);
        }
        
        System.out.println("===== Hasil Import Gagal =====");
        for (ImportError err : hasil.getError()) {
            System.out.println("Baris : "+err.getBaris());
            System.out.println("Keterangan : "+err.getKeterangan());
            System.out.println("Data : "+err.getData());
            System.out.println("---------------------------");
        }
        
        System.out.println("Sukses dilakukan");
    }
    
    public static void getConverter(){
        
    }

    
}
