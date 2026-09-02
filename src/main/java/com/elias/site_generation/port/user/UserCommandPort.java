package com.elias.site_generation.port.user;

import com.elias.site_generation.domain.user.User;

public interface UserCommandPort {

    User save(User user);

}
