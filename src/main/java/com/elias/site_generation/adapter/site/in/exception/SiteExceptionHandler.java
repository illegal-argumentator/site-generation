package com.elias.site_generation.adapter.site.in.exception;

import com.elias.site_generation.domain.site.exception.DomainAlreadyExistsException;
import com.elias.site_generation.domain.site.exception.SiteAlreadyDeployedException;
import com.elias.site_generation.domain.site.exception.SiteCreationException;
import com.elias.site_generation.domain.site.exception.SiteHasNotCreatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SiteExceptionHandler {

    @ExceptionHandler(SiteCreationException.class)
    public void handleSiteCreationException(SiteCreationException e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler(DomainAlreadyExistsException.class)
    public ResponseEntity<String> handleDomainAlreadyExistsException(DomainAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(e.getMessage());
    }

    @ExceptionHandler(SiteAlreadyDeployedException.class)
    public ResponseEntity<String> handleSiteAlreadyDeployedException(SiteAlreadyDeployedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
    }

    @ExceptionHandler(SiteHasNotCreatedException.class)
    public ResponseEntity<String> handleSiteHasNotCreatedException(SiteHasNotCreatedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
    }

}
