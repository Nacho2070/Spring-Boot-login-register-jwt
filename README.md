# Spring Boot Project: JWT Authentication with MySQL

This project demonstrates how to implement JSON Web Token (JWT) authentication with MySQL in a Spring Boot application. It includes endpoints for user authentication (login/register) and an endpoint to change the password.

## Features:

- **JWT Authentication**: Implements secure authentication using JSON Web Tokens.
- **MySQL Database**: Utilizes MySQL to store user data securely.
- **User Authentication**: Provides endpoints for user login, registration, and password change.
- **RESTful API**: Exposes RESTful endpoints for interacting with the application.

## Technologies Used:

- **Spring Boot**: Framework for building Java applications quickly and with minimal configuration.
- **JWT (JSON Web Tokens)**: Securely transmitting information between parties as a JSON object.
- **MySQL**: Relational database management system used for storing user data.
- **Spring Security**: Provides authentication and authorization support for Spring applications.
- **Hibernate**: ORM (Object-Relational Mapping) framework for mapping Java objects to database tables.

## Endpoints:

- **POST /api/auth/login: Endpoint for user login.
- **POST /api/auth/register: Endpoint for user registration.
- **PATCH /api/auth/changePassword: Endpoint for changing user password.
