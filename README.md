# Nutri-Track

Nutri-Track is a personal project I am developing to improve my backend development skills with Java and Spring Boot.

The goal of this application is to allow users to track their meals, calculate their daily nutritional intake, and manage their nutrition goals.

This project is also an opportunity for me to practice REST API development, database management, software architecture, and unit testing.

## Technologies

**Backend**
- Java 21
- Spring Boot 4
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Jakarta Bean Validation
- JUnit 5
- Mockito
- Git / GitHub
- Postman

**Frontend (in progress)**
- React
- JavaScript
- HTML / CSS
- Vite

## Features

The backend currently includes:

- User management (CRUD)
- Food category management (CRUD)
- Food management (CRUD)
- Meal entry management (CRUD)
- Daily nutritional goal management
- Daily nutrition tracking through DailyLog
- Automatic calculation of calories, proteins, carbohydrates, and fats
- Automatic DailyLog recalculation when meal entries are created, updated, or deleted
- DTOs for handling meal entry requests and responses
- Request validation using Jakarta Bean Validation
- Global exception handling
- REST endpoints for managing application data
- MySQL database integration using Spring Data JPA and Hibernate

## Project Structure

The backend follows a layered architecture:

- **Controller:** Handles HTTP requests and returns responses.
- **Service:** Contains business logic and nutritional calculations.
- **Repository:** Communicates with the database using Spring Data JPA.
- **Entity:** Represents the application's database entities.
- **DTO:** Defines the data exchanged between the API and its clients.
- **Exception:** Handles application errors and exceptions.

## Nutritional Calculations

Nutri-Track calculates nutritional values based on the quantity of food consumed.

For example, if 100 g of chicken contains 165 calories, consuming 200 g results in:

165 × (200 / 100) = 330 calories

The same principle is used to calculate proteins, carbohydrates, and fats.

The application also calculates daily totals based on the user's meal entries.

## Testing

I use JUnit 5 and Mockito to write unit tests for the service layer.

The tests cover nutritional calculations, daily nutrition totals, and service operations.

To run the tests:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

## Getting Started

### Prerequisites

- Java 21
- MySQL
- Git

### Installation

Clone the repository:

```bash
git clone https://github.com/Magomed-Emi/nutrition-tracker-api.git
```

Navigate to the project directory:

```bash
cd nutrition-tracker-api
```

Create a MySQL database:

```sql
CREATE DATABASE nutri_track;
```

Configure the database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nutri_track
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

Replace the database credentials with your local MySQL configuration.

Start the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend runs locally at:

`http://localhost:8080`

## Future Improvements

I plan to continue developing the application by adding:

- Authentication and authorization with Spring Security
- JWT-based authentication
- User roles and protected endpoints
- A daily nutrition summary comparing consumed values with daily goals
- A React frontend connected to the backend API
- Additional automated tests
- Improvements to API security and error handling

## Project Status

This project is currently under development.

The backend includes the main entities, CRUD operations, nutritional calculations, and unit tests.

I am continuing to improve the application while learning and applying backend development concepts.

## Author

Magomed-Emi Usumov