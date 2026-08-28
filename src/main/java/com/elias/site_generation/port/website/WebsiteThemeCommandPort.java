package com.elias.site_generation.port.website;

import com.elias.site_generation.domain.site.nested.Db;

public interface WebsiteThemeCommandPort {

    void downloadWebsite(String hostname);

    void installWebsite(String hostname);

    void installTheme(String themePath, String hostname);

    void activateTheme(String name, String hostname);

    void createConfig(Db db, String hostname);

}
