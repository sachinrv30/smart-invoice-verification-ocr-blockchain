package com.smartinvoice.parser;

import com.smartinvoice.model.ExtractedInvoice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceParser {

    public ExtractedInvoice parse(String ocrText) {

        ExtractedInvoice invoice = new ExtractedInvoice();

        // Invoice Number
        Pattern invoicePattern = Pattern.compile("(INV[- ]?\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher invoiceMatcher = invoicePattern.matcher(ocrText);
        if (invoiceMatcher.find()) {
            invoice.setInvoiceNumber(invoiceMatcher.group());
        }

        // Date (dd/mm/yyyy or dd-mm-yyyy)
        Pattern datePattern = Pattern.compile("\\b\\d{2}[/-]\\d{2}[/-]\\d{4}\\b");
        Matcher dateMatcher = datePattern.matcher(ocrText);
        if (dateMatcher.find()) {
            invoice.setInvoiceDate(dateMatcher.group());
        }

        // GST Number (India)
        Pattern gstPattern = Pattern.compile("\\b\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}\\b");
        Matcher gstMatcher = gstPattern.matcher(ocrText);
        if (gstMatcher.find()) {
            invoice.setGstNumber(gstMatcher.group());
        }

        // Amount
        Pattern amountPattern = Pattern.compile("\\b\\d+\\.\\d{2}\\b");
        Matcher amountMatcher = amountPattern.matcher(ocrText);
        if (amountMatcher.find()) {
            invoice.setAmount(Double.parseDouble(amountMatcher.group()));
        }

        return invoice;
    }
}