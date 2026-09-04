package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.event.SiteActivationEvent;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.event.ThemePublishEvent;
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
    public void createAsync(TemplateType type, User user, Site site) {
        Site savedPending = saveInit(type, site);
        saveUserSite(user, savedPending);

        String themeId = themeCommandPort.save();
        String title = themeGenerationPort.generate(themeId, site);

        Theme updated = themeCommandPort.update(themeId, title);
        Site savedCreated = saveCreated(savedPending.getId(), updated);

        publishDeploy(savedCreated);
    }

    @Async
    public void publishActivationAsync(Site site) {
        if (site.getActiveStatus() != null && site.getActiveStatus() != ActiveStatus.PENDING) {
            Site updated = siteCommandPort.update(site.getId(), Site.builder().activeStatus(ActiveStatus.IN_PROGRESS).build());
            publisher.publishEvent(new SiteActivationEvent(updated));
            return;
        }

        publisher.publishEvent(new SiteActivationEvent(site));
    }

    @Async
    public void publishDeployAsync(Site site) {
        publishDeploy(site);
    }

    private Site saveInit(TemplateType type, Site site) {
        site.setCreationStatus(CreationStatus.IN_PROGRESS);
        site.setActiveStatus(ActiveStatus.PENDING);
        site.setDeployStatus(DeployStatus.PENDING);
        site.setType(type);
        site.setDb(dbGenerationPort.generate());

        return siteCommandPort.save(site);
    }

    private void saveUserSite(User user, Site site) {
        userCommandPort.update(user.getId(), User.builder().sites(user.collectSites(site)).build());
    }

    private Site saveCreated(long siteId, Theme theme) {
        Site forUpdate = Site.builder().creationStatus(CreationStatus.CREATED).theme(theme).build();
        return siteCommandPort.update(siteId, forUpdate);
    }

    private void publishDeploy(Site site) {
        if (site.getDeployStatus() != null && site.getDeployStatus() != DeployStatus.PENDING) {
            Site updated = siteCommandPort.update(site.getId(), Site.builder().failReason(null).deployStatus(DeployStatus.IN_PROGRESS).build());
            publisher.publishEvent(new ThemePublishEvent(updated));
            return;
        }

        publisher.publishEvent(new ThemePublishEvent(site));
    }

}
