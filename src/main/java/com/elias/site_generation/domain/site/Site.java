package com.elias.site_generation.domain.site;

import com.elias.site_generation.domain.site.exception.SiteActivationException;
import com.elias.site_generation.domain.site.exception.SiteDeployException;
import com.elias.site_generation.domain.site.exception.SiteHasNotCreatedException;
import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
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

    private User owner;
    private String language;
    private String content;

    private Db db;
    private String hostname;

    private CreationStatus creationStatus;
    private DeployStatus deployStatus;
    private ActiveStatus activeStatus;

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
        throwIfAlreadyPublished();
        throwIfNotCreated();
    }

    public void validateReadyForActivation() {
        throwIfNotCreated();
        throwIfNotPublished();
        throwIfAlreadyActivated();
    }

    private void throwIfAlreadyPublished() {
        if (deployStatus == DeployStatus.PUBLISHED) {
            throw new SiteDeployException("Site already deployed.");
        }
    }

    private void throwIfNotPublished() {
        if (deployStatus != DeployStatus.PUBLISHED) {
            throw new SiteDeployException("Site not deployed.");
        }
    }

    private void throwIfAlreadyActivated() {
        if (activeStatus == ActiveStatus.ACTIVATED) {
            throw new SiteActivationException("Site already activated.");
        }
    }

    private void throwIfNotCreated() {
        if (creationStatus != CreationStatus.CREATED) {
            throw new SiteHasNotCreatedException("Site has not created yet.");
        }
    }


}
