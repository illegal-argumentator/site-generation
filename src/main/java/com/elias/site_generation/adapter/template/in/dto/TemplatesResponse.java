package com.elias.site_generation.adapter.template.in.dto;

import java.util.List;

public record TemplatesResponse(List<TemplateResponse> data) {

    public static TemplatesResponse from(List<TemplateResponse> data) {
        return new TemplatesResponse(data);
    }

}
