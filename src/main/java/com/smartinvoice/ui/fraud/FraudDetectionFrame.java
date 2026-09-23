package com.smartinvoice.ui.fraud;

import com.smartinvoice.model.Invoice;
import com.smartinvoice.service.FraudService;
import com.smartinvoice.service.InvoiceService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.smartinvoice.report.PDFReportService;

public class FraudDetectionFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextArea txtResult;

    private final InvoiceService invoiceService = new InvoiceService();
    private final FraudService fraudService = new FraudService();
    private final PDFReportService pdfService = new PDFReportService();
    public FraudDetectionFrame() {

        setTitle("SmartLedger AI - Fraud Detection");
        setSize(1100,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15,15));
        main.setBorder(new EmptyBorder(20,20,20,20));

        JLabel title = new JLabel("SMARTLEDGER AI - FRAUD DETECTION");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        main.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Invoice",
                        "Vendor",
                        "Amount",
                        "Status"
                },0){

            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane tableScroll = new JScrollPane(table);

        txtResult = new JTextArea();
        txtResult.setEditable(false);
        txtResult.setFont(new Font("Monospaced", Font.PLAIN, 15));

        JScrollPane resultScroll = new JScrollPane(txtResult);
        resultScroll.setBorder(
                BorderFactory.createTitledBorder("Verification Result")
        );

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                tableScroll,
                resultScroll
        );

        splitPane.setDividerLocation(350);

        main.add(splitPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton btnRefresh = new JButton("Refresh");
        JButton btnVerify = new JButton("Verify Selected");
        JButton btnPDF = new JButton("Export PDF");

        bottom.add(btnRefresh);
        bottom.add(btnVerify);
        bottom.add(btnPDF);

        main.add(bottom, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> loadInvoices());
        btnVerify.addActionListener(e -> verifyInvoice());
        btnPDF.addActionListener(e -> exportPDF());
        add(main);

        loadInvoices();

        setVisible(true);
    }
    private void exportPDF() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an invoice."
            );
            return;
        }

        int invoiceId = Integer.parseInt(
                model.getValueAt(row, 0).toString()
        );

        Invoice invoice = invoiceService.getInvoiceById(invoiceId);

        String report = fraudService.verifyInvoice(invoiceId);

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new java.io.File(
                        "Invoice_Report_" +
                                invoice.getInvoiceNumber() +
                                ".pdf"
                )
        );

        int option = chooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {

            pdfService.generateReport(
                    invoice,
                    report,
                    chooser.getSelectedFile().getAbsolutePath()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "✅ PDF Report Generated Successfully!"
            );
        }
    }

    private void loadInvoices() {

        model.setRowCount(0);

        List<Invoice> invoices = invoiceService.getAllInvoices();

        for (Invoice invoice : invoices) {

            model.addRow(new Object[]{
                    invoice.getInvoiceId(),
                    invoice.getInvoiceNumber(),
                    invoice.getVendorName(),
                    invoice.getAmount(),
                    invoice.getStatus()
            });
        }
    }

    private void verifyInvoice() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an invoice."
            );
            return;
        }

        int invoiceId = Integer.parseInt(
                model.getValueAt(row,0).toString()
        );

        String report = fraudService.verifyInvoice(invoiceId);

        Invoice invoice = invoiceService.getInvoiceById(invoiceId);

        if(invoice != null){

            report += "\n\n";
            report += fraudService.analyzeSimilarity(invoice);
        }

        txtResult.setText(report);

        loadInvoices();
    }
}