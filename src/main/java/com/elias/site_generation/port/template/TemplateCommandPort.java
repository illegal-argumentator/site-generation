package com.elias.site_generation.port.template;

import com.elias.site_generation.domain.template.Template;

public interface TemplateCommandPort {

    void save(Template template);

}
