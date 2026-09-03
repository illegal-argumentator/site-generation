package com.elias.site_generation.adapter.auth.in.dto;

import com.elias.site_generation.shared.user.UserRegexPatterns;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AuthRequest(

        @Schema(example = "admin@gmail.com")
        @NotNull(message = "Email is required.")
        @Pattern(regexp = UserRegexPatterns.EMAIL, message = "Invalid email. Example format: example@domain.com")
        String email,

        @Schema(example = "Qwerty1234@")
        @NotNull(message = "Password is required.")
        @Pattern(regexp = UserRegexPatterns.PASSWORD, message = "Invalid password. Should include minimum 8 characters, 1 uppercase character, 1 lowercase character, 1 special symbol.")
        String password

) {
}
