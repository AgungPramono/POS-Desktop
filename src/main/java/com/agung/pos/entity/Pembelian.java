/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  Pembelian.java
 * 
 *  Created on Dec 7, 2015, 4:30:35 PM
 */
package com.agung.pos.entity;

import com.agung.pos.entity.security.Kasir;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agung
 */

@Entity
@Table(name = "tb_pembelian")
public class Pembelian extends BaseEntity{
    
    @Column(name = "tgl_pembelian",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalPembelian;
    
    @OneToMany(mappedBy = "pembelian",cascade = CascadeType.ALL)
    private List<PembelianDetail> daftarPembelian = new ArrayList<PembelianDetail>();
    
    @Column(name = "total_pembelian",nullable = false,scale = 0,precision = 19)
    private BigDecimal totalPembelian = BigDecimal.ZERO;
    
    @ManyToOne
    @JoinColumn(name = "id_petugas", nullable = false)
    private Kasir kasir;
    
    @ManyToOne
    @JoinColumn(name = "id_distributor",nullable = false)
    private Distributor distributor;
}
