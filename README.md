# Employee Management REST API

A simple REST API built with **Java, Spring Boot, Spring Data JPA, and MySQL** to manage employee records. It uses a layered structure with controllers, services, repositories, entities, DTOs, and centralized exception handling.

## Features

- Create, view, update, and delete employees
- Request validation and exception handling
- Pagination, employee search, and status filtering
- Swagger/OpenAPI documentation

## Technologies

- Java 17+ (tested locally with Java 21)
- Spring Boot 4.1.1
- Spring Web MVC, Spring Data JPA, Jakarta Validation
- MySQL-compatible database (local XAMPP setup)
- Maven and Swagger UI

## Database Structure

The application uses a relational database and an `employees` table managed through Spring Data JPA.

| Column | Data Type | Description |
|---|---|---|
| `id` | BIGINT | Primary key, auto-generated |
| `emp_name` | VARCHAR | Employee name, required |
| `dept_name` | VARCHAR | Department name, required |
| `salary` | DECIMAL(12,2) | Salary, required |
| `place` | VARCHAR | Employee city/place |
| `location` | VARCHAR | Employee location |
| `status` | VARCHAR | Employee status (`ACTIVE` or `INACTIVE`) |

Database connection and JPA settings are maintained in `src/main/resources/application.properties`. Configure them for your local database environment before running the application. Credentials and connection details are intentionally not included in this README.

The table structure is based on the `Employee` entity. With JPA schema update enabled in local development, Hibernate can create or update the table.

## How to Run

1. Import the project into **Eclipse**: **File → Import → Maven → Existing Maven Projects**.
2. Start your database server and configure the database connection in `src/main/resources/application.properties`.
3. Open `EmployeeManagementApplication.java` and select **Run As → Java Application** (or **Spring Boot App**).
4. The API runs at `http://localhost:8080`.
5. Open Swagger UI at **http://localhost:8080/swagger-ui.html**.

Alternatively, run the project from a terminal using `mvn spring-boot:run`.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees` | Create an employee |
| GET | `/api/employees` | List employees with pagination |
| GET | `/api/employees/{id}` | Get an employee by ID |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |
| GET | `/api/employees/search?keyword=IT` | Search by name or department |
| GET | `/api/employees/status/ACTIVE` | Filter by status |

**Pagination example:** `GET /api/employees?pageNumber=0&pageSize=5` (pages start at 0).

## Sample Request and Response

**Create employee** — `POST /api/employees`

```json
{
  "empName": "Arun Kumar",
  "deptName": "IT",
  "salary": 45000.00,
  "place": "Kochi",
  "location": "Kerala",
  "status": "ACTIVE"
}
```

**Example success response** (`201 Created`):

```json
{
  "id": 1,
  "empName": "Arun Kumar",
  "deptName": "IT",
  "salary": 45000.00,
  "place": "Kochi",
  "location": "Kerala",
  "status": "ACTIVE"
}
```

Typical HTTP responses include `200 OK` for retrieval/update, `201 Created` for creation, `204 No Content` for deletion, `400 Bad Request` for invalid input, and `404 Not Found` for a missing employee. Actual responses depend on the controller and exception handler.

## API Documentation

Test endpoints directly through **Swagger UI**: http://localhost:8080/swagger-ui.html  
OpenAPI specification: http://localhost:8080/v3/api-docs
