// File: Main.java
package com.pro1.sms;

public class Main {
    //We create the dbManager once, making it accessible to all parts of our Main class
    private static DatabaseManager dbManager = new DatabaseManager();

    public static void main(String[] args) {
        //welcome banner
        System.out.println("\n==============================================");
        System.out.println("    BMSCE Student Management System v1.0    ");
        System.out.println("==============================================\n");

        //main application "game loop" thsi will run until the user chooses to exit
        while(true){
            displayMenu();
            int choice = InputHelper.getIntInput("Enter Your Choise : ");
            switch(choice){
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    enrollStudent();
                    break;
                case 4:
                    updateMarks();
                    break;
                case 5:
                    viewStudentReportCard();
                    break;
                case 6:
                    viewCourseRoster();
                    break;
                case 7:
                    System.out.println("\n Thank You for using the Student Management System. Goodbye!");
                    return; //exit the application
                default:
                    System.out.println("ERROR: Invalid choice. Please enter a number between 1 and 7");
            }
        }
    }

    //helper method to keep the main method clean and readable
    private static void displayMenu(){
        System.out.println("\n========= Main Menu =========");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. Update Student Marks");
        System.out.println("5. View Student Report Card");
        System.out.println("6. View Course Report");
        System.out.println("7. Exit");
        System.out.println("=============================");
    }

    //these are the methods that handel each menu option
    private static void addStudent(){
        System.out.println("\n--- Add new Student ---");
        String usn = InputHelper.getStringInput("Enter Student USN: ");
        String name = InputHelper.getStringInput("Enter Student Name: ");
        String department = InputHelper.getStringInput("Enter Student Department: ");
        String email = InputHelper.getStringInput("Enter Student Email: ");
        Student newStudent = new Student(usn, name, department, email);
        dbManager.addStudent(newStudent);
        System.out.println("Student added successfully!");
    }

    private static void addCourse(){
        System.out.println("\n--- Add new Course ---");
        String code = InputHelper.getStringInput("Enter Course Code: ");
        String name = InputHelper.getStringInput("Enter Course Name: ");
        int credits = InputHelper.getIntInput("Enter Course Credits: ");
        String type =InputHelper.getStringInput("Enter Course Type (Core/Elective/Non-Credit): ");
        String dept = InputHelper.getStringInput("Enter Department Offering the Course: ");
        Course newCourse = new Course(code , name , credits , type , dept);
        dbManager.addCourse(newCourse);
    }

    private static void enrollStudent(){
        System.out.println("\n--- Enroll Student in Course ---");
        String usn = InputHelper.getStringInput("Enter Student USN to Enroll : ");
        String courseCode = InputHelper.getStringInput("Enter Course Code to Enroll In : ");
        dbManager.enrollStudent(usn, courseCode);
    }

    private static void updateMarks(){
        System.out.println("\n--- Update Student Marks ---");
        String usn = InputHelper.getStringInput("Enter Student USN : ");
        String courseCode = InputHelper.getStringInput("Enter Course Code : ");
        int marks = InputHelper.getIntInput("Enter New Marks(1-100) : ");
        dbManager.updateMarks(usn, courseCode, marks);
    }

    private static void viewStudentReportCard(){
        System.out.println("\n--- View Student Report Card ---");
        String usn = InputHelper.getStringInput("Enter Student USN : ");
        dbManager.viewStudentReportCard(usn);
    }

    private static void  viewCourseRoster(){
        System.out.println("\n--- View Course Report ---");
        String courseCode = InputHelper.getStringInput("Enter Course Code : ");
        dbManager.viewCourseRoster(courseCode);
    }


}