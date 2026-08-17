package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.theme.Theme;

public interface ThemeCommandPort {

    void save(Theme theme);

}
