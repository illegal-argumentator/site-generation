package com.elias.site_generation.domain.site.exception;

public class SiteLimitReachedException extends RuntimeException {
    public SiteLimitReachedException(String message) {
        super(message);
    }
}
