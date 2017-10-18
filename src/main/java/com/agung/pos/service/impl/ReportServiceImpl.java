/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ReportServiceImpl.java
 * 
 *  Created on Dec 8, 2015, 10:19:48 PM
 */
package com.agung.pos.service.impl;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

import com.agung.pos.entity.LapHarian;
import com.agung.pos.entity.LapPendapatan;
import com.agung.pos.entity.Nota;
import com.agung.pos.entity.PenjualanDetail;
import com.agung.pos.entity.Produk;
import com.agung.pos.service.MasterService;
import com.agung.pos.service.ReportService;
import com.agung.pos.service.TransaksiService;
import com.agung.pos.ui.report.LaporanPendapatan;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import org.hibernate.HibernateException;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author agung
 */
@Service("reportService")
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = new LoggerContext().getLogger(ReportServiceImpl.class);

    @Autowired
    private TransaksiService transaksiService;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MasterService masterService;

    private List<Nota> dataBelanja(String id) {
        List<Nota> result = new ArrayList<>();

        List<PenjualanDetail> details = transaksiService.cariDataBelanjaByIdPenjualan(id);
        for (PenjualanDetail pd : details) {

            Nota nt = new Nota();

            nt.setNamaProduk(pd.getProduk().getNamaProduk());
            //nt.setIdPenjualan(pd.getPenjualan().getId());
            nt.setNoStruk(pd.getPenjualan().getId().substring(0, 5));
            nt.setQuantity(pd.getQuantity());
            nt.setHarga(pd.getHarga());
            nt.setSubTotal(pd.getSubTotal());
            nt.setTanggal(pd.getPenjualan().getCreateDate());
            result.add(nt);
        }

        return result;
    }

    private List<Produk> dataProduk() {
        List<Produk> result = new ArrayList<>();

        List<Produk> details = masterService.cariSemuaProduk();
        for (Produk p : details) {

            Produk pd = new Produk();

            pd.setKodeProduk(p.getKodeProduk());
            pd.setNamaProduk(p.getNamaProduk());
            pd.setHarga(p.getHarga());
            pd.setJumlahProduk(p.getJumlahProduk());
            result.add(pd);
        }
        return result;
    }

    @Override
    public JasperPrint cetakLaporanTransaksiHarian(Date tanggal) {
        try {

            List<LapHarian> hasil
                    = sessionFactory.getCurrentSession().createQuery("select pd.produk.namaProduk as namaProduk , "
                            + "sum(pd.quantity) as jumlah,"
                            + "sum(pd.subTotal) as subTotal from PenjualanDetail pd "
                            + "where date(pd.penjualan.createDate) = date(:tanggal) "
                            + "group by pd.produk.namaProduk "
                            + "order by pd.produk.namaProduk")
                    .setParameter("tanggal", tanggal)
                    .setResultTransformer(Transformers.aliasToBean(LapHarian.class))
                    .list();

            InputStream inputStream
                    = getClass().getResourceAsStream("/report/LaporanTransaksiHarian.jasper");
            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("tanggal", tanggal);

            JasperPrint jp
                    = JasperFillManager
                    .fillReport(inputStream, parameter,
                            new JRBeanCollectionDataSource(hasil));

            return jp;
        } catch (JRException | HibernateException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public JasperPrint cetakNotaBelanja(String id) {
        try {
            JRFileVirtualizer virtualizer = new JRFileVirtualizer(2,"/tmp");

            InputStream inputStream
                    = getClass().getResourceAsStream("/report/nota_belanja.jasper");
            List<Nota> listWrapper = dataBelanja(id);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("idPenjualan", id);
            parameter.put("title", "NOTA BELANJA");
            parameter.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            
            JasperPrint jp
                    = JasperFillManager
                    .fillReport(inputStream, parameter,
                            new JRBeanCollectionDataSource(listWrapper));
            
            virtualizer.setReadOnly(false);

            return jp;
        } catch (JRException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public JasperPrint cetakRekapProduk() {
        try {

            JRFileVirtualizer virtualizer = new JRFileVirtualizer(2, "/tmp");

            InputStream inputStream
                    = getClass().getResourceAsStream("/report/Rekap_produk.jasper");
            List<Produk> listWrapper = dataProduk();
            Map<String, Object> parameter = new HashMap<>();

            parameter.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            parameter.put("tanggalCetak", new Date());

            JasperPrint jp
                    = JasperFillManager
                    .fillReport(inputStream, parameter,
                            new JRBeanCollectionDataSource(listWrapper));

            virtualizer.setReadOnly(false);

            return jp;
        } catch (JRException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public JasperPrint cetakLaporanPendapatan(Date mulai, Date sampai) {
        try {

            JRFileVirtualizer virtualizer = new JRFileVirtualizer(2, "/tmp");

            List<LaporanPendapatan> hasil
                    = sessionFactory.getCurrentSession()
                    .createQuery("select p.tanggalTransaksi as tanggalTransaksi, "
                            + "sum(p.totalBayar) as totalBayar "
                            + "from Penjualan p "
                            + "where p.createDate "
                            + ">= :mulai and p.createDate <= :sampai "
                            + "group by p.tanggalTransaksi "
                            + "order by p.tanggalTransaksi asc")
                    .setParameter("mulai", mulai)
                    .setParameter("sampai", sampai)
                    .setResultTransformer(Transformers.aliasToBean(LapPendapatan.class))
                    .list();

            InputStream inputStream
                    = getClass().getResourceAsStream("/report/LaporanPendapatan.jasper");
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("tglMulai", mulai);
            parameter.put("tglSampai", sampai);
            parameter.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

            JasperPrint jp
                    = JasperFillManager
                    .fillReport(
                            inputStream, parameter,
                            new JRBeanCollectionDataSource(hasil));

            virtualizer.setReadOnly(false);

            return jp;

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

}
