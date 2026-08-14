package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.Status;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.port.SiteDeploymentPort;
import com.elias.site_generation.port.SiteThemeGenerationPort;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.site.usecase.SiteUseCase;
import com.elias.site_generation.port.theme.ThemeQueryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class SiteService implements SiteUseCase {

    private final ThemeQueryPort themeQueryPort;
    private final SiteCommandPort siteCommandPort;
    private final SiteThemeGenerationPort siteThemeGenerationPort;
    private final SiteDeploymentPort siteDeploymentPort;

    @Override
    public void create(String themeId, Site site) {
        Theme theme = themeQueryPort.getById(themeId);
        Long newSiteId = saveNewSite(site, theme);

        String newSiteThemeId = siteThemeGenerationPort.generate(site, theme);
        log.info("Generated new site theme {}.", newSiteThemeId);

        siteDeploymentPort.deploy(newSiteThemeId);
        log.info("New site theme {} successfully deployed.", newSiteThemeId);

        siteCommandPort.update(newSiteId, Site.builder().status(Status.CREATED).build());
        log.info("Site creation flow finished.");
    }

    private Long saveNewSite(Site site, Theme theme) {
        site.setTheme(theme);
        site.setStatus(Status.PENDING);

        Long id = siteCommandPort.save(site);
        log.info("Saved new site {} with status {}.", id, site.getStatus());
        return id;
    }

}
