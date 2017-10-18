/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ReportService.java
 * 
 *  Created on Dec 8, 2015, 10:19:34 PM
 */
package com.agung.pos.service;

import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author agung
 */
public interface ReportService {
    
    JasperPrint cetakLaporanTransaksiHarian(Date tanggal);
    JasperPrint cetakNotaBelanja(String id);
    JasperPrint cetakRekapProduk();
    JasperPrint cetakLaporanPendapatan(Date mulai,Date sampai);
}
