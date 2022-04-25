package com.svalero.academia.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import com.svalero.academia.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<User> findAllUsers() throws SQLException {
       String sql = "SELECT * FROM USERS";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setIdUser(resultSet.getInt("id_user"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            user.setUserFirstName(resultSet.getString("user_firstname"));
            user.setUserLastName(resultSet.getString("user_lastname"));
            user.setUserTlp(resultSet.getString("user_telephone"));
            user.setUserEmail(resultSet.getString("user_email"));

            users.add(user);
        }
        return users;
    }

    public Optional<User> findUserById(int idUser) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE id_user = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idUser);
        ResultSet resultSet = statement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setIdUser(resultSet.getInt("id_user"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            user.setUserFirstName(resultSet.getString("user_firstname"));
            user.setUserLastName(resultSet.getString("user_lastname"));
            user.setUserTlp(resultSet.getString("user_telephone"));
            user.setUserEmail(resultSet.getString("user_email"));
        }

        return Optional.ofNullable(user);
    }

    public Optional<User> loginUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setIdUser(resultSet.getInt("id_user"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            user.setUserFirstName(resultSet.getString("user_firstname"));
            user.setUserLastName(resultSet.getString("user_lastname"));
            user.setUserTlp(resultSet.getString("user_telephone"));
            user.setUserEmail(resultSet.getString("user_email"));
        }

        return Optional.ofNullable(user);
    }

    public boolean modifyUser(int idUser, User user) throws SQLException {
        connection.setAutoCommit(false);

        String sql = "UPDATE USERS SET id_user = ?, username = ?, password = ?, role = ?, user_firstname = ?, user_lastname = ?, user_telephone = ?, user_email = ? WHERE id_user = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getIdUser());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole());
        statement.setString(5, user.getUserFirstName());
        statement.setString(6, user.getUserLastName());
        statement.setString(7, user.getUserTlp());
        statement.setString(8, user.getUserEmail());
        statement.setInt(9,idUser);
        int rows = statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);

        return rows == 1;
    }

    public void addUser(User user) throws SQLException {
        connection.setAutoCommit(false);

        String sql = "INSERT INTO USERS (id_user, username, password, role, user_firstname, user_lastname, user_telephone, user_email) VALUE (?,?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getIdUser());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole());
        statement.setString(5, user.getUserFirstName());
        statement.setString(6, user.getUserLastName());
        statement.setString(7, user.getUserTlp());
        statement.setString(8, user.getUserEmail());
        statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);
    }
}
