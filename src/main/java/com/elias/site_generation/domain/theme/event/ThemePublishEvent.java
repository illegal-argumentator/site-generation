package com.elias.site_generation.domain.theme.event;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.Theme;

public record ThemePublishEvent(Site site, Theme theme) {
}
