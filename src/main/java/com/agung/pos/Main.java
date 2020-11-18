/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  Main.java
 * 
 *  Created on Dec 20, 2015, 10:51:25 PM
 */
package com.agung.pos;

import com.agung.pos.entity.security.Kasir;
import com.agung.pos.service.MasterService;
import com.agung.pos.service.ProdukService;
import com.agung.pos.service.ReportService;
import com.agung.pos.service.SecurityService;
import com.agung.pos.service.TransaksiService;
import com.agung.pos.ui.LaunchProgressBar;
import com.agung.pos.ui.LoginDialog;
import com.agung.pos.ui.MainMenu;
import com.agung.pos.ui.util.PasswordHelper;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author agung
 */
public class Main {
    private static ApplicationContext context;
    private static MasterService masterService;
    private static ProdukService produkService;
    private static ReportService reportService;
    private static TransaksiService transaksiService;
    private static SecurityService securityService;
    private static MainMenu mainMenu;
    private static Kasir kasir;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static SecurityService getSecurityService() {
        return securityService;
    }
    
    public static MasterService getMasterService() {
        return masterService;
    }

    public static ProdukService getProdukService() {
        return produkService;
    }

    public static ReportService getReportService() {
        return reportService;
    }

    public static TransaksiService getTransaksiService() {
        return transaksiService;
    }

    public static MainMenu getMainForm() {
        return mainMenu;
    }

    public static Kasir getKasir() {
        return kasir;
    }

    public static void setKasir(Kasir kasir) {
        Main.kasir = kasir;
    }
    
    public static void initContext(){
        context = new ClassPathXmlApplicationContext("classpath*:ApplicationConfig.xml");
        //context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        securityService = (SecurityService) context.getBean("securityService");
        masterService = (MasterService)context.getBean("masterService");
        transaksiService = (TransaksiService)context.getBean("transaksiService");
        reportService = (ReportService)context.getBean("reportService");
        
    }
    
    public static void checkDefaultUser(){
        List<Kasir> listKasir = securityService.cariSemuaKasir();
        Kasir defaultPetugas = securityService.findByUsername("root");
        //if(listPetugas != null && listPetugas.isEmpty()){
            if(defaultPetugas == null || listKasir.isEmpty()){
                kasir = new Kasir();
                kasir.setCreateDate(new Date());
                kasir.setKodeKasir("k-001");
                kasir.setUserName("root");
                kasir.setNamaKasir("agung");
                kasir.setPassword(PasswordHelper.getEncryptedTextFromPlainText("admin"));
                kasir.setJenisKelamin("LAKI");
                kasir.setAlamatKasir("Nganjuk");
                //k.setUsername("root");
                securityService.simpanKasir(kasir);
            }
        //}
    }
    
    public static void initLogin(){
        checkDefaultUser();
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        
        boolean notLogin = Boolean.TRUE;
        while(notLogin){
            notLogin = new LoginDialog().showDialog();
        }
        mainMenu.initSecurity();
        mainMenu.setVisible(true);
    }
    
    public static void LaunchSplashScreen(){
        LaunchProgressBar pb = new LaunchProgressBar();
        pb.setVisible(true);
        for (int i = 0; i < 1200; i++) {
            try {
                pb.getProgressBar().setValue(i);
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pb.dispose();
//            initLogin();
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, 
                                    InstantiationException, 
                                    IllegalAccessException {
        initContext();
        //checkDefaultUser();
        
       try {
            PlasticXPLookAndFeel laf = new PlasticXPLookAndFeel();
            PlasticXPLookAndFeel.setCurrentTheme(new ExperienceBlue());
            Options.setPopupDropShadowEnabled(true);
            UIManager.setLookAndFeel(laf);
           
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                initLogin();
//                LaunchSplashScreen();
            }
        });
    }
}
