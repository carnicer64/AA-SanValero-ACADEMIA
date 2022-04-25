package com.svalero.academia.exception;

public class SubjectDoesNotExistException extends Exception {
    public SubjectDoesNotExistException() {
        super("Subject doesn't exists.");
    }
}
