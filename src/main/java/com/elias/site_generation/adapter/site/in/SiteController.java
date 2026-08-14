package com.elias.site_generation.adapter.site.in;

import com.elias.site_generation.adapter.site.in.dto.CreateSiteRequest;
import com.elias.site_generation.adapter.site.out.mapper.DtoSiteMapper;
import com.elias.site_generation.port.site.usecase.SiteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteUseCase useCase;
    private final DtoSiteMapper mapper;

    @PostMapping
    public void create(@RequestParam String themeId, @RequestBody CreateSiteRequest request) {
        useCase.create(themeId, mapper.toSite(request));
    }

}
