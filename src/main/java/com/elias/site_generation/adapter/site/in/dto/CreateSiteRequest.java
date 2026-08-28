package com.elias.site_generation.adapter.site.in.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.util.StringUtils;

public record CreateSiteRequest(
        String language,
        String content,

        @NotBlank(message = "Hostname is required.")
        String hostname
) {

    public CreateSiteRequest(String language, String content, String hostname) {
        if (!StringUtils.hasText(language)) {
            this.language = "English";
        } else {
            this.language = language;
        }

        this.content = content;
        this.hostname = hostname;
    }

}
