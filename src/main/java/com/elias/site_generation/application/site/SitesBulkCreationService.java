package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.SiteParallelCreationLimitReachedException;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.auth.AuthUserPort;
import com.elias.site_generation.port.site.SiteQueryPort;
import com.elias.site_generation.port.site.usecase.SitesBulkCreationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class SitesBulkCreationService implements SitesBulkCreationUseCase {

    @Value("${sites.parallel-limit}")
    private int parallelLimit;

    private final AuthUserPort authUserPort;

    private final SiteQueryPort siteQueryPort;
    private final SiteValidationService siteValidationService;
    private final SiteCreationAsyncProcessor asyncProcessor;

    @Override
    public void createBulk(Map<TemplateType, Site> sites) {
        User owner = authUserPort.getAuthUser();

        throwIfCreationLimitReached(sites.size(), owner);
        validateSitesCreation(sites);

        processAsyncSitesCreation(owner, sites);
    }

    private void validateSitesCreation(Map<TemplateType, Site> sites) {
        sites.forEach(siteValidationService::validateSiteCreation);
    }

    private void processAsyncSitesCreation(User owner, Map<TemplateType, Site> sites) {
        sites.forEach((type, site) -> asyncProcessor.createAsync(type, owner, site));
    }

    private void throwIfCreationLimitReached(int sitesToCreate, User user) {
        List<Site> entities = siteQueryPort.findAllById(Site.collectIds(user.getSites()));
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }

        long inProgressCount = Site.getInProgressCount(entities);
        if (parallelLimit - inProgressCount < sitesToCreate) {
            throw new SiteParallelCreationLimitReachedException("Maximum %d sites can be created in parallel.".formatted(parallelLimit));
        }
    }

}
