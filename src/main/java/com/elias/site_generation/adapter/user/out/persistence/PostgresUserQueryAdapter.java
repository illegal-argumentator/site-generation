package com.elias.site_generation.adapter.user.out.persistence;

import com.elias.site_generation.adapter.user.out.mapper.UserMapper;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.domain.user.exception.UserNotFoundException;
import com.elias.site_generation.port.user.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresUserQueryAdapter implements UserQueryPort {

    private final UserMapper mapper;
    private final PostgresUserRepository repository;

    @Override
    public User findByEmail(String email) {
        PostgresUser entity = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        return mapper.toUser(entity);
    }

    @Override
    public User findById(String id) {
        PostgresUser entity = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        return mapper.toUser(entity);
    }
}
