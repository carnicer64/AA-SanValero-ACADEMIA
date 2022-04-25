package com.svalero.academia.dao;

import com.svalero.academia.domain.Enroll;
import com.svalero.academia.domain.Student;
import com.svalero.academia.domain.Subject;
import com.svalero.academia.exception.EnrollAlreadyExistsException;
import com.svalero.academia.exception.SubjectAlreadyExistsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EnrollDao {

    private Connection connection;

    public EnrollDao(Connection connection) {
        this.connection = connection;
    }

    // CREAR MATRICULA

    public void addEnroll(Enroll enroll, int idStudent, int idSubject) throws SQLException, EnrollAlreadyExistsException {
        if (existEnroll(enroll.getIdEnroll())){
            throw new EnrollAlreadyExistsException();
        }

        connection.setAutoCommit(false);

        String sql = "INSERT INTO ENROLLS (id_enroll, enroll_price, enroll_payquotes, enroll_notes, id_subject, id_student) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, enroll.getIdEnroll());
        statement.setInt(2, enroll.getEnPrice());
        statement.setInt(3, enroll.getEnQuotes());
        statement.setString(4, enroll.getEnNotes());
        statement.setInt(5, idSubject);
        statement.setInt(6, idStudent);
        statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);
    }

    private boolean existEnroll(int idEnroll) throws SQLException {
        Optional<Enroll> enroll = findByIdEnroll(idEnroll);
        return enroll.isPresent();
    }

    //BUSCAR MATRICULA POR ID

    private Optional<Enroll> findByIdEnroll(int idEnroll) throws SQLException {
        Enroll enroll = null;

        String sql = "SELECT * FROM ENROLLS WHERE id_enroll = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idEnroll);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            enroll = new Enroll();
            enroll.setIdEnroll(resultSet.getInt("id_enroll"));
            enroll.setEnPrice(resultSet.getInt("enroll_price"));
            enroll.setEnQuotes(resultSet.getInt("enroll_payquotes"));
            enroll.setEnNotes(resultSet.getString("enroll_notes"));
        }

        return Optional.ofNullable(enroll);
    }

    //BUSCAR MATRICULA POR IDSTUDENT

    public ArrayList<Enroll> findEnrollByIdStudent(int idStudent) throws SQLException {
        ArrayList<Enroll> enrolls = new ArrayList<Enroll>();

        String sql = "SELECT * FROM ENROLLS WHERE id_student = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,idStudent);
        ResultSet resultSet = statement.executeQuery();

       while (resultSet.next()) {
            Enroll enroll = new Enroll(); // este constructor es el que tiene los id de subject y student
            enroll.setIdEnroll(resultSet.getInt("id_enroll"));
            enroll.setEnPrice(resultSet.getInt("enroll_price"));
            enroll.setEnQuotes(resultSet.getInt("enroll_payquotes"));
            enroll.setEnNotes(resultSet.getString("enroll_notes"));
            enroll.setEnIdSubject(resultSet.getInt("id_subject"));
            enroll.setEnIdStudent(resultSet.getInt("id_student"));
            enrolls.add(enroll);
        }
       return enrolls;
    }

    //BUSCAR TODAS LAS MATRICULAS

    public ArrayList<Enroll> findAllEnroll() throws SQLException {
        ArrayList<Enroll> enrolls = new ArrayList<Enroll>();

        String sql = "SELECT * FROM ENROLLS ORDER BY id_student";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Enroll enroll = new Enroll(); // este constructor es el que tiene los id de subject y student
            enroll.setIdEnroll(resultSet.getInt("id_enroll"));
            enroll.setEnPrice(resultSet.getInt("enroll_price"));
            enroll.setEnQuotes(resultSet.getInt("enroll_payquotes"));
            enroll.setEnNotes(resultSet.getString("enroll_notes"));
            enroll.setEnIdSubject(resultSet.getInt("id_subject"));
            enroll.setEnIdStudent(resultSet.getInt("id_student"));
            enrolls.add(enroll);
        }
        return enrolls;
    }

    //ELIMINAR MATRICULA

    public boolean deleteEnroll(int idEnroll) throws SQLException {
        connection.setAutoCommit(false);

        String sql = "DELETE FROM ENROLLS WHERE id_enroll = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idEnroll);
        int rows = statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);

        return rows == 1;
    }

}
