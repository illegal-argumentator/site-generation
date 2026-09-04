package com.elias.site_generation.adapter.security.out.jwt;

import lombok.Getter;

@Getter
public enum TokenClaim {

    ID("id"),
    EMAIL("email"),
    ROLES("roles");

    private final String claim;

    TokenClaim(String claim) {
        this.claim = claim;
    }


    }
