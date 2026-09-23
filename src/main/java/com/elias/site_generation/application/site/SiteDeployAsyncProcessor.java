package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.event.ThemeDeployEvent;
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
        Site deploy = saveDeployInProgress(domain, site);
        publishDeploy(deploy);
    }

    private Site saveDeployInProgress(String domain, Site site) {
        site.setHostname(domain);
        Site update = Site.builder().deployStatus(DeployStatus.IN_PROGRESS).activeStatus(ActiveStatus.PENDING).hostname(domain).build();
        return siteCommandPort.update(site.getId(), update);
    }

    private void publishDeploy(Site site) {
        publisher.publishEvent(new ThemeDeployEvent(site));
    }

}
