package com.core.desktop;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class MainClass {
    public static void main(String[] args) {
        setUpUI();
        EventQueue.invokeLater(MainFrame::new);
    }

    private static void setUpUI() {
        FlatLightLaf.install();
        UIManager.getLookAndFeelDefaults().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        UIManager.put( "Component.focusWidth", 0 );
        UIManager.put( "Component.innerFocusWidth", 0 );

        UIManager.put( "Button.focusedBackground", UIManager.getColor( "Button.background" ) );
        UIManager.put( "Button.default.focusedBackground", UIManager.getColor( "Button.default.background" ) );
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 18));
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, new FontUIResource(
                        "Segoe", Font.PLAIN, 18));
            }
        }
    }
}
