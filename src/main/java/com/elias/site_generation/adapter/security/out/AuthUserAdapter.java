package com.elias.site_generation.adapter.security.out;

import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.auth.AuthUserPort;
import org.springframework.stereotype.Component;

@Component
public class AuthUserAdapter implements AuthUserPort {

    @Override
    public User getAuthUser() {
        return SecurityContextHelper.getAuthFromContext(User.class);
    }

}
