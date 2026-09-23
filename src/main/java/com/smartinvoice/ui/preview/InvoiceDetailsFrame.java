package com.smartinvoice.ui.preview;

import com.smartinvoice.model.Block;
import com.smartinvoice.model.Invoice;
import com.smartinvoice.service.BlockchainService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.File;

public class InvoiceDetailsFrame extends JFrame {

    private final Invoice invoice;
    private final BlockchainService blockchainService = new BlockchainService();

    public InvoiceDetailsFrame(Invoice invoice) {

        this.invoice = invoice;

        setTitle("SmartLedger AI - Invoice Details");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();

        setVisible(true);
    }

    private void initUI() {

        JPanel main = new JPanel(new BorderLayout(15,15));
        main.setBorder(new EmptyBorder(20,20,20,20));

        JLabel title = new JLabel("SMARTLEDGER AI - INVOICE DETAILS");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

        main.add(title, BorderLayout.NORTH);
        JPanel infoPanel = new JPanel(new GridLayout(6,2,10,10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Invoice Information"));

        infoPanel.add(new JLabel("Invoice ID"));
        infoPanel.add(new JLabel(String.valueOf(invoice.getInvoiceId())));

        infoPanel.add(new JLabel("Invoice Number"));
        infoPanel.add(new JLabel(invoice.getInvoiceNumber()));

        infoPanel.add(new JLabel("Vendor"));
        infoPanel.add(new JLabel(invoice.getVendorName()));

        infoPanel.add(new JLabel("Invoice Date"));
        infoPanel.add(new JLabel(invoice.getInvoiceDate()));

        infoPanel.add(new JLabel("Amount"));
        infoPanel.add(new JLabel("₹ " + invoice.getAmount()));

        infoPanel.add(new JLabel("Status"));
        infoPanel.add(new JLabel(invoice.getStatus()));
        Block block = blockchainService.getBlockByInvoiceId(invoice.getInvoiceId());

        JPanel blockchainPanel = new JPanel(new GridLayout(4,2,10,10));
        blockchainPanel.setBorder(BorderFactory.createTitledBorder("Blockchain Information"));

        if(block != null){

            blockchainPanel.add(new JLabel("Block ID"));
            blockchainPanel.add(new JLabel(String.valueOf(block.getBlockId())));

            blockchainPanel.add(new JLabel("Previous Hash"));
            blockchainPanel.add(new JLabel(block.getPreviousHash()));

            blockchainPanel.add(new JLabel("Current Hash"));
            blockchainPanel.add(new JLabel(block.getCurrentHash()));

            blockchainPanel.add(new JLabel("Timestamp"));
            blockchainPanel.add(new JLabel(block.getTimeStamp()));

        }else{

            blockchainPanel.add(new JLabel("No Blockchain Record"));
        }
        JPanel left = new JPanel(new BorderLayout(10,10));

        left.add(infoPanel,BorderLayout.NORTH);
        left.add(blockchainPanel,BorderLayout.CENTER);
        JLabel preview = new JLabel();
        preview.setHorizontalAlignment(SwingConstants.CENTER);
        preview.setBorder(BorderFactory.createTitledBorder("Invoice Preview"));

        File file = new File(invoice.getFilePath());

        if(file.exists()){

            if(file.getName().toLowerCase().endsWith(".png")
                    || file.getName().toLowerCase().endsWith(".jpg")
                    || file.getName().toLowerCase().endsWith(".jpeg")){

                ImageIcon icon = new ImageIcon(file.getAbsolutePath());

                Image image = icon.getImage().getScaledInstance(
                        500,
                        600,
                        Image.SCALE_SMOOTH);

                preview.setIcon(new ImageIcon(image));

            }else{

                preview.setText(
                        "<html><center><h2>PDF Invoice</h2><br>"
                                + file.getName()
                                + "</center></html>");

            }

        }else{

            preview.setText("Invoice file not found.");
        }
        JButton verify = new JButton("Verify");
        JButton print = new JButton("Print");
        JButton close = new JButton("Close");

        JPanel buttons = new JPanel();

        buttons.add(verify);
        buttons.add(print);
        buttons.add(close);
        verify.addActionListener(e->{

            boolean valid = blockchainService.verifyBlockchain();

            if(valid){

                JOptionPane.showMessageDialog(
                        this,
                        "Invoice verified successfully.");

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Blockchain verification failed.");
            }

        });
        print.addActionListener(e -> {

            try {

                Desktop.getDesktop().open(new File(invoice.getFilePath()));

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to open invoice."
                );

                ex.printStackTrace();
            }

        });
        close.addActionListener(e->dispose());
        JPanel right = new JPanel(new BorderLayout());

        right.add(preview,BorderLayout.CENTER);
        right.add(buttons,BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                left,
                right);

        split.setDividerLocation(450);

        main.add(split,BorderLayout.CENTER);

        add(main);
    }
}