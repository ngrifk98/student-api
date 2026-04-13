package com.nick.studentapi.model;

import jakarta.persistence.*;

/**
 * Student entity — maps to the STUDENTS table in the database.
 *
 * @Entity tells JPA/Hibernate this class is a database table.
 * @Table specifies the table name.
 * @Id marks the primary key field.
 * @GeneratedValue tells the database to auto-generate the ID (identity strategy).
 * @Column maps field names to column names.
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String major;

    // Default no-arg constructor required by JPA
    public Student() {}

    // Convenience constructor for creating students without ID
    public Student(String firstName, String lastName, String email, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + firstName + " " + lastName
                + ", email=" + email + ", major=" + major + "}";
    }
}
