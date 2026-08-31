package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.theme.ThemeActivationUseCase;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThemeActivationService implements ThemeActivationUseCase {

    private final WebsiteThemeCommandPort websiteThemeCommandPort;
    private final SiteCommandPort siteCommandPort;

    @Override
    public void activate(String themeName, Site site) {
        websiteThemeCommandPort.deleteIndex(site.getHostname());
        websiteThemeCommandPort.activateTheme(themeName, site.getHostname());
        siteCommandPort.update(site.getId(), Site.builder().activeStatus(ActiveStatus.ACTIVATED).build());
        log.info("Theme {} activated.", themeName);
    }
}
