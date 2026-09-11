package com.elias.site_generation.adapter.site.in.exception;

import com.elias.site_generation.domain.site.exception.*;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.exception.SiteGenerationException;
import com.elias.site_generation.shared.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class SiteExceptionHandler {

    private final SiteExceptionService exceptionService;

    @ExceptionHandler(SiteParallelCreationLimitReachedException.class)
    public ResponseEntity<ExceptionResponse> handleSiteParallelCreationLimitReachedException(SiteParallelCreationLimitReachedException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(DomainAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleDomainAlreadyExistsException(DomainAlreadyExistsException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(exceptionResponse);
    }

    @ExceptionHandler(SiteDeployException.class)
    public ResponseEntity<ExceptionResponse> handleSiteAlreadyDeployedException(SiteDeployException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(exceptionResponse);
    }

    @ExceptionHandler(SiteHasNotCreatedException.class)
    public ResponseEntity<ExceptionResponse> handleSiteHasNotCreatedException(SiteHasNotCreatedException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exceptionResponse);
    }

    @ExceptionHandler(SiteNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSiteNotFoundException(SiteNotFoundException e, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .code(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exceptionResponse);
    }

    @ExceptionHandler(SiteGenerationException.class)
    public void handleSiteGenerationException(SiteGenerationException e) {
        exceptionService.publishSiteCreationFailed(e.getSiteId(), e.getMessage(), CreationStatus.FAILED);
    }

}
