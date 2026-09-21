package com.elias.site_generation.port.site.usecase;

import com.elias.site_generation.domain.site.Site;

import java.util.List;

public interface SitesBulkCreationUseCase {

    void createBulk(List<Site> sites);

}
