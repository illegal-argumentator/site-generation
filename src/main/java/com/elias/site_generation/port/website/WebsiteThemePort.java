package com.elias.site_generation.port.website;

public interface WebsiteThemePort {

    void downloadWebsite();

    void installWebsite();

    void installTheme(String themePath);

    void activateTheme(String name);

    void createConfig();

}
