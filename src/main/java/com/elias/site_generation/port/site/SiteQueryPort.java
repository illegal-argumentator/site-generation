package com.elias.site_generation.port.site;

import com.elias.site_generation.domain.site.Site;

public interface SiteQueryPort {

    Site findById(long id);

}
