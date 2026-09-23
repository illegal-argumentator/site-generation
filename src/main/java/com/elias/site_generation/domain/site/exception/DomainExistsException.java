package com.elias.site_generation.domain.site.exception;

public class DomainExistsException extends RuntimeException {
    public DomainExistsException(String message) {
        super(message);
    }
}
