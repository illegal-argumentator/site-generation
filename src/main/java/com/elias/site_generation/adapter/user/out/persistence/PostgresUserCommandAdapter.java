package com.elias.site_generation.adapter.user.out.persistence;

import com.elias.site_generation.adapter.user.out.mapper.UserMapper;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.user.UserCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresUserCommandAdapter implements UserCommandPort {

    private final UserMapper mapper;
    private final PostgresUserRepository repository;

    @Override
    public User save(User user) {
        PostgresUser entity = mapper.toEntity(user);
        PostgresUser saved = repository.save(entity);
        return mapper.toUser(saved);
    }
}
