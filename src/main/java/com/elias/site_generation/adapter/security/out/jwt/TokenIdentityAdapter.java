package com.elias.site_generation.adapter.security.out.jwt;

import com.elias.site_generation.adapter.security.out.config.JwtProperties;
import com.elias.site_generation.port.security.TokenIdentityPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenIdentityAdapter implements TokenIdentityPort {

    private final JwtProperties jwtProperties;

    @Override
    public String extractId(String token) {
        try {
            Claims claims = JwtService.extractAll(token, jwtProperties.getSecretKey());
            return parseId(claims);
        } catch (JwtException e) {
            log.warn("Token parsing failed: {}", e.getMessage());
            throw new BadCredentialsException("Invalid or expired token.");
        }
    }

    private String parseId(Claims claims) {
        String id = claims.get(TokenClaim.ID.getClaim(), String.class);

        if (!StringUtils.hasText(id)) {
            throw new BadCredentialsException("Invalid or expired token.");
        }

        return id;
    }
}
