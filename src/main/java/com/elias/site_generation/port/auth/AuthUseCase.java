package com.elias.site_generation.port.auth;

import com.elias.site_generation.application.auth.command.AuthRequestCommand;
import com.elias.site_generation.application.auth.command.AuthResponseCommand;
import com.elias.site_generation.application.auth.command.RefreshRequestCommand;

public interface AuthUseCase {

    AuthResponseCommand signIn(AuthRequestCommand command);

    AuthResponseCommand signUp(AuthRequestCommand command);

    AuthResponseCommand refresh(RefreshRequestCommand command);

}
