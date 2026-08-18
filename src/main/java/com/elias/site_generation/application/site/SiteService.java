package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.Status;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.exception.ThemeNotFoundException;
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
    public void create(TemplateType type, Site site) {
        throwIfTemplateNotExists(type);

        Site newSite = savePendingSite(type, site);
        String themeId = siteThemeGenerationPort.generate(newSite);

        completeSite(themeId, newSite.getId());
//        siteDeploymentPort.deploy(themeId);
    }

    private Site savePendingSite(TemplateType type, Site site) {
        site.setStatus(Status.PENDING);
        site.setType(type);
        return siteCommandPort.save(site);
    }

    private void completeSite(String themeId, Long siteId) {
        siteCommandPort.update(siteId, Site.builder().status(Status.CREATED).themeId(themeId).build());
    }

    private void throwIfTemplateNotExists(TemplateType type) {
        if (!themeQueryPort.exists(type)) {
            throw new ThemeNotFoundException("Theme not found by type: %s.".formatted(type));
        }
    }
}
