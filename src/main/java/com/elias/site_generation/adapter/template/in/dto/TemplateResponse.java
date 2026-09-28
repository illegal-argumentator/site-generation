package com.elias.site_generation.adapter.template.in.dto;

import com.elias.site_generation.domain.theme.TemplateType;
import lombok.Builder;
import lombok.With;

@Builder
public record TemplateResponse(

        long id,

        String name,
        String description,

        @With
        String imageUrl,

        TemplateType type

) {
}
