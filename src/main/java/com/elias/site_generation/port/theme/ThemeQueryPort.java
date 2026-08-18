package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;

public interface ThemeQueryPort {

    boolean exists(TemplateType type);

}
