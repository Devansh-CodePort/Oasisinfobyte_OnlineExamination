# Oasisinfobyte_OnlineExamination
# Java Online Examination System
A comprehensive online examination system implemented in Java with a graphical user interface and MySQL database integration.

## Features
- User registration and authentication
- Password update functionality
- Timed quiz with multiple-choice questions
- Rules display before exam
- Automatic submission when time expires
- Score calculation and display

## Components
1. **Login.java**: User authentication and registration interface.
2. **Quiz.java**: Main quiz interface with timed questions and answer submission.
3. **Rules.java**: Displays exam rules before starting the quiz.
4. **UpdateProfile.java**: Allows users to update their profile information.
5. **OnlineExamsqlQuery.sql**: SQL query for database setup.

## Technologies Used
- Java Swing for GUI
- JDBC for database connectivity
- MySQL for data storage
- Regular expressions for password validation

## Setup
1. Ensure you have Java and MySQL installed on your system.
2. Run the SQL query in `OnlineExamsqlQuery.sql` to set up the database.
3. Update the database connection details in the Java files if necessary.
4. Compile and run the Java files, starting with `Login.java`.

## Usage
1. New users can sign up through the registration form.
2. Existing users can log in with their credentials.
3. Users can update their profile information if needed.
4. Before starting the exam, users are presented with the rules.
5. The quiz consists of multiple-choice questions with a time limit.
6. After completing the exam, users receive their score.
