package com.elias.site_generation.port.website;

import com.elias.site_generation.domain.theme.TemplateType;

public interface WebsiteTemplateSlugQueryPort {

    String getSlug(TemplateType type);

}
