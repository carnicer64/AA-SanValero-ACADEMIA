package com.svalero.academia.domain;

import java.util.Objects;

public class Student {

    private int idStudent;
    private String sName;
    private String sLastName;
    private String sAddress;
    private String sTlp;
    private String sEmail;

    public Student(int idStudent, String sName, String sLastName, String sAddress, String sTlp, String sEmail) {
        this.idStudent = idStudent;
        this.sName = sName;
        this.sLastName = sLastName;
        this.sAddress = sAddress;
        this.sTlp = sTlp;
        this.sEmail = sEmail;
    }

    public Student() {
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsTlp() {
        return sTlp;
    }

    public void setsTlp(String sTlp) {
        this.sTlp = sTlp;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return idStudent == student.idStudent && Objects.equals(sName, student.sName) && Objects.equals(sLastName, student.sLastName) && Objects.equals(sAddress, student.sAddress) && Objects.equals(sTlp, student.sTlp) && Objects.equals(sEmail, student.sEmail);
    }

    @Override
    public String toString() {
        return "Alumno {" +
                " Núm. alumno: " + idStudent +
                " Nombre: " + sName + '\'' +
                " Apellido: " + sLastName + '\'' +
                " Dirección: " + sAddress + '\'' +
                " Telefono: " + sTlp + '\'' +
                " Email: " + sEmail + '\'' +
                '}';
    }
}
