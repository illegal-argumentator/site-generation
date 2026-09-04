package com.elias.site_generation.domain.site;

import com.elias.site_generation.domain.site.exception.SiteActivationException;
import com.elias.site_generation.domain.site.exception.SiteDeployException;
import com.elias.site_generation.domain.site.exception.SiteHasNotCreatedException;
import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.Instant;

@Data
@Builder
public class Site {

    private Long id;

    private String language;
    private String content;

    private Db db;
    private String hostname;

    private CreationStatus creationStatus;
    private DeployStatus deployStatus;
    private ActiveStatus activeStatus;

    private String failReason;

    @With
    private Theme theme;
    private TemplateType type;

    private Instant createdAt;
    private Instant updatedAt;

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
