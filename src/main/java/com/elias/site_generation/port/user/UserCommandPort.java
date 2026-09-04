package com.elias.site_generation.port.user;

import com.elias.site_generation.domain.user.User;

public interface UserCommandPort {

    User save(User user);

    void update(String id, User user);


}
