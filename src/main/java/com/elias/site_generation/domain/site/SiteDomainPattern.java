package com.elias.site_generation.domain.site;

public final class SiteDomainPattern {

    public static final String DOMAIN_PATTERN = "^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+){1,2}$";
    public static final String DOMAIN_PATTERN_MESSAGE = "Invalid domain format. Please use a valid domain, e.g. supertest.io or supertest.dataox.io.";

}
