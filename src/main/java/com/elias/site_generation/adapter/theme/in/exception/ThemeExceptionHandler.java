package com.elias.site_generation.adapter.theme.in.exception;

import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.theme.exception.ThemeGenerationException;
import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ThemeExceptionHandler {

    private final ThemeExceptionService exceptionService;

    @ExceptionHandler(ThemeGenerationException.class)
    public void handleThemeGenerationException(ThemeGenerationException e) {
        exceptionService.publishSiteCreationFailed(e.getSiteId(), e.getMessage(), CreationStatus.FAILED);
    }

    @ExceptionHandler(ThemePublishingException.class)
    public void handleThemePublishingException(ThemePublishingException e) {
        exceptionService.publishSiteCreationFailed(e.getSiteId(), e.getMessage(), e.getCreationStatus());
    }
}
