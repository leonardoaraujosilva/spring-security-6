package com.example.demo.config.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RestException {

    public UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "User already exists", "The username already in usage.");
    }
}
