package com.elias.site_generation.domain.user;

import com.elias.site_generation.domain.site.Site;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class User {

    private String id;
    private List<Site> sites;

    private Set<Role> roles;

    private String email;
    private String password;

    private Instant createdAt;
    private Instant updatedAt;

    public Set<String> toRoleNames() {
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    public List<Site> collectSites(Site site) {
        if (CollectionUtils.isEmpty(sites)) {
            sites = new ArrayList<>(List.of(site));
        } else {
            sites.add(site);
        }

        return sites;
    }

}
