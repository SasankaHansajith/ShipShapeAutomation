package com.shipshape.inventory;

import com.mycompany.shipshapeautomation.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class InventoryManagementPanel extends JPanel {
    private final JTextField partNameField;
    private final JTextField stockField;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton removeButton;

    public InventoryManagementPanel() {
        partNameField = new JTextField(20);
        stockField = new JTextField(5);
        addButton = new JButton("Add Part");
        updateButton = new JButton("Update Part");
        removeButton = new JButton("Remove Part");

        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Part Name:"));
        inputPanel.add(partNameField);
        inputPanel.add(new JLabel("Stock:"));
        inputPanel.add(stockField);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addPart());
        updateButton.addActionListener(e -> updatePart());
        removeButton.addActionListener(e -> removePart());
    }

    private void addPart() {
        String sql = "INSERT INTO inventory(part_name, stock) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, partNameField.getText());
            pstmt.setInt(2, Integer.parseInt(stockField.getText()));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Part added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePart() {
        // Assuming the part name is unique and used as a key for updating
        String sql = "UPDATE inventory SET stock = ? WHERE part_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(stockField.getText()));
            pstmt.setString(2, partNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Part updated successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removePart() {
        // Assuming the part name is unique and used as a key for removing
        String sql = "DELETE FROM inventory WHERE part_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, partNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Part removed successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
