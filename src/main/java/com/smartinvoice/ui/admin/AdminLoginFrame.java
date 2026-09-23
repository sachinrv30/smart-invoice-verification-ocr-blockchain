package com.smartinvoice.ui.admin;

import com.smartinvoice.service.AuthService;
import com.smartinvoice.ui.auth.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class AdminLoginFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;

    private JButton btnLogin;
    private JButton btnBack;

    private JCheckBox chkShowPassword;

    private final AuthService authService;

    private char defaultEcho;

    public AdminLoginFrame() {

        authService = new AuthService();

        setTitle("SmartLedger AI - Administrator Login");
        setSize(950,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel main = new JPanel(new GridLayout(1,2));

        // ==========================================================
        // LEFT PANEL
        // ==========================================================

        JPanel left = new JPanel();
        left.setBackground(new Color(28,28,28));
        left.setLayout(new BoxLayout(left,BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(70,45,70,45));

        JLabel title = new JLabel("SMARTLEDGER AI");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,32));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sub = new JLabel("Administrator Control Center");
        sub.setForeground(new Color(210,210,210));
        sub.setFont(new Font("Segoe UI",Font.PLAIN,18));
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea desc = new JTextArea(
                "\nSecure administration portal for\n" +
                        "system monitoring and management.\n\n" +

                        "Administrator Features\n\n" +

                        "✓ Manage Employees\n\n" +
                        "✓ Manage Invoices\n\n" +
                        "✓ AI Fraud Monitoring\n\n" +
                        "✓ Blockchain Audit\n\n" +
                        "✓ Analytics Dashboard\n\n" +
                        "✓ Reports & Logs"
        );

        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setForeground(Color.WHITE);
        desc.setFont(new Font("Segoe UI",Font.PLAIN,17));

        left.add(title);
        left.add(Box.createVerticalStrut(15));
        left.add(sub);
        left.add(Box.createVerticalStrut(30));
        left.add(desc);

        // ==========================================================
        // RIGHT PANEL
        // ==========================================================

        JPanel right = new JPanel(new GridBagLayout());
        right.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(8,15,8,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel lblLogin = new JLabel("Secure Administrator Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Segoe UI",Font.BOLD,30));

        gbc.gridy = 0;
        right.add(lblLogin,gbc);

        JLabel lblWelcome = new JLabel(
                "Authorized administrators only",
                SwingConstants.CENTER);

        lblWelcome.setForeground(Color.GRAY);
        lblWelcome.setFont(new Font("Segoe UI",Font.PLAIN,14));

        gbc.gridy++;
        right.add(lblWelcome,gbc);

        JLabel lblEmail = new JLabel("Administrator Email");

        gbc.gridy++;
        right.add(lblEmail,gbc);

        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(330,42));

        gbc.gridy++;
        right.add(txtEmail,gbc);

        JLabel lblPassword = new JLabel("Administrator Password");

        gbc.gridy++;
        right.add(lblPassword,gbc);

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(330,42));

        defaultEcho = txtPassword.getEchoChar();

        gbc.gridy++;
        right.add(txtPassword,gbc);

        chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setBackground(Color.WHITE);

        chkShowPassword.addActionListener(e -> {

            if(chkShowPassword.isSelected()){

                txtPassword.setEchoChar((char)0);

            }else{

                txtPassword.setEchoChar(defaultEcho);

            }

        });

        gbc.gridy++;
        right.add(chkShowPassword,gbc);

        btnLogin = new JButton("Administrator Login");
        btnLogin.setBackground(new Color(37,99,235));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI",Font.BOLD,16));
        btnLogin.setPreferredSize(new Dimension(330,45));

        gbc.gridy++;
        right.add(btnLogin,gbc);

        btnBack = new JButton("Back to Employee Login");

        gbc.gridy++;
        right.add(btnBack,gbc);

        JLabel security = new JLabel(
                "Only authorized administrators can access this portal.",
                SwingConstants.CENTER);

        security.setForeground(Color.GRAY);
        security.setFont(new Font("Segoe UI",Font.PLAIN,12));

        gbc.gridy++;
        right.add(security,gbc);

        JLabel footer = new JLabel(
                "Version 1.0 | © 2026 SmartLedger AI",
                SwingConstants.CENTER);

        footer.setForeground(Color.GRAY);

        gbc.gridy++;
        right.add(footer,gbc);

        main.add(left);
        main.add(right);

        add(main);

        // ==========================================================
        // EVENTS
        // ==========================================================

        btnLogin.addActionListener(e -> login());

        btnBack.addActionListener(e -> {

            dispose();
            new LoginFrame();

        });

        setVisible(true);
    }

    private void login() {

        String email = txtEmail.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());

        if(email.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Administrator email is required.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if(password.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Administrator password is required.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String result = authService.loginUser(email,password);

        if(!result.equals("Login Successful")){

            JOptionPane.showMessageDialog(
                    this,
                    result,
                    "Authentication Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if(!authService.isAdmin()){

            JOptionPane.showMessageDialog(
                    this,
                    "Access Denied!\n\nThis portal is reserved for administrators only.",
                    "Unauthorized",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Authentication Successful.\n\nWelcome Administrator!",
                "Login Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();

        new AdminDashboard();
    }
}