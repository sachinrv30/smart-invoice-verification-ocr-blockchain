package com.smartinvoice.validation;

import com.smartinvoice.model.Invoice;

public class InvoiceValidator {

    public static String validate(Invoice invoice) {

        if (invoice.getInvoiceNumber() == null ||
                invoice.getInvoiceNumber().trim().isEmpty())
            return "Invoice Number is required.";

        if (invoice.getVendorName() == null ||
                invoice.getVendorName().trim().isEmpty())
            return "Vendor Name is required.";

        if (invoice.getInvoiceDate() == null ||
                invoice.getInvoiceDate().trim().isEmpty())
            return "Invoice Date is required.";

        if (invoice.getAmount() <= 0)
            return "Invalid Invoice Amount.";

        if (invoice.getFilePath() == null ||
                invoice.getFilePath().trim().isEmpty())
            return "Invoice file not selected.";

        return "VALID";
    }
}