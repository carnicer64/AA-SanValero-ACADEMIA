package com.svalero.academia;

//Esta clase se ha realizado para la AA de Entornos de desarrollo

import com.svalero.academia.dao.*;
import com.svalero.academia.domain.Enroll;
import com.svalero.academia.domain.Student;
import com.svalero.academia.domain.Subject;
import com.svalero.academia.domain.User;
import com.svalero.academia.exception.EnrollAlreadyExistsException;
import com.svalero.academia.exception.StudentAlreadyExistsException;
import com.svalero.academia.exception.SubjectAlreadyExistsException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner enter = new Scanner(System.in);
    private Database database = new Database();

    public void showMenu() {
        String choice;
        do {
            System.out.println("ACADEMIA SERVLET");
            System.out.println("--Menú--");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Añadir estudiante");
            System.out.println("3. Añadir curso");
            System.out.println("4. Añadir matrícula");
            System.out.println("5. Ver usuarios");
            System.out.println("6. Ver estudiantes");
            System.out.println("7. Ver cursos");
            System.out.println("8. Ver matriculas");
            System.out.println("9. Salir");
            System.out.println("---------------------");
            System.out.println("Opcion: ");
            choice = enter.nextLine();
            System.out.println("---------------------");

            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    addStudent();
                    break;
                case "3":
                    addSubject();
                    break;
                case "4":
                    addEnroll();
                    break;
                case "5":
                    showUsers();
                    break;
                case "6":
                    showStudents();
                    break;
                case "7":
                    showSubjects();
                    break;
                case "8":
                    showEnrolls();
                    break;
                case "9":
                    System.out.println("ADIOS");
                    System.exit(0);
                    break;
            }
        }while (!choice.equals("9"));
    }

    private void showEnrolls() {
        ArrayList<Enroll> enrolls = new ArrayList<Enroll>();
        EnrollDao enrollDao = new EnrollDao(database.getConnection());

        try {
            enrolls = enrollDao.findAllEnroll();
            for (Enroll enroll : enrolls) {
                System.out.println("--------------------");
                System.out.println(enroll.toString());
                System.out.println("--------------------");
            }
        }catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        }
    }

    private void showSubjects() {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        SubjectDao subjectDao = new SubjectDao(database.getConnection());

        try {
            subjects = subjectDao.findAllSubjects();
            for (Subject subject : subjects) {
                System.out.println("--------------------");
                System.out.println(subject.toString());
                System.out.println("--------------------");
            }
        }catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        }
    }

    private void showStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        StudentDao studentDao = new StudentDao(database.getConnection());

        try {
            students = studentDao.findAllStudents();
            for (Student student : students) {
                System.out.println("--------------------");
                System.out.println(student.toString());
                System.out.println("--------------------");
            }
        }catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        }
    }

    private void showUsers() {
        ArrayList<User> users = new ArrayList<User>();
        UserDao userDao = new UserDao(database.getConnection());

        try {
            users = userDao.findAllUsers();
            for (User user : users) {
                System.out.println("--------------------");
                System.out.println(user.toString());
                System.out.println("--------------------");

            }
        } catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        }
    }

    private void addEnroll() {
        System.out.println("Introduce el identificador de la matrícula: ");
        int idEnroll = enter.nextInt();
        System.out.println("Introduce el precio de la matrícula: ");
        int enPrice = enter.nextInt();
        System.out.println("Introduce el número de cuotas de la matrícula: ");
        int enQuotes = enter.nextInt();
        System.out.println("Introduce la nota del expediente del alumno (aprobado/suspendido/pendiente): ");
        String enNotes = enter.nextLine();
        System.out.println("Introduce el identificador del curso al que va a matricularse el alumno: ");
        int enIdSubject = enter.nextInt();
        System.out.println("Introduce el número del alumno que va a matricularse");
        int enIdStudent = enter.nextInt();

        Enroll enroll = new Enroll(idEnroll, enPrice, enQuotes, enNotes);

        EnrollDao enrollDao = new EnrollDao(database.getConnection());

        try {
            enrollDao.addEnroll(enroll, enIdStudent, enIdSubject);
            System.out.println("La matrícula se ha añadido a la base de datos");
        } catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        } catch (EnrollAlreadyExistsException eaee) {
            System.out.println("La matrícula ya existe en la base de datos");
            eaee.printStackTrace();
        }

    }

    private void addSubject() {
        System.out.println("Introduce identificador del curso: ");
        int idSubject = enter.nextInt();
        System.out.println("Introduce nombre del curso: ");
        String subName = enter.nextLine();
        System.out.println("Introduce el número de vacantes: ");
        int subVacancies = enter.nextInt();
        System.out.println("Introduce nombre del profesor: ");
        String subTeacher = enter.nextLine();
        System.out.println("Introduce precio del curso");
        int subPrice = enter.nextInt();

        Subject subject = new Subject(idSubject, subName, subVacancies, subTeacher, subPrice);

        SubjectDao subjectDao = new SubjectDao(database.getConnection());

        try {
            subjectDao.addSubject(subject);
            System.out.println("El curso se ha introducido en la base de datos");
        } catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        } catch (SubjectAlreadyExistsException saee) {
            System.out.println("El curso ya existe en la base de datos");
            saee.printStackTrace();
        }
    }

    private void addStudent() {
        System.out.println("Introduce número de alumno: ");
        int idStudent = enter.nextInt();
        System.out.println("Introduce nombre del alumno: ");
        String sName = enter.nextLine();
        System.out.println("Introduce apellido del alumno: ");
        String sLastName = enter.nextLine();
        System.out.println("Introduce dirección del alumno: ");
        String sAddress = enter.nextLine();
        System.out.println("Introduce telefono del alumno: ");
        String sTlp = enter.nextLine();
        System.out.println("Introduce email del alumno: ");
        String sEmail = enter.nextLine();

        Student student = new Student(idStudent, sName, sLastName, sAddress, sTlp, sEmail);

        StudentDao studentDao = new StudentDao(database.getConnection());

        try {
            studentDao.addStudent(student);
            System.out.println("Se ha añadido el alumno a la base de datos");
        }catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        }catch (StudentAlreadyExistsException saee) {
            System.out.println("El alumno ya existe");
            saee.printStackTrace();
        }
    }

    private void addUser() {
        System.out.println("Introduce nick de usuario: ");
        String username = enter.nextLine();
        System.out.println("Introduce contraseña: ");
        String password = enter.nextLine();
        System.out.println("Introduce el id de usuario: ");
        int idUser = enter.nextInt();
        System.out.println("Introduce el rol del usuario (Admin/Secretary): ");
        String role = enter.nextLine();
        System.out.println("Introduce el nombre del usuario: ");
        String userFirstName = enter.nextLine();
        System.out.println("Introduce el apellido del usuario: ");
        String userLastName = enter.nextLine();
        System.out.println("Introduce el telefono del usuario: ");
        String userTlp = enter.nextLine();
        System.out.println("Introduce el email del usuario: ");
        String userEmail = enter.nextLine();

        User user = new User(username, password, idUser, role, userFirstName, userLastName, userTlp, userEmail);

        UserDao userDao = new UserDao(database.getConnection());

        try {
            userDao.addUser(user);
            System.out.println("Se ha añadido el usuario a la base de datos");
        }catch (SQLException sqlee) {
            System.out.println("Error al conectar con la base de datos");
            sqlee.printStackTrace();
        }

    }


}
