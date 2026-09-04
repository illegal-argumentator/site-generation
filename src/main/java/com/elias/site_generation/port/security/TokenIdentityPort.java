package com.elias.site_generation.port.security;

public interface TokenIdentityPort {

    String extractId(String token);

}
