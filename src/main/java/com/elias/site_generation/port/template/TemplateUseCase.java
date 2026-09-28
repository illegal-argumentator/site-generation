package com.elias.site_generation.port.template;

import com.elias.site_generation.domain.template.Template;

public interface TemplateUseCase {

    void save(Long imageId, Template template);

}
