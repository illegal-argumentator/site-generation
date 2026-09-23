package com.elias.site_generation.adapter.site.in.dto;

import com.elias.site_generation.domain.theme.TemplateComponent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SiteEditRequest(
        @NotBlank(message = "Content is required.")
        String content,
        @NotNull(message = "Template component is required.")
        TemplateComponent component
) {
}
