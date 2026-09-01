package com.elias.site_generation.adapter.site.out.persistence;

import com.elias.site_generation.adapter.site.out.mapper.SiteMapper;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.SiteNotFoundException;
import com.elias.site_generation.port.site.SiteCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresSiteCommandAdapter implements SiteCommandPort {

    private final SiteMapper mapper;
    private final PostgresSiteRepository repository;

    @Override
    public Site save(Site site) {
        PostgresSite entity = repository.save(mapper.toEntity(site));
        return mapper.toSite(entity);
    }

    @Override
    public Site update(Long id, Site site) {
        System.out.println("Site for update: " + site);
        PostgresSite entity = getOrThrow(id);

        mapper.updateEntity(entity, site);
        PostgresSite updated = repository.save(entity);

        return mapper.toSite(updated);
    }

    private PostgresSite getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new SiteNotFoundException("Site not found."));
    }

}
