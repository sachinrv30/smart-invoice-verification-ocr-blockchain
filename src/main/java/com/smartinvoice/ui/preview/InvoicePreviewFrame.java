package com.smartinvoice.ui.preview;

import com.smartinvoice.model.Invoice;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InvoicePreviewFrame extends JFrame {

    public InvoicePreviewFrame(Invoice invoice) {

        setTitle("Invoice Details");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("Invoice Number"));
        panel.add(new JLabel(invoice.getInvoiceNumber()));

        panel.add(new JLabel("Vendor"));
        panel.add(new JLabel(invoice.getVendorName()));

        panel.add(new JLabel("Date"));
        panel.add(new JLabel(invoice.getInvoiceDate()));

        panel.add(new JLabel("Amount"));
        panel.add(new JLabel("₹ " + invoice.getAmount()));

        panel.add(new JLabel("Status"));
        panel.add(new JLabel(invoice.getStatus()));

        panel.add(new JLabel("File"));
        panel.add(new JLabel(invoice.getFileName()));

        JButton openButton = new JButton("Open Invoice");

        openButton.addActionListener(e -> {

            try {

                Desktop.getDesktop().open(
                        new File(invoice.getFilePath())
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to open invoice file."
                );
            }

        });

        add(panel, BorderLayout.CENTER);
        add(openButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}