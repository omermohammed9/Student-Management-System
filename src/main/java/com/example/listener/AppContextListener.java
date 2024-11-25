package com.example.listener;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.DriverManager;
import java.sql.SQLException;


public class AppContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Deregister JDBC drivers to avoid memory leaks
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered driver: " + driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Optionally handle the abandoned connection cleanup thread
        try {
            Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
                .getMethod("checkedShutdown")
                .invoke(null);
        } catch (ReflectiveOperationException e) {
            System.err.println("AbandonedConnectionCleanupThread shutdown failed: " + e.getMessage());
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No initialization required here
    }
}
