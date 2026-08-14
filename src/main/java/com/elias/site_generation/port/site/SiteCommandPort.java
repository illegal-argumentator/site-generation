package com.elias.site_generation.port.site;

import com.elias.site_generation.domain.site.Site;

public interface SiteCommandPort {

    Long save(Site site);

    void update(Long id, Site site);

}
