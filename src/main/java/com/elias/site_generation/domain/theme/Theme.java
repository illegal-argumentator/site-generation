package com.elias.site_generation.domain.theme;

import java.time.Instant;

public record Theme(
        String id,
        byte[] data,
        Instant createdAt,
        Instant updatedAt
) {

    public static Theme from(byte[] data) {
        return new Theme(null, data, null, null);
    }

}
