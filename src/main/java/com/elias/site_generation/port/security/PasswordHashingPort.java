package com.elias.site_generation.port.security;

public interface PasswordHashingPort {

    String hash(String raw);
    boolean matches(String raw, String hash);

}
