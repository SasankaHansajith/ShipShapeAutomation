package com.shipshape.reports;

import com.mycompany.shipshapeautomation.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;

public class SalesReportPanel extends JPanel {
    private final JButton generateButton;
    private final JTextArea reportArea;

    public SalesReportPanel() {
        generateButton = new JButton("Generate Report");
        reportArea = new JTextArea(10, 30);

        setLayout(new BorderLayout());
        add(generateButton, BorderLayout.NORTH);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        generateButton.addActionListener(e -> generateReport());
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:shipshape.db"; // Adjust the URL if using a different DBMS
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder();
        String sql = "SELECT strftime('%Y-%m', order_date) AS month, SUM(total_sales) AS total_sales " +
                     "FROM sales_reports GROUP BY month ORDER BY month";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            report.append("Monthly Sales Report\n");
            report.append("====================\n");
            while (rs.next()) {
                String month = rs.getString("month");
                double totalSales = rs.getDouble("total_sales");
                report.append("Month: ").append(month).append(" | Total Sales: $").append(totalSales).append("\n");
            }

            reportArea.setText(report.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
