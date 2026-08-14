package com.elias.site_generation.domain.user;

import com.elias.site_generation.domain.site.Site;

import java.time.Instant;
import java.util.List;

public record User(
        String id,
        List<Site> sites,

        Role role,

        String nickname,
        String password,

        Instant createdAt,
        Instant updatedAt
) {
}
