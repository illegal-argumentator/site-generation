package com.elias.site_generation.domain.theme.event;

import com.elias.site_generation.domain.site.type.ActiveStatus;

public record ThemeActivationFailedEvent(Long id, String reason, ActiveStatus activeStatus) {
}
