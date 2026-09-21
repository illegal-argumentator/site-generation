package com.elias.site_generation.adapter.site.out.mapper;

import com.elias.site_generation.adapter.site.in.dto.CreateSitePayload;
import com.elias.site_generation.adapter.site.in.dto.CreateSiteRequest;
import com.elias.site_generation.adapter.site.in.dto.CreateSitesRequest;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

import java.util.Map;
import java.util.stream.Collectors;

@Mapper(config = MapStructConfig.class)
public interface DtoSiteMapper {

    Site toSite(CreateSiteRequest request);

    Site toSite(CreateSitePayload payload);

    default Map<TemplateType, Site> toSites(CreateSitesRequest request) {
        return request.requests().stream().collect(
                        Collectors.toMap(CreateSitePayload::type,
                        this::toSite)
        );
    }



}
