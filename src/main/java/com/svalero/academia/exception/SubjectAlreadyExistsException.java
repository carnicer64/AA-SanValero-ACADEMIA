package com.svalero.academia.exception;

public class SubjectAlreadyExistsException extends Exception {
    public SubjectAlreadyExistsException() {
        super("Subject already exists.");
    }
}
