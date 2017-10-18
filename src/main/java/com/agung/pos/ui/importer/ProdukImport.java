/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukExporter.java
 * 
 *  Created on Dec 27, 2015, 8:52:51 AM
 */
package com.agung.pos.ui.importer;

import com.agung.pos.entity.Produk;
import com.agung.pos.ui.util.TextComponentUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agung
 */
public class ProdukImport {
   
   public static HasilImportProduk proses(File file) throws FileNotFoundException{
       try {
           List<Produk> produks = new ArrayList<>();
           List<ImportError> gagal = new ArrayList<>();
           
           HasilImportProduk hasil = new HasilImportProduk();
           hasil.setSucces(produks);
           hasil.setError(gagal);
           
           FileReader fr = new FileReader(file);
           BufferedReader br = new BufferedReader(fr);
           
           String data = br.readLine();
           
           Integer noBaris = 1;
           
           while((data = br.readLine()) != null){
               noBaris++;
               System.out.println("Data : "+data);
               String[] kolom = data.split(",");
               System.out.println("jumlah kolom "+kolom.length);
               
               if(kolom.length != 4){
                   ImportError err = new ImportError();
                   
                   err.setBaris(noBaris);
                   err.setData(data);
                   err.setKeterangan("jumlah field salah " + kolom.length);
                   
                   gagal.add(err);
                   
                   continue;
               }
               
               Produk p = new Produk();
               p.setKodeProduk(kolom[0]);
               p.setNamaProduk(kolom[1]);
               p.setHarga(TextComponentUtils.parseNumberToBigDecimal(kolom[2]));
               p.setJumlahProduk(Long.parseLong(kolom[3]));
               
               produks.add(p);
           }
           br.close();
           fr.close();
           
           return hasil;
       } catch (IOException ex) {
           Logger.getLogger(ProdukImport.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }   
}
