package com.elias.site_generation.port;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.Theme;

public interface SiteThemeGenerationPort {

    String generate(Site site, Theme theme);

}
