package com.elias.site_generation.port.template;

import com.elias.site_generation.domain.theme.TemplateType;

public interface TemplateFileQueryPort {

    boolean exists(TemplateType type);

}
