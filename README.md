# LybSys Library Management System

## Description
The LybSys Library Management System is a spring-based web application that allows students of schools or colleges to issue books from a library in a hassle-free manner. It also helps the library administrators to quickly view, create or update any information regarding the books present in the library which makes it a great tool for the administrators to manage the book inventory.

## Technologies 
### Backend 
 - Java 11
 - Spring Boot
 - Hibernate/JPA
 - REST APIs

### Database
 - H2 database

 ## Features
LybSys can be accessed by two types of users: 

 - Students
 - Library administrators


### Features for students
Students can:- 
 - Get the list of all books available in the library
 - Search a book by its id
 - Search a book by its title or author
 - Issue books from the library
 - Get the list of all books issued by them
 -  Return the issued books

### Features for administrators
Admins can:-
 - Get the list of all books available in the library
 - Search a book by its id
 - Search a book by its name
 - Add a book to the library catalogue
 - Update details of a book present in the library
 - Remove a book from the library catalogue
 - Get the list of all books issued by a student
 - Get the list of all departments
 - Get the list of all students
 - Search a student by their id
 - Search a student by their name 
 - Add a student to the student database
 - Update details of a student
 - Remove a student from the student database
 
## Backlog
 LybSys does not currently include the following features
 
 - A login page
 - Role based authentication and authorisation

## Steps to run the application locally

 - Clone this repo
 - Navigate to the directory of the cloned project 
 - Execute `mvn spring-boot:run` to start the application
 - Navigate to [http://localhost:8080/api/home](http://localhost:8080/api/home)
 - You will be welcomed with the message: Welcome to LybSys Application
 - Navigate to [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/) to get the URL for other APIs
