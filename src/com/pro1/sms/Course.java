package com.pro1.sms;

public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private String courseType;
    private String department;

    //constructor
    public Course(String courseCode , String courseName , int credits , String courseType , String department){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.courseType = courseType;
        this.department = department;
    }

    //Getter and Setter
    public String getCourseCode() {return courseCode;}
    public void setCourseCode(String courseCode){this.courseCode = courseCode;}
    public String getCourseName(){return courseName;}
    public void setCourseName(String courseName){this.courseName = courseName;}
    public int getCredits(){return credits;}
    public void setCredits(int credits) {this.credits = credits;}
    public String getCourseType(){return courseType;}
    public void setCourseType(String courseType) {this.courseType = courseType;}
    public String getDepartment(){return department;}
    public void setDepartment(String department){this.department = department;}

    @Override
    public String toString(){
        return "Course{" + "courseCode='" + courseCode + '\'' + ", courseName='" + courseName + '\'' + '}';
    }
}
