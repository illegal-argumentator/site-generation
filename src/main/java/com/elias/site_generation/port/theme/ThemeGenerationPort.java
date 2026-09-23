package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.site.Site;

public interface ThemeGenerationPort {

    String generate(String themeId, Site site);

}
