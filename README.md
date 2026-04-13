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
