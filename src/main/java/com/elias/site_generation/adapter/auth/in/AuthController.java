package com.elias.site_generation.adapter.auth.in;

import com.elias.site_generation.adapter.auth.in.dto.AuthRequest;
import com.elias.site_generation.adapter.auth.in.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/sign-in")
    ResponseEntity<AuthResponse> signIn(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(new AuthResponse("access", "refresh"));
    }

    @PostMapping("/sign-up")
    ResponseEntity<AuthResponse> signUp(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(new AuthResponse("access", "refresh"));
    }
}