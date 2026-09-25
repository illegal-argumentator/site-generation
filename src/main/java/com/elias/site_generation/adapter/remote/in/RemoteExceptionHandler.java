package com.elias.site_generation.adapter.remote.in;

import com.elias.site_generation.adapter.remote.out.exception.RemoteExecutionFailedException;
import com.elias.site_generation.shared.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RemoteExceptionHandler {

    @ExceptionHandler(RemoteExecutionFailedException.class)
    public ResponseEntity<ExceptionResponse> handleRemoteExecutionFailedException(RemoteExecutionFailedException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(exceptionResponse);
    }

}
