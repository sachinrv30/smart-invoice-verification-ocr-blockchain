package com.smartinvoice.ui.analytics;

import com.smartinvoice.service.DashboardService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

public class AnalyticsFrame extends JFrame {

    private final DashboardService dashboardService = new DashboardService();

    public AnalyticsFrame() {

        setTitle("SmartLedger AI - Analytics");
        setSize(1200,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(20,20));
        main.setBackground(new Color(245,247,250));
        main.setBorder(new EmptyBorder(20,20,20,20));

        JLabel title = new JLabel("SMARTLEDGER AI ANALYTICS");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));

        main.add(title, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(2,3,20,20));
        cards.setOpaque(false);
        int total = dashboardService.getTotalInvoices();
        int verified = dashboardService.getVerifiedInvoices();
        int fraud = dashboardService.getFraudInvoices();
        int blockchain = dashboardService.getBlockchainBlocks();

        int uploaded = total - verified;

        double verifyRate =
                total == 0 ? 0 : (verified * 100.0 / total);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(total, "Invoices", "Total");
        dataset.addValue(verified, "Invoices", "Verified");
        dataset.addValue(uploaded, "Invoices", "Uploaded");
        dataset.addValue(fraud, "Invoices", "Fraud");
        dataset.addValue(blockchain, "Invoices", "Blockchain");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Invoice Statistics",
                "Category",
                "Count",
                dataset
        );

        barChart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = barChart.getCategoryPlot();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(220,220,220));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(37,99,235));

        renderer.setMaximumBarWidth(0.10);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(1100, 350));
        chartPanel.setMouseWheelEnabled(true);

        cards.add(createCard("📄 Total Invoices", String.valueOf(total), new Color(37,99,235)));
        cards.add(createCard("✅ Verified", String.valueOf(verified), new Color(34,197,94)));
        cards.add(createCard("📤 Uploaded", String.valueOf(uploaded), new Color(245,158,11)));
        cards.add(createCard("⚠ Fraud", String.valueOf(fraud), new Color(239,68,68)));
        cards.add(createCard("⛓ Blockchain", String.valueOf(blockchain), new Color(99,102,241)));
        cards.add(createCard("📈 Verification %", String.format("%.1f%%", verifyRate), new Color(16,185,129)));

        JPanel centerPanel = new JPanel(new BorderLayout(20,20));

        centerPanel.setOpaque(false);

        centerPanel.add(cards, BorderLayout.NORTH);
        centerPanel.add(chartPanel, BorderLayout.CENTER);

        main.add(centerPanel, BorderLayout.CENTER);

        add(main);

        setVisible(true);
    }

    private JPanel createCard(String title, String value, Color color){

        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5,0,0,0,color),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,20));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI",Font.BOLD,44));
        lblValue.setForeground(color);

        card.add(lblTitle,BorderLayout.NORTH);
        card.add(lblValue,BorderLayout.CENTER);

        return card;
    }
}