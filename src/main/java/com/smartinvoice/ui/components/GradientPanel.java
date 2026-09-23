package com.smartinvoice.ui.components;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    private final Color color1;
    private final Color color2;

    public GradientPanel(Color color1, Color color2) {

        this.color1 = color1;
        this.color2 = color2;

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        GradientPaint gp = new GradientPaint(
                0, 0, color1,
                0, getHeight(), color2);

        g2.setPaint(gp);

        g2.fillRect(0, 0, getWidth(), getHeight());

    }

}