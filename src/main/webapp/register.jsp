<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <!-- Bootstrap 5.3.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        .btn-primary {
            width: 100%;
        }
        .form-title {
            font-weight: bold;
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2 class="form-title">Register</h2>
        <form action="/StudentManagement/registerAction"  method="post" class="needs-validation" novalidate>
            <!-- Username -->
            <div class="mb-3">
                <label for="username" class="form-label">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required>
                <div class="invalid-feedback">Please enter a username.</div>
            </div>
            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
                <div class="invalid-feedback">Please enter a valid email.</div>
            </div>
            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <div class="invalid-feedback">Please enter a password.</div>
            </div>
            <!-- Role Selection -->
   <div class="mb-3">
        <label for="role" class="form-label">Role:</label>
        <select class="form-select" id="role" name="role" required>
            <option value="" selected disabled>Choose a role</option>
            <option value="Admin">Admin</option>
            <option value="User">User</option>
        </select>
        <div class="invalid-feedback">Please select a role.</div>
    </div>    
            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Form Validation -->
    <script>
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
