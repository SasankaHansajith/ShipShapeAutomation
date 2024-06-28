
package com.mycompany.shipshapeautomation;

import com.shipshape.customers.CustomerManagementPanel;
import com.shipshape.employees.EmployeeManagementPanel;
import com.shipshape.inventory.InventoryManagementPanel;
import com.shipshape.jobs.JobAllocationPanel;
import com.shipshape.reports.SalesReportPanel;
import com.shipshape.suppliers.SupplierManagementPanel;

import javax.swing.*;
import java.awt.*;

public class ShipShapeApp extends JFrame {
    public ShipShapeApp() {
        setTitle("ShipShape Automation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Customers", new CustomerManagementPanel());
        tabbedPane.addTab("Suppliers", new SupplierManagementPanel());
        tabbedPane.addTab("Inventory", new InventoryManagementPanel());
        tabbedPane.addTab("Employees", new EmployeeManagementPanel());
        tabbedPane.addTab("Jobs", new JobAllocationPanel());
        tabbedPane.addTab("Sales Reports", new SalesReportPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShipShapeApp().setVisible(true);
        });
    }
}

