package com.elias.site_generation.domain.theme.exception;

import lombok.Getter;

@Getter
public class ThemeGenerationException extends RuntimeException {

    private final long siteId;

    public ThemeGenerationException(long siteId, String message) {
        this.siteId = siteId;
        super(message);
    }

}
