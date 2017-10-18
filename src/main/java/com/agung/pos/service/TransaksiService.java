/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  TransaksiService.java
 * 
 *  Created on Dec 7, 2015, 4:31:59 PM
 */
package com.agung.pos.service;

import com.agung.pos.entity.Penjualan;
import com.agung.pos.entity.PenjualanDetail;
import java.util.Date;
import java.util.List;

/**
 *
 * @author agung
 */
public interface TransaksiService {
    void save(Penjualan p);
    void saveReturn(Penjualan p);
    void hapusDataPenjualan(Penjualan p);
    List<Penjualan> cariSemuaDataPenjualan();
    Penjualan cariPenjualanById(Penjualan p);
    List<Penjualan> cariPenjualanBerdasarkanPeriode(Date tglMulai,Date tglSampai);
    List<PenjualanDetail> cariDataBelanjaByIdPenjualan(String id);
}
