package com.akadk.exception;

public class CantUpdateException extends RuntimeException {
    public CantUpdateException(String message) {
        super(message);
    }
}
