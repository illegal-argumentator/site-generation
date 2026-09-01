package com.elias.site_generation.domain.site.exception;

import lombok.Getter;

@Getter
public class SiteGenerationException extends RuntimeException {

    private final long siteId;

    public SiteGenerationException(long siteId, String message) {
        this.siteId = siteId;
        super(message);
    }

}
