package com.elias.site_generation.adapter.template.in.dto;

import com.elias.site_generation.domain.theme.TemplateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveTemplateRequest(

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Description is required.")
        String description,

        @NotNull(message = "Type is required.")
        TemplateType type

) {
}
