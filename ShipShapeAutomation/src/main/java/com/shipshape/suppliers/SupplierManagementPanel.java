package com.shipshape.suppliers;

import com.mycompany.shipshapeautomation.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SupplierManagementPanel extends JPanel {
    private final JTextField supplierNameField;
    private final JTextField contactInfoField;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton removeButton;

    public SupplierManagementPanel() {
        supplierNameField = new JTextField(20);
        contactInfoField = new JTextField(20);
        addButton = new JButton("Add Supplier");
        updateButton = new JButton("Update Supplier");
        removeButton = new JButton("Remove Supplier");

        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        inputPanel.add(new JLabel("Supplier Name:"));
        inputPanel.add(supplierNameField);
        inputPanel.add(new JLabel("Contact Info:"));
        inputPanel.add(contactInfoField);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addSupplier());
        updateButton.addActionListener(e -> updateSupplier());
        removeButton.addActionListener(e -> removeSupplier());
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/shipshape";
        String user = "your-username";
        String password = "your-password";

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void addSupplier() {
        String sql = "INSERT INTO suppliers(name, contact_info) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplierNameField.getText());
            pstmt.setString(2, contactInfoField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSupplier() {
        String sql = "UPDATE suppliers SET contact_info = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contactInfoField.getText());
            pstmt.setString(2, supplierNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier updated successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSupplier() {
        String sql = "DELETE FROM suppliers WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplierNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier removed successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
