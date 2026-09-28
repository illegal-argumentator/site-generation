package com.elias.site_generation.adapter.template.out.mapper;

import com.elias.site_generation.adapter.template.in.SaveTemplateRequest;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface TemplateRequestMapper {

    Template toTemplate(SaveTemplateRequest request);

}
