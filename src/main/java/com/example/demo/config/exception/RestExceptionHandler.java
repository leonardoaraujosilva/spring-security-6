package com.example.demo.config.exception;

import com.example.demo.dto.exception.RestExceptionData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Component
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<RestExceptionData> handleRestException(RestException restException){

        var error = new RestExceptionData();
        error.setTitle(restException.getTitle());
        error.setDetails(restException.getDetails());

        log.error("Error {} {}: {} - {}",
                restException.getStatusCode(),
                restException.getClass().getSimpleName(),
                restException.getTitle(),
                restException.getDetails());

        return ResponseEntity.status(restException.getStatusCode())
                .body(error);
    }

}
