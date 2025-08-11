## 🛒 E-commerce Order Management API

**Technologies:** Spring Boot, PostgreSQL, JWT, REST API, JPA/Hibernate

### 🎯 Project Goal

The aim of this project is to create a simple yet functional API for managing orders in an e-commerce store.
It focuses on developing backend skills, particularly in data modeling, user authentication, and implementing CRUD operations.

---

### 🔐 Authentication & Authorization

* Users can **register**, **log in**, and **log out** of the system.
* Role-based authorization: **Administrator**, **Seller**, **Customer**.
* The system generates a **Session ID token** used for authentication and authorization to access protected resources.
* Basic Spring Security configuration with a **custom `UserDetailsService`** and
  `UsernamePasswordAuthenticationToken` for user authentication.

---

### 📦 API Features

**Use Cases:**

* User registration, login, and logout.
* **GET** endpoint to fetch all products using `Pageable` with optional sorting.
* **POST** – Create a new product.
* **DELETE** – Remove a product.
* **PATCH** – Update a product.
* Upload and download files using `MultipartFile`.
* Retrieve user details by **ID**.
* Update a user’s role.

**Additional Features:**

* **Custom Rate Limiter** implemented via `OncePerRequestFilter`.
* **Global Exception Handler** using `@ControllerAdvice`.
* **SessionDebugFilter** for logging and debugging incoming requests.
* Mappers and converters for enums.

---

### 🧱 Technologies & Libraries

* **Spring Boot** (Java)
* **PostgreSQL**
* **Docker**
* **Spring Data JPA**
* **Spring Security**
* **JUnit 5**, **Mockito**, **Testcontainers** for unit and integration testing

---

### 🗂 Architectural Notes

* Package structure inspired by **Screaming Architecture** principles.
* Applied programming principles:

  * **YAGNI** – You Aren’t Gonna Need It
  * **KISS** – Keep It Simple, Stupid
  * **SOLID** principles
  * **DRY** – Don’t Repeat Yourself
* API versioning implemented.

---

### 📅 Roadmap

The project’s structure and development path were partially inspired by:

<img width="1404" height="929" alt="SPRING BOOT ROADMAP" src="https://github.com/user-attachments/assets/46e04785-a0e8-4430-a714-3f8282646b96" />

---

### 🚀 Possible Improvements & Future Development

The primary goal of this project was to explore new Spring Boot features,
strengthen my understanding of its core functionality, and gain initial experience with PostgreSQL.
While the project is functional, it can be improved in several areas:

* **Business analysis** – Some solutions are oversimplified and may not address real-world business needs.
* **Session storage** – Implement Redis for distributed session management.
* **Rate limiting** – Use a token bucket algorithm (e.g., **Bucket4j**).
* **Payment integration** – Add integration with a payment gateway API.
* **Security enhancements** – Consider switching to **JWT** (discussion-worthy).
* **Architecture improvements** – Explore better architectural patterns and make more informed design decisions.
* **Testing** – Improve test quality; current tests are basic examples intended for concept learning.

---
