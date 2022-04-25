package com.svalero.academia.dao;

import com.svalero.academia.domain.Student;
import com.svalero.academia.exception.StudentAlreadyExistsException;
import com.svalero.academia.exception.StudentDoesNotExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao {

    private Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }



    //CRUD
    //
    //AÑADIR

    public void addStudent(Student student) throws SQLException, StudentAlreadyExistsException {
        if (existStudent(student.getIdStudent())) {
            throw new StudentAlreadyExistsException();
        }

        connection.setAutoCommit(false);

        String sql = "INSERT INTO STUDENTS (id_student, student_name, student_lastname, student_address, student_telephone, student_email) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getIdStudent());
        statement.setString(2, student.getsName());
        statement.setString(3, student.getsLastName());
        statement.setString(4, student.getsAddress());
        statement.setString(5, student.getsTlp());
        statement.setString(6, student.getsEmail());
        statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);
    }

    //MODIFICAR

    public boolean modifyStudentById(Student student, int idStudent) throws SQLException, StudentDoesNotExistException {
        if (!existStudent(student.getIdStudent())) {
            throw new StudentDoesNotExistException();
        }

        connection.setAutoCommit(false);

        String sql = "UPDATE STUDENTS SET id_student = ?, student_name = ?, student_lastname = ?, student_address = ?, student_telephone = ?, student_email = ? WHERE id_student = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getIdStudent());
        statement.setString(2,student.getsName());
        statement.setString(3,student.getsLastName());
        statement.setString(4,student.getsAddress());
        statement.setString(5,student.getsTlp());
        statement.setString(6, student.getsEmail());
        statement.setInt(7, idStudent);
        int rows = statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);

        return rows == 1;
    }

    //ELIMINAR

    public boolean deleteStudentByID(int idStudent) throws SQLException, StudentDoesNotExistException {
        if (!existStudent(idStudent)) {
            throw new StudentDoesNotExistException();
        }

        connection.setAutoCommit(false);

        String sql = "DELETE FROM STUDENTS WHERE id_student = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idStudent);
        int rows = statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);

        return rows == 1;
    }

    //BUSCAR

    public Optional<Student> findByIdStudent(int idStudent) throws SQLException {
        Student student = null;

        String sql = "SELECT * FROM STUDENTS WHERE id_student = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idStudent);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            student = new Student();
            student.setIdStudent(resultSet.getInt("id_student"));
            student.setsName(resultSet.getString("student_name"));
            student.setsLastName(resultSet.getString("student_lastname"));
            student.setsAddress(resultSet.getString("student_address"));
            student.setsTlp(resultSet.getString("student_telephone"));
            student.setsEmail(resultSet.getString("student_email"));
        }
        return Optional.ofNullable(student);
    }

    public ArrayList<Student> findAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<Student>();

        String sql = "SELECT * FROM STUDENTS ORDER BY id_student";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Student student = new Student();
            student.setIdStudent(resultSet.getInt("id_student"));
            student.setsName(resultSet.getString("student_name"));
            student.setsLastName(resultSet.getString("student_lastname"));
            student.setsAddress(resultSet.getString("student_address"));
            student.setsTlp(resultSet.getString("student_telephone"));
            student.setsEmail(resultSet.getString("student_email"));
            students.add(student);
        }
        return students;
    }

    public ArrayList<Student> findAllStudents(String searchText) throws SQLException {
        ArrayList<Student> students = new ArrayList<Student>();

        String sql = "SELECT * FROM STUDENTS WHERE INSTR(id_student,?) != 0 OR INSTR(student_name,?) != 0 ORDER BY id_student";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, searchText);
        statement.setString(2, searchText);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Student student = new Student();
            student.setIdStudent(resultSet.getInt("id_student"));
            student.setsName(resultSet.getString("student_name"));
            student.setsLastName(resultSet.getString("student_lastname"));
            student.setsAddress(resultSet.getString("student_address"));
            student.setsTlp(resultSet.getString("student_telephone"));
            student.setsEmail(resultSet.getString("student_email"));
            students.add(student);
        }
        return students;
    }

    private boolean existStudent(int idStudent) throws SQLException{
        Optional<Student> student = findByIdStudent(idStudent);
        return student.isPresent();
    }
}
