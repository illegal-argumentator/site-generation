package com.elias.site_generation.port.site;

import com.elias.site_generation.domain.site.Site;

import java.util.List;
import java.util.Set;

public interface SiteQueryPort {

    Site findById(long id);

    List<Site> findAllById(Set<Long> ids);


}
