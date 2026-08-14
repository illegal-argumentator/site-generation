package com.elias.site_generation.adapter.site.out.persistence;

import com.elias.site_generation.adapter.theme.out.persistence.PostgresTheme;
import com.elias.site_generation.adapter.user.out.persistence.PostgresUser;
import com.elias.site_generation.domain.site.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
@Table(name = "sites")
public class PostgresSite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private PostgresUser owner;

    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private PostgresTheme theme;

}
