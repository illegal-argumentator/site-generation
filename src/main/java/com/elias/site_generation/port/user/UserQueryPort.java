package com.elias.site_generation.port.user;

import com.elias.site_generation.domain.user.User;

public interface UserQueryPort {

    User findByEmail(String email);
    User findById(String id);

}
