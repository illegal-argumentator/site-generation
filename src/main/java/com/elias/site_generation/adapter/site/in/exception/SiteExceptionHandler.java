package com.elias.site_generation.adapter.site.in.exception;

import com.elias.site_generation.domain.site.exception.SiteCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SiteExceptionHandler {

    @ExceptionHandler(SiteCreationException.class)
    public void handleSiteCreationException(SiteCreationException e) {
        log.error(e.getMessage());
    }

}
