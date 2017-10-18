/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agung.pos.ui.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import java.awt.Rectangle;
import javax.swing.JTable;
//import org.apache.log4j.Logger;

/**
 *
 * @author ifnu
 */
public class ComponentUtils {
    private static final Logger LOGGER = new LoggerContext().getLogger(ComponentUtils.class);

    public static void scrollToRect(JTable table,int nextSelectedRow){
        LOGGER.info("scroll jtable ");
        Rectangle currentVisible = table.getVisibleRect();
        Rectangle scrollToRect = table.getCellRect(nextSelectedRow, 0, true);
        if(scrollToRect.getY() > currentVisible.getY() + currentVisible.getHeight()){
            scrollToRect.setLocation(0,
                    (int)(scrollToRect.getY() + currentVisible.getHeight() - scrollToRect.getHeight()));
            
        }
        table.scrollRectToVisible(scrollToRect);
    }

}
