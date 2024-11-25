<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap 5.3.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
        }
        .form-title {
            font-weight: bold;
            color: #2575fc;
            text-align: center;
            margin-bottom: 20px;
        }
        .btn-primary {
            background: #2575fc;
            border: none;
            transition: background 0.3s ease;
        }
        .btn-primary:hover {
            background: #1a5bb5;
        }
        .form-label {
            font-weight: 500;
        }
        .alert {
            font-size: 0.9rem;
        }
        .forgot-password {
            display: block;
            text-align: right;
            margin-top: 10px;
            font-size: 0.85rem;
        }
        .forgot-password a {
            color: #2575fc;
            text-decoration: none;
        }
        .forgot-password a:hover {
            text-decoration: underline;
        }
        .footer {
            text-align: center;
            margin-top: 15px;
            font-size: 0.85rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2 class="form-title">Login</h2>
        <form action="${pageContext.request.contextPath}/login" method="post" class="needs-validation" novalidate>
            <!-- Username or Email -->
            <div class="mb-3">
                <label for="username" class="form-label">Username/Email:</label>
                <input type="text" class="form-control" id="username" name="username" required>
                <div class="invalid-feedback">Please enter your username or email.</div>
            </div>
            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <div class="invalid-feedback">Please enter your password.</div>
            </div>
            <!-- Login Button -->
            <button type="submit" class="btn btn-primary w-100">Login</button>
            <!-- Error Message -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3">${errorMessage}</div>
            </c:if>
        </form>
        <!-- Footer -->
        <div class="footer">
            © 2024 Student Management System
        </div>
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
