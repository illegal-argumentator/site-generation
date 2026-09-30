package com.elias.site_generation.adapter.theme.out.generation.component;

import com.elias.site_generation.adapter.theme.out.generation.dto.ElementPayload;
import com.elias.site_generation.domain.theme.TemplateType;

import java.util.Map;

public record ThemePayload(TemplateType type, Map<String, ElementPayload> elements) {

    public static ThemePayload from(TemplateType type, Map<String, ElementPayload> elements) {
        return new ThemePayload(type, elements);
    }

}
