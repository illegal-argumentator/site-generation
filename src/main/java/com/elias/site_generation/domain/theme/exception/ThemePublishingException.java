package com.elias.site_generation.domain.theme.exception;

import com.elias.site_generation.domain.site.type.DeployStatus;
import lombok.Getter;

@Getter
public class ThemePublishingException extends RuntimeException {

    private final long siteId;
    private final DeployStatus deployStatus;

    public ThemePublishingException(long siteId, String message, DeployStatus deployStatus) {
        this.siteId = siteId;
        this.deployStatus = deployStatus;
        super(message);
    }

    public ThemePublishingException(long siteId, String message, DeployStatus deployStatus, Exception e) {
        this.siteId = siteId;
        this.deployStatus = deployStatus;
        super(message, e);
    }
}
