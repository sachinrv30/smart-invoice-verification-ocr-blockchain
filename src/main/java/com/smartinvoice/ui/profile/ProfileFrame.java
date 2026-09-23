package com.smartinvoice.ui.profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileFrame extends JFrame {

    public ProfileFrame() {

        setTitle("SmartLedger AI - Profile");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBorder(new EmptyBorder(20,20,20,20));
        main.setBackground(new Color(245,247,250));

        JLabel title = new JLabel("ADMIN PROFILE", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        main.add(title, BorderLayout.NORTH);

        JPanel details = new JPanel(new GridLayout(6,2,15,15));
        details.setBackground(Color.WHITE);
        details.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        details.add(new JLabel("Name"));
        details.add(new JLabel("Sachin R V"));

        details.add(new JLabel("Email"));
        details.add(new JLabel("sachinrv@gmail.com"));

        details.add(new JLabel("Mobile"));
        details.add(new JLabel("7483781762"));

        details.add(new JLabel("Role"));
        details.add(new JLabel("System Administrator"));

        details.add(new JLabel("Project"));
        details.add(new JLabel("SmartLedger AI"));

        details.add(new JLabel("Version"));
        details.add(new JLabel("Version 1.0"));

        main.add(details, BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        JButton editBtn = new JButton("Edit Profile");
        JButton passwordBtn = new JButton("Change Password");
        JButton closeBtn = new JButton("Close");

        buttons.add(editBtn);
        buttons.add(passwordBtn);
        buttons.add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        editBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Edit Profile feature will be added soon.")
        );

        passwordBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Change Password feature will be added soon.")
        );

        main.add(buttons, BorderLayout.SOUTH);

        add(main);

        setVisible(true);
    }
}