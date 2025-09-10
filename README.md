# Student Management System (BMSCE Model)

![Language](https://img.shields.io/badge/Language-Java-orange.svg)
![Database](https://img.shields.io/badge/Database-PostgreSQL-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

A comprehensive, command-line based Student Management System (SMS) built in Java. This application connects to a PostgreSQL database to manage student, course, and enrollment data for a multi-department college environment. The data and business logic (grading, SGPA calculation) are modeled after the official curriculum of BMS College of Engineering (BMSCE).

---
## 🎬 Demo

![App Demo](SS/app-screenshot.png)

---

## ✨ Key Features

* **🗃️ Persistent Data Storage:** All data is stored and managed in a PostgreSQL database, ensuring data integrity with primary and foreign key constraints.
* **👨‍🎓 Student & Course Management:** Full functionality to add students and courses to the database from a pre-loaded, realistic catalog.
* **✍️ Enrollment & Grading:** Enroll students in various courses and update their marks.
* **📊 Automatic SGPA Calculation:**
    * Automatically translates numerical marks (0-100) into a letter grade (S, A, B...) and a grade point (10, 9, 8...) based on the official BMSCE grading scheme.
    * Calculates the final SGPA for a student based on their credits and grade points.
* **📄 Dynamic Reporting:**
    * Generate a full, formatted Report Card for any student, including their final SGPA.
    * Generate a Course Roster (list of enrolled students) for any course.
* **🖥️ Interactive & Robust CLI:** A user-friendly command-line interface with full input validation to prevent crashes from incorrect user input.

---

## 🛠️ Tech Stack

* **Language:** Java
* **Database:** PostgreSQL
* **API/Library:** JDBC (Java Database Connectivity) for database communication.
* **IDE:** Visual Studio Code

---

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

* Java JDK 17 or later
* PostgreSQL Server
* A tool to run SQL scripts (e.g., pgAdmin)

### Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/harshhhh20/StudentManagementSystem.git
    ```
2.  **Database Setup:**
    * Create a new database in PostgreSQL (the default `postgres` database will work).
    * Run the `create_tables.sql` script provided in this repository to set up the necessary tables.
3.  **Configure the Connection:**
    * Open `src/com/pro1/sms/DatabaseManager.java`. 
    * Update the `password` variable with your own PostgreSQL password.
4.  **Run the Application:**
    * Open the project in VS Code and run the `Main.java` file.

---

## Usage

Once the application is running, you will be presented with a main menu. Simply enter the number corresponding to the action you wish to perform and follow the on-screen prompts.

```text
==============================================
    BMSCE Student Management System v1.0    
==============================================

----------- MAIN MENU -----------
1. Add a New Student
2. Add a New Course
3. Enroll a Student in a Course
4. Enter/Update Marks for a Student
5. View a Student's Report Card
6. View a Course's Roster
7. Exit
---------------------------------
Enter your choice: 
```

---

## 📄 License

This project is licensed under the MIT License - see the `LICENSE` file for details.