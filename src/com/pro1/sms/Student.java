package com.pro1.sms;

public class Student {
    //private to ensure data is only accessed through controlled method
    private String usn;
    private String name;
    private String department;
    private String email;
    
    //constructor
    //used to create a new instance of Student
    public Student(String usn , String name , String department , String email){
        this.usn = usn;
        this.name = name;
        this.department = department;
        this.email = email;
    }


    // Getters and Setters
    // public method to get(access) adn set(modify) the private fields
    public String getUsn() {return usn;}
    public void setUsn(String usn) {this.usn = usn;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDepartment() {return department;}
    public void setDepartment(String department) {this.department = department;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    //toString method
    //a useful method for debugging as it provides a clean and redable string representation ofthe Student obj u are trying to print
    @Override
    public String toString(){
        return "Student{" + "usn='" + usn + '\'' +  ", name=" + name + '\'' + '}';
    }
}