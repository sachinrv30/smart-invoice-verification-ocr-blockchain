package com.smartinvoice.ui.verification;

import com.smartinvoice.model.Invoice;
import com.smartinvoice.service.InvoiceService;
import com.smartinvoice.service.VerificationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InvoiceVerificationFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private final InvoiceService invoiceService = new InvoiceService();
    private final VerificationService verificationService = new VerificationService();

    public InvoiceVerificationFrame() {

        setTitle("Blockchain Invoice Verification");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Blockchain Invoice Verification", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{
                "ID",
                "Invoice Number",
                "Vendor",
                "Date",
                "Amount",
                "Status"
        });

        table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton verifyBtn = new JButton("Verify Selected Invoice");
        JButton refreshBtn = new JButton("Refresh");

        bottom.add(verifyBtn);
        bottom.add(refreshBtn);

        add(bottom, BorderLayout.SOUTH);

        verifyBtn.addActionListener(e -> verifyInvoice());

        refreshBtn.addActionListener(e -> loadInvoices());

        loadInvoices();

        setVisible(true);
    }

    private void loadInvoices() {

        model.setRowCount(0);

        List<Invoice> invoices = invoiceService.getAllInvoices();

        for (Invoice invoice : invoices) {

            model.addRow(new Object[]{
                    invoice.getInvoiceId(),
                    invoice.getInvoiceNumber(),
                    invoice.getVendorName(),
                    invoice.getInvoiceDate(),
                    invoice.getAmount(),
                    invoice.getStatus()
            });

        }
    }
    private void verifyInvoice() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an invoice.");
            return;
        }

        int invoiceId = Integer.parseInt(model.getValueAt(row, 0).toString());

        boolean verified = verificationService.verifyInvoice(invoiceId);

        if (verified) {

            JOptionPane.showMessageDialog(
                    this,
                    "✅ Invoice Verified Successfully."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "❌ Invoice Tampered."
            );
        }

        // Refresh JTable
        loadInvoices();
    }}