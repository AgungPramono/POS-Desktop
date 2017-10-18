/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  DistributorDao.java
 * 
 *  Created on Dec 8, 2015, 8:31:55 PM
 */
package com.agung.pos.dao;

import com.agung.pos.entity.Distributor;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */
@Repository
public class DistributorDao{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(Distributor d){
        sessionFactory.getCurrentSession().saveOrUpdate(d);
    }
    
    public void delete(Distributor d){
        sessionFactory.getCurrentSession().delete(d);
    }
    
    public Distributor findOne(String id){
        return (Distributor) sessionFactory.getCurrentSession()
                .createQuery("from Distributor d where d.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }
    
    public List<Distributor> findAllDistributor() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Distributor d")
                .list();
    }
    
    public Distributor findByKode(String kode){
        return (Distributor) sessionFactory.getCurrentSession()
                .createQuery("from Distributor d where d.kodeDistributor=:kode")
                .setParameter("kode", kode)
                .uniqueResult();
    }
    
    public Distributor findByNama(String nama){
        return (Distributor) sessionFactory.getCurrentSession()
                .createQuery("from Distributor d where d.namaDistributor=:nama")
                .setParameter("nama", nama)
                .uniqueResult();
    }
    
}
