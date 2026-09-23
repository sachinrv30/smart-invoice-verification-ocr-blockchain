package com.smartinvoice.dao;

import com.smartinvoice.config.DBConnection;
import com.smartinvoice.model.Blockchain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.smartinvoice.model.Block;
import java.util.ArrayList;
import java.util.List;

public class BlockchainDAO {

    // Get last block hash
    public String getLastHash() {

        String sql = "SELECT current_hash FROM blockchain ORDER BY block_id DESC LIMIT 1";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("current_hash");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "0";
    }

    // Save blockchain block
    public boolean saveBlock(Blockchain block) {

        String sql = "INSERT INTO blockchain(invoice_id, previous_hash, current_hash) VALUES(?,?,?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, block.getInvoiceId());
            ps.setString(2, block.getPreviousHash());
            ps.setString(3, block.getCurrentHash());
            System.out.println("Saving blockchain block...");
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Dashboard
    public int getTotalBlocks() {

        String sql = "SELECT COUNT(*) FROM blockchain";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public String getCurrentHash(int invoiceId) {

        String sql = "SELECT current_hash FROM blockchain WHERE invoice_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("current_hash");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public Block getBlockByInvoiceId(int invoiceId) {

        String sql = "SELECT * FROM blockchain WHERE invoice_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Block block = new Block();

                block.setBlockId(rs.getInt("block_id"));
                block.setInvoiceId(rs.getInt("invoice_id"));
                block.setPreviousHash(rs.getString("previous_hash"));
                block.setCurrentHash(rs.getString("current_hash"));
                block.setTimeStamp(rs.getString("time_stamp"));

                return block;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Block> getAllBlocks() {

        List<Block> blocks = new ArrayList<>();

        String sql = """
    SELECT block_id,
           invoice_id,
           previous_hash,
           current_hash,
           time_stamp
    FROM blockchain
    ORDER BY block_id ASC
    """;

        try {

            Connection con = DBConnection.getConnection();

            System.out.println("Database Connected = " + (con != null));

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("Loading Block ID = " + rs.getInt("block_id"));

                Block block = new Block();

                block.setBlockId(rs.getInt("block_id"));
                block.setInvoiceId(rs.getInt("invoice_id"));
                block.setPreviousHash(rs.getString("previous_hash"));
                block.setCurrentHash(rs.getString("current_hash"));
                block.setTimeStamp(rs.getString("time_stamp"));

                blocks.add(block);
            }

            System.out.println("Total Loaded = " + blocks.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return blocks;
    }}