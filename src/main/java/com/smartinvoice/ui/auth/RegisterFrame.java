package com.smartinvoice.ui.auth;

import com.smartinvoice.model.User;
import com.smartinvoice.service.AuthService;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;

    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    private JButton btnRegister;
    private JButton btnClear;
    private JButton btnBack;

    private JCheckBox chkShowPassword;

    private final AuthService authService;

    private char defaultEcho;
    private char defaultConfirmEcho;

    public RegisterFrame() {

        authService = new AuthService();

        setTitle("SmartLedger AI - Employee Registration");
        setSize(550, 560);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("EMPLOYEE REGISTRATION");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(95,20,350,35);

        JLabel lblName = new JLabel("Full Name");
        lblName.setBounds(50,90,150,25);

        txtName = new JTextField();
        txtName.setBounds(200,90,280,35);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50,140,150,25);

        txtEmail = new JTextField();
        txtEmail.setBounds(200,140,280,35);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(50,190,150,25);

        txtPhone = new JTextField();
        txtPhone.setBounds(200,190,280,35);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50,240,150,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200,240,280,35);

        defaultEcho = txtPassword.getEchoChar();

        JLabel lblConfirm = new JLabel("Confirm Password");
        lblConfirm.setBounds(50,290,150,25);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(200,290,280,35);

        defaultConfirmEcho = txtConfirmPassword.getEchoChar();

        chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setBackground(Color.WHITE);
        chkShowPassword.setBounds(200,335,160,25);

        chkShowPassword.addActionListener(e -> {

            if(chkShowPassword.isSelected()){

                txtPassword.setEchoChar((char)0);
                txtConfirmPassword.setEchoChar((char)0);

            }else{

                txtPassword.setEchoChar(defaultEcho);
                txtConfirmPassword.setEchoChar(defaultConfirmEcho);

            }

        });

        btnRegister = new JButton("Register Employee");
        btnRegister.setBounds(40,390,150,45);
        btnRegister.setBackground(new Color(37,99,235));
        btnRegister.setForeground(Color.WHITE);

        btnClear = new JButton("Clear");
        btnClear.setBounds(205,390,120,45);

        btnBack = new JButton("Back to Login");
        btnBack.setBounds(340,390,150,45);

        JLabel footer = new JLabel(
                "Version 1.0 | © 2026 SmartLedger AI",
                SwingConstants.CENTER
        );

        footer.setForeground(Color.GRAY);
        footer.setBounds(70,470,400,25);

        panel.add(title);

        panel.add(lblName);
        panel.add(txtName);

        panel.add(lblEmail);
        panel.add(txtEmail);

        panel.add(lblPhone);
        panel.add(txtPhone);

        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(lblConfirm);
        panel.add(txtConfirmPassword);

        panel.add(chkShowPassword);

        panel.add(btnRegister);
        panel.add(btnClear);
        panel.add(btnBack);

        panel.add(footer);

        add(panel);

        btnRegister.addActionListener(e -> registerUser());

        btnClear.addActionListener(e -> clearFields());

        btnBack.addActionListener(e -> {

            dispose();
            new LoginFrame();

        });

    }

    private void registerUser() {

        if(txtName.getText().trim().isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Full Name is required."
            );
            return;

        }

        if(txtEmail.getText().trim().isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Email is required."
            );
            return;

        }

        if(txtPhone.getText().trim().isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Phone Number is required."
            );
            return;

        }

        User user = new User();

        user.setFullName(txtName.getText().trim());
        user.setEmail(txtEmail.getText().trim());
        user.setPhone(txtPhone.getText().trim());
        user.setRole("EMPLOYEE");
        user.setPassword(String.valueOf(txtPassword.getPassword()));

        String confirmPassword =
                String.valueOf(txtConfirmPassword.getPassword());

        String message =
                authService.registerUser(user, confirmPassword);

        JOptionPane.showMessageDialog(
                this,
                message
        );

        if(message.equals("Registration Successful.")){

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee account created successfully.\nPlease login."
            );

            dispose();

            new LoginFrame();

        }

    }

    private void clearFields(){

        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");

    }

}