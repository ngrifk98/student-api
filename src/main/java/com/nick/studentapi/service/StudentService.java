package com.nick.studentapi.service;

import com.nick.studentapi.model.Student;
import com.nick.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * StudentService — Business Logic Layer.
 *
 * @Service is a specialization of @Component that marks this as the service layer.
 * Spring picks it up via @ComponentScan and registers it as a bean.
 *
 * @Autowired injects the StudentRepository dependency automatically —
 * this is Dependency Injection in action. Spring finds a bean of type
 * StudentRepository and injects it here. We never call "new StudentRepository()".
 *
 * The service layer sits between the Controller and Repository:
 *   Controller → Service → Repository → Database
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor Injection — recommended approach (Kunal: mandatory dependencies go here)
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ---- CREATE ----
    public Student createStudent(Student student) {
        // Check for duplicate email before saving
        Optional<Student> existing = studentRepository.findByEmail(student.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("A student with email " + student.getEmail() + " already exists.");
        }
        return studentRepository.save(student);
    }

    // ---- READ ALL ----
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ---- READ BY ID ----
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // ---- READ BY MAJOR ----
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }

    // ---- UPDATE ----
    public Student updateStudent(Long id, Student updatedData) {
        Student existing = getStudentById(id); // throws if not found
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        existing.setMajor(updatedData.getMajor());
        return studentRepository.save(existing);
    }

    // ---- DELETE ----
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
