package com.demo.configuration.exception;

public class NoRollbackException extends Exception {
    public NoRollbackException() {}

    public NoRollbackException(String message) {
        super(message);
    }
}
