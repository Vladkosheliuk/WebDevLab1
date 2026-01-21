package com.example.demo.exception;

public class BeverageNotFoundException extends RuntimeException {
    public BeverageNotFoundException(String message) {
        super(message);
    }
}
