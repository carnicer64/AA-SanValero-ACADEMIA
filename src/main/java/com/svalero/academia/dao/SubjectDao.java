package com.svalero.academia.dao;

import com.svalero.academia.domain.Subject;
import com.svalero.academia.exception.SubjectAlreadyExistsException;
import com.svalero.academia.exception.SubjectDoesNotExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SubjectDao {
    
    private Connection connection;

    public SubjectDao(Connection connection) {
        this.connection = connection;
    }

    //AÑADIR
    
    public void addSubject(Subject subject) throws SQLException, SubjectAlreadyExistsException {
        if (existSubject(subject.getIdSubject())){
            throw new SubjectAlreadyExistsException();
        }

        connection.setAutoCommit(false);

        String sql = "INSERT INTO SUBJECTS (id_subject, subject_name, subject_vacancies, subject_teacher, subject_price) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, subject.getIdSubject());
        statement.setString(2, subject.getSubName());
        statement.setInt(3, subject.getSubVacancies());
        statement.setString(4, subject.getSubTeacher());
        statement.setInt(5, subject.getSubPrice());
        statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);
    }

    //MODIFICAR

    public boolean modifySubjectById(int idSubject, Subject subject) throws SQLException, SubjectDoesNotExistException {
        if (!existSubject(subject.getIdSubject())) {
            throw new SubjectDoesNotExistException();
        }

        connection.setAutoCommit(false);

        String sql = "UPDATE SUBJECTS SET id_subject = ?, subject_name = ?, subject_vacancies = ?, subject_teacher = ?, subject_price = ? WHERE id_subject = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, subject.getIdSubject());
        statement.setString(2, subject.getSubName());
        statement.setInt(3, subject.getSubVacancies());
        statement.setString(4, subject.getSubTeacher());
        statement.setInt(5, subject.getSubPrice());
        statement.setInt(6, idSubject);
        int rows = statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);

        return rows == 1;
    }

    //ELIMINAR

    public boolean deleteSubjectByID(int idSubject) throws SQLException, SubjectDoesNotExistException {
        if (!existSubject(idSubject)) {
            throw new SubjectDoesNotExistException();
        }

        connection.setAutoCommit(false);

        String sql = "DELETE FROM SUBJECTS WHERE id_subject = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idSubject);
        int rows = statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);

        return rows == 1;
    }

    //BUSCAR

    public ArrayList<Subject> findAllSubjects() throws SQLException{
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        String sql = "SELECT * FROM SUBJECTS ORDER BY id_subject";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
           Subject subject= new Subject();
           subject.setIdSubject(resultSet.getInt("id_subject"));
           subject.setSubName(resultSet.getString("subject_name"));
           subject.setSubVacancies(resultSet.getInt("subject_vacancies"));
           subject.setSubTeacher(resultSet.getString("subject_teacher"));
           subject.setSubPrice(resultSet.getInt("subject_price"));
           subjects.add(subject);
        }

        return subjects;
    }

    public ArrayList<Subject> findAllSubjects(String searchText) throws SQLException {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        String sql = "SELECT * FROM SUBJECTS WHERE INSTR(id_subject,?) != 0 OR INSTR(subject_name,?) != 0 ORDER BY id_subject";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, searchText);
        statement.setString(2, searchText);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Subject subject= new Subject();
            subject.setIdSubject(resultSet.getInt("id_subject"));
            subject.setSubName(resultSet.getString("subject_name"));
            subject.setSubVacancies(resultSet.getInt("subject_vacancies"));
            subject.setSubTeacher(resultSet.getString("subject_teacher"));
            subject.setSubPrice(resultSet.getInt("subject_price"));
            subjects.add(subject);
        }
        return subjects;
    }

    private boolean existSubject(int idSubject) throws SQLException{
        Optional<Subject> subject = findByIdSubject(idSubject);
        return subject.isPresent();
    }

    public Optional<Subject> findByIdSubject(int idSubject) throws SQLException{
        Subject subject = null;

        String sql = "SELECT * FROM SUBJECTS WHERE id_subject = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idSubject);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            subject = new Subject();
            subject.setIdSubject(resultSet.getInt("id_subject"));
            subject.setSubName(resultSet.getString("subject_name"));
            subject.setSubVacancies(resultSet.getInt("subject_vacancies"));
            subject.setSubTeacher(resultSet.getString("subject_teacher"));
            subject.setSubPrice(resultSet.getInt("subject_price"));
        }

        return Optional.ofNullable(subject);
    }




}
