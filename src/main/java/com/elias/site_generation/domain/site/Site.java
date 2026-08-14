package com.elias.site_generation.domain.site;

import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Site {

    private Long id;

    private Status status;
    private User owner;
    private String language;
    private String content;

    private Theme theme;

    private Instant createdAt;
    private Instant updatedAt;

}
