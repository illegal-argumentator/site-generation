package com.elias.site_generation.adapter.site.out.persistence;

import com.elias.site_generation.adapter.theme.out.persistence.PostgresTheme;
import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@Table(name = "sites")
@EntityListeners(AuditingEntityListener.class)
public class PostgresSite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ActiveStatus activeStatus;

    @Enumerated(value = EnumType.STRING)
    private CreationStatus creationStatus;

    @Enumerated(value = EnumType.STRING)
    private DeployStatus deployStatus;

    private String content;
    private String language;
    private String failReason;

    private String hostname;

    @Embedded
    private Db db;

    private TemplateType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    private PostgresTheme theme;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private PostgresUser owner;
}
