package com.elias.site_generation.adapter.site.in;

import com.elias.site_generation.adapter.site.in.dto.CreateSitesRequest;
import com.elias.site_generation.adapter.site.out.mapper.DtoSiteMapper;
import com.elias.site_generation.port.site.usecase.SitesBulkCreationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sites/bulk")
public class SitesBulkCreationController {

    private final DtoSiteMapper mapper;
    private final SitesBulkCreationUseCase useCase;

    @PostMapping
    public void createBulk(@Valid @RequestBody CreateSitesRequest request) {
        useCase.createBulk(mapper.toSites(request.requests()));
    }

}
