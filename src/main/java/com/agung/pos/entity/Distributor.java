/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  Distributor.java
 * 
 *  Created on Dec 7, 2015, 5:37:42 PM
 */
package com.agung.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "tb_distributor")
public class Distributor extends BaseEntity{
    
    @Column(name = "kode_distributor",nullable = false,unique = true,length = 50)
    private String kodeDistributor;
    @Column(name = "nama_distributor",nullable = false,length = 150)
    private String namaDistributor;
    @Column(name = "alamat_distributor",nullable = false,length = 150)
    private String alamatDistributor;

    public String getKodeDistributor() {
        return kodeDistributor;
    }

    public void setKodeDistributor(String kodeDistributor) {
        this.kodeDistributor = kodeDistributor;
    }

    public String getNamaDistributor() {
        return namaDistributor;
    }

    public void setNamaDistributor(String namaDistributor) {
        this.namaDistributor = namaDistributor;
    }

    public String getAlamatDistributor() {
        return alamatDistributor;
    }

    public void setAlamatDistributor(String alamatDistributor) {
        this.alamatDistributor = alamatDistributor;
    }
    
    
    
}
