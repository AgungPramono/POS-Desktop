/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  SecurityService.java
 * 
 *  Created on Dec 7, 2015, 4:33:17 PM
 */
package com.agung.pos.service;

import com.agung.pos.entity.security.Kasir;
import java.util.List;

/**
 *
 * @author agung
 */
public interface SecurityService {
    void simpanKasir(Kasir k);
    Kasir findByUsername(String username);
    List<Kasir> cariSemuaKasir();
    void hapusKasir(Kasir k);
    Kasir findByKode(String kode);
}
