package com.elias.site_generation.adapter.template.out.mapper;

import com.elias.site_generation.adapter.template.in.dto.TemplateResponse;
import com.elias.site_generation.domain.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class TemplateRequestMapper {

    @Value("${app.base-url}")
    private String BASE_URL;

    private final TemplateMapper mapper;

    public List<TemplateResponse> toResponses(List<Template> templates) {
        return templates.stream().map(this::toResponse).toList();
    }

    private TemplateResponse toResponse(Template template) {
        TemplateResponse response = mapper.toResponse(template);
        return response.withImageUrl(buildImageUrl(template.image().id()));
    }

    private String buildImageUrl(long imageId) {
        return BASE_URL.concat("/images/".concat(String.valueOf(imageId)));
    }

}