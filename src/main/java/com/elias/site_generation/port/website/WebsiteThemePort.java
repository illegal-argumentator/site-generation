package com.elias.site_generation.port.website;

public interface WebsiteThemePort {

    void downloadWebsite();

    void installWebsite(String hostname);

    void installTheme(String themePath);

    void activateTheme(String name);

    void createConfig(String name, String password);

}
