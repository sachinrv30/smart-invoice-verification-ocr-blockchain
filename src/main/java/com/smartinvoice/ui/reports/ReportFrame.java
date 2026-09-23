package com.smartinvoice.ui.reports;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.smartinvoice.report.PdfReportGenerator;
import com.smartinvoice.report.ExcelReportGenerator;
import com.smartinvoice.report.CsvReportGenerator;
public class ReportFrame extends JFrame {

    public ReportFrame() {

        setTitle("SmartLedger AI - Reports");
        setSize(600,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30,30,30,30));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("SMARTLEDGER AI REPORTS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton pdfBtn = new JButton("📄 Export PDF Report");
        JButton excelBtn = new JButton("📊 Export Excel Report");
        JButton csvBtn = new JButton("📃 Export CSV Report");
        JButton closeBtn = new JButton("Close");

        Dimension size = new Dimension(260,45);

        pdfBtn.setMaximumSize(size);
        excelBtn.setMaximumSize(size);
        csvBtn.setMaximumSize(size);
        closeBtn.setMaximumSize(size);

        panel.add(title);
        panel.add(Box.createVerticalStrut(40));

        panel.add(pdfBtn);
        panel.add(Box.createVerticalStrut(15));

        panel.add(excelBtn);
        panel.add(Box.createVerticalStrut(15));

        panel.add(csvBtn);
        panel.add(Box.createVerticalStrut(15));

        panel.add(closeBtn);

        closeBtn.addActionListener(e -> dispose());
        csvBtn.addActionListener(e ->
                CsvReportGenerator.generate());
        excelBtn.addActionListener(e ->
                ExcelReportGenerator.generate());
        pdfBtn.addActionListener(e -> PdfReportGenerator.generate());
        add(panel);

        setVisible(true);
    }
}