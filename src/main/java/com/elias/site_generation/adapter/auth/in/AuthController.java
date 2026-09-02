package com.elias.site_generation.adapter.auth.in;

import com.elias.site_generation.adapter.auth.in.dto.AuthRequest;
import com.elias.site_generation.adapter.auth.in.dto.AuthResponse;
import com.elias.site_generation.adapter.auth.in.dto.RefreshRequest;
import com.elias.site_generation.adapter.auth.out.mapper.AuthMapper;
import com.elias.site_generation.application.auth.command.AuthResponseCommand;
import com.elias.site_generation.port.auth.AuthUseCase;
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

    private final AuthMapper mapper;
    private final AuthUseCase useCase;

    @PostMapping("/sign-in")
    ResponseEntity<AuthResponse> signIn(@Valid @RequestBody AuthRequest request) {
        AuthResponseCommand responseCommand = useCase.signIn(mapper.toCommand(request));
        return ResponseEntity.ok(mapper.toResponse(responseCommand));
    }

    @PostMapping("/sign-up")
    ResponseEntity<AuthResponse> signUp(@Valid @RequestBody AuthRequest request) {
        AuthResponseCommand responseCommand = useCase.signIn(mapper.toCommand(request));
        return ResponseEntity.ok(mapper.toResponse(responseCommand));
    }

    @PostMapping("/refresh")
    ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest request) {
        AuthResponseCommand responseCommand = useCase.refresh(mapper.toCommand(request));
        return ResponseEntity.ok(mapper.toResponse(responseCommand));
    }
}