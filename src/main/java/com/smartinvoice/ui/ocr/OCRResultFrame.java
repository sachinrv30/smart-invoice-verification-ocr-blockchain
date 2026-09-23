package com.smartinvoice.ui.ocr;

import javax.swing.*;
import java.awt.*;
import com.smartinvoice.ocr.OCRService;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.Image;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

import java.io.FileWriter;
public class OCRResultFrame extends JFrame {

    private JLabel previewLabel;
    private JTextArea txtResult;

    private JButton btnBrowse;
    private JButton btnExtract;
    private JButton btnCopy;
    private JButton btnSave;
    private JButton btnClear;
    private File selectedFile;

    private final OCRService ocrService = new OCRService();
    public OCRResultFrame() {

        setTitle("SmartLedger AI - OCR Extraction");
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();

        setVisible(true);
    }

    private void initUI() {

        JPanel main = new JPanel(new BorderLayout(15,15));
        main.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ================= TITLE =================

        JLabel title = new JLabel("SMARTLEDGER AI - OCR EXTRACTION");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        main.add(title, BorderLayout.NORTH);

        // ================= LEFT PANEL =================

        previewLabel = new JLabel("No Invoice Selected");
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewLabel.setBorder(BorderFactory.createTitledBorder("Invoice Preview"));

        JScrollPane previewScroll = new JScrollPane(previewLabel);

        // ================= RIGHT PANEL =================

        txtResult = new JTextArea();
        txtResult.setEditable(false);
        txtResult.setLineWrap(true);
        txtResult.setWrapStyleWord(true);
        txtResult.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane resultScroll = new JScrollPane(txtResult);
        resultScroll.setBorder(BorderFactory.createTitledBorder("OCR Extracted Text"));

        // ================= SPLIT =================

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                previewScroll,
                resultScroll
        );

        splitPane.setDividerLocation(400);

        main.add(splitPane, BorderLayout.CENTER);

        // ================= BUTTONS =================

        JPanel bottom = new JPanel(new FlowLayout());

        btnBrowse = new JButton("Browse");
        btnExtract = new JButton("Extract Text");
        btnCopy = new JButton("Copy");
        btnSave = new JButton("Save");
        btnClear = new JButton("Clear");

        bottom.add(btnBrowse);
        bottom.add(btnExtract);
        bottom.add(btnCopy);
        bottom.add(btnSave);
        bottom.add(btnClear);

        main.add(bottom, BorderLayout.SOUTH);

        add(main);
        btnBrowse.addActionListener(e -> browseInvoice());
        btnExtract.addActionListener(e -> extractText());
        btnCopy.addActionListener(e -> copyText());
        btnSave.addActionListener(e -> saveText());
        btnClear.addActionListener(e -> clearScreen());
    }
    private void clearScreen() {

        selectedFile = null;

        previewLabel.setIcon(null);
        previewLabel.setText("No Invoice Selected");

        txtResult.setText("");

    }
    private void copyText() {

        if (txtResult.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,"No text to copy.");
            return;
        }

        StringSelection selection =
                new StringSelection(txtResult.getText());

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(selection, null);

        JOptionPane.showMessageDialog(
                this,
                "OCR Text Copied Successfully!"
        );
    }
    private void saveText() {

        if (txtResult.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,"No OCR text available.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("ocr_result.txt"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

            try {

                FileWriter writer =
                        new FileWriter(chooser.getSelectedFile());

                writer.write(txtResult.getText());

                writer.close();

                JOptionPane.showMessageDialog(
                        this,
                        "OCR Text Saved Successfully!"
                );

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
    private void browseInvoice() {

        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Images & PDF",
                        "png",
                        "jpg",
                        "jpeg",
                        "pdf"
                )
        );

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            selectedFile = chooser.getSelectedFile();

            // PDF Preview
            if (selectedFile.getName().toLowerCase().endsWith(".pdf")) {

                previewLabel.setIcon(null);

                previewLabel.setText(
                        "<html><center><h2>PDF Selected</h2><br>"
                                + selectedFile.getName()
                                + "</center></html>"
                );

            }

            // Image Preview
            else {

                ImageIcon icon =
                        new ImageIcon(selectedFile.getAbsolutePath());

                Image image =
                        icon.getImage().getScaledInstance(
                                350,
                                450,
                                Image.SCALE_SMOOTH
                        );

                previewLabel.setIcon(new ImageIcon(image));
                previewLabel.setText("");

            }

            txtResult.setText("");
        }

    }
private void extractText() {

    if (selectedFile == null) {

        JOptionPane.showMessageDialog(
                this,
                "Please select an invoice first."
        );
        return;
    }

    try {

        String text = ocrService.extractText(selectedFile);

        txtResult.setText(text);

        JOptionPane.showMessageDialog(
                this,
                "OCR Extraction Completed Successfully!"
        );

    } catch (Exception ex) {

        ex.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                "OCR Failed!\n" + ex.getMessage()
        );
    }
}}