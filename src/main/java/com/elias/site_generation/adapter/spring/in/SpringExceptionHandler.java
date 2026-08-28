package com.elias.site_generation.adapter.spring.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SpringExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class})
    public ResponseEntity<String> handleUnexpectedExceptions(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
