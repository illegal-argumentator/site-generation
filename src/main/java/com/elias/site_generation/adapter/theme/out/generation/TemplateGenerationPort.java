package com.elias.site_generation.adapter.theme.out.generation;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.domain.theme.TemplateType;

public interface TemplateGenerationPort {

    byte[] generate(TemplateType type, ThemeGenerationRequest request);

}
