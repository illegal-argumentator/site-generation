package com.elias.site_generation.adapter.user.out.persistence;

import com.elias.site_generation.adapter.user.out.mapper.UserMapper;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.domain.user.exception.UserAlreadyExistsException;
import com.elias.site_generation.domain.user.exception.UserNotFoundException;
import com.elias.site_generation.port.user.UserCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresUserCommandAdapter implements UserCommandPort {

    private final UserMapper mapper;
    private final PostgresUserRepository repository;

    @Override
    public User save(User user) {
        PostgresUser entity = mapper.toEntity(user);

        try {
            PostgresUser saved = repository.save(entity);
            return mapper.toUser(saved);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("User already exists.");
        }
    }

    @Override
    public void update(String id, User user) {
        PostgresUser entity = findById(id);
        mapper.updateEntity(entity, user);
        repository.save(entity);
    }

    private PostgresUser findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

}
