package com.smartinvoice.service;

import com.smartinvoice.dao.BlockchainDAO;
import com.smartinvoice.dao.InvoiceDAO;

public class DashboardService {

    private final InvoiceDAO invoiceDAO = new InvoiceDAO();
    private final BlockchainDAO blockchainDAO = new BlockchainDAO();

    public int getTotalInvoices() {
        return invoiceDAO.getTotalInvoices();
    }

    public int getVerifiedInvoices() {
        return invoiceDAO.getVerifiedInvoices();
    }

    public int getFraudInvoices() {
        return invoiceDAO.getFraudInvoices();
    }

    public int getBlockchainBlocks() {
        return blockchainDAO.getTotalBlocks();
    }
}