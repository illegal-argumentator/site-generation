package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.site.Site;

public interface ThemeActivationUseCase {

    void activate(String themeName, Site site);

}
