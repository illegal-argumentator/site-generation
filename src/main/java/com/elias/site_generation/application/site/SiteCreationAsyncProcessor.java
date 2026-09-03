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
import com.elias.site_generation.port.auth.AuthUserPort;
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

    private final AuthUserPort authUserPort;
    private final UserCommandPort userCommandPort;

    private final DbGenerationPort dbGenerationPort;
    private final SiteCommandPort siteCommandPort;

    private final ThemeGenerationPort themeGenerationPort;
    private final ThemeCommandPort themeCommandPort;

    private final ApplicationEventPublisher publisher;

    @Async
    public void createAsync(TemplateType type, Site site) {
        User owner = authUserPort.getAuthUser();
        Site savedPending = savePending(type, site);

        String themeId = themeCommandPort.save();
        String title = themeGenerationPort.generate(themeId, site);

        Theme updated = themeCommandPort.update(themeId, title);
        Site savedCreated = saveCreated(savedPending.getId(), updated);

        userCommandPort.update(owner.getId(), User.builder().sites(owner.collectSites(site)).build());
        publishDeploy(savedCreated);
    }

    @Async
    public void publishActivationAsync(Site site) {
        siteCommandPort.update(site.getId(), Site.builder().activeStatus(ActiveStatus.PENDING).build());
        publisher.publishEvent(new SiteActivationEvent(site));
    }

    @Async
    public void publishDeployAsync(Site site) {
        publishDeploy(site);
    }

    private Site savePending(TemplateType type, Site site) {
        site.setCreationStatus(CreationStatus.PENDING);
        site.setType(type);
        site.setDb(dbGenerationPort.generate());
        return siteCommandPort.save(site);
    }

    private Site saveCreated(long siteId, Theme theme) {
        Site forUpdate = Site.builder().creationStatus(CreationStatus.CREATED).theme(theme).build();
        return siteCommandPort.update(siteId, forUpdate);
    }

    private void publishDeploy(Site site) {
        siteCommandPort.update(site.getId(), Site.builder().deployStatus(DeployStatus.PENDING).build());
        publisher.publishEvent(new ThemePublishEvent(site));
    }

}
