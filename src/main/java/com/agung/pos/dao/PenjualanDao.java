/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  PenjualanDao.java
 * 
 *  Created on Dec 7, 2015, 8:41:33 PM
 */
package com.agung.pos.dao;

import com.agung.pos.entity.Penjualan;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */
@Repository
public class PenjualanDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(Penjualan p) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(p);
    }
    
    public void saveReturn(Penjualan p){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(p);
    }
    
    public void delete(Penjualan p){
        sessionFactory.getCurrentSession()
                .delete(p);
    }
    
    public List<Penjualan> cariSemua() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Penjualan p order by p.tanggalTransaksi desc")
                .list();
    }
    
    public Penjualan findByIdPenjualan(String id){
        return (Penjualan) sessionFactory.getCurrentSession()
                .createQuery("from Penjualan p left join fetch p.daftarPenjualan dp where p.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }
    
    public List<Penjualan> findByTanggalTransaksiBetween(Date tglMulai,Date tglSampai) {
        return sessionFactory.getCurrentSession()
                .createQuery("select p FROM Penjualan p "
                        + "where date(p.createDate) <= date(:tglMulai) and => date(:tglSampai)")
                .setParameter("tglMulai", tglMulai)
                .setParameter("tglSampai", tglSampai)
                .list();
    }
}
