package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.event.ThemePublishEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SiteDeployAsyncProcessor {

    private final SiteCommandPort siteCommandPort;

    private final ApplicationEventPublisher publisher;

    @Async
    public void publishAsync(String domain, Site site) {
        saveDeployPending(domain, site);
        publishDeploy(site);
    }

    private void saveDeployPending(String domain, Site site) {
        site.setHostname(domain);
        Site update = Site.builder().deployStatus(DeployStatus.IN_PROGRESS).activeStatus(ActiveStatus.PENDING).hostname(domain).build();
        siteCommandPort.update(site.getId(), update);
    }

    private void publishDeploy(Site site) {
        siteCommandPort.update(site.getId(), Site.builder().deployStatus(DeployStatus.IN_PROGRESS).build());
        publisher.publishEvent(new ThemePublishEvent(site));
    }

}
