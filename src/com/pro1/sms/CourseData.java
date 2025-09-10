package com.pro1.sms;

import java.util.ArrayList;

public class CourseData {

    public static ArrayList<Course> getInitialCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        // --- Computer Science & Engg ---
        courses.add(new Course("23MA3BSSDM", "Statistics and Discrete Mathematics", 3, "Core", "Computer Science"));
        courses.add(new Course("23CS3ESCOA", "Computer Organization and Architecture", 3, "Core", "Computer Science"));
        courses.add(new Course("23CS3PCOOJ", "Object Oriented Java Programming", 4, "Core", "Computer Science"));
        courses.add(new Course("23CS3PCLOD", "Logic Design", 2, "Core", "Computer Science"));
        courses.add(new Course("23CS3PCDBM", "Database Management Systems", 4, "Core", "Computer Science"));
        courses.add(new Course("23CS3PCDST", "Data Structures", 4, "Core", "Computer Science"));
        courses.add(new Course("23CS3AEFWD", "Full Stack Web development", 2, "Elective", "Computer Science"));
        
        // --- Electronics & Communication Engg ---
        courses.add(new Course("23EC3ESHDL", "HDL Programming", 3, "Core", "Electronics"));
        courses.add(new Course("23EC3PCACD", "Analog Electronic Circuits", 3, "Core", "Electronics"));
        courses.add(new Course("23EC3PCDCD", "Digital Circuit Design", 3, "Core", "Electronics"));

        // --- Mechanical Engg ---
        courses.add(new Course("23ME3PCETD", "Engineering Thermodynamics", 4, "Core", "Mechanical"));
        courses.add(new Course("22ME3PCMAP", "Manufacturing Processes", 4, "Core", "Mechanical"));
        
        // --- Chemical Engg ---
        courses.add(new Course("23CH3PCPPC", "Process Principles and Calculations", 4, "Core", "Chemical"));
        courses.add(new Course("23CH3PCFME", "Fluid Mechanics", 4, "Core", "Chemical"));

        // --- Civil Engg ---
        courses.add(new Course("23CV3PCBC", "Building Materials and Construction", 3, "Core", "Civil"));
        courses.add(new Course("23CV3PCSOM", "Strength of Materials", 4, "Core", "Civil"));

        // --- Common Courses (Mandatory Non-Credit) ---
        courses.add(new Course("23NCM3NS1", "NSS", 0, "Non-Credit", "Common"));
        courses.add(new Course("23NCM3YG1", "Yoga", 0, "Non-Credit", "Common"));
        courses.add(new Course("23NCM3PE1", "Physical Education", 0, "Non-Credit", "Common"));
        
        return courses;
    }
}