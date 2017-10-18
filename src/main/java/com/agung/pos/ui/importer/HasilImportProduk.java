/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  HasilImportProduk.java
 * 
 *  Created on Dec 27, 2015, 9:19:24 AM
 */
package com.agung.pos.ui.importer;

import com.agung.pos.entity.Produk;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agung
 */
public class HasilImportProduk {
    private List<Produk> succes = new ArrayList<>();
    private List<ImportError> error = new ArrayList<>();

    public List<Produk> getSucces() {
        return succes;
    }

    public void setSucces(List<Produk> succes) {
        this.succes = succes;
    }

    public List<ImportError> getError() {
        return error;
    }

    public void setError(List<ImportError> error) {
        this.error = error;
    }
}
