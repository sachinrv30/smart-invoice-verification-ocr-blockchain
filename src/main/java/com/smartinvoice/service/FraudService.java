package com.smartinvoice.service;

import com.smartinvoice.dao.BlockchainDAO;
import com.smartinvoice.dao.InvoiceDAO;
import com.smartinvoice.model.Invoice;
import com.smartinvoice.utils.HashUtil;
import java.util.List;

public class FraudService {

    private final InvoiceDAO invoiceDAO = new InvoiceDAO();
    private final BlockchainDAO blockchainDAO = new BlockchainDAO();

    private final InvoiceService invoiceService = new InvoiceService();
    private final AISimilarityService aiService = new AISimilarityService();

    public String verifyInvoice(int invoiceId) {

        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);

        if (invoice == null) {
            return "Invoice not found.";
        }

        int score = 100;

        StringBuilder result = new StringBuilder();

        // ---------------- Duplicate Check ----------------

        if (invoiceDAO.invoiceExists(invoice.getInvoiceNumber())) {

            result.append("✔ Invoice Number Exists\n");

        } else {

            score -= 20;
            result.append("❌ Invoice Number Missing\n");
        }

        // ---------------- Vendor ----------------

        if (invoice.getVendorName() == null ||
                invoice.getVendorName().isBlank()) {

            score -= 10;
            result.append("❌ Vendor Invalid\n");

        } else {

            result.append("✔ Vendor Valid\n");
        }

        // ---------------- Amount ----------------

        if (invoice.getAmount() <= 0) {

            score -= 10;
            result.append("❌ Amount Invalid\n");

        } else {

            result.append("✔ Amount Valid\n");
        }

        // ---------------- Blockchain ----------------

        String generatedHash = HashUtil.generateSHA256(

                invoice.getInvoiceNumber() +
                        invoice.getVendorName() +
                        invoice.getInvoiceDate() +
                        invoice.getAmount()
        );

        String blockchainHash =
                blockchainDAO.getCurrentHash(invoiceId);

        if (generatedHash.equals(blockchainHash)) {

            result.append("✔ Blockchain Verified\n");

        } else {

            score -= 40;
            result.append("❌ Blockchain Hash Mismatch\n");
        }

        // ---------------- Result ----------------

        result.append("\n");
        result.append("====================================\n");
        result.append(" SMARTLEDGER AI - FRAUD REPORT\n");
        result.append("====================================\n\n");

        result.append("Invoice Number : ")
                .append(invoice.getInvoiceNumber())
                .append("\n");

        result.append("Vendor         : ")
                .append(invoice.getVendorName())
                .append("\n");

        result.append("Amount         : ₹")
                .append(invoice.getAmount())
                .append("\n");

        result.append("Invoice Date   : ")
                .append(invoice.getInvoiceDate())
                .append("\n\n");

        result.append("------------------------------------\n");
        result.append("Verification Score : ")
                .append(score)
                .append("%\n");
        result.append("------------------------------------\n\n");

        if (score >= 80) {

            invoiceDAO.updateInvoiceStatus(invoiceId, "Verified");

            result.append("🟢 RESULT : GENUINE INVOICE");

        } else {

            invoiceDAO.updateInvoiceStatus(invoiceId, "Fraud");

            result.append("🔴 RESULT : FRAUD DETECTED");
        }

        return result.toString();
    }

    public String analyzeSimilarity(Invoice invoice) {

        List<Invoice> invoices =
                invoiceService.getAllInvoicesExcept(invoice.getInvoiceId());

        double highestScore = 0;
        Invoice similarInvoice = null;

        for (Invoice dbInvoice : invoices) {

            double score = aiService.calculateSimilarity(invoice, dbInvoice);

            if (score > highestScore) {
                highestScore = score;
                similarInvoice = dbInvoice;
            }
        }

        StringBuilder report = new StringBuilder();

        report.append("\n=====================================\n");
        report.append(" SMARTLEDGER AI - AI ANALYSIS\n");
        report.append("=====================================\n\n");

        if (similarInvoice == null) {

            report.append("No similar invoices found.");

            return report.toString();
        }

        report.append("Most Similar Invoice\n\n");

        report.append("Invoice Number : ")
                .append(similarInvoice.getInvoiceNumber())
                .append("\n");

        report.append("Vendor : ")
                .append(similarInvoice.getVendorName())
                .append("\n");

        report.append("Amount : ₹")
                .append(similarInvoice.getAmount())
                .append("\n\n");

        report.append("Similarity Score : ")
                .append(String.format("%.0f", highestScore))
                .append("%\n\n");

        if (highestScore >= 90) {

            report.append("⚠ POSSIBLE DUPLICATE INVOICE");

        } else if (highestScore >= 70) {

            report.append("⚠ SUSPICIOUS INVOICE");

        } else {

            report.append("✅ UNIQUE INVOICE");
        }

        return report.toString();
    }
}