# StudySync AI Backend

This repository contains a production-oriented Java 21 + Spring Boot 3 backend for the StudySync AI application. It includes:

- JWT-based authentication and authorization
- Student and admin role support
- Subject, exam, assignment, and topic tracking
- Personalized academic study plan generation
- Career roadmap and skill-gap analysis
- AI-assisted learning services
- Analytics, notifications, and gamification support
- Flyway database migrations and JPA entities
- REST APIs documented with Swagger/OpenAPI

## Tech stack

- Java 21
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- MySQL 8
- Flyway
- Springdoc OpenAPI
- JUnit 5 + Mockito

## Project structure

- `src/main/java/com/studysync/...`
- `src/main/resources/application.yml`
- `src/main/resources/db/migration/...`
- `src/test/java/com/studysync/...`

## Run locally

1. Create MySQL database:

```sql
CREATE DATABASE studysync CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Update database credentials in `src/main/resources/application.yml` if needed.

3. Start the application:

```bash
mvn clean spring-boot:run
```

4. Open API docs:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Authentication

Register a user:

```http
POST /api/auth/register
```

Login a user:

```http
POST /api/auth/login
```

## Main APIs

- `/api/auth/**`
- `/api/users/**`
- `/api/subjects/**`
- `/api/study-plans/**`
- `/api/career-domains/**`
- `/api/career-roadmaps/**`
- `/api/skills/**`
- `/api/ai/**`
- `/api/analytics/**`
- `/api/planning/**`
- `/api/notifications/**`

## Testing

```bash
mvn clean test
```

## Notes

This is backend-only code. No frontend UI files are included.
