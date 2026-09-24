package com.elias.site_generation.adapter.user.out.persistence;

import com.elias.site_generation.adapter.site.out.persistence.PostgresSite;
import com.elias.site_generation.domain.user.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class PostgresUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private List<PostgresSite> sites;

    private Set<Role> roles;

    @Column(unique = true)
    private String email;

    private String password;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }

    public List<PostgresSite> collectSites(PostgresSite site) {
        if (CollectionUtils.isEmpty(sites)) {
            sites = new ArrayList<>(List.of(site));
        } else {
            sites.add(site);
        }

        return sites;
    }

    @Override
    public @NonNull String getUsername() {
        return email;
    }
}
