package com.smartinvoice.report;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.smartinvoice.model.Invoice;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PDFReportService {

    public void generateReport(
            Invoice invoice,
            String verificationReport,
            String filePath) {

        try {

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(filePath)
            );

            document.open();

            // ================= TITLE =================

            Font titleFont =
                    new Font(Font.HELVETICA,22,Font.BOLD);

            Paragraph title =
                    new Paragraph(
                            "SMARTLEDGER AI\nInvoice Verification Report",
                            titleFont);

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph("\n"));

            // ================= TABLE =================

            PdfPTable table = new PdfPTable(2);

            table.setWidthPercentage(100);

            table.addCell(cell("Invoice ID"));
            table.addCell(cell(String.valueOf(invoice.getInvoiceId())));

            table.addCell(cell("Invoice Number"));
            table.addCell(cell(invoice.getInvoiceNumber()));

            table.addCell(cell("Vendor"));
            table.addCell(cell(invoice.getVendorName()));

            table.addCell(cell("Invoice Date"));
            table.addCell(cell(invoice.getInvoiceDate()));

            table.addCell(cell("Amount"));
            table.addCell(cell("₹ " + invoice.getAmount()));

            table.addCell(cell("Status"));
            table.addCell(cell(invoice.getStatus()));

            document.add(table);

            document.add(new Paragraph("\n"));

            // ================= REPORT =================

            Font heading =
                    new Font(Font.HELVETICA,16,Font.BOLD);

            document.add(
                    new Paragraph(
                            "Fraud Verification Report",
                            heading
                    )
            );

            document.add(new Paragraph("\n"));

            document.add(
                    new Paragraph(
                            verificationReport
                    )
            );

            document.add(new Paragraph("\n"));

            // ================= DATE =================

            document.add(
                    new Paragraph(
                            "Generated On : "
                                    + LocalDateTime.now()
                                    .format(
                                            DateTimeFormatter.ofPattern(
                                                    "dd-MM-yyyy HH:mm:ss"
                                            )
                                    )
                    )
            );

            document.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private PdfPCell cell(String text){

        PdfPCell cell =
                new PdfPCell(new Phrase(text));

        cell.setPadding(8);

        return cell;
    }

}