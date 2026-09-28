package com.elias.site_generation.adapter.image.out.persistence;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class PostgresImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String filename;
    private String contentType;

    @Lob
    private byte[] bytes;

}
