package com.elias.site_generation.adapter.site.in;

import com.elias.site_generation.adapter.site.in.dto.SiteEditRequest;
import com.elias.site_generation.port.site.usecase.SiteCommandUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sites")
public class SiteCommandController {

    private final SiteCommandUseCase useCase;

    @PatchMapping("/{siteId}/change-domain")
    public void changeDomain(@PathVariable long siteId, @RequestParam String domain) {
        useCase.changeDomain(siteId, domain);
    }

    @PostMapping("/{siteId}/edit")
    public void edit(@PathVariable long siteId, @Valid SiteEditRequest request) {
        useCase.edit(siteId, request.content(), request.component());
    }

}
