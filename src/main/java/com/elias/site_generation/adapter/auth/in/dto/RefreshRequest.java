package com.elias.site_generation.adapter.auth.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RefreshRequest(@NotBlank(message = "Refresh token is required.") String refreshToken) {
}
