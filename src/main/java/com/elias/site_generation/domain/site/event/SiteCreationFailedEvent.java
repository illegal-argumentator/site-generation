package com.elias.site_generation.domain.site.event;

import com.elias.site_generation.domain.site.type.CreationStatus;

public record SiteCreationFailedEvent(Long id, String reason, CreationStatus creationStatus) {
}
