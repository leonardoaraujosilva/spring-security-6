package com.example.demo.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class RestException extends RuntimeException {

    private final HttpStatus statusCode;

    private final String title;

    private final String details;

    public RestException(HttpStatus statusCode, String title, String details) {
        this.statusCode = statusCode;
        this.title = title;
        this.details = details;
    }

    public RestException(HttpStatus statusCode, String title) {
        this(statusCode, title, null);
    }
}
