package com.elias.site_generation.adapter.site.out.mapper;

import com.elias.site_generation.adapter.site.in.dto.CreateSitePayload;
import com.elias.site_generation.adapter.site.in.dto.CreateSiteRequest;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface DtoSiteMapper {

    Site toSite(CreateSiteRequest request);
    List<Site> toSites(List<CreateSitePayload> requests);

}
