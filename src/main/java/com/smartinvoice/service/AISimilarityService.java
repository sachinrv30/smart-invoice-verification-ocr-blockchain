package com.smartinvoice.service;

import com.smartinvoice.model.Invoice;

public class AISimilarityService {

    public double calculateSimilarity(Invoice a, Invoice b) {

        double score = 0;

        // Invoice Number
        if (a.getInvoiceNumber().equalsIgnoreCase(b.getInvoiceNumber()))
            score += 40;

        // Vendor
        if (a.getVendorName().equalsIgnoreCase(b.getVendorName()))
            score += 20;

        // Date
        if (a.getInvoiceDate().equals(b.getInvoiceDate()))
            score += 20;

        // Amount
        if (a.getAmount() == b.getAmount())
            score += 20;

        return score;
    }
}