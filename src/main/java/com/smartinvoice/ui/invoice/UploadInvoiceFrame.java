package com.smartinvoice.ui.invoice;

import com.smartinvoice.model.ExtractedInvoice;
import com.smartinvoice.model.Invoice;
import com.smartinvoice.ocr.OCRService;
import com.smartinvoice.parser.InvoiceParser;
import com.smartinvoice.service.BlockchainService;
import com.smartinvoice.service.InvoiceService;
import com.smartinvoice.utils.HashUtil;
import com.smartinvoice.validation.InvoiceValidator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class UploadInvoiceFrame extends JFrame {

    private JTextField txtInvoiceNo;
    private JTextField txtVendor;
    private JTextField txtDate;
    private JTextField txtAmount;
    private JTextField txtFile;
    private JLabel previewLabel;
    private JTextArea txtOCR;

    private JButton btnBrowse;
    private JButton btnUpload;

    private File selectedFile;

    private final InvoiceService invoiceService = new InvoiceService();
    private final BlockchainService blockchainService = new BlockchainService();
    private final OCRService ocrService = new OCRService();
    private final InvoiceParser parser = new InvoiceParser();

    public UploadInvoiceFrame() {

        setTitle("SmartLedger AI - Upload Invoice");
        setSize(900,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15,15));
        main.setBorder(new EmptyBorder(20,20,20,20));
        main.setBackground(new Color(245,247,250));

        JLabel title = new JLabel("UPLOAD INVOICE");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,30));

        main.add(title,BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5,3,10,10));
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(20,20,20,20));

        form.add(new JLabel("Invoice Number"));
        txtInvoiceNo = new JTextField();
        form.add(txtInvoiceNo);
        form.add(new JLabel());

        form.add(new JLabel("Vendor Name"));
        txtVendor = new JTextField();
        form.add(txtVendor);
        form.add(new JLabel());

        form.add(new JLabel("Invoice Date (YYYY-MM-DD)"));
        txtDate = new JTextField();
        form.add(txtDate);
        form.add(new JLabel());

        form.add(new JLabel("Amount"));
        txtAmount = new JTextField();
        form.add(txtAmount);
        form.add(new JLabel());

        form.add(new JLabel("Invoice File"));

        txtFile = new JTextField();
        txtFile.setEditable(false);
        form.add(txtFile);

        btnBrowse = new JButton("Browse");
        form.add(btnBrowse);

        txtOCR = new JTextArea();
        txtOCR.setFont(new Font("Monospaced",Font.PLAIN,13));
        txtOCR.setEditable(false);
        txtOCR.setLineWrap(true);
        txtOCR.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtOCR);
        scroll.setBorder(BorderFactory.createTitledBorder("OCR Extracted Text"));
        previewLabel = new JLabel("No Invoice Selected");
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewLabel.setPreferredSize(new Dimension(280,350));
        previewLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel center = new JPanel(new BorderLayout(15,15));
        center.setOpaque(false);

        center.add(form,BorderLayout.NORTH);
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(previewLabel),
                scroll
        );

        splitPane.setDividerLocation(300);

        center.add(splitPane, BorderLayout.CENTER);

        main.add(center,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(245,247,250));

        btnUpload = new JButton("Upload Invoice");

        bottom.add(btnUpload);

        main.add(bottom,BorderLayout.SOUTH);

        btnBrowse.addActionListener(e -> browseFile());
        btnUpload.addActionListener(e -> uploadInvoice());

        add(main);

        setVisible(true);
    }
    private void browseFile() {

        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            selectedFile = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

            Image image = icon.getImage().getScaledInstance(
                    280,
                    350,
                    Image.SCALE_SMOOTH
            );

            previewLabel.setIcon(new ImageIcon(image));
            previewLabel.setText("");
            txtFile.setText(selectedFile.getAbsolutePath());

            try {

                // OCR Extraction
                String text = ocrService.extractText(selectedFile);

                txtOCR.setText(text);

                System.out.println("========== OCR OUTPUT ==========");
                System.out.println(text);
                System.out.println("================================");

                // Parse Extracted Data
                ExtractedInvoice extracted = parser.parse(text);

                if (extracted.getInvoiceNumber() != null &&
                        !extracted.getInvoiceNumber().isBlank()) {

                    txtInvoiceNo.setText(extracted.getInvoiceNumber());
                }

                if (extracted.getVendorName() != null &&
                        !extracted.getVendorName().isBlank()) {

                    txtVendor.setText(extracted.getVendorName());
                }

                if (extracted.getInvoiceDate() != null &&
                        !extracted.getInvoiceDate().isBlank()) {

                    txtDate.setText(extracted.getInvoiceDate());
                }

                if (extracted.getAmount() > 0) {

                    txtAmount.setText(String.valueOf(extracted.getAmount()));
                }

                JOptionPane.showMessageDialog(
                        this,
                        "OCR Completed Successfully!",
                        "OCR",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "OCR Failed\n\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    private void uploadInvoice() {

        if (selectedFile == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an invoice file.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            // Validate Input Fields
            if (txtInvoiceNo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invoice Number is required.");
                return;
            }

            if (txtVendor.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vendor Name is required.");
                return;
            }

            if (txtDate.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invoice Date is required.");
                return;
            }

            if (txtAmount.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Amount is required.");
                return;
            }

            // Validate Date
            LocalDate.parse(txtDate.getText().trim());

            // Create Upload Folder
            File uploadDir = new File("uploads");

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destination = new File(uploadDir, selectedFile.getName());

            Files.copy(
                    selectedFile.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            // Create Invoice Object
            Invoice invoice = new Invoice();

            invoice.setInvoiceNumber(txtInvoiceNo.getText().trim());
            invoice.setVendorName(txtVendor.getText().trim());
            invoice.setInvoiceDate(txtDate.getText().trim());
            invoice.setAmount(Double.parseDouble(txtAmount.getText().trim()));

            invoice.setFileName(selectedFile.getName());
            invoice.setFilePath(destination.getAbsolutePath());
            invoice.setStatus("Uploaded");

            // Validation
            String validation = InvoiceValidator.validate(invoice);

            if (!validation.equals("VALID")) {

                JOptionPane.showMessageDialog(
                        this,
                        validation,
                        "Validation Failed",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // SHA-256 Hash
            String invoiceData =
                    invoice.getInvoiceNumber()
                            + invoice.getVendorName()
                            + invoice.getInvoiceDate()
                            + invoice.getAmount();

            String hash = HashUtil.generateSHA256(invoiceData);

            System.out.println("================================");
            System.out.println("SHA-256 HASH");
            System.out.println(hash);
            System.out.println("================================");

            // Save Invoice
            int invoiceId = invoiceService.saveInvoice(invoice);

            if (invoiceId == -2) {

                JOptionPane.showMessageDialog(
                        this,
                        "Duplicate Invoice Detected!",
                        "Duplicate",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (invoiceId == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invoice Save Failed!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Blockchain
            boolean blockchainSaved =
                    blockchainService.createBlock(invoiceId, hash);

            if (blockchainSaved) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invoice Uploaded Successfully!\n\n"
                                + "Invoice ID : " + invoiceId
                                + "\n\nOCR Completed ✔"
                                + "\nValidation Passed ✔"
                                + "\nSHA-256 Generated ✔"
                                + "\nBlockchain Block Created ✔",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invoice saved but Blockchain block was not created.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }

            clearFields();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private void clearFields() {

        txtInvoiceNo.setText("");
        txtVendor.setText("");
        txtDate.setText("");
        txtAmount.setText("");
        txtFile.setText("");

        txtOCR.setText("");

        selectedFile = null;

        txtInvoiceNo.requestFocus();
        previewLabel.setIcon(null);
        previewLabel.setText("No Invoice Selected");
    }
}