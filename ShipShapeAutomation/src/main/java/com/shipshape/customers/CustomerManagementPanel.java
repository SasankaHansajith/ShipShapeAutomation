package com.shipshape.customers;

import com.mycompany.shipshapeautomation.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CustomerManagementPanel extends JPanel {
    private final JTextField customerNameField;
    private final JTextArea orderDetailsArea;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton removeButton;

    public CustomerManagementPanel() {
        customerNameField = new JTextField(20);
        orderDetailsArea = new JTextArea(5, 20);
        addButton = new JButton("Add Order");
        updateButton = new JButton("Update Order");
        removeButton = new JButton("Remove Order");

        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(customerNameField);
        inputPanel.add(new JLabel("Order Details:"));
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(orderDetailsArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addOrder());
        updateButton.addActionListener(e -> updateOrder());
        removeButton.addActionListener(e -> removeOrder());
    }

    private void addOrder() {
        String sql = "INSERT INTO orders(customer_name, order_details) VALUES(?, ?)"; // there are no columns in the table as you've mentioned here

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerNameField.getText());
            pstmt.setString(2, orderDetailsArea.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Order added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOrder() {
        // Assuming there's a way to select an existing order, e.g., through a list or ID field
        String sql = "UPDATE orders SET order_details = ? WHERE customer_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderDetailsArea.getText());
            pstmt.setString(2, customerNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Order updated successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeOrder() {
        // Assuming there's a way to select an existing order, e.g., through a list or ID field
        String sql = "DELETE FROM orders WHERE customer_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Order removed successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
