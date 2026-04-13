-- This file auto-runs on startup because Spring Boot detects it in resources/
-- Seeds the H2 in-memory database with sample student data

INSERT INTO students (first_name, last_name, email, major) VALUES ('Nick', 'Kingston', 'nick@classboxes.com', 'Computer Science');
INSERT INTO students (first_name, last_name, email, major) VALUES ('Surya', 'Patel', 'surya@classboxes.com', 'Information Systems');
INSERT INTO students (first_name, last_name, email, major) VALUES ('Arj', 'Kumar', 'arj@classboxes.com', 'Software Engineering');
INSERT INTO students (first_name, last_name, email, major) VALUES ('Kunal', 'Kabi', 'kunal@classboxes.com', 'Computer Science');
