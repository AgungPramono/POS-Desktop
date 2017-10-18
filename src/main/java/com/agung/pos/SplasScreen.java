/*
 *  Copyright (c) 2016 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  Windows.java
 * 
 *  Created on Feb 28, 2016, 8:56:10 AM
 */
package com.agung.pos;

import com.agung.pos.ui.MainMenu;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.WindowConstants;

/**
 *
 * @author agung
 */
public class SplasScreen extends JWindow {
    public static void getSplashScreen() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    final SplasScreen sp = new SplasScreen();
                    sp.setAlwaysOnTop(true);
                    sp.setImage(new ImageIcon(getClass().getResource("/asset/splashScreen-pos.png")).getImage());
                    sp.setLocationRelativeTo(null);
                    sp.setVisible(true);
                    Thread.sleep(7000);
                    
                    final JFrame frame = new JFrame();
                    frame.setSize(400,400);
                    
                    //frame.setLocationRelativeTo(null);
                    //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    sp.setVisible(false);
                    
                    //frame.setVisible(true);
                    //Main.initLogin();
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        }).start();
    }
    
    private Image image;
    private final JLabel label ;
    
    
    public SplasScreen(){
        super();
        this.label = new JLabel();
        setLayout(new BorderLayout());
        add(this.label);
    }
    
    public Image getImage(){
        return this.image;
    }
    
    public void setImage(final Image image){
        this.image = image;
        this.label.setIcon(new ImageIcon(image));
        pack();
    }
}
