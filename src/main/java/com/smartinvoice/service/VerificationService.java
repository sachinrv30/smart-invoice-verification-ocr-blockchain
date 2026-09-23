package com.smartinvoice.service;

import com.smartinvoice.dao.BlockchainDAO;
import com.smartinvoice.dao.InvoiceDAO;
import com.smartinvoice.model.Invoice;
import com.smartinvoice.utils.HashUtil;

public class VerificationService {

    private final InvoiceDAO invoiceDAO = new InvoiceDAO();
    private final BlockchainDAO blockchainDAO = new BlockchainDAO();

    public boolean verifyInvoice(int invoiceId) {
        System.out.println("===== VerificationService is running =====");
        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);

        if (invoice == null) {
            return false;
        }

        String invoiceData =
                invoice.getInvoiceNumber()
                        + invoice.getVendorName()
                        + invoice.getInvoiceDate()
                        + invoice.getAmount();

        String newHash = HashUtil.generateSHA256(invoiceData);

        String blockchainHash = blockchainDAO.getCurrentHash(invoiceId);

        if (blockchainHash == null) {

            invoiceDAO.updateInvoiceStatus(invoiceId, "Fraud");
            return false;
        }

        if (newHash.equals(blockchainHash)) {

            boolean updated = invoiceDAO.updateInvoiceStatus(invoiceId, "Verified");
            System.out.println("Status Updated : " + updated);
            return true;

        } else {

            boolean updated = invoiceDAO.updateInvoiceStatus(invoiceId, "Fraud");
            System.out.println("Status Updated : " + updated);
            return false;
        }
    }}