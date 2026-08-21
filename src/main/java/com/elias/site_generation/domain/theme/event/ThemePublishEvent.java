package com.elias.site_generation.domain.theme.event;

import com.elias.site_generation.domain.theme.Theme;

public record ThemePublishEvent(long siteId, Theme theme) {
}
