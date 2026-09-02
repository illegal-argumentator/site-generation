package com.elias.site_generation.domain.user;

import com.elias.site_generation.domain.site.Site;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record User(
        String id,
        List<Site> sites,

        Set<Role> roles,

        String email,
        String password,

        Instant createdAt,
        Instant updatedAt
) {

    public Set<String> toRoleNames() {
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

}
