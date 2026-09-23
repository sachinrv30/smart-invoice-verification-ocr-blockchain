package com.smartinvoice.ui.invoice;

import com.smartinvoice.model.Invoice;
import com.smartinvoice.service.InvoiceService;
import com.smartinvoice.ui.preview.InvoiceDetailsFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InvoiceManagementFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtSearch;

    private JRadioButton rbInvoice;
    private JRadioButton rbVendor;

    private final InvoiceService service = new InvoiceService();

    public InvoiceManagementFrame() {

        setTitle("Invoice Management");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Invoice Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel();

        txtSearch = new JTextField(20);

        rbInvoice = new JRadioButton("Invoice No", true);
        rbVendor = new JRadioButton("Vendor");

        ButtonGroup group = new ButtonGroup();
        group.add(rbInvoice);
        group.add(rbVendor);

        JButton btnSearch = new JButton("Search");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnView = new JButton("View Invoice");

        searchPanel.add(new JLabel("Search"));
        searchPanel.add(txtSearch);
        searchPanel.add(rbInvoice);
        searchPanel.add(rbVendor);
        searchPanel.add(btnSearch);
        searchPanel.add(btnRefresh);
        searchPanel.add(btnView);


        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
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

        btnSearch.addActionListener(e -> searchInvoices());

        btnRefresh.addActionListener(e -> loadInvoices());
        btnView.addActionListener(e -> {

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

            Invoice invoice = service.getInvoiceById(invoiceId);

            if (invoice == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invoice not found."
                );
                return;
            }

            new InvoiceDetailsFrame(invoice);

        });
        loadInvoices();

        setVisible(true);
    }

    private void loadInvoices() {

        model.setRowCount(0);

        List<Invoice> invoices = service.getAllInvoices();

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

    private void searchInvoices() {

        model.setRowCount(0);

        String keyword = txtSearch.getText().trim();

        List<Invoice> invoices;

        if (rbInvoice.isSelected()) {
            invoices = service.searchByInvoiceNumber(keyword);
        } else {
            invoices = service.searchByVendor(keyword);
        }

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
}