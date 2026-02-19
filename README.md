# Java Fullstack Learning Project

## Overview
This is a personal learning project where I am building my understanding of **Java from the ground up**, gradually moving toward creating a fullstack web application.  

The project is built using **Spring Boot** and currently implements basic CRUD functionality for a `User` entity. Initially, endpoints were tested using local in-memory storage, and the project is now migrating to a **SQLite persistent database** for real-world data handling.

---

## API Endpoints

- **GET `/user`** – Returns a list of all users.
- **POST `/user`** – Adds a new user to the system.
- **GET `/user/{id}`** – Retrieves a single user by their ID.
- **PUT `/user/{id}`** – Updates a user's information by ID.

---

## Learning Progress

| Topic                                   | Status       | Notes |
|-----------------------------------------|------------|-------|
| Java Basics: variables, classes, loops, collections | ✅ Completed | Learned core Java concepts |
| Spring Boot Basics: Dependency Injection, Beans, API creation | ✅ Completed | Created controllers, services, repositories |
| In-memory CRUD API testing               | ✅ Completed | Tested endpoints with local variables |
| JDBC Connections with SQLite             | ⏳ In Progress | Connecting Spring Boot to SQLite and mapping ResultSet → entities |
| JPA / ORM Integration                    | 🔮 Next     | Plan to replace JDBC with JPA for easier database handling |
| JWT Authentication & Password Hashing   | 🔮 Future   | Secure API endpoints with authentication |
| Fullstack integration with React / Next.js | 🔮 Future   | Connect frontend to backend APIs |
| Production-ready Spring Boot practices  | 🔮 Future   | Error handling, DTOs, clean architecture, connection pooling |

---

## How to Run the Project
1. Clone the repository
2. Open in **IntelliJ IDEA**
3. Make sure **SQLite JDBC driver** is included in `pom.xml`
4. Run the Spring Boot application
5. Use **Postman** or a frontend to test API endpoints

---

## Notes
- This is a **learning project**, so some practices are kept simple for clarity.
- The repository structure and code will evolve as I progress through more advanced Spring Boot and fullstack topics.
- The progress table above can be updated regularly to track learning milestones.
