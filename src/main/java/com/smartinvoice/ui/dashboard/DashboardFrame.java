package com.smartinvoice.ui.dashboard;

import com.smartinvoice.service.DashboardService;
import com.smartinvoice.ui.invoice.UploadInvoiceFrame;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import com.smartinvoice.ui.verification.InvoiceVerificationFrame;
import com.smartinvoice.ui.invoice.InvoiceManagementFrame;

import com.smartinvoice.service.InvoiceService;
import com.smartinvoice.model.Invoice;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import com.smartinvoice.ui.analytics.AnalyticsFrame;
import javax.swing.table.DefaultTableCellRenderer;
import com.smartinvoice.ui.reports.ReportFrame;
import com.smartinvoice.ui.blockchain.BlockchainFrame;

import com.smartinvoice.ui.ocr.OCRResultFrame;
import com.smartinvoice.ui.fraud.FraudDetectionFrame;
import com.smartinvoice.ui.profile.ProfileFrame;
import com.smartinvoice.ui.preview.InvoicePreviewFrame;
import com.smartinvoice.ui.about.AboutFrame;
public class DashboardFrame extends JFrame {

    private final DashboardService dashboardService = new DashboardService();
    private final InvoiceService invoiceService = new InvoiceService();

    private JTable recentTable;
    private DefaultTableModel recentTableModel;

    private JLabel lblTotalInvoices;
    private JLabel lblVerified;
    private JLabel lblFraud;
    private JLabel lblBlockchain;

    public DashboardFrame() {

        setTitle("SmartLedger AI Dashboard");
        setSize(1450,850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245,247,250));
        // ================= Sidebar =================

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(300,850));
        sidebar.setBackground(new Color(15,23,42));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20,20,20,20));
        sidebar.add(Box.createVerticalStrut(10));
        JButton dashboardBtn = createMenuButton("🏠 Dashboard");
        JButton uploadBtn = createMenuButton("📤 Upload Invoice");
        JButton ocrBtn = createMenuButton("📄 OCR Extraction");
        JButton validationBtn = createMenuButton("✔ Validation");
        JButton blockchainBtn = createMenuButton("⛓ Blockchain");
        JButton fraudBtn = createMenuButton("⚠ Fraud Detection");
        JButton reportsBtn = createMenuButton("📊 Reports");
        JButton analyticsBtn = createMenuButton("📈 Analytics");
        JButton profileBtn = createMenuButton("👤 Profile");
        JButton logoutBtn = createMenuButton("🚪 Logout");
        logoutBtn.setBackground(new Color(220,53,69));
        JButton invoiceBtn = createMenuButton("📄 Manage Invoices");
        JButton btnPreview = new JButton("👁 Preview Invoice");
        JButton btnAbout = createMenuButton("ℹ About System");
        sidebar.add(dashboardBtn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(profileBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(btnAbout);

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(invoiceBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(ocrBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(validationBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(blockchainBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(fraudBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(analyticsBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(reportsBtn);
        sidebar.add(Box.createVerticalStrut(10));


        sidebar.add(Box.createVerticalGlue());

        sidebar.add(logoutBtn);

        uploadBtn.addActionListener(e -> new UploadInvoiceFrame());

        dashboardBtn.addActionListener(e -> {
            loadDashboardData();
            loadRecentInvoices();
        });
        blockchainBtn.addActionListener(e -> new BlockchainFrame());
        invoiceBtn.addActionListener(e -> new InvoiceManagementFrame());
        analyticsBtn.addActionListener(e -> new AnalyticsFrame());
        reportsBtn.addActionListener(e -> new ReportFrame());
        logoutBtn.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if(option == JOptionPane.YES_OPTION){

                dispose();

                new com.smartinvoice.ui.auth.LoginFrame();
            }

        });

        // ================= Header =================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(37,99,235));
        header.setPreferredSize(new Dimension(1450,90));

        JPanel titlePanel = new JPanel(new GridLayout(3,1));
        titlePanel.setOpaque(false);

        JLabel title = new JLabel("SMARTLEDGER AI");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));

        JLabel subTitle1 = new JLabel("Secure Invoice Verification Platform");
        subTitle1.setForeground(Color.WHITE);
        subTitle1.setFont(new Font("Segoe UI", Font.BOLD, 15));

        JLabel subTitle2 = new JLabel("OCR • Blockchain • Fraud Detection");
        subTitle2.setForeground(new Color(230,230,230));
        subTitle2.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        titlePanel.add(title);
        titlePanel.add(subTitle1);
        titlePanel.add(subTitle2);

        header.add(titlePanel, BorderLayout.WEST);
        JLabel admin = new JLabel("👤 Welcome, Employee");

        admin.setForeground(Color.WHITE);

        admin.setFont(new Font("Segoe UI", Font.BOLD,18));

        header.add(admin, BorderLayout.EAST);
        // ================= Center =================

        JPanel center = new JPanel(new BorderLayout(20,20));
        JPanel topPanel = new JPanel(new BorderLayout(20,20));

        JPanel cardsPanel = new JPanel(new GridLayout(2,2,30,30));

        cardsPanel.setOpaque(false);
        cardsPanel.setOpaque(false);
        center.setBackground(new Color(245,247,250));
        center.setBorder(new EmptyBorder(30, 30, 30, 30));
// ================= Dashboard Cards =================

        cardsPanel.add(createCard("Total Invoices", 1));
        cardsPanel.add(createCard("Verified", 2));
        cardsPanel.add(createCard("Fraud Detected", 3));
        cardsPanel.add(createCard("Blockchain Blocks", 4));

// ================= Add Panels =================
        JPanel quickPanel = new JPanel();

        quickPanel.setLayout(new BoxLayout(quickPanel, BoxLayout.Y_AXIS));

        quickPanel.setPreferredSize(new Dimension(280, 450));

        quickPanel.setBackground(Color.WHITE);

        quickPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        JLabel quickTitle = new JLabel("⚡ Quick Actions");

        quickTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));

        quickTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        quickPanel.add(quickTitle);

        quickPanel.add(Box.createVerticalStrut(20));

        JButton btnUpload = new JButton("📤 Upload Invoice");
        JButton btnVerify = new JButton("✔ Verify Invoice");
        JButton btnManage = new JButton("📄 Manage Invoices");
        JButton btnReport = new JButton("📊 Reports");

        Dimension size = new Dimension(210,45);

        btnUpload.setMaximumSize(size);
        btnVerify.setMaximumSize(size);
        btnManage.setMaximumSize(size);
        btnReport.setMaximumSize(size);

        quickPanel.add(btnUpload);
        quickPanel.add(Box.createVerticalStrut(10));

        quickPanel.add(btnVerify);
        quickPanel.add(Box.createVerticalStrut(10));

        quickPanel.add(btnManage);
        quickPanel.add(Box.createVerticalStrut(10));

        quickPanel.add(btnReport);
        ocrBtn.addActionListener(e -> new OCRResultFrame());

        validationBtn.addActionListener(e -> new InvoiceVerificationFrame());

        fraudBtn.addActionListener(e -> new FraudDetectionFrame());

        btnUpload.addActionListener(e -> new UploadInvoiceFrame());

        btnVerify.addActionListener(e -> new InvoiceVerificationFrame());

        btnManage.addActionListener(e -> new InvoiceManagementFrame());

        btnReport.addActionListener(e -> new ReportFrame());

        profileBtn.addActionListener(e -> new ProfileFrame());
        btnAbout.addActionListener(e -> new AboutFrame());
        //btnPreview.addActionListener(e -> new InvoicePreviewFrame());
        // ================= Recent Invoices Panel =================

        JPanel recentPanel = new JPanel(new BorderLayout());
        recentPanel.setBackground(Color.WHITE);

        recentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));

        JLabel recentTitle = new JLabel("\"\uD83D\uDCCB Recent Invoice Activity\"");
        recentTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        recentTitle.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));

        String[] columns = {
                "Invoice No",
                "Vendor",
                "Amount",
                "Status",
                "Date"
        };

        recentTableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        recentTable = new JTable(recentTableModel);

        recentTable.setRowHeight(35);
        recentTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        recentTable.setSelectionBackground(new Color(37, 99, 235));
        recentTable.setSelectionForeground(Color.WHITE);
        recentTable.setGridColor(new Color(230,230,230));

        recentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        recentTable.getTableHeader().setBackground(new Color(37,99,235));
        recentTable.getTableHeader().setForeground(Color.WHITE);
        recentTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(recentTable);
        scrollPane.setBorder(null);

        recentPanel.add(recentTitle, BorderLayout.NORTH);
        recentPanel.add(scrollPane, BorderLayout.CENTER);
        // ================= Add Everything =================

        topPanel.add(cardsPanel, BorderLayout.CENTER);
        topPanel.add(quickPanel, BorderLayout.EAST);

        center.add(topPanel, BorderLayout.NORTH);
        center.add(recentPanel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);

        loadDashboardData();
        loadRecentInvoices();
        styleStatusColumn();
        setVisible(true);
    }

    private JButton createMenuButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        button.setPreferredSize(new Dimension(260, 50));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30,41,59));

        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12,18,12,18));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(37,99,235));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(30,41,59));
            }
        });

        return button;
    }

    private JPanel createCard(String title, int type) {

        JPanel card = new JPanel(new BorderLayout(10,10));

        card.setBackground(Color.WHITE);

        JLabel icon = new JLabel();

        Color color = new Color(37,99,235);

        switch(type){

            case 1:
                icon.setText("📄");
                color = new Color(37,99,235);
                break;

            case 2:
                icon.setText("✅");
                color = new Color(34,197,94);
                break;

            case 3:
                icon.setText("⚠");
                color = new Color(239,68,68);
                break;

            case 4:
                icon.setText("⛓");
                color = new Color(245,158,11);
                break;
        }
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 0, 0, 0, color),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220,220,220)),
                        BorderFactory.createEmptyBorder(20,20,20,20)
                )
        ));

        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 34));
        icon.setForeground(color);

        JLabel lblTitle = new JLabel(title);

        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,20));

        JPanel top = new JPanel(new BorderLayout());

        top.setOpaque(false);

        top.add(icon,BorderLayout.WEST);

        top.add(lblTitle,BorderLayout.CENTER);
        top.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));

        JLabel lblValue = new JLabel("0");

        lblValue.setFont(new Font("Segoe UI",Font.BOLD,60));

        lblValue.setForeground(color);

        JLabel desc = new JLabel("Last Updated: Just Now");

        desc.setForeground(Color.GRAY);

        desc.setFont(new Font("Segoe UI",Font.PLAIN,14));

        switch(type){

            case 1 -> lblTotalInvoices = lblValue;
            case 2 -> lblVerified = lblValue;
            case 3 -> lblFraud = lblValue;
            case 4 -> lblBlockchain = lblValue;

        }

        card.add(top,BorderLayout.NORTH);

        card.add(lblValue,BorderLayout.CENTER);

        card.add(desc,BorderLayout.SOUTH);

        return card;
    }

    private void loadDashboardData() {

        lblTotalInvoices.setText(
                String.valueOf(dashboardService.getTotalInvoices()));

        lblVerified.setText(
                String.valueOf(dashboardService.getVerifiedInvoices()));

        lblFraud.setText(
                String.valueOf(dashboardService.getFraudInvoices()));

        lblBlockchain.setText(
                String.valueOf(dashboardService.getBlockchainBlocks()));
    }
    private void loadRecentInvoices() {

        recentTableModel.setRowCount(0);

        List<Invoice> invoices = invoiceService.getAllInvoices();

        for (Invoice invoice : invoices) {

            recentTableModel.addRow(new Object[]{

                    invoice.getInvoiceNumber(),
                    invoice.getVendorName(),
                    invoice.getAmount(),
                    invoice.getStatus(),
                    invoice.getInvoiceDate()

            });
        }
    }
    private void styleStatusColumn() {

        recentTable.getColumnModel().getColumn(3)
                .setCellRenderer(new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        JLabel label = (JLabel) super.getTableCellRendererComponent(
                                table, value, isSelected, hasFocus, row, column);

                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

                        if (value == null)
                            return label;

                        String status = value.toString().trim().toLowerCase();

                        switch (status) {

                            case "verified":
                                label.setForeground(new Color(34,197,94));
                                break;

                            case "uploaded":
                                label.setForeground(new Color(245,158,11));
                                break;

                            case "fraud":
                                label.setForeground(new Color(239,68,68));
                                break;

                            default:
                                label.setForeground(Color.BLACK);
                        }

                        return label;
                    }
                });
    }
    public void refreshDashboard() {
        loadDashboardData();
        loadRecentInvoices();
        styleStatusColumn();

        new javax.swing.Timer(3000, e -> refreshDashboard()).start();

        setVisible(true);
    }


}