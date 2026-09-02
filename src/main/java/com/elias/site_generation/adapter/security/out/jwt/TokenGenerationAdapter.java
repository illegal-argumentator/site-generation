package com.elias.site_generation.adapter.security.out.jwt;

import com.elias.site_generation.adapter.security.out.config.JwtProperties;
import com.elias.site_generation.application.auth.command.TokenPayloadCommand;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.security.TokenGenerationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.elias.site_generation.shared.utils.Delimiters.COMMA_DELIMITER;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenGenerationAdapter implements TokenGenerationPort {

    private final JwtProperties jwtProperties;

    @Override
    public TokenPayloadCommand generate(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is required.");
        }

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        return TokenPayloadCommand.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String generateAccessToken(User user) {

        Map<String, Object> claims = Map.of(
                TokenClaim.ID.getClaim(), user.id(),
                TokenClaim.EMAIL.getClaim(), user.email(),
                TokenClaim.ROLES.getClaim(), String.join(COMMA_DELIMITER, user.toRoleNames())
        );

        JwtPayload jwtPayload = JwtPayload.builder()
                .subject(user.email())
                .claims(claims)
                .expiration(jwtProperties.getAccessExpirationTime())
                .build();
        return JwtService.buildToken(jwtPayload, jwtProperties.getSecretKey());
    }

    private String generateRefreshToken(User user) {
        JwtPayload jwtPayload = JwtPayload.builder()
                .subject(user.email())
                .claims(Map.of(TokenClaim.ID.getClaim(), user.id()))
                .expiration(jwtProperties.getRefreshExpirationTime())
                .build();

        return JwtService.buildToken(jwtPayload, jwtProperties.getSecretKey());
    }

}
