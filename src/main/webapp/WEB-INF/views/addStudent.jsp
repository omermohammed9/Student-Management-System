<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
    <!-- Bootstrap 5.3.3 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">


    
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="form-container">
                <h2 class="form-title text-center">Add New Student</h2>
                <form action="${pageContext.request.contextPath}/students" method="post" class="needs-validation" novalidate>
                    <!-- Hidden field to control action in servlet -->
                    <input type="hidden" name="action" value="new">

                    <!-- Full Name -->
                    <div class="mb-3">
                        <label for="fullName" class="form-label">Full Name:</label>
                        <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Enter full name" required>
                        <div class="invalid-feedback">
                            Please provide the full name.
                        </div>
                    </div>

                    <!-- Phone Number -->
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone Number:</label>
                        <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="e.g. +1 234 567 890" pattern="^[0-9\s-]+$" required>
                        <div class="invalid-feedback">
                            Please provide a valid phone number.
                        </div>
                    </div>

                    <!-- Email -->
                    <div class="mb-3">
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="e.g. student@example.com" required>
                        <div class="invalid-feedback">
                            Please provide a valid email address.
                        </div>
                    </div>

                    <!-- Gender -->
                    <div class="mb-3">
                        <label class="form-label">Gender:</label>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="Male" checked>
                            <label class="form-check-label" for="male">Male</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender" id="female" value="Female">
                            <label class="form-check-label" for="female">Female</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender" id="other" value="Other">
                            <label class="form-check-label" for="other">Other</label>
                        </div>
                    </div>

                    <!-- Birthday -->
                    <div class="mb-3">
                        <label for="birthday" class="form-label">Birthday:</label>
                        <input type="date" class="form-control" id="birthday" name="birthday" required>
                        <div class="invalid-feedback">
                            Please select your date of birth.
                        </div>
                    </div>

                    <!-- Country -->
                    <div class="mb-3">
                        <label for="country" class="form-label">Country:</label>
                        <select class="form-select" id="country" name="country" required>
                            <option value="" selected disabled>Choose your country</option>
                            <option value="USA">USA</option>
                            <option value="Canada">Canada</option>
                            <option value="UK">UK</option>
                            <option value="Australia">Australia</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a country.
                        </div>
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
                        <textarea class="form-control" id="fullAddress" name="fullAddress" rows="3" placeholder="Enter full address" required></textarea>
                        <div class="invalid-feedback">
                            Please provide your address.
                        </div>
                    </div>

                    <!-- Language -->
                    <div class="mb-3">
                        <label for="language" class="form-label">Language:</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="languageEnglish" name="language[]" value="English">
                            <label class="form-check-label" for="languageEnglish">English</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="languageSpanish" name="language[]" value="Spanish">
                            <label class="form-check-label" for="languageSpanish">Spanish</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="languageFrench" name="language[]" value="French">
                            <label class="form-check-label" for="languageFrench">French</label>
                        </div>
                    </div>

                    <!-- Description -->
                    <div class="mb-3">
                        <label for="description" class="form-label">Description:</label>
                        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Additional details..."></textarea>
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary w-100">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap 5.3.3 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Form Validation Script -->
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
