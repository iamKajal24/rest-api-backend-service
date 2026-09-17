# REST API CRUD Application

A clean and structured **REST API** built with **Java and Spring Boot**, demonstrating CRUD operations with DTOs, validation, exception handling, HTTP request/response management, and MySQL database integration.

## 🚀 Features

* CRUD Operations
* RESTful API
* DTO-based Request & Response
* Request Validation
* Custom Exception Handling
* Global Exception Handler
* HTTP Status Code Handling
* Soft Delete
* MySQL Database Integration
* Spring Data JPA & Hibernate
* Postman API Testing
* Created & Updated Timestamps

## 🛠️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **MySQL**
* **Maven**
* **Postman**
* **Jakarta Validation**

## 📁 Project Structure

```text
src/main/java
└── in.kajalCoder.crudDtoDemo
    │
    ├── controller
    │   └── StudentController.java
    │
    ├── service
    │   └── StudentService.java
    │
    ├── repository
    │   └── StudentRepository.java
    │
    ├── entity
    │   └── Student.java
    │
    ├── dto
    │   ├── CreateStudentRequestDTO.java
    │   ├── CreateStudentResponseDto.java
    │   ├── UpdateStudentRequestDto.java
    │   └── UpdateStudentResponseDto.java
    │
    └── exception
        ├── ResourceNotFoundException.java
        └── GlobalExceptionHandler.java
```

## 🔄 API Operations

| Operation   | Method   | Endpoint                         |
| ----------- | -------- | -------------------------------- |
| Create      | `POST`   | `/api/students`                  |
| Get By ID   | `GET`    | `/api/students/get?id=1`         |
| Get All     | `GET`    | `/api/students/getAll`           |
| Update      | `PUT`    | `/api/students?id=1`             |
| Delete      | `DELETE` | `/api/students/delete?id=1`      |
| Soft Delete | `PATCH`  | `/api/students/delete-soft?id=1` |

## 📦 DTO Architecture

The application uses separate DTOs for handling API requests and responses.

```text
Request
   ↓
Request DTO
   ↓
Controller
   ↓
Service
   ↓
Entity
   ↓
Repository
   ↓
Database
   ↓
Entity
   ↓
Response DTO
   ↓
Response
```

This keeps the API layer separate from the database entity layer.

## ✅ Validation

Request data is validated before processing to prevent invalid data from entering the application.

Validation includes fields such as:

* Name
* Age
* Roll Number
* Subject
* Email

## ⚠️ Exception Handling

The application includes:

* Custom `ResourceNotFoundException`
* Global exception handling
* Standardized error responses
* Appropriate HTTP status codes

Example:

```json
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Resource not found"
}
```

## 🌐 HTTP Status Codes

| Status                      | Usage                         |
| --------------------------- | ----------------------------- |
| `200 OK`                    | Successful request            |
| `201 CREATED`               | Resource successfully created |
| `400 BAD REQUEST`           | Validation/request error      |
| `404 NOT FOUND`             | Resource not found            |
| `500 INTERNAL SERVER ERROR` | Unexpected server error       |

## ♻️ Soft Delete

The application supports soft deletion using a `deleted` flag instead of permanently removing the record.

```text
deleted = false → Active
deleted = true  → Deleted
```

Only active records are returned during normal retrieval operations.

## 🗄️ Database

MySQL is used for data persistence with Spring Data JPA and Hibernate.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crud_db
spring.datasource.username=****
spring.datasource.password=****

spring.jpa.hibernate.ddl-auto=update
```

## 🧪 API Testing

The APIs are tested using **Postman** with JSON request bodies and HTTP methods.

```text
POST    → Create
GET     → Read
PUT     → Update
DELETE  → Delete
PATCH   → Soft Delete
```

## 🎯 Key Concepts Demonstrated

* REST API Development
* Layered Architecture
* Controller-Service-Repository Pattern
* DTO Pattern
* Entity Mapping
* CRUD Operations
* Request Validation
* Exception Handling
* Global Exception Handling
* HTTP Request & Response
* HTTP Status Codes
* JPA & Hibernate
* MySQL Integration
* Soft Delete
* Postman Testing


