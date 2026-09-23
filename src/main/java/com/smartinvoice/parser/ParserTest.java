package com.smartinvoice.parser;

import com.smartinvoice.model.ExtractedInvoice;

public class ParserTest {

    public static void main(String[] args) {

        String text = """
                Invoice Number : INV1001
                Vendor : ABC Technologies
                Date : 30/07/2026
                GST : 29ABCDE1234F1Z5
                Total Amount : 15000.00
                """;

        InvoiceParser parser = new InvoiceParser();

        ExtractedInvoice invoice = parser.parse(text);

        System.out.println(invoice);
    }
}