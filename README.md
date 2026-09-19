# TaskFlow — A Java CLI Task Manager

TaskFlow is a command-line task manager built in Java. It allows users to add tasks, set priorities and due dates, mark them as complete, and generate simple productivity reports. All data is saved locally in a CSV file. No GUI, no database, just plain Java.

Built for the Programming in Java course (VITyarthi Flipped Course Evaluation).

## Features
- Add tasks with title, due date, priority, status, and description
- View all saved tasks
- Filter tasks by priority (High / Medium / Low) and status (Pending / Completed)
- Update a task by ID
- Delete a task by ID
- Mark a task as complete
- Generate a productivity report (total, completed, pending, overdue, completion rate, priority breakdown)
- Data persists between runs using a CSV file
- Input validation — bad input gets re-prompted, app never crashes

## Technologies Used
- Java 17+ (Standard Library only — no external dependencies)
- java.time for date handling
- java.util for collections and Scanner
- Java Streams for filtering and reporting
- java.nio.file for CSV file operations
- Git + GitHub for version control
- VS Code for development

## How to Run
1. Clone the repository:
   git clone https://github.com/Mann875-hash/taskflow-java.git
2. Go into the folder:
   cd taskflow-java
3. Compile the code:
   javac src/*.java -d out
4. Run the app:
   java -cp out Main

## How to Run Tests
The test file TaskServiceTest.java uses an in-memory repository so the real CSV file is not modified.
Run it with:
javac src/*.java -d out
java -cp out TaskServiceTest

## Sample Output
> Add Task
Title: Submit Java Assignment
Due Date: 2026-09-25
Priority: HIGH
Task saved. ID: T-001

> Report
Total Tasks : 1
Completed : 0
Pending : 1
Overdue : 0
Completion Rate : 0%

## Author
Mahiman Seetha
Registration Number: 24MIP10135
Course: Programming in Java
