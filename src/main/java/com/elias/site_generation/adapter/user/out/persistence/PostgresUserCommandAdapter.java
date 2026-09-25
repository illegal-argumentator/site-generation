package com.elias.site_generation.adapter.user.out.persistence;

import com.elias.site_generation.adapter.site.out.mapper.SiteMapper;
import com.elias.site_generation.adapter.site.out.persistence.PostgresSite;
import com.elias.site_generation.adapter.user.out.mapper.UserMapper;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.domain.user.exception.UserAlreadyExistsException;
import com.elias.site_generation.domain.user.exception.UserNotFoundException;
import com.elias.site_generation.port.site.SiteQueryPort;
import com.elias.site_generation.port.user.UserCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostgresUserCommandAdapter implements UserCommandPort {

    private final UserMapper userMapper;
    private final PostgresUserRepository repository;

    private final SiteMapper siteMapper;
    private final SiteQueryPort siteQueryPort;

    @Override
    public User save(User user) {
        PostgresUser entity = userMapper.toEntity(user);

        try {
            PostgresUser saved = repository.save(entity);
            return userMapper.toUser(saved);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("User already exists.");
        }
    }

    @Override
    public void update(String id, User user) {
        PostgresUser entity = findById(id);
        userMapper.updateEntity(entity, user);
        repository.save(entity);
    }

    @Override
    public void addSite(String userId, long siteId) {
        PostgresUser postgresUser = findById(userId);

        Site site = siteQueryPort.findById(siteId);
        PostgresSite postgresSite = siteMapper.toEntity(site);

        postgresUser.setSites(postgresUser.collectSites(postgresSite));
        repository.save(postgresUser);
    }

    private PostgresUser findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

}
