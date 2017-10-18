/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  Produk.java
 * 
 *  Created on Nov 13, 2015, 8:47:05 AM
 */
package com.agung.pos.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "tb_produk")
public class Produk extends BaseEntity{
   
    @Column(nullable = false,unique = true,name = "kode_produk",length = 150)
    private String kodeProduk;
    
    @Column(name = "nama_produk",nullable = false,length = 150)
    private String namaProduk;
    
    @Column(name = "jumlah",nullable = true,length = 255)
    private Long jumlahProduk;
    
    @Column(nullable = false, scale = 0,precision = 19)
    private BigDecimal harga = BigDecimal.ZERO;

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public Long getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(Long jumlahProduk) {
        this.jumlahProduk = jumlahProduk;
    }

    @Override
    public String toString() {
        return getKodeProduk();
    }
    
    
}
