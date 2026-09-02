package com.elias.site_generation.application.auth.command;

import lombok.Builder;

@Builder
public record AuthResponseCommand(String accessToken, String refreshToken) {
}
