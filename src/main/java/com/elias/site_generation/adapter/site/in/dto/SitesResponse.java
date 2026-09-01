package com.elias.site_generation.adapter.site.in.dto;

import com.elias.site_generation.domain.site.Site;

import java.util.List;

public record SitesResponse(List<Site> data) {
}
