package com.elias.site_generation.adapter.site.in.dto;

import com.elias.site_generation.domain.theme.TemplateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.elias.site_generation.domain.site.SiteDomainPattern.DOMAIN_PATTERN;
import static com.elias.site_generation.domain.site.SiteDomainPattern.DOMAIN_PATTERN_MESSAGE;

public record CreateSitePayload(
        @NotNull(message = "Template type is required.")
        TemplateType type,

        String language,
        String content,

        @Pattern(regexp = DOMAIN_PATTERN, message = DOMAIN_PATTERN_MESSAGE)
        String hostname
) {
}
