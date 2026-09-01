package com.elias.site_generation.adapter.site.in.exception;

import com.elias.site_generation.domain.site.exception.*;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.theme.exception.ThemeGenerationException;
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

    @ExceptionHandler(SiteCreationException.class)
    public void handleSiteCreationException(SiteCreationException e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler(DomainAlreadyExistsException.class)
    public ResponseEntity<String> handleDomainAlreadyExistsException(DomainAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(e.getMessage());
    }

    @ExceptionHandler(SiteDeployException.class)
    public ResponseEntity<String> handleSiteAlreadyDeployedException(SiteDeployException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
    }

    @ExceptionHandler(SiteHasNotCreatedException.class)
    public ResponseEntity<String> handleSiteHasNotCreatedException(SiteHasNotCreatedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
    }

    @ExceptionHandler(SiteNotFoundException.class)
    public ResponseEntity<String> handleSiteNotFoundException(SiteNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(e.getMessage());
    }

    @ExceptionHandler(ThemeGenerationException.class)
    public void handleThemeGenerationException(ThemeGenerationException e) {
        exceptionService.publishSiteCreationFailed(e.getSiteId(), e.getMessage(), CreationStatus.FAILED);
    }

}
