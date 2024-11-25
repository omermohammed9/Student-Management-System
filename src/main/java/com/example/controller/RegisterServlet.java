package com.example.controller;


import com.example.dao.UserDAO;
import com.example.util.HashPassword;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        System.out.println("RegisterServlet initialized.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  // Safely retrieve form parameters
        String username = request.getParameter("username") != null ? request.getParameter("username").trim() : "";
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
        String password = request.getParameter("password") != null ? request.getParameter("password").trim() : "";
        String role = request.getParameter("role") != null ? request.getParameter("role").trim() : "";
        
        
        // Print the captured data to check if the form is capturing the values
        System.out.println("Captured Username: " + username);
        System.out.println("Captured Email: " + email);
        System.out.println("Captured Password: " + password);
        System.out.println("Captured Password: " + role);



        // Validate input
        if (username == null || email == null || password == null || role == null || username.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

     // Hash the password before saving it
        String hashedPassword = HashPassword.hash(password);
        System.out.println("Hashed Password during registration: " + hashedPassword);


        // Save user to the database
        boolean isCreated = userDAO.createUser(username, email, hashedPassword, role);

        if (isCreated) {
            request.setAttribute("message", "User registered successfully! You can now log in.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Registration failed. Username or email already exists.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);
        }
    }
}




