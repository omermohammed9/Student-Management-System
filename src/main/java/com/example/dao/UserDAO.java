package com.example.dao;

import com.example.model.User;
import com.example.util.DBConnection;
import com.example.util.HashPassword;

import java.sql.*;

public class UserDAO {
	
	
	public boolean createUser(String username, String email, String password, String role) {
	    String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";
	    String insertQuery = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection()) {

	        // Check if username or email already exists
	        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
	            checkStmt.setString(1, username);
	            checkStmt.setString(2, email);

	            ResultSet rs = checkStmt.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                System.out.println("Username or email already exists: " + username + ", " + email);
	                return false;
	            }
	        }

	        // Hash the password before inserting
	        String hashedPassword = HashPassword.hash(password);
	        System.out.println("Hashed password for user: " + hashedPassword);

	        // Insert the new user with the role
	        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
	            insertStmt.setString(1, username);
	            insertStmt.setString(2, email);
	            insertStmt.setString(3, hashedPassword);
	            insertStmt.setString(4, role);

	            int rowsAffected = insertStmt.executeUpdate();
	            System.out.println("User successfully created: " + username + ", Role: " + role);
	            return rowsAffected > 0;
	        }

	    } catch (SQLException e) {
	        System.err.println("Error while creating user: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}


	
	
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public String validateCredentials(String usernameOrEmail, String password) {
        String query = "SELECT role, password FROM users WHERE username = ? OR email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);

            System.out.println("Validating credentials for username or email: " + usernameOrEmail);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                String role = rs.getString("role");

                // Hash the provided password
                String hashedPassword = HashPassword.hash(password);
                System.out.println("Stored hash: " + storedHash);
                System.out.println("Generated hash: " + hashedPassword);

                // Validate the hashed password
                if (storedHash.equals(hashedPassword)) {
                    System.out.println("Validation successful. Role: " + role);
                    return role; // Return role if the password matches
                } else {
                    System.out.println("Validation failed: Password mismatch.");
                }
            } else {
                System.out.println("Validation failed: No matching username or email found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Return null if credentials are invalid
    }
}