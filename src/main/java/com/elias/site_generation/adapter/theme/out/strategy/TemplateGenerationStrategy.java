package com.elias.site_generation.adapter.theme.out.strategy;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.domain.theme.TemplateType;

public interface TemplateGenerationStrategy {

    byte[] generate(ThemeGenerationRequest request);

    TemplateType getType();

}
