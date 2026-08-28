package com.elias.site_generation.domain.site;

import com.elias.site_generation.domain.site.exception.SiteAlreadyDeployedException;
import com.elias.site_generation.domain.site.exception.SiteHasNotCreatedException;
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

    private Db db;

    private String failReason;

    private TemplateType type;
    private String themeId;

    private Instant createdAt;
    private Instant updatedAt;

    private static final String DB_NAME_PREFIX = "name", DB_USER_PREFIX = "user";
    private static final int FIRST = 0, FOUR = 4, EIGHT = 8;

    public static Db generateDbCreds() {
         String user = DB_USER_PREFIX.concat(UUID.randomUUID().toString().substring(FIRST, FOUR));
         String name = DB_NAME_PREFIX.concat(UUID.randomUUID().toString().substring(FIRST, FOUR));
         String password = String.valueOf(UUID.randomUUID()).substring(FIRST, EIGHT);
         return new Db(user, name, password);
    }

    public void validateReadyForRedeploy() {
        throwIfAlreadyDeployed();
        throwIfNotCreated();
    }

    private void throwIfAlreadyDeployed() {
        if (status == Status.PUBLISHED) {
            throw new SiteAlreadyDeployedException("Site already deployed.");
        }
    }

    private void throwIfNotCreated() {
        if (status == Status.PENDING || status == Status.FAILED) {
            throw new SiteHasNotCreatedException("Site has not created yet.");
        }
    }


}
