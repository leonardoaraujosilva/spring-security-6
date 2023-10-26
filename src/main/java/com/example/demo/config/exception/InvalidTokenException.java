package com.example.demo.config.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends RestException {

    private final static String TITLE = "Invalid token";

    public InvalidTokenException(){
        super(HttpStatus.FORBIDDEN, TITLE);
    }
}
