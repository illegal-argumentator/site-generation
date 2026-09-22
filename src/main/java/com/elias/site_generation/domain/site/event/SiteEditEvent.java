package com.elias.site_generation.domain.site.event;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateComponent;

public record SiteEditEvent(String content, TemplateComponent component, Site site) {
}
