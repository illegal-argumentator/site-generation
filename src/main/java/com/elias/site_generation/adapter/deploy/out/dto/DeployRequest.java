package com.elias.site_generation.adapter.deploy.out.dto;

public record DeployRequest(
        String name,
        String domain,
        String filepath
) {
}
