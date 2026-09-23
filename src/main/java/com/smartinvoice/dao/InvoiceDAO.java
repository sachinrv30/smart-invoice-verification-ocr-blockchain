package com.smartinvoice.dao;

import com.smartinvoice.config.DBConnection;
import com.smartinvoice.model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InvoiceDAO {

    public int saveInvoice(Invoice invoice) {

        String sql = "INSERT INTO invoice (invoice_number, vendor_name, invoice_date, amount, file_name, file_path, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, invoice.getInvoiceNumber());
            ps.setString(2, invoice.getVendorName());
            ps.setString(3, invoice.getInvoiceDate());
            ps.setDouble(4, invoice.getAmount());
            ps.setString(5, invoice.getFileName());
            ps.setString(6, invoice.getFilePath());
            ps.setString(7, invoice.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);   // invoice_id
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    public boolean invoiceExists(String invoiceNumber) {

        String sql = "SELECT COUNT(*) FROM invoice WHERE invoice_number = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, invoiceNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    // Total invoices
    public int getTotalInvoices() {

        String sql = "SELECT COUNT(*) FROM invoice";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Verified invoices
    public int getVerifiedInvoices() {

        String sql = "SELECT COUNT(*) FROM invoice WHERE status='Verified'";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Fraud invoices
    public int getFraudInvoices() {

        String sql = "SELECT COUNT(*) FROM invoice WHERE status='Fraud'";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public boolean updateInvoiceStatus(int invoiceId, String status) {

        String sql = "UPDATE invoice SET status=? WHERE invoice_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, invoiceId);

            int rows = ps.executeUpdate();

            System.out.println("Invoice ID : " + invoiceId);
            System.out.println("New Status : " + status);
            System.out.println("Rows Updated : " + rows);

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public Invoice getInvoiceById(int invoiceId) {

        String sql = "SELECT * FROM invoice WHERE invoice_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setInvoiceNumber(rs.getString("invoice_number"));
                invoice.setVendorName(rs.getString("vendor_name"));
                invoice.setInvoiceDate(rs.getString("invoice_date"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setFileName(rs.getString("file_name"));
                invoice.setFilePath(rs.getString("file_path"));
                invoice.setStatus(rs.getString("status"));

                return invoice;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public java.util.List<Invoice> getAllInvoices() {

        java.util.List<Invoice> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM invoice ORDER BY invoice_id DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setInvoiceNumber(rs.getString("invoice_number"));
                invoice.setVendorName(rs.getString("vendor_name"));
                invoice.setInvoiceDate(rs.getString("invoice_date"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setStatus(rs.getString("status"));

                list.add(invoice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public java.util.List<Invoice> searchByInvoiceNumber(String keyword) {

        java.util.List<Invoice> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM invoice WHERE invoice_number LIKE ? ORDER BY invoice_id DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setInvoiceNumber(rs.getString("invoice_number"));
                invoice.setVendorName(rs.getString("vendor_name"));
                invoice.setInvoiceDate(rs.getString("invoice_date"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setStatus(rs.getString("status"));

                list.add(invoice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public java.util.List<Invoice> getAllInvoicesExcept(int invoiceId) {

        java.util.List<Invoice> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM invoice WHERE invoice_id <> ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, invoiceId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setInvoiceNumber(rs.getString("invoice_number"));
                invoice.setVendorName(rs.getString("vendor_name"));
                invoice.setInvoiceDate(rs.getString("invoice_date"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setStatus(rs.getString("status"));

                list.add(invoice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public java.util.List<Invoice> searchByVendor(String keyword) {

        java.util.List<Invoice> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM invoice WHERE vendor_name LIKE ? ORDER BY invoice_id DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setInvoiceNumber(rs.getString("invoice_number"));
                invoice.setVendorName(rs.getString("vendor_name"));
                invoice.setInvoiceDate(rs.getString("invoice_date"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setStatus(rs.getString("status"));

                list.add(invoice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}