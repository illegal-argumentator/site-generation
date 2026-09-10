package com.elias.site_generation.adapter.theme.in.dto;

import java.util.Map;

public record ThemeGenerationRequest(
        String title,
        Map<String, byte[]> images,
        String content,
        String language,
        byte[] template
) {
}
