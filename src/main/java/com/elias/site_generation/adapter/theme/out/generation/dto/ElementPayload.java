package com.elias.site_generation.adapter.theme.out.generation.dto;

import lombok.With;

import java.util.Set;

public record ElementPayload(@With String prompt, Set<String> tags) {

    public static ElementPayload from(String prompt, Set<String> tags) {
        return new ElementPayload(prompt, tags);
    }

}
