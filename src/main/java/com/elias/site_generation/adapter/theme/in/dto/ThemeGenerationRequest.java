package com.elias.site_generation.adapter.theme.in.dto;

public record ThemeGenerationRequest(
        String title,
        String content,
        String language,
        byte[] template
) {
}
