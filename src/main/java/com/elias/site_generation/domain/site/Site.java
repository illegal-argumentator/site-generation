package com.elias.site_generation.domain.site;

import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.type.Status;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class Site {

    private Long id;

    private Status status;
    private User owner;
    private String language;
    private String content;

    private String hostname;
    private String dbUser;
    private String dbPass;

    private String failReason;

    private TemplateType type;
    private String themeId;

    private Instant createdAt;
    private Instant updatedAt;

    private static final String DB_USER_PREFIX = "admin";
    private static final int FIRST = 0, FOUR = 4, EIGHT = 8;

    public static Db generateDbCreds() {
         String username = DB_USER_PREFIX.concat(UUID.randomUUID().toString().substring(FIRST, FOUR));
         String password = String.valueOf(UUID.randomUUID()).substring(FIRST, EIGHT);
         return new Db(username, password);
    }

}
