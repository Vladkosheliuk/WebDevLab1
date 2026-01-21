package com.example.demo.exception;

public class BeverageAlreadyExistsException extends RuntimeException {
    public BeverageAlreadyExistsException(String message) {
        super(message);
    }
}
