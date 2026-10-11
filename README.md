# 🔗 URL Shortener

Long URLs are useful, but they are not always pleasant to share.

This project turns them into short, memorable links through a clean Spring Boot REST API. Create an account, authenticate securely, shorten a URL, and send people to the original destination with a simple redirect.

It is a small project with a complete backend workflow—and a practical playground for learning how the main pieces of Spring Boot fit together.

## ✨ What can it do?

- 👤 Register users with validated credentials
- 🔐 Store passwords securely using BCrypt hashing
- ✂️ Shorten valid HTTP, HTTPS, and FTP URLs
- 🚀 Redirect visitors from a short code to the original URL
- 🗄️ Persist users and shortened URLs with Spring Data JPA
- 🧭 Manage database changes with Flyway migrations
- 🧱 Keep responsibilities separated across controllers, services, repositories, entities, and DTOs
- 🛡️ Protect URL creation behind Spring Security and HTTP Basic authentication
- ⚠️ Return a clear error when a short URL cannot be found

## 🛠️ Built with

| Technology | Purpose |
| --- | --- |
| Java 25 | Application language |
| Spring Boot 4.1.1 | Application foundation |
| Spring Web MVC | REST endpoints and redirects |
| Spring Security | Authentication and endpoint protection |
| Spring Data JPA / Hibernate | Data access and entity mapping |
| SQLite | Lightweight local database |
| Flyway | Version-controlled database migrations |
| Maven | Build and dependency management |
| Lombok + MapStruct | Less boilerplate and DTO mapping |

## 🚀 Get started

### Prerequisites

- Java 25 or later
- Maven, or the included Maven Wrapper

### Start the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`. For local development, the application uses the `url-shortener-database.sqlite` database file.

## 🔌 Try the API

### 1. Create an account

```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{"username":"hemant","password":"strong-password"}'
```

### 2. Shorten a URL

URL shortening requires authentication:

```bash
curl -X POST http://localhost:8080/api/v1/shorten-url \
  -u hemant:strong-password \
  -H "Content-Type: application/json" \
  -d '{"originalUrl":"https://spring.io/projects/spring-boot"}'
```

The response contains a short URL such as:

```text
http://localhost:8080/aB3xYz
```

### 3. Share it

Open the short URL in a browser—or send a request to it—and the service will redirect you to the original destination:

```text
http://localhost:8080/{shortCode}
```

## 🧩 Project layout

```text
src/main/java/tech/hemantsharma/urlshortner/
├── common/          Security configuration and global exception handling
├── urlshortener/    URL shortening API, domain logic, persistence, and DTOs
└── user/            User registration, authentication, persistence, and DTOs
```

Database schema changes live in:

```text
src/main/resources/db/migration/
```

## ✅ Build and test

```bash
./mvnw clean test
```

The project currently includes an application context test and is ready to grow with focused controller, service, security, and persistence tests.

## 🌱 Why this project?

This is a learning project built to explore the journey from request to database and back again: accepting and validating input, authenticating users, mapping entities, saving data, handling errors, and returning useful HTTP responses.

Small project, real backend concepts, plenty of room to grow.

## 📌 Current scope

This project is intended for learning and personal development. The local SQLite setup and hardcoded development URL make it convenient to run locally; deployment-specific configuration can be added as the application evolves.
