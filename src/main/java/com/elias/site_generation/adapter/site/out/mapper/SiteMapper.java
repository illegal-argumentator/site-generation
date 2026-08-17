package com.elias.site_generation.adapter.site.out.mapper;

import com.elias.site_generation.adapter.site.out.persistence.PostgresSite;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapStructConfig.class)
public interface SiteMapper {

    PostgresSite toEntity(Site site);
    Site toSite(PostgresSite entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget PostgresSite entity, Site site);

}
