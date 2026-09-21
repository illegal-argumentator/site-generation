package com.elias.site_generation.adapter.site.in.dto;

import com.elias.site_generation.domain.theme.TemplateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSitePayload(
        @NotNull(message = "Template type is required.")
        TemplateType type,

        String language,
        String content,

        @NotBlank(message = "Hostname is required.")
        String hostname
) {
}
