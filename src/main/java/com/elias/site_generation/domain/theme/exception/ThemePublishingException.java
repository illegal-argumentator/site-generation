package com.elias.site_generation.domain.theme.exception;

import com.elias.site_generation.domain.site.type.Status;
import lombok.Getter;

@Getter
public class ThemePublishingException extends RuntimeException {

    private final long siteId;
    private final Status status;

    public ThemePublishingException(long siteId, String message, Status status) {
        this.siteId = siteId;
        this.status = status;
        super(message);
    }
}
