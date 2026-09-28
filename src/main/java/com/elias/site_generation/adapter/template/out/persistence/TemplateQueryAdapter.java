package com.elias.site_generation.adapter.template.out.persistence;

import com.elias.site_generation.adapter.template.out.mapper.TemplateMapper;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.port.template.TemplateQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TemplateQueryAdapter implements TemplateQueryPort {

    private final TemplateMapper mapper;
    private final PostgresTemplateRepository repository;

    @Override
    public List<Template> findAll() {
        List<PostgresTemplate> entities = repository.findAll();
        return mapper.toTemplates(entities);
    }
}
