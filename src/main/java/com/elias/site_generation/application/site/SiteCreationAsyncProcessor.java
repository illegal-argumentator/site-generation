package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.event.SiteActivationEvent;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.event.ThemeDeployEvent;
import com.elias.site_generation.domain.theme.event.ThemePostDeployEvent;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.site.DbGenerationPort;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.theme.ThemeCommandPort;
import com.elias.site_generation.port.theme.ThemeGenerationPort;
import com.elias.site_generation.port.user.UserCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SiteCreationAsyncProcessor {

    private final UserCommandPort userCommandPort;

    private final DbGenerationPort dbGenerationPort;
    private final SiteCommandPort siteCommandPort;

    private final ThemeGenerationPort themeGenerationPort;
    private final ThemeCommandPort themeCommandPort;

    private final ApplicationEventPublisher publisher;

    @Async
    public void createAsync(long siteId, TemplateType type, User user) {
        Site savedPending = saveCreationInProgress(siteId, type);
        processCreationAsync(user, savedPending);
    }

    @Async
    public void recreateAsync(long siteId, User user) {
        Site savedPending = saveRecreationInProgress(siteId);
        processCreationAsync(user, savedPending);
    }

    private void processCreationAsync(User user, Site savedPending) {
        saveUserSite(user, savedPending);

        String themeId = themeCommandPort.save();
        String title = themeGenerationPort.generate(themeId, savedPending);

        Theme updated = themeCommandPort.update(themeId, title);
        Site savedCreated = saveCreated(savedPending.getId(), updated);

        publishDeploy(savedCreated);
    }

    @Async
    public void publishActivationAsync(Site site) {
        siteCommandPort.update(site.getId(), Site.builder().activeStatus(ActiveStatus.IN_PROGRESS).build());
        publisher.publishEvent(new SiteActivationEvent(site));
    }

    @Async
    public void publishDeployAsync(Site site) {
        publishDeploy(site);
    }

    private Site saveCreationInProgress(long siteId, TemplateType type) {
        Site update = Site.builder()
                .creationStatus(CreationStatus.IN_PROGRESS)
                .activeStatus(ActiveStatus.PENDING)
                .deployStatus(DeployStatus.PENDING)
                .type(type)
                .db(dbGenerationPort.generate())
                .build();

        return siteCommandPort.update(siteId, update);
    }

    private Site saveRecreationInProgress(long siteId) {
        Site update = Site.builder().creationStatus(CreationStatus.IN_PROGRESS).activeStatus(ActiveStatus.PENDING).deployStatus(DeployStatus.PENDING).build();
        return siteCommandPort.update(siteId, update);
    }

    private void saveUserSite(User user, Site site) {
        userCommandPort.update(user.getId(), User.builder().sites(user.collectSites(site)).build());
    }

    private Site saveCreated(long siteId, Theme theme) {
        Site forUpdate = Site.builder().creationStatus(CreationStatus.CREATED).theme(theme).build();
        return siteCommandPort.update(siteId, forUpdate);
    }

    private void publishDeploy(Site site) {
        siteCommandPort.update(site.getId(), Site.builder().deployStatus(DeployStatus.IN_PROGRESS).build());
        publisher.publishEvent(site.getDeployStatus() == DeployStatus.THEME_REMOVE_FAILED ? new ThemePostDeployEvent(site) : new ThemeDeployEvent(site));
    }

}
