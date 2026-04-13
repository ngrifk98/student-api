package com.nick.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Student API Spring Boot application.
 *
 * @SpringBootApplication combines three annotations:
 *   - @Configuration        -> this class is a source of bean definitions
 *   - @EnableAutoConfiguration -> Spring Boot auto-configures beans based on classpath
 *   - @ComponentScan        -> scans this package and sub-packages for @Component, @Service, etc.
 *
 * SpringApplication.run() bootstraps the application, starts embedded Tomcat on port 8080.
 */
@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }
}
