

Servlet User Access Control System:
An advanced Java Servlet application tailored for comprehensive management of user access control across software applications. 
This system empowers administrators with robust tools to review, approve, or reject user access requests, thereby enforcing stringent access levels tailored to user roles (Employee, Manager, Admin).


Features:
User registration and login
Role-based access control (Employee, Manager, Admin)
Request and approval workflows for software access


Required Libraries:
Servlet API (javax.servlet) - Manages HTTP requests and responses.
PostgreSQL JDBC Driver - Connects to a PostgreSQL database.
Lombok - Reduces boilerplate code for model classes.

Setup Instructions:
Clone the repository and navigate to the project directory.
Import into your preferred IDE as a Maven project.
Configure the database connection in DBConnection.java.
Deploy on a server like Apache Tomcat.
Access via http://localhost:8080/[your-app-name].