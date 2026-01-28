package com.example.demo.exception;

public class BeverageNotFoundException extends RuntimeException {
    public BeverageNotFoundException(Long id) {
        super("Beverage not found: " + id);
    }
}

