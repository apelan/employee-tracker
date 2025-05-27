# Employee Tracker
A lightweight Spring Boot application for managing teams and employees, featuring RESTful APIs, uses PostgreSQL, Flyway for migrations, and Swagger UI for API documentation.

## Features
- Manage employees and teams
- Search employees by name, team, or lead
- Pagination support for large datasets
- Flyway for database migrations
- H2 in-memory database for testing 
- Swagger UI for API documentation

## Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL (data storage)
- H2 (integration testing)
- Flyway
- Lombok
- JUnit 5 and Mockito
- Springdoc OpenAPI (Swagger)


## Getting Started
Prerequisites:
- Java 21+
- Maven
- PostgreSQL

Step to run locally:
1. Clone the Repository <br>
`git clone https://github.com/apelan/employee-tracker.git`
2. Configure the Database <br>
Ensure PostgreSQL is running and create empty database named _employee_tracker_.
3. Update _src/main/resources/application.yaml_ with your PostgreSQL credentials
4. Run the Application
`./mvnw spring-boot:run`

## Running Tests
The project includes unit and integration tests. To run them:
`./mvnw test`

Integration tests use an H2 in-memory database configured in _src/test/resources/application-test.yaml_.

## API Documentation
Swagger UI is available at: http://localhost:8080/swagger-ui/index.html

This provides basic documentation for all available endpoints.
