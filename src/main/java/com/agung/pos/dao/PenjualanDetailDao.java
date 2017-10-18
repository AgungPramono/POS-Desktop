/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  PenjualanDetailDao.java
 * 
 *  Created on Dec 7, 2015, 8:42:20 PM
 */
package com.agung.pos.dao;

import com.agung.pos.entity.PenjualanDetail;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author agung
 */
@Repository
public class PenjualanDetailDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<PenjualanDetail>cariDataBelanjaByIdPenjualan(String id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from PenjualanDetail pd where pd.penjualan.id=:id")
                .setParameter("id", id)
                .list();
    }
}
