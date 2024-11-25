<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student</title>
    <!-- Bootstrap 5.3.3 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editFormStyle.css">

</head>
<body>
<div class="container mt-5">
    <div class="form-container">
        <h2 class="form-title">Edit Student</h2>
        <form action="${pageContext.request.contextPath}/students" method="post" class="needs-validation" novalidate>
            <!-- Hidden fields for action and student ID -->
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" value="${student.id}">

            <!-- Full Name -->
            <div class="mb-3">
                <label for="fullName" class="form-label">Full Name:</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="${student.fullName}" required>
                <div class="invalid-feedback">Please provide the full name.</div>
            </div>

            <!-- Phone Number -->
            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Phone Number:</label>
                <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${student.phoneNumber}" pattern="^[0-9\s-]+$" required>
                <div class="invalid-feedback">Please provide a valid phone number.</div>
            </div>

            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${student.email}" required>
                <div class="invalid-feedback">Please provide a valid email address.</div>
            </div>

            <!-- Gender -->
            <div class="mb-3">
                <label class="form-label">Gender:</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="gender" id="male" value="Male" 
                           <c:if test="${student.gender == 'Male'}">checked</c:if>>
                    <label class="form-check-label" for="male">Male</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="gender" id="female" value="Female" 
                           <c:if test="${student.gender == 'Female'}">checked</c:if>>
                    <label class="form-check-label" for="female">Female</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="gender" id="other" value="Other" 
                           <c:if test="${student.gender == 'Other'}">checked</c:if>>
                    <label class="form-check-label" for="other">Other</label>
                </div>
            </div>

            <!-- Birthday -->
            <div class="mb-3">
                <label for="birthday" class="form-label">Birthday:</label>
                <input type="date" class="form-control" id="birthday" name="birthday" value="${student.birthday}" required>
                <div class="invalid-feedback">Please select a valid birthday.</div>
            </div>

            <!-- Country -->
            <div class="mb-3">
                <label for="country" class="form-label">Country:</label>
                <input type="text" class="form-control" id="country" name="country" value="${student.country}">
            </div>

                 <!-- City -->
                    <div class="mb-3">
                        <label for="city" class="form-label">City:</label>
                        <select class="form-select" id="city" name="city" required>
                            <option value="" disabled selected>Select your city</option>
                            <option value="New York">New York</option>
                            <option value="Los Angeles">Los Angeles</option>
                            <option value="Chicago">Chicago</option>
                            <option value="Houston">Houston</option>
                            <option value="Phoenix">Phoenix</option>
                            <option value="San Francisco">San Francisco</option>
                            <option value="Seattle">Seattle</option>
                            <option value="Boston">Boston</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select your city.
                        </div>
                    </div>


            <!-- Full Address -->
            <div class="mb-3">
                <label for="fullAddress" class="form-label">Full Address:</label>
                <textarea class="form-control" id="fullAddress" name="fullAddress" rows="3">${student.fullAddress}</textarea>
            </div>

            <!-- Language -->
            <div class="mb-3">
                <label for="language" class="form-label">Language:</label>
                <input type="text" class="form-control" id="language" name="language" value="${student.language}">
            </div>

            <!-- Description -->
            <div class="mb-3">
                <label for="description" class="form-label">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="3">${student.description}</textarea>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">Update Student</button>
        </form>
    </div>
</div>

<!-- Bootstrap 5.3.3 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Form Validation
    (() => {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>
</body>
</html>
