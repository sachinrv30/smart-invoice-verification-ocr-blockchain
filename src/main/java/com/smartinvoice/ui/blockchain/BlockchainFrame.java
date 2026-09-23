package com.smartinvoice.ui.blockchain;

import com.smartinvoice.model.Block;
import com.smartinvoice.service.BlockchainService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BlockchainFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JLabel totalBlocksLabel;

    private final BlockchainService service = new BlockchainService();

    public BlockchainFrame() {

        setTitle("SmartLedger AI - Blockchain Explorer");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        main.setBackground(new Color(245, 247, 250));

        // ================= HEADER =================

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel title = new JLabel("SMARTLEDGER AI - Blockchain Explorer");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        totalBlocksLabel = new JLabel("Total Blocks : 0");
        totalBlocksLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(totalBlocksLabel, BorderLayout.EAST);

        main.add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {
                "Block ID",
                "Invoice ID",
                "Previous Hash",
                "Current Hash",
                "Timestamp"
        };

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(37, 99, 235));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);

        main.add(scrollPane, BorderLayout.CENTER);

        // ================= BUTTONS =================

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton refreshBtn = new JButton("🔄 Refresh");
        JButton verifyBtn = new JButton("✔ Verify Chain");

        bottom.add(refreshBtn);
        bottom.add(verifyBtn);

        main.add(bottom, BorderLayout.SOUTH);

        // ================= EVENTS =================

        refreshBtn.addActionListener(e -> loadBlocks());

        verifyBtn.addActionListener(e -> {

            boolean valid = service.verifyBlockchain();

            if (valid) {

                JOptionPane.showMessageDialog(
                        this,
                        "✅ Blockchain Verified Successfully!\n\nNo tampering detected.",
                        "Blockchain Verification",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Blockchain Integrity Failed!\n\nBlockchain data has been modified.",
                        "Blockchain Verification",
                        JOptionPane.ERROR_MESSAGE);
            }

        });

        add(main);

        loadBlocks();

        setVisible(true);
    }

    // ================= LOAD BLOCKS =================

    private void loadBlocks() {

        model.setRowCount(0);

        List<Block> blocks = service.getAllBlocks();
        System.out.println("Blocks received by UI = " + blocks.size());
        for (Block block : blocks) {

            model.addRow(new Object[]{
                    block.getBlockId(),
                    block.getInvoiceId(),
                    shortHash(block.getPreviousHash()),
                    shortHash(block.getCurrentHash()),
                    block.getTimeStamp()
            });

        }

        totalBlocksLabel.setText("Total Blocks : " + blocks.size());

    }

    // ================= SHORT HASH =================

    private String shortHash(String hash) {

        if (hash == null)
            return "";

        if (hash.length() <= 20)
            return hash;

        return hash.substring(0, 20) + "...";
    }

}