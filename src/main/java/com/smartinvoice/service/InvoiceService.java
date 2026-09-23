package com.smartinvoice.service;

import com.smartinvoice.dao.InvoiceDAO;
import com.smartinvoice.model.Invoice;

import java.util.List;

public class InvoiceService {

    private final InvoiceDAO invoiceDAO = new InvoiceDAO();

    // Save Invoice
    public int saveInvoice(Invoice invoice) {

        if (invoiceDAO.invoiceExists(invoice.getInvoiceNumber())) {
            return -2;
        }

        return invoiceDAO.saveInvoice(invoice);
    }

    // Get Invoice by ID
    public Invoice getInvoiceById(int invoiceId) {
        return invoiceDAO.getInvoiceById(invoiceId);
    }

    // Get All Invoices
    public List<Invoice> getAllInvoices() {
        return invoiceDAO.getAllInvoices();
    }

    // Search by Invoice Number
    public List<Invoice> searchByInvoiceNumber(String keyword) {
        return invoiceDAO.searchByInvoiceNumber(keyword);
    }

    // Search by Vendor
    public List<Invoice> searchByVendor(String keyword) {
        return invoiceDAO.searchByVendor(keyword);
    }

    // Get All Invoices Except Current Invoice
    public List<Invoice> getAllInvoicesExcept(int invoiceId) {
        return invoiceDAO.getAllInvoicesExcept(invoiceId);
    }
}