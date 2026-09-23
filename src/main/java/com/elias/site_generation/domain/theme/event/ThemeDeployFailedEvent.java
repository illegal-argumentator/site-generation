package com.elias.site_generation.domain.theme.event;

import com.elias.site_generation.domain.site.type.DeployStatus;

public record ThemeDeployFailedEvent(Long id, String reason, DeployStatus deployStatus) {
}
