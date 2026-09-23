package com.elias.site_generation.port.site.usecase;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateComponent;

public interface SiteEditUseCase {

    void edit(String content, TemplateComponent component, Site site);

}
