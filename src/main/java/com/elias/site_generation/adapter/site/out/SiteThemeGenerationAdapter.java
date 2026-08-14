package com.elias.site_generation.adapter.site.out;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.port.SiteThemeGenerationPort;
import org.springframework.stereotype.Component;

@Component
class SiteThemeGenerationAdapter implements SiteThemeGenerationPort {
    @Override
    public String generate(Site site, Theme theme) {
        return "";
    }
}
