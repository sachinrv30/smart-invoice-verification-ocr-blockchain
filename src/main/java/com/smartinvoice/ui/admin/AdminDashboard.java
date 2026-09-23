package com.smartinvoice.ui.admin;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("SmartLedger AI - Administrator Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ================= HEADER =================

        JLabel title = new JLabel(
                "SMARTLEDGER AI - ADMINISTRATOR DASHBOARD",
                SwingConstants.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(title, BorderLayout.NORTH);

        // ================= CENTER =================

        JPanel center = new JPanel(new GridLayout(2,3,20,20));
        center.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JButton btnUsers = new JButton("Manage Users");
        JButton btnInvoices = new JButton("Manage Invoices");
        JButton btnReports = new JButton("Reports");
        JButton btnAnalytics = new JButton("Analytics");
        JButton btnFraud = new JButton("Fraud Detection");
        JButton btnLogout = new JButton("Logout");

        Font font = new Font("Segoe UI", Font.BOLD, 18);

        btnUsers.setFont(font);
        btnInvoices.setFont(font);
        btnReports.setFont(font);
        btnAnalytics.setFont(font);
        btnFraud.setFont(font);
        btnLogout.setFont(font);

        center.add(btnUsers);
        center.add(btnInvoices);
        center.add(btnReports);
        center.add(btnAnalytics);
        center.add(btnFraud);
        center.add(btnLogout);

        add(center, BorderLayout.CENTER);

        // ================= EVENTS =================

        btnUsers.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Manage Users Module"));

        btnInvoices.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Manage Invoices Module"));

        btnReports.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Reports Module"));

        btnAnalytics.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Analytics Module"));

        btnFraud.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Fraud Detection Module"));

        btnLogout.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if(option == JOptionPane.YES_OPTION){

                dispose();

                new com.smartinvoice.ui.auth.LoginFrame();

            }

        });

        setVisible(true);
    }

}