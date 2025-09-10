package com.pro1.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class DatabaseManager {
    
    private ArrayList<Student> students  =new ArrayList<>();
    private ArrayList<Course> courses  =new ArrayList<>();
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    // This will hold our live database connection.
    private Connection connection;

    //Constructor
    //thsi will run automatically when you create a database manager obj
    public DatabaseManager(){

        connectToDatabase(); // Connect first!
        preloadCourses();    // Then use the connection.

    }

    //connectToDatabase method
    // This method establishes a connection to the database using JDBC.
    private void connectToDatabase(){
        System.out.println("LOG: Attempting to connect to the database...");

        // Database URL, username, and password
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password  ="1344";

        try{
            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("LOG: Database connection established successfully.");
        } catch (SQLException e) {
            System.err.println("ERROR: Unable to connect to the database.");
            e.printStackTrace();
        }
    }

    //preload method
    // This method gets the course catalog from our dedicated data class
    private void preloadCourses(){
        System.out.println("LOG: Loading course catalog from CourseData Class... ");
        this.courses  = CourseData.getInitialCourses();
        System.out.println("Successfully loaded" + this.courses.size() + "courses.");

        syncCoursesWithDB();
    }

    
    // This method takes the courses from the ArrayList and saves them to the database.
    private void syncCoursesWithDB() {
        System.out.println("LOG: Syncing courses with the database...");
        String sql = "INSERT INTO courses (course_code, course_name, credits, course_type, department) VALUES (?, ?, ?, ?, ?) ON CONFLICT (course_code) DO NOTHING;";
        // "ON CONFLICT...DO NOTHING" is a handy PostgreSQL command that prevents errors if we try to insert a course that's already there.

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Course course : this.courses) {
                pstmt.setString(1, course.getCourseCode());
                pstmt.setString(2, course.getCourseName());
                pstmt.setInt(3, course.getCredits());
                pstmt.setString(4, course.getCourseType());
                pstmt.setString(5, course.getDepartment());
                pstmt.executeUpdate(); // Execute the insert for each course
            }
            System.out.println("LOG: Course sync complete.");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not sync courses with database.");
            e.printStackTrace();
        }
    }



    // --- Core Logic Methods (Your To-Do List for next week) ---
    public void addStudent(Student student) {
        // The SQL command template with placeholders for the values
        String sql = "INSERT INTO students (usn , name  ,department , email) VALUES (?, ?, ?, ?)";  //there's one critical concept. We will not just send a plain text SQL command to the database. We will use a special, more secure method called a PreparedStatement. 
                                                                                                    //What it is: Instead of building a string like "INSERT INTO students... VALUES ('Harsh', ...)",
                                                                                                    // we send a template with placeholders (?): "INSERT INTO students... VALUES (?, ...)".
                                                                                                    // We then safely fill in those placeholders. Why we use it: This is a huge security feature.
                                                                                                    // It completely prevents a common and dangerous type of cyberattack called SQL Injection. Using PreparedStatement is the professional standard.
        try (PreparedStatement pstm = connection.prepareStatement(sql)){
            // Safely set the values for the placeholders
            // The first number is the placeholder index (1-based)
            pstm.setString(1, student.getUsn());
            pstm.setString(2, student.getName());
            pstm.setString(3, student.getDepartment());
            pstm.setString(4, student.getEmail());

            // Execute the command to insert the data into the database
            int rowsAffected = pstm.executeUpdate();

            if(rowsAffected > 0){
                System.out.println("LOG: Student " + student.getName() + " added successfully to the database.");
            }
        } catch (SQLException e){
            System.out.println("ERROR: Unable to add student " + student.getName() + "to the database.");
            e.printStackTrace();
        }

        System.out.println("LOG: addStudent method called for " + student.getName());
    }
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (course_code, course_name, credits, course_type , department) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getCourseName());
            pstmt.setInt(3, course.getCredits());
            pstmt.setString(4, course.getCourseType());
            pstmt.setString(5, course.getDepartment());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("LOG: Course " + course.getCourseName() + " added successfully to the database.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to add course " + course.getCourseName() + " to the database.");
            e.printStackTrace();
        }
        System.out.println("LOG: addCourse method called for " + course.getCourseName());
    }
    public void enrollStudent(String studentUsn, String courseCode) {
        //We will add logic later to check if student and course exist first
        String sql = "INSERT INTO enrollments (student_usn, course_code) VALUES (?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, studentUsn);
            pstmt.setString(2, courseCode);

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("LOG: Student " + studentUsn + " enrolled in course " + courseCode + " successfully.");
            }
        } catch (SQLException e){
            System.out.println("ERROR: Unable to enroll student " + studentUsn + " in course " + courseCode + ".");
            e.printStackTrace();
        }
        System.out.println("LOG: enrollStudent method called for " + studentUsn);
    }
    public void updateMarks(String studentUsn, String courseCode, int marks) {
        try{
            //Use our GradeCalculator to get the grade and grade point first
            String[] gradeInfo = GradeCalculator.calculateGrade(marks);
            String grade  =gradeInfo[0];
            int gradePoint = Integer.parseInt(gradeInfo[1]);

            //Write the SQL UPDATE command with placeholders
            //The WHERE clause is crucial to update only the correct record
            String sql = "UPDATE enrollments SET marks = ? , grade = ? , grade_point = ? WHERE student_usn = ? AND course_code = ?";

            //Create and execute the PreparedStatement
            try(PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.setInt(1, marks);
                pstmt.setString(2, grade);
                pstmt.setInt(3, gradePoint);
                pstmt.setString(4, studentUsn);
                pstmt.setString(5, courseCode);

                int rowsAffected = pstmt.executeUpdate();

                if(rowsAffected>0){
                    System.out.println("LOG: Updated mark for student " + studentUsn + " in course " + courseCode + " to " + marks);
                }else{
                    System.out.println("WARNING: No enrollment record found for student " + studentUsn + " in course " + courseCode + ". No update performed.");
                }
            }
        }catch (IllegalArgumentException | SQLException e){
            //Catches errors from the GradeCalculator (e.g., marks > 100) or SQL errors
            System.out.println("ERROR: Unable to update marks for student " + studentUsn + " in course " + courseCode + ".");
            e.printStackTrace();
        }
        System.out.println("LOG: updateMarks method called for " + studentUsn);
    }

    public void viewStudentReportCard(String studentUsn){
        if (connection == null) return;

        //This SQL query joins enrollments and courses to get all the details we need
        String sql = """
                        SELECT
                            c.course_code,
                            c.course_name,
                            c.credits,
                            e.marks,
                            e.grade,
                            e.grade_point
                        FROM enrollments e
                        JOIN courses c ON e.course_code = c.course_code
                        WHERE e.student_usn = ?
                        """;
        System.out.println("\n-----------------------------------------");
        System.out.println("REPORT CARD FOR: " + studentUsn);
        System.out.println("-----------------------------------------");

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, studentUsn);
            //executeQuery() for SELECT statements, which returns a ResultSet
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            //loop through the result set row by row
            while (rs.next()){
                found = true;
                //Extract the data from the current row
                String courseCode = rs.getString("course_code");
                String courseName = rs.getString("course_name");
                int credits = rs.getInt("credits");
                int marks = rs.getInt("marks");
                String grade = rs.getString("grade");
                int gradePoint = rs.getInt("grade_point");

                // Print the formatted report line
                System.out.println("COURSE: " + courseName);
                System.out.println("CREDITS: " + credits + " | MARKS: " + marks + " | GRADE: " + grade + " | GP: " + gradePoint);
                System.out.println("---");
            }

            if (found) {
            double sgpa = calculateSgpa(studentUsn);
            System.out.printf("FINAL RESULT: SGPA = %.2f\n", sgpa); //Prints the SGPA formatted to 2 decimal places
            }
            System.out.println("-----------------------------------------");
        }catch (SQLException e){
            System.out.println("ERROR: Unable to retrieve report card for student " + studentUsn + ".");
            e.printStackTrace();
        }
    }

    public void viewCourseRoster(String courseCode){
        if(connection == null){
            System.out.println("ERROR: No database connection.");
            return;
        }

        //this sql query joins enrollments and students to get all the details we need
        String sql = """
                SELECT
                    s.usn,
                    s.name,
                    s.email
                FROM enrollments e
                JOIN  students s ON e.student_usn = s.usn
                WHERE e.course_code = ?
                """;

        System.out.println("\n-----------------------------------------");
        System.out.println("COURSE ROSTER FOR : " + courseCode);
        System.out.println("-----------------------------------------");

        try(PreparedStatement pstmt =connection.prepareStatement(sql)){
            pstmt.setString(1, courseCode);

            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()){
                found = true;
                String usn = rs.getString("usn");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("USN: " + usn + " | NAME: " + name + " | EMAIL: " + email);

            }

            if(!found){
                System.out.println("No students enrolled in this course yet.");
            }

            System.out.println("-----------------------------------------");

        }catch (SQLException e){
            System.out.println("ERROR: Unable to retrieve course roster for course " + courseCode + ".");
            e.printStackTrace();
        }
    }


    public double calculateSgpa (String studentUsn){
        //this query gets all the necessary details for the SGPA calculation.
        String sql = """
            SELECT
                c.credits,
                e.grade_point
            FROM enrollments e
            JOIN courses c ON e.course_code = c.course_code
            WHERE e.student_usn = ? AND e.marks >= 0
            """;
            //"e.marks >= 0" ensures we only include courses where marks have been entered

            double totalCreditPoints = 0;
            int totalCredits=0;

            try(PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.setString(1, studentUsn);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    int credits = rs.getInt("credits");
                    int gradePoint = rs.getInt( "grade_point");

                    totalCreditPoints += credits * gradePoint;
                    totalCredits += credits;
                }
            }catch(SQLException e){
                System.out.println("ERROR: Unable to calculate SGPA for student " + studentUsn + ".");
                e.printStackTrace();
                return 0.0;
            }
            //avoid divison by 0 if student has no credits
            if(totalCredits == 0){
                System.out.println("WARNING: Student " + studentUsn + " has no completed courses. SGPA is 0.0 by default.");
                return 0.0;
            }
            //Calculate and return the SGPA
            return totalCreditPoints / totalCredits;
    }



    // Add these two new methods to your DatabaseManager.java file

    public void clearEnrollments() {
        System.out.println("LOG: Clearing all enrollment data...");
        String sql = "DELETE FROM enrollments";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearStudents() {
        System.out.println("LOG: Clearing all student data...");
        String sql = "DELETE FROM students";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}


