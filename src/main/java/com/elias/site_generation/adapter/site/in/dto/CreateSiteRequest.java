package com.elias.site_generation.adapter.site.in.dto;

import jakarta.validation.constraints.Pattern;
import org.springframework.util.StringUtils;

import static com.elias.site_generation.domain.site.SiteDomainPattern.DOMAIN_PATTERN;
import static com.elias.site_generation.domain.site.SiteDomainPattern.DOMAIN_PATTERN_MESSAGE;

public record CreateSiteRequest(
        String language,
        String content,

        @Pattern(regexp = DOMAIN_PATTERN, message = DOMAIN_PATTERN_MESSAGE)
        String hostname
) {

    private static final String ENGLISH_LANG = "English";

    public CreateSiteRequest(String language, String content, String hostname) {
        if (!StringUtils.hasText(language)) {
            this.language = ENGLISH_LANG;
        } else {
            this.language = language;
        }

        this.content = content;
        this.hostname = hostname;
    }

}
