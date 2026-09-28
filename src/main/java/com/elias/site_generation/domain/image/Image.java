package com.elias.site_generation.domain.image;

import lombok.Builder;

@Builder
public record Image(

        long id,

        String filename,
        String contentType,

        byte[] bytes
) {
}
