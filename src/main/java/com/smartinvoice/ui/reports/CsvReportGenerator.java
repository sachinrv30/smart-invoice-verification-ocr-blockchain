package com.smartinvoice.report;

import com.smartinvoice.model.Invoice;
import com.smartinvoice.service.InvoiceService;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CsvReportGenerator {

    public static void generate() {

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("SmartLedger_Report.csv"));

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        try (FileWriter writer = new FileWriter(chooser.getSelectedFile())) {

            writer.write("Invoice No,Vendor,Amount,Status,Date\n");

            InvoiceService service = new InvoiceService();
            List<Invoice> invoices = service.getAllInvoices();

            for (Invoice invoice : invoices) {

                writer.write(
                        invoice.getInvoiceNumber() + "," +
                                invoice.getVendorName() + "," +
                                invoice.getAmount() + "," +
                                invoice.getStatus() + "," +
                                invoice.getInvoiceDate() + "\n"
                );
            }

            JOptionPane.showMessageDialog(
                    null,
                    "CSV Report Generated Successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}