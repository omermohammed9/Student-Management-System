package com.example.dao;

import com.example.model.Student;
import com.example.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Retrieve all students
	public List<Student> getAllStudents() {
	    List<Student> students = new ArrayList<>();
	    try (Connection conn = DBConnection.getConnection()) {
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM students");
	        while (rs.next()) {
	            Student student = new Student(
	                rs.getInt("student_id"), rs.getString("full_name"), rs.getString("phone_number"),
	                rs.getString("email"), rs.getString("gender"), rs.getDate("birthday"),
	                rs.getString("country"), rs.getString("city"), rs.getString("full_address"),
	                rs.getString("language"), rs.getString("description")
	            );
	            students.add(student);
	        }
	        // Print the total number of students retrieved, after the loop
	        System.out.println("Attempting to connect to database and retrieve students...");
	        System.out.println("Number of students retrieved: " + students.size());
	        for (Student stud : students) {
	            System.out.println(stud);
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return students;
	}

    // Save a new student
    public void saveStudent(Student student) {
        String sql = "INSERT INTO students (full_name, phone_number, email, gender, birthday, country, city, full_address, language, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getPhoneNumber());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getGender());
            pstmt.setDate(5, new java.sql.Date(student.getBirthday().getTime()));
            pstmt.setString(6, student.getCountry());
            pstmt.setString(7, student.getCity());
            pstmt.setString(8, student.getFullAddress());
            pstmt.setString(9, student.getLanguage());
            pstmt.setString(10, student.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing student
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET full_name = ?, phone_number = ?, email = ?, gender = ?, birthday = ?, country = ?, city = ?, full_address = ?, language = ?, description = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getPhoneNumber());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getGender());
            pstmt.setDate(5, new java.sql.Date(student.getBirthday().getTime()));
            pstmt.setString(6, student.getCountry());
            pstmt.setString(7, student.getCity());
            pstmt.setString(8, student.getFullAddress());
            pstmt.setString(9, student.getLanguage());
            pstmt.setString(10, student.getDescription());
            pstmt.setInt(11, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a student
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a single student by ID
    public Student getStudent(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"), rs.getString("full_name"), rs.getString("phone_number"),
                    rs.getString("email"), rs.getString("gender"), rs.getDate("birthday"),
                    rs.getString("country"), rs.getString("city"), rs.getString("full_address"),
                    rs.getString("language"), rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
