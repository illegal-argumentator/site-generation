package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.port.site.usecase.SiteEditUseCase;
import com.elias.site_generation.port.theme.ThemeEditPort;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteEditService implements SiteEditUseCase {

    private final ThemeEditPort themeEditPort;

    private final WebsiteThemeCommandPort websiteThemeCommandPort;
    private final WebsiteTemplateSlugQueryPort websiteTemplateSlugQueryPort;

    @Override
    public void edit(String content, TemplateComponent component, Site site) {
        String slug = websiteTemplateSlugQueryPort.getSlug(site.getType());
        websiteThemeCommandPort.removeTheme(slug, site.getHostname());
        byte[] editedTheme = themeEditPort.edit(content, component, site);
        // TODO activate it automatically
    }

}
