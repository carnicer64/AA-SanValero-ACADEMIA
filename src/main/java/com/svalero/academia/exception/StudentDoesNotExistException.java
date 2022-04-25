package com.svalero.academia.exception;

public class StudentDoesNotExistException extends Exception {

    public StudentDoesNotExistException() {
        super("Student doesn't exist.");
    }
}
