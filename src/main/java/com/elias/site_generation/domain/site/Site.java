package com.elias.site_generation.domain.site;

import com.elias.site_generation.domain.theme.TemplateType;
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

    private TemplateType type;
    private String themeId;

    private Instant createdAt;
    private Instant updatedAt;

}
