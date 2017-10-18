/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukService.java
 * 
 *  Created on Nov 13, 2015, 8:47:16 AM
 */
package com.agung.pos.service;

import com.agung.pos.entity.Produk;

/**
 *
 * @author agung
 */
public interface ProdukService {
    public void save(Produk p);
    public void delete(Produk p);
    public Produk findByKode(String kode);
    public Produk findProdukById(String id);
    Long countAllProduk();
    
}
