package com.elias.site_generation.domain.theme;

import java.time.Instant;

public record Theme(
        String id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
