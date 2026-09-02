package com.elias.site_generation.adapter.user.out.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostgresUserRepository extends CrudRepository<PostgresUser, String> {

    Optional<PostgresUser> findByEmail(String email);

}
