

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%
    // Ensure the session exists and the user is logged in
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Students</title>
    <!-- Bootstrap 5.3.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4 text-center">List of Students</h2>

    <!-- Add New Student Button -->
    <div class="d-flex justify-content-end mb-3">
        <a href="${pageContext.request.contextPath}/students?action=new" class="btn btn-success">Add New Student</a>
    </div>

    <!-- Table to display students -->
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Gender</th>
                    <th>Birthday</th>
                    <th>Country</th>
                    <th>City</th>
                    <th>Address</th>
                    <th>Language</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty listStudents}">
                        <c:forEach var="student" items="${listStudents}">
                            <tr>
                                <td><c:out value="${student.fullName}"/></td>
                                <td><c:out value="${student.email}"/></td>
                                <td><c:out value="${student.phoneNumber}"/></td>
                                <td><c:out value="${student.gender}"/></td>
                                <td><fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${student.country}"/></td>
                                <td><c:out value="${student.city}"/></td>
                                <td><c:out value="${student.fullAddress}"/></td>
                                <td><c:out value="${student.language}"/></td>
                                <td><c:out value="${student.description}"/></td>
                                <td class="text-center">
                                    <a href="students?action=edit&id=${student.id}" class="btn btn-outline-primary btn-sm me-2">Edit</a>
                                    <a href="students?action=delete&id=${student.id}" class="btn btn-outline-danger btn-sm" 
                                       onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="11" class="text-center">No students found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <!-- Logout Button -->
    <div class="text-end mt-3">
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>










