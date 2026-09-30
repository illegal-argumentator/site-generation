package com.elias.site_generation.adapter.template.out.persistence;

import com.elias.site_generation.adapter.image.out.persistence.PostgresImage;
import com.elias.site_generation.domain.theme.TemplateType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "templates")
public class PostgresTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private PostgresImage image;

    @Enumerated(EnumType.STRING)
    private TemplateType type;

}
