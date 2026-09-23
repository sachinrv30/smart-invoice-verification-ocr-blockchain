package com.smartinvoice.ui.auth;

import com.smartinvoice.service.AuthService;
import com.smartinvoice.ui.dashboard.DashboardFrame;

import com.smartinvoice.ui.admin.AdminLoginFrame;
import com.smartinvoice.ui.admin.AdminDashboard;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;

    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnForgotPassword;
    private JButton btnAdminLogin;
    private JCheckBox remember;
    private JCheckBox showPassword;

    private final AuthService authService;

    private char defaultEchoChar;

    public LoginFrame() {

        authService = new AuthService();

        setTitle("SmartLedger AI - Employee Login");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Uncomment if logo.png exists in src/main/resources/images
        /*
        ImageIcon icon = new ImageIcon(
                getClass().getResource("/images/logo.png"));
        setIconImage(icon.getImage());
        */

        JPanel main = new JPanel(new GridLayout(1, 2));

        // ==========================================================
        // LEFT PANEL
        // ==========================================================

        JPanel left = new JPanel();
        left.setBackground(new Color(37, 99, 235));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));

        JLabel logo = new JLabel("SMARTLEDGER AI");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sub = new JLabel("Secure Invoice Verification Platform");
        sub.setForeground(Color.WHITE);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea features = new JTextArea(
                "Platform Features\n\n" +
                        "✓ OCR Invoice Extraction\n\n" +
                        "✓ Blockchain Verification\n\n" +
                        "✓ AI Fraud Detection\n\n" +
                        "✓ Invoice Management\n\n" +
                        "✓ Analytics & Reports\n\n" +
                        "✓ Secure Employee Login"
        );

        features.setEditable(false);
        features.setOpaque(false);
        features.setForeground(Color.WHITE);
        features.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        left.add(logo);
        left.add(Box.createVerticalStrut(15));
        left.add(sub);
        left.add(Box.createVerticalStrut(40));
        left.add(features);

        left.add(Box.createVerticalGlue());



        // ==========================================================
        // RIGHT PANEL
        // ==========================================================

        JPanel right = new JPanel(new GridBagLayout());
        right.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        JLabel welcome = new JLabel("Employee Login");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 34));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        right.add(welcome, gbc);

        JLabel desc = new JLabel("Access your secure employee account");
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setForeground(Color.GRAY);
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        gbc.gridy++;
        right.add(desc, gbc);

        // Email

        JLabel emailLabel = new JLabel("📧 Email");

        gbc.gridy++;
        right.add(emailLabel, gbc);

        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(330, 42));

        gbc.gridy++;
        right.add(txtEmail, gbc);

        // Password

        JLabel passLabel = new JLabel("🔒 Password");

        gbc.gridy++;
        right.add(passLabel, gbc);

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(330, 42));

        defaultEchoChar = txtPassword.getEchoChar();

        gbc.gridy++;
        right.add(txtPassword, gbc);

        // Show Password

        showPassword = new JCheckBox("Show Password");
        showPassword.setBackground(Color.WHITE);

        showPassword.addActionListener(e -> {

            if (showPassword.isSelected()) {

                txtPassword.setEchoChar((char) 0);

            } else {

                txtPassword.setEchoChar(defaultEchoChar);
            }

        });

        gbc.gridy++;
        right.add(showPassword, gbc);

        // Forgot Password

        btnForgotPassword = new JButton("Forgot Password?");
        btnForgotPassword.setBorderPainted(false);
        btnForgotPassword.setContentAreaFilled(false);
        btnForgotPassword.setFocusPainted(false);
        btnForgotPassword.setForeground(new Color(37, 99, 235));
        btnForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridy++;
        right.add(btnForgotPassword, gbc);

        // Remember

        remember = new JCheckBox("Remember Me");
        remember.setBackground(Color.WHITE);

        gbc.gridy++;
        right.add(remember, gbc);

        // Login

        btnLogin = new JButton("Employee Login");
        btnLogin.setBackground(new Color(37, 99, 235));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setPreferredSize(new Dimension(330, 50));

        gbc.gridy++;
        right.add(btnLogin, gbc);

        // Register

        btnRegister = new JButton("Create Employee Account");

        gbc.gridy++;
        right.add(btnRegister, gbc);

        btnAdminLogin = new JButton("Administrator Login");
        btnAdminLogin.setBackground(new Color(55,55,55));
        btnAdminLogin.setForeground(Color.WHITE);
        btnAdminLogin.setFocusPainted(false);

        gbc.gridy++;
        right.add(btnAdminLogin, gbc);
        // Footer

        JLabel footer = new JLabel(
                "Version 1.0 | © 2026 SmartLedger AI",
                SwingConstants.CENTER);

        footer.setForeground(Color.GRAY);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        gbc.gridy++;
        right.add(footer, gbc);

        // ==========================================================

        main.add(left);
        main.add(right);

        add(main);

        // ==========================================================
        // EVENTS
        // ==========================================================

        btnLogin.addActionListener(e -> login());

        btnRegister.addActionListener(e -> {

            dispose();
            new RegisterFrame();

        });
        btnAdminLogin.addActionListener(e -> {

            dispose();
            new AdminLoginFrame();

        });

        btnForgotPassword.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Password Reset\n\n" +
                            "For security reasons, employee passwords\n" +
                            "can only be reset by the System Administrator.\n\n" +
                            "Contact:\nadmin@smartledger.ai",
                    "Forgot Password",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });

        setVisible(true);
    }

    // ==========================================================
    // LOGIN
    // ==========================================================

    private void login() {

        String email = txtEmail.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());

        // Validation
        if (email.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Email address is required.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password is required.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Authenticate user
        String result = authService.loginUser(email, password);

        if (!result.equals("Login Successful")) {

            JOptionPane.showMessageDialog(
                    this,
                    result,
                    "Authentication Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Allow only EMPLOYEE accounts here
        if (!authService.isEmployee()) {

            JOptionPane.showMessageDialog(
                    this,
                    "This portal is for Employees only.\n\nPlease use the Administrator Login.",
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Welcome " + authService.getLoggedInUser().getFullName() + "!",
                "Login Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();

        new DashboardFrame();
    }}