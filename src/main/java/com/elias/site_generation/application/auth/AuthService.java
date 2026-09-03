package com.elias.site_generation.application.auth;

import com.elias.site_generation.application.auth.command.AuthRequestCommand;
import com.elias.site_generation.application.auth.command.AuthResponseCommand;
import com.elias.site_generation.application.auth.command.RefreshRequestCommand;
import com.elias.site_generation.application.auth.command.TokenPayloadCommand;
import com.elias.site_generation.domain.user.Role;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.domain.user.exception.IncorrectPasswordException;
import com.elias.site_generation.port.auth.AuthUseCase;
import com.elias.site_generation.port.security.PasswordHashingPort;
import com.elias.site_generation.port.security.TokenGenerationPort;
import com.elias.site_generation.port.security.TokenIdentityPort;
import com.elias.site_generation.port.user.UserCommandPort;
import com.elias.site_generation.port.user.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserQueryPort userQueryPort;
    private final UserCommandPort userCommandPort;

    private final TokenIdentityPort tokenIdentityPort;
    private final TokenGenerationPort tokenGenerationPort;
    private final PasswordHashingPort passwordHashingPort;

    @Override
    public AuthResponseCommand signIn(AuthRequestCommand command) {
        User user = userQueryPort.findByEmail(command.email());

        boolean matches = passwordHashingPort.matches(command.password(), user.getPassword());
        if (!matches) throw new IncorrectPasswordException("Wrong credentials.");

        return buildAuthResponse(user);
    }

    @Override
    public AuthResponseCommand signUp(AuthRequestCommand command) {
        String hashedPassword = passwordHashingPort.hash(command.password());
        User user = userCommandPort.save(User.builder()
                .email(command.email())
                .roles(Set.of(Role.USER))
                .password(hashedPassword)
                .build());

        return buildAuthResponse(user);
    }

    @Override
    public AuthResponseCommand refresh(RefreshRequestCommand command) {
        User user = userQueryPort.findById(tokenIdentityPort.extractId(command.refreshToken()));
        return buildAuthResponse(user);
    }

    private AuthResponseCommand buildAuthResponse(User user) {
        TokenPayloadCommand tokenPayload = tokenGenerationPort.generate(user);
        return AuthResponseCommand.builder()
                .accessToken(tokenPayload.accessToken())
                .refreshToken(tokenPayload.refreshToken())
                .build();
    }

}
