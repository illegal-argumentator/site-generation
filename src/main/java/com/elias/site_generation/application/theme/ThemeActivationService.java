package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.theme.exception.ThemeActivationException;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.theme.ThemeActivationUseCase;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import com.elias.site_generation.shared.utils.FuncUtils;
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

    @Override
    public void activate(Site site) {
        String slug = websiteTemplateSlugQueryPort.getSlug(site.getType());

        deleteIndex(site);
        activateTheme(slug, site);

        siteCommandPort.update(site.getId(), Site.builder().activeStatus(ActiveStatus.ACTIVATED).build());
        log.info("Theme activated.");
    }

    private void deleteIndex(Site site) {
        if (shouldRunStep(site.getActiveStatus(), ActiveStatus.INDEX_DELETION_FAILED)) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.deleteIndex(site.getHostname()), new ThemeActivationException(site.getId(), "Failed to delete index.", ActiveStatus.INDEX_DELETION_FAILED));
            log.info("Deleted index for site: {}.", site.getId());
        }
    }

    private void activateTheme(String slug, Site site) {
        if (shouldRunStep(site.getActiveStatus(), ActiveStatus.ACTIVATION_FAILED)) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.activateTheme(slug, site.getHostname()), new ThemeActivationException(site.getId(), "Failed to activate theme.", ActiveStatus.ACTIVATION_FAILED));
            log.info("Activated theme for site: {}.", site.getId());
        }
    }

    private boolean shouldRunStep(ActiveStatus status, ActiveStatus fail) {
        return (status == null || status == ActiveStatus.IN_PROGRESS) || status == fail;
    }
}
