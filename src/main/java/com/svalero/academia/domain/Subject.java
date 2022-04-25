package com.svalero.academia.domain;

import java.util.Objects;

public class Subject {

    private int idSubject;
    private String subName;
    private int subVacancies;
    private String subTeacher;
    private int subPrice;

    public Subject(int idSubject, String subName, int subVacancies, String subTeacher, int subPrice) {
        this.idSubject = idSubject;
        this.subName = subName;
        this.subVacancies = subVacancies;
        this.subTeacher = subTeacher;
        this.subPrice = subPrice;
    }

    public Subject() {}

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getSubVacancies() {
        return subVacancies;
    }

    public void setSubVacancies(int subVacancies) {
        this.subVacancies = subVacancies;
    }

    public String getSubTeacher() {
        return subTeacher;
    }

    public void setSubTeacher(String subTeacher) {
        this.subTeacher = subTeacher;
    }

    public int getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(int subPrice) {
        this.subPrice = subPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return idSubject == subject.idSubject && subVacancies == subject.subVacancies && subPrice == subject.subPrice && Objects.equals(subName, subject.subName) && Objects.equals(subTeacher, subject.subTeacher);
    }

    @Override
    public String toString() {
        return "Curso{" +
                " Identificador curso: " + idSubject +
                " Nombre: " + subName + '\'' +
                " Vacantes: " + subVacancies +
                " Profesor: " + subTeacher + '\'' +
                " Precio: " + subPrice +
                '}';
    }
}

