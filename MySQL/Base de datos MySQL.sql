CREATE DATABASE academia;
USE academia;

CREATE TABLE STUDENTS(
    id_student INT PRIMARY KEY,
    student_name VARCHAR(50),
    student_lastname VARCHAR(50),
    student_address VARCHAR(100),
    student_telephone VARCHAR(30),
    student_email VARCHAR(50)
);

CREATE TABLE SUBJECTS(
    id_subject INT PRIMARY KEY,
    subject_name VARCHAR(100),
    subject_vacancies INT,
    subject_teacher VARCHAR(100),
    subject_price INT
);

CREATE TABLE ENROLLS(
    id_enroll INT PRIMARY KEY,
    enroll_price INT,
    enroll_payquotes INT,
    enroll_notes VARCHAR(30),
    id_subject INT REFERENCES SUBJECTS (id_subject),
    id_student INT REFERENCES STUDENTS (id_student)
);

CREATE TABLE PAYMENTS(
    id_pay INT PRIMARY KEY,
    payed_quotes INT,
    id_enroll INT REFERENCES ENROLLS (id_enroll)
);

CREATE TABLE USERS(
	id_user INT PRIMARY KEY,
    username VARCHAR(30),
    password VARCHAR(30),
    role VARCHAR(30),
    user_firstname VARCHAR(30),
    user_lastname VARCHAR(30),
    user_telephone VARCHAR(30),
    user_email VARCHAR(30)
);

DROP TABLE STUDENTS;
DROP TABLE SUBJECTS;
DROP TABLE ENROLLS;
DROP TABLE PAYMENTS;
DROP TABLE USERS;