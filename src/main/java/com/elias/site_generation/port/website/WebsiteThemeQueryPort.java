package com.elias.site_generation.port.website;

public interface WebsiteThemeQueryPort {

    boolean existsDomain(String hostname);
    boolean existsDb(String dbName);

}
