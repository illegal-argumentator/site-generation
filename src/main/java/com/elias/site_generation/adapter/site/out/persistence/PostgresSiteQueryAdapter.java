package com.elias.site_generation.adapter.site.out.persistence;

import com.elias.site_generation.adapter.site.out.mapper.SiteMapper;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.SiteNotFoundException;
import com.elias.site_generation.port.site.SiteQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresSiteQueryAdapter implements SiteQueryPort {

    private final SiteMapper mapper;
    private final PostgresSiteRepository repository;

    @Override
    public Site findById(long id) {
         PostgresSite entity = repository.findById(id)
                 .orElseThrow(() -> new SiteNotFoundException("Site not found."));

         return mapper.toSite(entity);
    }
}
