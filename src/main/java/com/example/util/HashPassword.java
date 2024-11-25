package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    /**
     * Hashes a password using SHA-256.
     *
     * @param password The plain text password to hash.
     * @return The hashed password as a hexadecimal string.
     */
    public static String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
