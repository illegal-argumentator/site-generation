package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.theme.Theme;

public interface ThemeCommandPort {

    String save();
    Theme update(String id, String title);

}
