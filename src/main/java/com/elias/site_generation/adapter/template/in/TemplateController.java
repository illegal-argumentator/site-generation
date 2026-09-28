package com.elias.site_generation.adapter.template.in;

import com.elias.site_generation.adapter.template.in.dto.SaveTemplateRequest;
import com.elias.site_generation.adapter.template.in.dto.TemplatesResponse;
import com.elias.site_generation.adapter.template.out.mapper.TemplateMapper;
import com.elias.site_generation.adapter.template.out.mapper.TemplateRequestMapper;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.port.template.TemplateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateMapper mapper;
    private final TemplateRequestMapper requestMapper;
    private final TemplateUseCase useCase;

    @GetMapping
    public ResponseEntity<TemplatesResponse> getAll() {
        List<Template> templates = useCase.findAll();
        return ResponseEntity.ok(TemplatesResponse.from(requestMapper.toResponses(templates)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void save(@RequestParam(required = false) Long imageId, @Valid @RequestBody SaveTemplateRequest request) {
        Template template = mapper.toTemplate(request);
        useCase.save(imageId, template);
    }

}

