package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.Theme;

public interface ThemePublishUseCase {

    void publish(Site site, Theme theme);

}
