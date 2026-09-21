package com.elias.site_generation.port.site.usecase;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateType;

import java.util.Map;

public interface SitesBulkCreationUseCase {

    void createBulk(Map<TemplateType, Site> sites);

}
