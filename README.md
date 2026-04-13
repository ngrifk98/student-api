# Student API — Spring Boot Project
**Nicholas Griffith Kingston | ClassBoxes Training | April 2026**

---

## Project Overview

A full CRUD REST API for managing students, built with Spring Boot 3, Spring Data JPA, and an H2 in-memory database. Demonstrates the complete layered architecture Kunal teaches: Controller → Service → Repository → Database.

---

## How to Run

```bash
# From the project root directory:
mvn spring-boot:run

# Or build the JAR first:
mvn clean package
java -jar target/student-api-0.0.1-SNAPSHOT.jar
```

App runs on: http://localhost:8080

---

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/students | Create a new student |
| GET | /api/students | Get all students |
| GET | /api/students/{id} | Get student by ID |
| GET | /api/students/major/{major} | Get students by major |
| PUT | /api/students/{id} | Update a student |
| DELETE | /api/students/{id} | Delete a student |

### Sample Requests (cURL or Postman)

```bash
# Create a student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Doe","email":"jane@test.com","major":"Computer Science"}'

# Get all students
curl http://localhost:8080/api/students

# Get student by ID
curl http://localhost:8080/api/students/1

# Update student
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Smith","email":"jane@test.com","major":"Data Engineering"}'

# Delete student
curl -X DELETE http://localhost:8080/api/students/1
```

---

## Actuator Endpoints

| URL | Description |
|-----|-------------|
| /actuator/health | App health status |
| /actuator/beans | All Spring beans registered |
| /actuator/env | All environment properties |
| /actuator/mappings | All URL mappings |

---

## Project Structure

```
student-api/
├── pom.xml                          <- Maven config, all dependencies
└── src/main/
    ├── java/com/nick/studentapi/
    │   ├── StudentApiApplication.java   <- @SpringBootApplication entry point
    │   ├── model/
    │   │   └── Student.java             <- @Entity, maps to DB table
    │   ├── repository/
    │   │   └── StudentRepository.java   <- @Repository, extends JpaRepository
    │   ├── service/
    │   │   └── StudentService.java      <- @Service, business logic
    │   └── controller/
    │       └── StudentController.java   <- @RestController, REST endpoints
    └── resources/
        ├── application.properties       <- Server/DB/Actuator config
        └── data.sql                     <- Seed data on startup
```

---

## Monday Talking Points (Explain This to Kunal)

### 1. Why @SpringBootApplication?
"It's a convenience annotation that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan. It marks this as the entry point and tells Spring Boot to auto-configure everything based on the dependencies in pom.xml."

### 2. Why the layered architecture?
"I followed the Controller → Service → Repository pattern. The Controller handles HTTP requests and responses. The Service contains all business logic — like checking for duplicate emails before saving. The Repository handles all database operations. Each layer has a single responsibility and is loosely coupled from the others."

### 3. How is Dependency Injection working here?
"I used Constructor Injection throughout — it's the recommended approach because it ensures the object is fully initialized before use. The StudentController depends on StudentService, and StudentService depends on StudentRepository. Spring's IoC container creates all three as singleton beans and injects them automatically — I never write 'new StudentService()' anywhere."

### 4. Why H2 for the database?
"H2 is an in-memory database — no installation or configuration needed. It's perfect for development and demos. In a production environment, I'd swap it for MySQL or PostgreSQL just by changing the application.properties datasource URL and adding the driver dependency. The rest of the code doesn't change because JPA abstracts the database."

### 5. What is Spring Data JPA doing?
"JpaRepository gives me all CRUD operations for free — save, findById, findAll, deleteById — without writing any SQL. Spring Data JPA generates the queries at runtime. I also added custom query methods like findByEmail and findByMajor using Spring Data's method naming convention — Spring automatically generates the SQL from the method name."

### 6. What does the Actuator do?
"Spring Boot Actuator provides production-ready monitoring endpoints automatically when I add spring-boot-starter-actuator to pom.xml. I can hit /actuator/health to see if the app is up, /actuator/beans to see all registered Spring beans, and /actuator/mappings to see all my REST endpoints."

---

## Key Annotations Used

| Annotation | Layer | Why |
|------------|-------|-----|
| @SpringBootApplication | Main | Entry point — combines @Configuration + @EnableAutoConfiguration + @ComponentScan |
| @Entity | Model | Marks Student as a JPA-managed database table |
| @Repository | Repository | Marks as data access layer, enables exception translation |
| @Service | Service | Marks as business logic layer |
| @RestController | Controller | Handles HTTP requests, auto-serializes responses to JSON |
| @RequestMapping | Controller | Sets base URL path for all endpoints |
| @GetMapping / @PostMapping / @PutMapping / @DeleteMapping | Controller | Maps specific HTTP methods to handler methods |
| @PathVariable | Controller | Extracts {id} from URL path |
| @RequestBody | Controller | Deserializes JSON request body to Java object |
| @Autowired | Service/Controller | Constructor injection of dependencies |
| @GeneratedValue | Model | Auto-increments primary key |
