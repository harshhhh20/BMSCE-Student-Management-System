package com.pro1.sms;

public class Enrollment {
    private String studentUsn;
    private String courseCode;
    private double marks;
    private String grade;
    private int gradePoint;

    //constructor 
    public Enrollment(String studentUsn , String courseCode , double marks , String grade , int gradePoint){
        this.studentUsn = studentUsn;
        this.courseCode = courseCode;
        this.marks = -1; // Default to -1 to indicate marks are not yet entered
        this.grade = "N/A"; // Default grade
        this.gradePoint = 0; // Default grade point
    }

    //Getters and Setter
    public String getStudentUsn() { return studentUsn; }
    public void setStudentUsn(String studentUsn) { this.studentUsn = studentUsn; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public double getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public int getGradePoint() { return gradePoint; }
    public void setGradePoint(int gradePoint) { this.gradePoint = gradePoint; }
}
