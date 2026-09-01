package com.elias.site_generation.adapter.theme.in.exception;

import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ThemeExceptionHandler {

    private final ThemeExceptionService exceptionService;

    @ExceptionHandler(ThemePublishingException.class)
    public void handleThemePublishException(ThemePublishingException e) {
        exceptionService.publishThemePublishFailed(e.getSiteId(), e.getMessage(), e.getDeployStatus());
    }
}
