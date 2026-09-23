package com.smartinvoice.service;

import com.smartinvoice.dao.BlockchainDAO;
import com.smartinvoice.model.Blockchain;

import com.smartinvoice.model.Block;
import java.util.List;
public class BlockchainService {

    private final BlockchainDAO dao = new BlockchainDAO();

    public boolean createBlock(int invoiceId, String currentHash) {

        System.out.println("========== BLOCKCHAIN ==========");
        System.out.println("Invoice ID : " + invoiceId);

        Blockchain block = new Blockchain();

        block.setInvoiceId(invoiceId);
        block.setPreviousHash(dao.getLastHash());
        block.setCurrentHash(currentHash);

        System.out.println("Previous Hash : " + block.getPreviousHash());
        System.out.println("Current Hash  : " + block.getCurrentHash());

        boolean result = dao.saveBlock(block);

        System.out.println("Blockchain Saved : " + result);
        System.out.println("================================");

        return result;
    }
    public Block getBlockByInvoiceId(int invoiceId) {
        return dao.getBlockByInvoiceId(invoiceId);
    }
    public List<Block> getAllBlocks() {
        return dao.getAllBlocks();
    }
    public boolean verifyBlockchain() {

        List<Block> blocks = dao.getAllBlocks();

        // Empty blockchain is considered valid
        if (blocks.isEmpty()) {
            return true;
        }

        // Check that each block points to the previous block
        for (int i = 1; i < blocks.size(); i++) {

            Block previous = blocks.get(i - 1);
            Block current = blocks.get(i);

            if (!current.getPreviousHash().equals(previous.getCurrentHash())) {
                return false;
            }
        }

        return true;
    }
}