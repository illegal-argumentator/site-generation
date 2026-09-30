package com.elias.site_generation.port.site.usecase;

import com.elias.site_generation.domain.theme.TemplateComponent;

public interface SiteCommandUseCase {

    void changeDomain(long siteId, String domain);
    void delete(long siteId);
    void edit(long siteId, String content, TemplateComponent component);

}
