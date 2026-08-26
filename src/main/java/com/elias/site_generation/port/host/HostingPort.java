package com.elias.site_generation.port.host;

public interface HostingPort {

    void createDomain(String hostname);

    void createDb(String dbUser, String dbPass);

}
