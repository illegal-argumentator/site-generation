package com.elias.site_generation.port.template;

import com.elias.site_generation.domain.template.Template;

import java.util.List;

public interface TemplateUseCase {

    void save(Long imageId, Template template);

    List<Template> findAll();

}
