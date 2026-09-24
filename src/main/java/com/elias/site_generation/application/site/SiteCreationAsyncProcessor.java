package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.event.SiteActivationEvent;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.event.ThemeDeployEvent;
import com.elias.site_generation.domain.theme.event.ThemePostDeployEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.theme.ThemeCommandPort;
import com.elias.site_generation.port.theme.ThemeGenerationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SiteCreationAsyncProcessor {

    private final SiteCommandPort siteCommandPort;
    private final SitePersistenceUtils persistenceUtils;

    private final ThemeGenerationPort themeGenerationPort;
    private final ThemeCommandPort themeCommandPort;

    private final ApplicationEventPublisher publisher;

    @Async
    public void createAsync(Site site) {
        String themeId = themeCommandPort.save();
        String title = themeGenerationPort.generate(themeId, site);

        Theme updated = themeCommandPort.update(themeId, title);
        Site savedCreated = persistenceUtils.saveCreated(site.getId(), updated);

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



    private void publishDeploy(Site site) {
        siteCommandPort.update(site.getId(), Site.builder().deployStatus(DeployStatus.IN_PROGRESS).build());
        publisher.publishEvent(site.getDeployStatus() == DeployStatus.THEME_REMOVE_FAILED ? new ThemePostDeployEvent(site) : new ThemeDeployEvent(site));
    }

}
