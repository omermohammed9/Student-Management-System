package com.example.controller;


import com.example.dao.UserDAO;
import com.example.util.HashPassword;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO(); // DAO to interact with the database
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username and password cannot be empty!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Hash the input password
        String hashedPassword = HashPassword.hash(password);
        System.out.println("Hashed Password during login: " + hashedPassword);

        // Validate credentials and retrieve role
        String role = userDAO.validateCredentials(username, hashedPassword);

        if (role != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            session.setMaxInactiveInterval(30 * 60);

            // Redirect based on role
            if ("Admin".equalsIgnoreCase(role)) {
            	response.sendRedirect(request.getContextPath() + "/students?action=list");

            } else {
            	response.sendRedirect(request.getContextPath() + "/students?action=list");

            }
        } else {
            System.out.println("Validation failed for username: " + username);
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

}


