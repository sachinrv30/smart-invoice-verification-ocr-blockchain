package com.smartinvoice.report;

import com.smartinvoice.model.Invoice;
import com.smartinvoice.service.InvoiceService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelReportGenerator {

    public static void generate() {

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("SmartLedger_Report.xlsx"));

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Invoices");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Invoice No");
            header.createCell(1).setCellValue("Vendor");
            header.createCell(2).setCellValue("Amount");
            header.createCell(3).setCellValue("Status");
            header.createCell(4).setCellValue("Date");

            InvoiceService service = new InvoiceService();
            List<Invoice> invoices = service.getAllInvoices();

            int rowNum = 1;

            for (Invoice invoice : invoices) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(invoice.getInvoiceNumber());
                row.createCell(1).setCellValue(invoice.getVendorName());
                row.createCell(2).setCellValue(invoice.getAmount());
                row.createCell(3).setCellValue(invoice.getStatus());
                row.createCell(4).setCellValue(invoice.getInvoiceDate());
            }

            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream out = new FileOutputStream(chooser.getSelectedFile());
            workbook.write(out);
            out.close();

            JOptionPane.showMessageDialog(
                    null,
                    "Excel Report Generated Successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}