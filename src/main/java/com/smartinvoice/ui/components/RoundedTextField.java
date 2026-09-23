package com.smartinvoice.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedTextField extends JTextField {

    public RoundedTextField(int columns) {

        super(columns);

        setOpaque(false);

        setBorder(new EmptyBorder(10,15,10,15));

        setFont(new Font("Segoe UI", Font.PLAIN,14));
    }

    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2=(Graphics2D)g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);

        g2.fillRoundRect(0,0,getWidth(),getHeight(),18,18);

        super.paintComponent(g);

        g2.dispose();

    }

}