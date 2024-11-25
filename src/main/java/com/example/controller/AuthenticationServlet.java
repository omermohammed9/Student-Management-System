package com.example.controller;

import com.example.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/auth", "/logout"})
public class AuthenticationServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO(); // DAO to interact with the database
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/auth".equals(action)) {
            authenticate(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/logout".equals(action)) {
            logout(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    /**
     * Authenticate the user by validating credentials
     */
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        // Input validation
        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username and password cannot be empty!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        System.out.println("Received Username: " + username);

        // Validate user credentials
        if (userDAO.validateCredentials(username, password) != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username); // Store username in session

            System.out.println("Login successful for user: " + username);
            response.sendRedirect(request.getContextPath() + "/students?action=list"); // Redirect to dashboard
        } else {
            System.out.println("Login failed for user: " + username);
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * Handle logout functionality
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // Get session without creating a new one
        if (session != null) {
            session.invalidate(); // Invalidate session
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp"); // Redirect to login page
    }
}














//@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/auth"})
//public class AuthenticationServlet extends HttpServlet {
//    private UserDAO userDAO;
//
//    @Override
//    public void init() {
//        userDAO = new UserDAO();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
//
//        switch (action) {
//            case "/logout":
//                logout(request, response);
//                break;
//            default:
//                request.getRequestDispatcher("/login.jsp").forward(request, response);
//                break;
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
//
//        switch (action) {
//            case "/login":
//                authenticate(request, response);
//                break;
//            default:
//                response.sendRedirect("/login.jsp");
//                break;
//        }
//    }
//
//    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String username = request.getParameter("username").trim();
//        String password = request.getParameter("password").trim();
//
//        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
//            request.setAttribute("NOTIFICATION", "Username and password cannot be empty!");
//            request.getRequestDispatcher("/login.jsp").forward(request, response);
//            return;
//        }
//
//        System.out.println("Received Username: " + username);
//
//        boolean isValid = userDAO.validateCredentials(username, password);
//
//        if (isValid) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username); // Store username in session
//            System.out.println("Login successful for user: " + username);
//            response.sendRedirect("students?action=list"); // Redirect to the dashboard
//        } else {
//            System.out.println("Login failed for user: " + username);
//            request.setAttribute("errorMessage", "Invalid username or password.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//            dispatcher.forward(request, response);
//        }
//
//    }
//
//    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        response.sendRedirect("/login.jsp");
//    }
//}
