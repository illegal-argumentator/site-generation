package com.elias.site_generation.port.site.usecase;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateType;

public interface SiteCreationUseCase {

    void create(TemplateType type, Site site);

    void redeploy(long siteId);

    void activate(long siteId);

}
