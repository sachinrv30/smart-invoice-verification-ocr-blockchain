package com.smartinvoice.ui.about;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutFrame extends JFrame {

    public AboutFrame() {

        setTitle("About SmartLedger AI");
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(25,25,25,25));

        JLabel title = new JLabel("SMARTLEDGER AI");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel version = new JLabel("Version 1.0");
        version.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        version.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea info = new JTextArea();

        info.setEditable(false);

        info.setFont(new Font("Monospaced", Font.PLAIN, 15));

        info.setText(
                "Technology Used\n\n" +

                        "✓ Java Swing\n" +
                        "✓ Java 17\n" +
                        "✓ MySQL Database\n" +
                        "✓ Tess4J OCR Engine\n" +
                        "✓ SHA-256 Blockchain\n" +
                        "✓ AI Similarity Detection\n" +
                        "✓ OpenPDF\n\n" +

                        "Modules\n\n" +

                        "• Login\n" +
                        "• Dashboard\n" +
                        "• OCR Extraction\n" +
                        "• Upload Invoice\n" +
                        "• Blockchain\n" +
                        "• Fraud Detection\n" +
                        "• AI Similarity\n" +
                        "• Invoice Management\n" +
                        "• Reports\n" +
                        "• Analytics\n\n" +

                        "Developer\n\n" +

                        "Sachin RV\n\n" +

                        "Smart Invoice Verification\nusing OCR and Blockchain"
        );

        JButton close = new JButton("Close");

        close.setAlignmentX(Component.CENTER_ALIGNMENT);

        close.addActionListener(e -> dispose());

        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(version);
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JScrollPane(info));
        panel.add(Box.createVerticalStrut(20));
        panel.add(close);

        add(panel);

        setVisible(true);
    }
}