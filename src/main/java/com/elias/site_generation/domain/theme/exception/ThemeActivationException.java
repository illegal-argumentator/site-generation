package com.elias.site_generation.domain.theme.exception;

import com.elias.site_generation.domain.site.type.ActiveStatus;
import lombok.Getter;

@Getter
public class ThemeActivationException extends RuntimeException {

    private final long siteId;
    private final ActiveStatus activeStatus;

    public ThemeActivationException(long siteId, String message, ActiveStatus activeStatus) {
        this.siteId = siteId;
        this.activeStatus = activeStatus;
        super(message);
    }
}
