/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  MasterService.java
 * 
 *  Created on Dec 7, 2015, 4:31:49 PM
 */
package com.agung.pos.service;

import com.agung.pos.entity.Distributor;
import com.agung.pos.entity.Produk;
import java.util.List;

/**
 *
 * @author agung
 */
public interface MasterService {
    void simpanProduk(Produk p);
    void hapusProduk(Produk p);
    Produk findProdukById(String id);
    Produk findByKodeProduk(String kode);
    List<Produk> cariSemuaProduk();
    
    void simpanDistributor(Distributor d);
    void hapusDistributor(Distributor d);
    Distributor findByIdDistributor(String id);
    Distributor findByKodeDistributor(String kode);
    List<Distributor> cariSemuaDistributor();
}
