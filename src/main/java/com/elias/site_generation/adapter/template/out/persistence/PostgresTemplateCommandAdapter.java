package com.elias.site_generation.adapter.template.out.persistence;

import com.elias.site_generation.adapter.template.out.mapper.TemplateMapper;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.port.template.TemplateCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresTemplateCommandAdapter implements TemplateCommandPort {

    private final TemplateMapper mapper;
    private final PostgresTemplateRepository repository;

    @Override
    public void save(Template template) {
        PostgresTemplate entity = mapper.toEntity(template);
        repository.save(entity);
    }
}
