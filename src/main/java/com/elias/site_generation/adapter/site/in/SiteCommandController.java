package com.elias.site_generation.adapter.site.in;

import com.elias.site_generation.adapter.site.in.dto.SiteEditRequest;
import com.elias.site_generation.port.site.usecase.SiteCommandUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.elias.site_generation.domain.site.SiteDomainPattern.DOMAIN_PATTERN;
import static com.elias.site_generation.domain.site.SiteDomainPattern.DOMAIN_PATTERN_MESSAGE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sites")
public class SiteCommandController {

    private final SiteCommandUseCase useCase;

    @PatchMapping("/{siteId}/change-domain")
    public void changeDomain(@PathVariable long siteId, @RequestParam @Valid @Pattern(regexp = DOMAIN_PATTERN, message = DOMAIN_PATTERN_MESSAGE) String domain) {
        useCase.changeDomain(siteId, domain);
    }

    @DeleteMapping("/{siteId}")
    public void delete(@PathVariable long siteId) {
        useCase.delete(siteId);
    }

    @PostMapping("/{siteId}/edit")
    public void edit(@PathVariable long siteId, @Valid SiteEditRequest request) {
        useCase.edit(siteId, request.content(), request.component());
    }

}
