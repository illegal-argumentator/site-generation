package com.elias.site_generation.domain.site.exception;

public class SiteParallelCreationLimitReachedException extends RuntimeException {
    public SiteParallelCreationLimitReachedException(String message) {
        super(message);
    }
}
