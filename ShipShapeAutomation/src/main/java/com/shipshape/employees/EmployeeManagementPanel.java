
package com.shipshape.employees;

import com.mycompany.shipshapeautomation.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeManagementPanel extends JPanel {
    private final JTextField employeeNameField;
    private final JTextField roleField;
    private final JTextField scheduleField;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton removeButton;

    public EmployeeManagementPanel() {
        employeeNameField = new JTextField(20);
        roleField = new JTextField(20);
        scheduleField = new JTextField(20);
        addButton = new JButton("Add Employee");
        updateButton = new JButton("Update Employee");
        removeButton = new JButton("Remove Employee");

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Employee Name:"));
        add(employeeNameField);
        add(new JLabel("Role:"));
        add(roleField);
        add(new JLabel("Schedule:"));
        add(scheduleField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        add(buttonPanel);

        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        removeButton.addActionListener(e -> removeEmployee());
    }

    private void addEmployee() {
        String sql = "INSERT INTO employees(name, role, schedule) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employeeNameField.getText());
            pstmt.setString(2, roleField.getText());
            pstmt.setString(3, scheduleField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee() {
        // Assuming there's a way to select an existing employee, e.g., through an ID field or selection list
        String sql = "UPDATE employees SET role = ?, schedule = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roleField.getText());
            pstmt.setString(2, scheduleField.getText());
            pstmt.setString(3, employeeNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeEmployee() {
        // Assuming there's a way to select an existing employee, e.g., through an ID field or selection list
        String sql = "DELETE FROM employees WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employeeNameField.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee removed successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

