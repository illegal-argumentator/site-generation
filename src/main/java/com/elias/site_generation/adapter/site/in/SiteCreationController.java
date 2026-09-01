package com.elias.site_generation.adapter.site.in;

import com.elias.site_generation.adapter.site.in.dto.CreateSiteRequest;
import com.elias.site_generation.adapter.site.out.mapper.DtoSiteMapper;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.port.site.usecase.SiteCreationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sites")
@RequiredArgsConstructor
public class SiteCreationController {

    private final DtoSiteMapper mapper;
    private final SiteCreationUseCase useCase;

    @PostMapping
    public void create(@RequestParam TemplateType type, @Valid @RequestBody CreateSiteRequest request) {
        useCase.create(type, mapper.toSite(request));
    }

    @PostMapping("/redeploy")
    public void redeploy(@RequestParam long siteId) {
        useCase.redeploy(siteId);
    }

    @PostMapping("/activate")
    public void activate(@RequestParam long siteId) {
        useCase.activate(siteId);
    }

}
