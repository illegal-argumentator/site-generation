package com.elias.site_generation.adapter.security.out.jwt;

import lombok.Builder;

import java.util.Map;

@Builder
public record JwtPayload(
        String subject,
        Map<String, Object> claims,
        long expiration
) {

}
