package com.svalero.academia.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Enroll {
    private int idEnroll;
    private int enPrice;
    private int enQuotes;
    private String enNotes;
    private int enIdSubject;
    private int enIdStudent;

    public Enroll(int idEnroll, int enPrice, int enQuotes, String enNotes) {
        this.idEnroll = idEnroll;
        this.enPrice = enPrice;
        this.enQuotes = enQuotes;
        this.enNotes = enNotes;

    }

    public Enroll( int enIdSubject, int enIdStudent, int idEnroll, int enPrice, int enQuotes, String enNotes) {
        this.idEnroll = idEnroll;
        this.enPrice = enPrice;
        this.enQuotes = enQuotes;
        this.enNotes = enNotes;
        this.enIdSubject = enIdSubject;
        this.enIdStudent = enIdStudent;
    }

    public Enroll() {
    }

    public int getIdEnroll() {
        return idEnroll;
    }

    public void setIdEnroll(int idEnroll) {
        this.idEnroll = idEnroll;
    }

    public int getEnPrice() {
        return enPrice;
    }

    public void setEnPrice(int enPrice) {
        this.enPrice = enPrice;
    }

    public int getEnQuotes() {
        return enQuotes;
    }

    public void setEnQuotes(int enQuotes) {
        this.enQuotes = enQuotes;
    }

    public String getEnNotes() {
        return enNotes;
    }

    public void setEnNotes(String enNotes) {
        this.enNotes = enNotes;
    }

    public int getEnIdSubject() {
        return enIdSubject;
    }

    public void setEnIdSubject(int enIdSubject) {
        this.enIdSubject = enIdSubject;
    }

    public int getEnIdStudent() {
        return enIdStudent;
    }

    public void setEnIdStudent(int enIdStudent) {
        this.enIdStudent = enIdStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enroll enroll = (Enroll) o;
        return idEnroll == enroll.idEnroll && enPrice == enroll.enPrice && enQuotes == enroll.enQuotes && enIdSubject == enroll.enIdSubject && enIdStudent == enroll.enIdStudent && Objects.equals(enNotes, enroll.enNotes);
    }

    @Override
    public String toString() {
        return "Matrícula{" +
                " Identificador matrícula: " + idEnroll +
                " Precio: " + enPrice +
                " Número de cuotas: " + enQuotes +
                " Nota del expediente: " + enNotes + '\'' +
                " Identificador del curso: " + enIdSubject +
                " Número de alumno: " + enIdStudent +
                '}';
    }
}
