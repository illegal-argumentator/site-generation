package com.elias.site_generation.adapter.site.out;

import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.port.site.DbGenerationPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class DbGenerationAdapter implements DbGenerationPort {

    private static final String DB_NAME_PREFIX = "name", DB_USER_PREFIX = "user";
    private static final int FIRST = 0, FOUR = 4, EIGHT = 8;

    @Override
    public Db generate() {
        String user = DB_USER_PREFIX.concat(UUID.randomUUID().toString().substring(FIRST, FOUR));
        String name = DB_NAME_PREFIX.concat(UUID.randomUUID().toString().substring(FIRST, FOUR));
        String password = String.valueOf(UUID.randomUUID()).substring(FIRST, EIGHT);
        return new Db(user, name, password);
    }

}
