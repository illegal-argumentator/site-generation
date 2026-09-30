package com.elias.site_generation.port.template;

import com.elias.site_generation.domain.template.Template;

import java.util.List;

public interface TemplateQueryPort {

    List<Template> findAll();

}
