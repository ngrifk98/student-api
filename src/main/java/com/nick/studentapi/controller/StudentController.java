package com.nick.studentapi.controller;

import com.nick.studentapi.model.Student;
import com.nick.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * StudentController — Presentation Layer (REST API).
 *
 * @RestController = @Controller + @ResponseBody
 *   - @Controller marks this as a Spring MVC controller
 *   - @ResponseBody means every method return value is written directly
 *     into the HTTP response body as JSON (via Jackson serialization)
 *
 * @RequestMapping("/api/students") sets the base URL for all endpoints in this class.
 *
 * The Controller receives HTTP requests, delegates to the Service,
 * and returns ResponseEntity objects with the appropriate HTTP status code.
 *
 * Full REST CRUD:
 *   POST   /api/students        -> Create student
 *   GET    /api/students        -> Get all students
 *   GET    /api/students/{id}   -> Get student by ID
 *   GET    /api/students/major/{major} -> Get students by major
 *   PUT    /api/students/{id}   -> Update student
 *   DELETE /api/students/{id}   -> Delete student
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor Injection — same pattern as Service layer
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * POST /api/students
     * Creates a new student. Returns 201 CREATED with the saved student object.
     * @RequestBody deserializes the incoming JSON body into a Student object.
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = studentService.createStudent(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * GET /api/students
     * Returns all students. Returns 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    /**
     * GET /api/students/{id}
     * Returns a student by ID. Returns 200 OK or 404 if not found.
     * @PathVariable extracts {id} from the URL path.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    /**
     * GET /api/students/major/{major}
     * Returns all students with a specific major.
     */
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        return ResponseEntity.ok(studentService.getStudentsByMajor(major));
    }

    /**
     * PUT /api/students/{id}
     * Updates an existing student. Returns 200 OK with updated student.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                  @RequestBody Student student) {
        Student updated = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/students/{id}
     * Deletes a student by ID. Returns 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
