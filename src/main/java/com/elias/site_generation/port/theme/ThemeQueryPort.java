package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.theme.Theme;

public interface ThemeQueryPort {

    Theme getById(String id);

}
