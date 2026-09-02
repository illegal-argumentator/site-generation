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

import java.time.Instant;
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

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PostgresSite> sites;

    private Set<Role> roles;

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

    @Override
    public String getUsername() {
        return email;
    }
}
