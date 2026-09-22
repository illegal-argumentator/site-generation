package com.elias.site_generation.adapter.theme.out.generation.strategy;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.domain.theme.TemplateType;

public interface ThemeGenerationStrategy {

    byte[] generate(ThemeGenerationRequest request);

    TemplateType getType();

}
