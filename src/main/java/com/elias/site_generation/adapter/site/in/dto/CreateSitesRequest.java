package com.elias.site_generation.adapter.site.in.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateSitesRequest(
        @Size(min = 1, max = 3, message = "Maximum 3 parallel sites and minimum 1.") List<CreateSitePayload> requests
) {

}
