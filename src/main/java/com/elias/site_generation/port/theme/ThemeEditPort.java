package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateComponent;

public interface ThemeEditPort {

    byte[] edit(String content, TemplateComponent component, Site site);

}
