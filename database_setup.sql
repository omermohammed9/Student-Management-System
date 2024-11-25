-- Create database
CREATE DATABASE IF NOT EXISTS student_management;

-- Use the created database
USE student_management;

-- Create `users` table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'User') NOT NULL
);

-- Create `students` table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15),
    email VARCHAR(100),
    gender ENUM('Male', 'Female', 'Other'),
    birthday DATE,
    country VARCHAR(50),
    city VARCHAR(50),
    fullAddress TEXT,
    language VARCHAR(100),
    description TEXT
);