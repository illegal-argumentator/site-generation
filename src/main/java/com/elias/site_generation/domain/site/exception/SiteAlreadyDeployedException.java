package com.elias.site_generation.domain.site.exception;

public class SiteAlreadyDeployedException extends RuntimeException {
    public SiteAlreadyDeployedException(String message) {
        super(message);
    }
}
