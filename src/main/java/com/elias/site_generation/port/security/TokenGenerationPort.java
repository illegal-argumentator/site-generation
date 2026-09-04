package com.elias.site_generation.port.security;

import com.elias.site_generation.application.auth.command.TokenPayloadCommand;
import com.elias.site_generation.domain.user.User;

public interface TokenGenerationPort {

    TokenPayloadCommand generate(User user);

}
