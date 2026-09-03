package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.theme.ThemeActivationUseCase;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThemeActivationService implements ThemeActivationUseCase {

    private final WebsiteTemplateSlugQueryPort websiteTemplateSlugQueryPort;
    private final WebsiteThemeCommandPort websiteThemeCommandPort;
    private final SiteCommandPort siteCommandPort;

    // TODO handle errors, if failed send activation failed
    // TODO in progress status, pending - as default value when site initialized for all statuses

    @Override
    public void activate(Site site) {
        String slug = websiteTemplateSlugQueryPort.getSlug(site.getType());
        websiteThemeCommandPort.deleteIndex(site.getHostname());
        websiteThemeCommandPort.activateTheme(slug, site.getHostname());
        siteCommandPort.update(site.getId(), Site.builder().activeStatus(ActiveStatus.ACTIVATED).build());
        log.info("Theme activated.");
    }
}
