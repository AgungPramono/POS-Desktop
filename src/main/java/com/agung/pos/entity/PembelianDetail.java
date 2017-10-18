/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  PembelianDetail.java
 * 
 *  Created on Dec 7, 2015, 4:30:45 PM
 */
package com.agung.pos.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "tb_pembelian_detail")
public class PembelianDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_produk", nullable = false)
    private Produk produk;

    @ManyToOne
    @JoinColumn(name = "id_pembelian", nullable = false)
    private Pembelian pembelian;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "harga", precision = 19, scale = 0, nullable = false)
    private BigDecimal harga;

    @Column(name = "subtotal", precision = 19, scale = 0, nullable = false)
    private BigDecimal subTotal = BigDecimal.ZERO;
}
