package com.pro1.sms;

public class GradeCalculator {

    // This method takes marks (0-100) and returns an array containing
    // the Grade and the Grade Point.
    public static String[] calculateGrade(int marks) {
        
        // Step 1: Add the validation check at the very top of the method.
        if (marks < 0 || marks > 100) {
            // If the marks are invalid, stop the method immediately and throw an error.
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }

        // Step 2: If the validation passes, the code continues to the grade calculation.
        if (marks >= 90) {
            return new String[]{"S", "10"};
        } else if (marks >= 80) {
            return new String[]{"A", "9"};
        } else if (marks >= 70) {
            return new String[]{"B", "8"};
        } else if (marks >= 60) {
            return new String[]{"C", "7"};
        } else if (marks >= 50) {
            return new String[]{"D", "6"};
        } else if (marks >= 40) {
            return new String[]{"E", "5"};
        } else {
            return new String[]{"F", "0"};
        }
    }
}