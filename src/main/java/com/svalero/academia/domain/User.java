package com.svalero.academia.domain;

import java.util.Objects;

public class User {

    private String username;
    private String password;
    private int idUser;
    private String role;
    private String userFirstName;
    private String userLastName;
    private String userTlp;
    private String userEmail;

    public User(String username, String password, int idUser, String role, String userFirstName, String userLastName, String userTlp, String userEmail) {
        this.username = username;
        this.password = password;
        this.idUser = idUser;
        this.role = role;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userTlp = userTlp;
        this.userEmail = userEmail;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserTlp() {
        return userTlp;
    }

    public void setUserTlp(String userTlp) {
        this.userTlp = userTlp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(userFirstName, user.userFirstName) && Objects.equals(userLastName, user.userLastName) && Objects.equals(userTlp, user.userTlp) && Objects.equals(userEmail, user.userEmail);
    }

    @Override
    public String toString() {
        return "Usuario {" +
                " Username: " + username + '\'' +
                " Password: " + password + '\'' +
                " Núm. identificador: " + idUser +
                " Rol: " + role + '\'' +
                " Nombre: " + userFirstName + '\'' +
                " Apellido: " + userLastName + '\'' +
                " Telefono: " + userTlp + '\'' +
                " Email: " + userEmail + '\'' +
                '}';
    }
}
