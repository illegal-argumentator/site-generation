package com.elias.site_generation.port.site;

import com.elias.site_generation.domain.site.Site;

public interface SiteCommandPort {

    Site save(Site site);

    Site update(Long id, Site site);

    void delete(Long id);

}
