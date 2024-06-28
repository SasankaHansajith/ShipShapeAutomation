package com.shipshape.jobs;

import com.mycompany.shipshapeautomation.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobAllocationPanel extends JPanel {
    private final JComboBox<String> employeeComboBox;
    private final JComboBox<String> jobComboBox;
    private final JButton allocateButton;

    public JobAllocationPanel() {
        employeeComboBox = new JComboBox<>();
        jobComboBox = new JComboBox<>();
        allocateButton = new JButton("Allocate Job");

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        topPanel.add(new JLabel("Employee:"));
        topPanel.add(employeeComboBox);
        topPanel.add(new JLabel("Job:"));
        topPanel.add(jobComboBox);
        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(allocateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load employees and jobs into the combo boxes
        loadEmployees();
        loadJobs();

        allocateButton.addActionListener(e -> allocateJob());
    }

    private void loadEmployees() {
        String sql = "SELECT id, name FROM employees";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String employee = rs.getInt("id") + " - " + rs.getString("name");
                employeeComboBox.addItem(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadJobs() {
        String sql = "SELECT id, description FROM jobs";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String job = rs.getInt("id") + " - " + rs.getString("description");
                jobComboBox.addItem(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void allocateJob() {
        String selectedEmployee = (String) employeeComboBox.getSelectedItem();
        String selectedJob = (String) jobComboBox.getSelectedItem();

        if (selectedEmployee == null || selectedJob == null) {
            JOptionPane.showMessageDialog(this, "Please select both an employee and a job.");
            return;
        }

        int employeeId = Integer.parseInt(selectedEmployee.split(" - ")[0]);
        int jobId = Integer.parseInt(selectedJob.split(" - ")[0]);

        String sql = "INSERT INTO job_assignments(job_id, employee_id) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jobId);
            pstmt.setInt(2, employeeId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Job allocated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
