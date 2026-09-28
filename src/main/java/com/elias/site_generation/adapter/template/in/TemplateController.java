package com.elias.site_generation.adapter.template.in;

import com.elias.site_generation.adapter.template.out.mapper.TemplateRequestMapper;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.port.template.TemplateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateRequestMapper mapper;
    private final TemplateUseCase useCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void save(@RequestParam(required = false) Long imageId, @Valid @RequestBody SaveTemplateRequest request) {
        Template template = mapper.toTemplate(request);
        useCase.save(imageId, template);
    }

}

