package com.elias.site_generation.port.site.usecase;

import com.elias.site_generation.domain.site.Site;

public interface SiteUseCase {

    void create(String themeId, Site site);

}
