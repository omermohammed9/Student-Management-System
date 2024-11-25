package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static {
        try {
            // This explicitly loads the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/studentmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "omar";

    public static Connection getConnection() throws SQLException {
    	System.out.println();
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
