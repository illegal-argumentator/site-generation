package com.elias.site_generation.adapter.theme.out.generation.strategy;

import java.util.Set;

public record ElementPayload(String prompt, Set<String> tags) {

    public static ElementPayload from(String prompt, Set<String> tags) {
        return new ElementPayload(prompt, tags);
    }

}
