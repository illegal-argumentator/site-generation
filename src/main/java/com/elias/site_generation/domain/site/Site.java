package com.elias.site_generation.domain.site;

import com.elias.site_generation.domain.site.exception.SiteActivationException;
import com.elias.site_generation.domain.site.exception.SiteDeployException;
import com.elias.site_generation.domain.site.exception.SiteEditException;
import com.elias.site_generation.domain.site.exception.SiteCreationException;
import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class Site {

    private Long id;

    private String language;
    private String content;

    private Db db;
    private String hostname;

    private DeployStatus deployStatus;
    private ActiveStatus activeStatus;
    private CreationStatus creationStatus;

    private String failReason;

    @With
    private Theme theme;
    private TemplateType type;

    private Instant createdAt;
    private Instant updatedAt;

    public void validateHasComponent(TemplateComponent component) {
        if (!type.getComponents().contains(component)) {
            throw new SiteEditException("Site has no %s component.".formatted(component));
        }
    }

    public void validateReadyForRedeploy() {
        throwIfAtLeastOneStatusInProgress(new SiteDeployException("Site is still in progress."));
        throwIfCreationStatusNotCreated();
        throwIfDeployStatusPublished();
        throwIfActiveStatusActivated();
    }

    public void validateReadyForActivation() {
        throwIfAtLeastOneStatusInProgress(new SiteActivationException("Site is still in progress."));
        throwIfCreationStatusNotCreated();
        throwIfDeployStatusNotPublished();
        throwIfActiveStatusActivated();
    }

    public void validateReadyForEdit() {
        throwIfAtLeastOneStatusInProgress(new SiteEditException("Site is still in progress."));
        throwIfCreationStatusNotCreated();
        throwIfDeployStatusNotPublished();
        // TODO what if site was activated and we updated it? should we activate it once again?
    }

    public void validateReadyForRecreation() {
        throwIfAtLeastOneStatusInProgress(new SiteCreationException("Site is still in progress."));
        throwIfCreationStatusCreated();
        throwIfDeployStatusPublished();
        throwIfActiveStatusActivated();
    }

    public static boolean hasMoreOrEqualInProgressThanLimit(int max, List<Site> sites) {
        return getInProgressCount(sites) >= max;
    }

    public static long getInProgressCount(List<Site> sites) {
        return sites.stream().filter(site ->
                site.getActiveStatus() == ActiveStatus.IN_PROGRESS ||
                        site.getDeployStatus() == DeployStatus.IN_PROGRESS ||
                        site.getCreationStatus() == CreationStatus.IN_PROGRESS
        ).count();
    }

    public static Set<Long> collectIds(List<Site> sites) {
        return sites.stream().map(Site::getId).collect(Collectors.toSet());
    }

    private void throwIfDeployStatusPublished() {
        if (deployStatus == DeployStatus.PUBLISHED) {
            throw new SiteDeployException("Site already deployed.");
        }
    }

    private void throwIfDeployStatusNotPublished() {
        if (deployStatus != DeployStatus.PUBLISHED) {
            throw new SiteDeployException("Site not deployed.");
        }
    }

    private void throwIfActiveStatusActivated() {
        if (activeStatus == ActiveStatus.ACTIVATED) {
            throw new SiteActivationException("Site already activated.");
        }
    }

    private void throwIfCreationStatusCreated() {
        if (creationStatus == CreationStatus.CREATED) {
            throw new SiteCreationException("Site has already created.");
        }
    }

    private void throwIfCreationStatusNotCreated() {
        if (creationStatus != CreationStatus.CREATED) {
            throw new SiteCreationException("Site has not created yet.");
        }
    }

    private void throwIfAtLeastOneStatusInProgress(RuntimeException ex) {
        if (
                this.creationStatus == CreationStatus.IN_PROGRESS ||
                this.deployStatus == DeployStatus.IN_PROGRESS ||
                this.activeStatus == ActiveStatus.IN_PROGRESS) {
            throw ex;
        }
    }
}
