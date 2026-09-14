package com.elias.site_generation.domain.site.exception;

public class NotSiteOwnerException extends RuntimeException {
    public NotSiteOwnerException(String message) {
        super(message);
    }
}
