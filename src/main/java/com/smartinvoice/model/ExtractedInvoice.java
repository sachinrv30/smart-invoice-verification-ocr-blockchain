package com.smartinvoice.model;

public class ExtractedInvoice {

    private String invoiceNumber;
    private String vendorName;
    private String invoiceDate;
    private String gstNumber;
    private double amount;

    public ExtractedInvoice() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExtractedInvoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", gstNumber='" + gstNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}