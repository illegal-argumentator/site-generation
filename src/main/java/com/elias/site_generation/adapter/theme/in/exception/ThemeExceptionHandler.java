package com.elias.site_generation.adapter.theme.in.exception;

import com.elias.site_generation.domain.theme.exception.ThemeGenerationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ThemeExceptionHandler {

    private final ThemeExceptionService exceptionService;

    @ExceptionHandler(ThemeGenerationException.class)
    public void handleThemeGenerationException(ThemeGenerationException e) {
        exceptionService.publishSiteCreationFailed(e.getSiteId(), e.getMessage());
    }

}
