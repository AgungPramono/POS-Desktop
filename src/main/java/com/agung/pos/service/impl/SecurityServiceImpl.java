/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  SecurityServiceImpl.java
 * 
 *  Created on Dec 7, 2015, 4:33:05 PM
 */
package com.agung.pos.service.impl;

import com.agung.pos.dao.KasirDao;
import com.agung.pos.entity.security.Kasir;
import com.agung.pos.service.SecurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author agung
 */
@Service("securityService")
@Transactional(readOnly = true)
public class SecurityServiceImpl implements SecurityService {
    
    @Autowired private KasirDao kd;
    
    @Transactional(readOnly = false)
    @Override
    public void simpanKasir(Kasir k) {
        if(k != null){
            kd.save(k);
        }
    }

    @Override
    public Kasir findByUsername(String username) {
        if(username == null){
            return null;
        }
        return kd.findByUserName(username);
    }

    @Override
    public List<Kasir> cariSemuaKasir() {
        return kd.findAll();
    }
    
    @Transactional(readOnly = false)
    @Override
    public void hapusKasir(Kasir k) {
        if(k != null){
            kd.delete(k);
        }
    }

    @Override
    public Kasir findByKode(String kode) {
        if(kode == null){
            return null;
        }
        return kd.findByKodeKasir(kode);
    }
    
}
