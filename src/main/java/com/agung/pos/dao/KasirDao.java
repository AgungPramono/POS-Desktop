/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  KasirDao.java
 * 
 *  Created on Dec 7, 2015, 4:39:08 PM
 */
package com.agung.pos.dao;


import com.agung.pos.entity.security.Kasir;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */
@Repository
public class KasirDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(Kasir k){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(k);
    }
    
    public void delete(Kasir k) {
        sessionFactory.getCurrentSession()
                .delete(k);
    }
    
    public List<Kasir> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Kasir k")
                .list();
    }
 
    public Kasir findByUserName(String username) {
        return (Kasir) sessionFactory.getCurrentSession()
                .createQuery("SELECT k from Kasir k WHERE k.userName=:username")
                .setParameter("username", username)
                .uniqueResult();
    }
    
    public Kasir findByKodeKasir(String kode) {
        return (Kasir) sessionFactory.getCurrentSession()
                .createQuery("SELECT k from Kasir k WHERE k.kodeKasir=:kode")
                .setParameter("kode", kode)
                .uniqueResult();
    }
  
}
