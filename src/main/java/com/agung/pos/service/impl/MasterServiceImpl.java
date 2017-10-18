/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  MasterServiceImpl.java
 * 
 *  Created on Dec 7, 2015, 4:32:27 PM
 */
package com.agung.pos.service.impl;

import com.agung.pos.dao.DistributorDao;
import com.agung.pos.dao.KasirDao;
import com.agung.pos.dao.ProdukDao;
import com.agung.pos.entity.Distributor;
import com.agung.pos.entity.Produk;
import com.agung.pos.entity.security.Kasir;
import com.agung.pos.service.MasterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author agung
 */
@Service("masterService")
@Transactional(readOnly = true)
public class MasterServiceImpl implements MasterService{
    
    @Autowired KasirDao kd;
    @Autowired ProdukDao pd;
    @Autowired DistributorDao dd;
    
    public Kasir findByUsername(Kasir k){
        if(k.getUserName() != null){
            
        }
        return null;
    }

    @Override
    public Produk findByKodeProduk(String kode) {
        return pd.findByKodeProduk(kode);
    }

    @Override
    public List<Produk> cariSemuaProduk() {
        return pd.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void simpanProduk(Produk p) {
        if (p != null) {
            pd.save(p);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusProduk(Produk p) {
        if(p != null){
            pd.delete(p);
        }
    }

    @Override
    public Produk findProdukById(String id) {
        if(id != null){
            return pd.findOne(id);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public void simpanDistributor(Distributor d) {
        dd.save(d);
    }

    @Override
    @Transactional(readOnly = false)
    public void hapusDistributor(Distributor d) {
        if(d != null){
            dd.delete(d);
        }
    }

    @Override
    public Distributor findByIdDistributor(String id) {
        if(id != null){
            return dd.findOne(id);
        }
        return null;
    }

    @Override
    public List<Distributor> cariSemuaDistributor() {
        return dd.findAllDistributor();
    }

    public Distributor findByKodeDistributor(String kode) {
        if(kode == null){
            return null;
        }
        return dd.findByKode(kode);
    }
    
}
