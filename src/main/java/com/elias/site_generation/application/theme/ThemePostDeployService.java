package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.theme.usecase.ThemePostDeployUseCase;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import com.elias.site_generation.shared.utils.FuncUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ThemePostDeployService implements ThemePostDeployUseCase {

    private final ThemeDeployActions deployActions;
    private final SiteCommandPort siteCommandPort;
    private final WebsiteTemplateSlugQueryPort templateSlugQueryPort;
    private final WebsiteThemeCommandPort themeCommandPort;

    @Override
    public void deploy(Site site) {
        process(site);
        updatePublished(site);
    }

    private void process(Site site) {
        switch (site.getDeployStatus()) {
            case PENDING, IN_PROGRESS, THEME_REMOVE_FAILED:
                removeTheme(site);
            case THEME_INSTALLATION_FAILED:
                installTheme(site);
        }
    }

    private void removeTheme(Site site) {
        String slug = templateSlugQueryPort.getSlug(site.getType());
        FuncUtils.runOrThrow(() -> themeCommandPort.removeTheme(slug, site.getHostname()), (e) -> new ThemePublishingException(site.getId(), "Failed to remove old theme.", DeployStatus.THEME_REMOVE_FAILED, e));
        log.info("Removed old theme for site: {}.", site.getId());
    }

    private void installTheme(Site site) {
        FuncUtils.runOrThrow(() -> deployActions.installTheme(site.getHostname(), site.getTheme().id()), (e) -> new ThemePublishingException(site.getId(), "Failed to install theme.", DeployStatus.THEME_INSTALLATION_FAILED, e));
        log.info("Installed theme for site: {}.", site.getId());
    }

    private void updatePublished(Site site) {
        Site update = Site.builder().failReason("").deployStatus(DeployStatus.PUBLISHED).build();
        siteCommandPort.update(site.getId(), update);
    }
}
