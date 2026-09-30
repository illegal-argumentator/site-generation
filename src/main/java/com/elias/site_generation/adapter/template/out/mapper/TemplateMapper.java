package com.elias.site_generation.adapter.template.out.mapper;

import com.elias.site_generation.adapter.template.in.dto.SaveTemplateRequest;
import com.elias.site_generation.adapter.template.in.dto.TemplateResponse;
import com.elias.site_generation.adapter.template.out.persistence.PostgresTemplate;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface TemplateMapper {

    Template toTemplate(PostgresTemplate entity);
    List<Template> toTemplates(List<PostgresTemplate> entities);

    PostgresTemplate toEntity(Template template);

    Template toTemplate(SaveTemplateRequest request);
    TemplateResponse toResponse(Template template);

}
