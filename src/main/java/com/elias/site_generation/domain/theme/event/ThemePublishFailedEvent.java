package com.elias.site_generation.domain.theme.event;

import com.elias.site_generation.domain.site.type.DeployStatus;

public record ThemePublishFailedEvent(Long id, String reason, DeployStatus deployStatus) {
}
