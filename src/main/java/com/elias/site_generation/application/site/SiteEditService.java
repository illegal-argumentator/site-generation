package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.domain.theme.event.ThemePostDeployEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.site.usecase.SiteEditUseCase;
import com.elias.site_generation.port.theme.ThemeEditPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteEditService implements SiteEditUseCase {

    private final SiteCommandPort siteCommandPort;
    private final ThemeEditPort themeEditPort;
    private final ApplicationEventPublisher publisher;

    @Override
    public void edit(String content, TemplateComponent component, Site site) {
        themeEditPort.process(content, component, site);
        log.info("Successfully edited site component.");

        Site updated = saveDeployInProgress(site.getId());
        publishPostDeploy(updated);
    }

    private Site saveDeployInProgress(long siteId) {
        return siteCommandPort.update(siteId, Site.builder().creationStatus(CreationStatus.CREATED).deployStatus(DeployStatus.IN_PROGRESS).build());
    }

    private void publishPostDeploy(Site site) {
        publisher.publishEvent(new ThemePostDeployEvent(site));
    }

}
