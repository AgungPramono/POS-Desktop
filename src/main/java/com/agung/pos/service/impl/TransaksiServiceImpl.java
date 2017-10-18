/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  TransaksiServiceImpl.java
 * 
 *  Created on Dec 7, 2015, 4:32:47 PM
 */
package com.agung.pos.service.impl;

import com.agung.pos.dao.PenjualanDao;
import com.agung.pos.dao.PenjualanDetailDao;
import com.agung.pos.dao.ProdukDao;
import com.agung.pos.entity.Penjualan;
import com.agung.pos.entity.PenjualanDetail;
import com.agung.pos.entity.Produk;
import com.agung.pos.service.TransaksiService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author agung
 */
@Service("transaksiService")
@Transactional(readOnly = true)
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    PenjualanDao pd;
    @Autowired
    private PenjualanDetailDao pDetailDao;
    @Autowired
    private ProdukDao produkDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Penjualan p) {
        if (p != null) {
            pd.save(p);
            for (PenjualanDetail detail : p.getDaftarPenjualan()) {
                Produk prd = detail.getProduk();
                prd.setJumlahProduk(prd.getJumlahProduk() - detail.getQuantity());
                produkDao.save(prd);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusDataPenjualan(Penjualan p) {
        if (p != null) {
            pd.delete(p);
        }
    }

    @Override
    public List<Penjualan> cariSemuaDataPenjualan() {
        return pd.cariSemua();
    }

    @Override
    public Penjualan cariPenjualanById(Penjualan p) {
        if (p != null) {
            return pd.findByIdPenjualan(p.getId());
        }
        return null;
    }

    @Override
    public List<PenjualanDetail> cariDataBelanjaByIdPenjualan(String id) {
        if (id != null) {
            return pDetailDao.cariDataBelanjaByIdPenjualan(id);
        }
        return null;
    }

    @Override
    public List<Penjualan> cariPenjualanBerdasarkanPeriode(Date tglMulai, Date tglSampai) {
        if (tglMulai == null && tglMulai == null) {
            return null;
        }
        return pd.findByTanggalTransaksiBetween(tglMulai, tglSampai);
    }

    @Transactional(readOnly = false)
    @Override
    public void saveReturn(Penjualan p) {
        if(p != null){
            pd.saveReturn(p);
            for (PenjualanDetail detail : p.getDaftarPenjualan()) {
                Produk prd = detail.getProduk();
                prd.setJumlahProduk(prd.getJumlahProduk() + 1);
                produkDao.save(prd);
            }
        }
    }
}
