package com.svalero.academia.exception;

public class StudentAlreadyExistsException extends Exception {

    public StudentAlreadyExistsException() {
        super("Student already exists.");
    }
}
