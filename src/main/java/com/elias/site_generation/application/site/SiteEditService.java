package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.domain.theme.event.ThemePublishEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.site.usecase.SiteEditUseCase;
import com.elias.site_generation.port.theme.ThemeEditPort;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
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

    private final WebsiteThemeCommandPort websiteThemeCommandPort;
    private final WebsiteTemplateSlugQueryPort websiteTemplateSlugQueryPort;

    private final ApplicationEventPublisher publisher;

    @Override
    public void edit(String content, TemplateComponent component, Site site) {
        themeEditPort.process(content, component, site);
        log.info("Successfully edited site component.");

        String slug = websiteTemplateSlugQueryPort.getSlug(site.getType());
        websiteThemeCommandPort.removeTheme(slug, site.getHostname());
        log.info("Removed old theme from WordPress.");

        Site updated = saveDeployInProgress(site.getId());
        publishDeploy(updated);
    }

    private Site saveDeployInProgress(long siteId) {
        return siteCommandPort.update(siteId, Site.builder().creationStatus(CreationStatus.CREATED).deployStatus(DeployStatus.IN_PROGRESS).build());
    }

    private void publishDeploy(Site site) {
        publisher.publishEvent(new ThemePublishEvent(site));
    }
}
