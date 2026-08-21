package com.elias.site_generation.adapter.site.out.persistence;

import com.elias.site_generation.domain.site.Status;
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
    private Status status;

    private String content;
    private String language;
    private String failReason;

    private TemplateType type;
    private String themeId;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private PostgresUser owner;
}
