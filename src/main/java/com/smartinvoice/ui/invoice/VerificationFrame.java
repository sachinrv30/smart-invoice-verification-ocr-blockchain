package com.smartinvoice.ui.verification;

import com.smartinvoice.service.VerificationService;

import javax.swing.*;
import java.awt.*;

public class VerificationFrame extends JFrame {

    private JTextField txtInvoiceId;

    private final VerificationService service =
            new VerificationService();

    public VerificationFrame() {

        setTitle("Blockchain Verification");
        setSize(400,200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,2,10,10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel("Invoice ID"));

        txtInvoiceId = new JTextField();
        add(txtInvoiceId);

        JButton btnVerify = new JButton("Verify Invoice");

        add(new JLabel());
        add(btnVerify);

        btnVerify.addActionListener(e -> verify());

        setVisible(true);
    }

    private void verify() {

        try {

            int id = Integer.parseInt(txtInvoiceId.getText());

            boolean verified =
                    service.verifyInvoice(id);

            if (verified) {

                JOptionPane.showMessageDialog(
                        this,
                        "✅ Invoice Verified\nBlockchain Integrity Maintained."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Invoice Tampered or Blockchain Record Missing."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Invoice ID."
            );
        }
    }
}