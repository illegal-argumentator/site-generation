package com.elias.site_generation.port.host;

import com.elias.site_generation.domain.site.nested.Db;

public interface HostingPort {

    void createDomain(String hostname);

    void enableSsl(String hostname);

    void createDb(Db db);

}
