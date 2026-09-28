package com.elias.site_generation.adapter.template.out.mapper;

import com.elias.site_generation.adapter.template.out.persistence.PostgresTemplate;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface TemplateMapper {

    PostgresTemplate toEntity(Template template);

}
