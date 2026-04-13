package com.nick.studentapi.repository;

import com.nick.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * StudentRepository — Data Access Layer.
 *
 * @Repository marks this as a Spring-managed DAO component.
 * Extending JpaRepository<Student, Long> gives us all CRUD operations for FREE:
 *   - save(student)         -> INSERT or UPDATE
 *   - findById(id)          -> SELECT by primary key
 *   - findAll()             -> SELECT *
 *   - deleteById(id)        -> DELETE by primary key
 *   - existsById(id)        -> check if record exists
 *   - count()               -> total row count
 *
 * Spring Data JPA auto-generates the SQL at runtime — no SQL needed!
 * Custom queries use method naming conventions or @Query annotation.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Spring Data JPA auto-generates: SELECT * FROM students WHERE email = ?
    Optional<Student> findByEmail(String email);

    // Spring Data JPA auto-generates: SELECT * FROM students WHERE major = ?
    List<Student> findByMajor(String major);

    // Spring Data JPA auto-generates: SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ?
    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);
}
