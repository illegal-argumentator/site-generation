package com.elias.site_generation.domain.theme;

public record Theme(
        String id,
        String name,
        byte[] data
) {
}
