# Student Management System

## Overview
The **Student Management System** is a web-based application developed using Java Servlets and JSP. It allows users to manage student records by adding, viewing, editing, and deleting entries. The application also includes role-based authentication for **Admin** and **User** roles.

---

## Features
1. **Authentication:**
   - Users can log in with their username/email and password.
   - Passwords are securely hashed before storage and validation.
   - Role-based access:
     - **Admin** can view all student records, add, edit, and delete records.
     - **User** can view the student records but cannot modify them.

2. **Student Management:**
   - **Add Student:**
     - Input fields include full name, phone number, email, gender, birthday, country, city, full address, languages, and description.
   - **View Students:**
     - A table displays all student records with filter options based on roles.
   - **Edit Student:**
     - Admin can modify existing records.
   - **Delete Student:**
     - Admin can delete student records after confirmation.

3. **Validation:**
   - Client-side validation using HTML5 and JavaScript.
   - Server-side validation ensures data integrity.

4. **Technologies Used:**
   - **Backend:** Java Servlets
   - **Frontend:** JSP, Bootstrap 5
   - **Database:** MySQL
   - **Utilities:** Password hashing, JDBC for database connectivity

---




## Prerequisites
1. **JDK 11 or higher**
2. **Apache Tomcat 10.x**
3. **MySQL Server**
4. **IDE:** Eclipse/IntelliJ IDEA
5. **Maven (Optional)** for dependency management.

---

## Setup and Configuration
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-repo/StudentManagement.git
   cd StudentManagement



## Project Structure
```
src/
 ├── main/
 │   ├── java/
 │   │   ├── com.example.controller/   # Servlet controllers
 │   │   ├── com.example.dao/          # Data Access Objects (DAO)
 │   │   ├── com.example.listener/     # App lifecycle listeners
 │   │   ├── com.example.model/        # Java classes for Student/User
 │   │   └── com.example.util/         # Utility classes (DB connection, password hashing)
 │   └── webapp/
 │       ├── css/                      # Custom CSS files
 │       ├── js/                       # Custom JavaScript files
 │       ├── WEB-INF/
 │       │   ├── lib/                  # Libraries (JARs)
 │       │   ├── views/                # JSP views (listStudents.jsp, addStudent.jsp, etc.)
 │       ├── login.jsp                 # Login page
 │       └── register.jsp              # Registration page
 ├── test/                             # Test files (if applicable)
 └── build/                            # Compiled classes
```

---

## Prerequisites
1. **JDK 11 or higher**
2. **Apache Tomcat 10.x**
3. **MySQL Server**
4. **IDE:** Eclipse/IntelliJ IDEA
5. **Maven (Optional)** for dependency management.

---

## Setup and Configuration
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-repo/StudentManagement.git
   cd StudentManagement
   ```

2. **Database Setup:**
   - Create a database in MySQL:
     ```sql
     CREATE DATABASE student_management;
     ```
   - Import the provided SQL file:
     ```bash
     mysql -u root -p student_management < student_management.sql
     ```

3. **Configure Database Connection:**
   - Update the `DBConnection.java` file under `com.example.util`:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/student_management";
     private static final String USER = "your-mysql-username";
     private static final String PASSWORD = "your-mysql-password";
     ```

4. **Build and Deploy:**
   - Open the project in your IDE and clean/build it.
   - Deploy the `StudentManagement` project to Apache Tomcat.

5. **Start the Server:**
   - Start Apache Tomcat.
   - Access the application at:
     ```
     http://localhost:8081/StudentManagement/login.jsp
     http://localhost:8081/StudentManagement/register.jsp
     ```

---

## Usage
1. **Admin Credentials:**
   - **Username:** admin
   - **Password:** admin123

2. **User Credentials:**
   - **Username:** user
   - **Password:** user123

3. **Features:**
   - Log in as Admin to access all functionalities.
   - Log in as User to view student records only.

---

## Testing
1. Log in using Admin credentials:
   - Add, edit, delete, and view student records.
2. Log in using User credentials:
   - Only view student records.
3. Validate error handling for invalid login credentials.

---

## Troubleshooting
1. **404 Not Found Error:**
   - Ensure all JSP files are correctly placed under `WEB-INF/views`.
   - Verify the mappings in `web.xml`.

2. **Database Connection Issues:**
   - Confirm that MySQL is running.
   - Verify the credentials in `DBConnection.java`.

3. **Password Validation Fails:**
   - Ensure passwords are hashed consistently during registration and login.

---
