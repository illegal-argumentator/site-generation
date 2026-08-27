package com.elias.site_generation.port.website;

public interface WebsiteThemePort {

    void downloadWebsite(String hostname);

    void installWebsite(String hostname);

    void installTheme(String themePath, String hostname);

    void activateTheme(String name, String hostname);

    void createConfig(String name, String password, String hostname);

}
