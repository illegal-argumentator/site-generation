package com.elias.site_generation.adapter.theme.out.persistence;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "themes")
public class PostgresTheme{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

}
