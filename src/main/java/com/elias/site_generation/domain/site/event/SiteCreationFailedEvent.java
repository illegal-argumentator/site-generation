package com.elias.site_generation.domain.site.event;

import com.elias.site_generation.domain.site.Status;

public record SiteCreationFailedEvent(Long id, String reason, Status status) {
}
