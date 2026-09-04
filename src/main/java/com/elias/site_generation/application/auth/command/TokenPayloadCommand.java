package com.elias.site_generation.application.auth.command;

import lombok.Builder;

@Builder
public record TokenPayloadCommand(String accessToken, String refreshToken) {
}
