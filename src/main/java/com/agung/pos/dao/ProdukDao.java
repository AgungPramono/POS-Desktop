/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukDao.java
 * 
 *  Created on Nov 13, 2015, 8:46:44 AM
 */
package com.agung.pos.dao;

import com.agung.pos.entity.Produk;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */
@Repository
public class ProdukDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(Produk p){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(p);
    }
    
    public void delete(Produk p){
        sessionFactory.getCurrentSession()
                .delete(p);
    }
    
    public Produk findOne(String id){
        return (Produk) sessionFactory.getCurrentSession()
                .createQuery("from Produk p where p.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }
    
    public List<Produk> findAll(){
        List<Produk> produks =  sessionFactory.getCurrentSession()
                .createQuery("from Produk p order by p.namaProduk asc")
                .list();
        return produks;
    }
    
    public Produk findByKodeProduk(String kode) {
        return (Produk) sessionFactory.getCurrentSession()
                .createQuery("from Produk p where p.kodeProduk=:kode")
                .setParameter("kode", kode)
                .uniqueResult();
    }
    
    public Long count(){
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select COUNT(p) FROM Produk p")
                .uniqueResult();
    }
}
