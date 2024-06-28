package com.mycompany.shipshapeautomation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:4000/shipshape_a"; // my port is 4000 and db name is shipshape_a. just change them back to yours.
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Update this line with the actual password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

