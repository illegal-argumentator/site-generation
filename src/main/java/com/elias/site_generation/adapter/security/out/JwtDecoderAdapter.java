package com.elias.site_generation.adapter.security.out;

import com.elias.site_generation.adapter.security.out.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
@RequiredArgsConstructor
public class JwtDecoderAdapter implements AuthDecoderPort{

    @Override
    public String decode(String auth) {
        Claims claims = extractAllClaims(auth);
        return claims.getSubject();
    }

    private final JwtProperties jwtProperties;

    private Claims extractAllClaims(String token) {
        return parseSignedClaims(token).getPayload();
    }

    private Jws<Claims> parseSignedClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token);
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

}
