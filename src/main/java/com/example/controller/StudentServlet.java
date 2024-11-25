package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = {"/students"})
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ensure session validation before proceeding
        HttpSession session = request.getSession(false); // Avoid creating a new session
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return; // Stop further execution if the user is not authenticated
        }

        // Continue with existing functionality
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            case "list":
            default:
                listStudents(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                addStudent(request, response);
                break;
            case "edit":
			try {
				updateStudent(request, response);
			} catch (IOException | ServletException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "list":
            default:
                listStudents(request, response);
                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/addStudent.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.getStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editStudent.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> listStudents = studentDAO.getAllStudents();
        try {            
        	System.out.println("Entering the listStudents method.");
        	System.out.println("Number of students retrieved: " + listStudents.size());
        	System.out.println("Exiting the listStudents method.");
        	
        	System.out.println("Number of students retrieved: " + listStudents.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("listStudents", listStudents);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/listStudents.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String gender = request.getParameter("gender");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = formatter.parse(request.getParameter("birthday"));
            java.sql.Date birthday = new java.sql.Date(date.getTime());
            String country = request.getParameter("country");
            String city = request.getParameter("city");
            String fullAddress = request.getParameter("fullAddress");
            String language = request.getParameter("language");
            String description = request.getParameter("description");

            Student newStudent = new Student(0, fullName, phoneNumber, email, gender, birthday, country, city, fullAddress, language, description);
            System.out.println("Attempting to save new student: " + newStudent);
            studentDAO.saveStudent(newStudent);
            System.out.println("Student saved successfully");

            //studentDAO.saveStudent(newStudent);
            
            response.sendRedirect("students?action=list");
        } catch (Exception e) {
            throw new ServletException("Error adding student", e);
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = formatter.parse(request.getParameter("birthday"));
        java.sql.Date birthday = new java.sql.Date(date.getTime());
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String fullAddress = request.getParameter("fullAddress");
        String language = request.getParameter("language");
        String description = request.getParameter("description");

        Student student = new Student(id, fullName, phoneNumber, email, gender, birthday, country, city, fullAddress, language, description);
        studentDAO.updateStudent(student);
        response.sendRedirect("students?action=list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students?action=list");
    }
}
