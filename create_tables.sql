    -- This script creates the three tables for our Student Management System.

-- Table 1: students
CREATE TABLE students (
    usn VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    email VARCHAR(100)
);

-- Table 2: courses
CREATE TABLE courses (
    course_code VARCHAR(20) PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT,
    course_type VARCHAR(50),
    department VARCHAR(100)
);

-- Table 3: enrollments
CREATE TABLE enrollments (
    enrollment_id SERIAL PRIMARY KEY,
    student_usn VARCHAR(20) REFERENCES students(usn),
    course_code VARCHAR(20) REFERENCES courses(course_code),
    marks INT,
    grade VARCHAR(2),
    grade_point INT
);

-- Let's add one sample student to test with.
INSERT INTO students (usn, name, department, email) 
VALUES ('1BM24CS001', 'Your Name', 'Computer Science', 'your.name@bmsce.ac.in');