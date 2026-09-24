package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.SiteLimitReachedException;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.auth.AuthUserPort;
import com.elias.site_generation.port.site.SiteQueryPort;
import com.elias.site_generation.port.site.usecase.SiteCreationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class SiteCreationService implements SiteCreationUseCase {

    @Value("${sites.parallel-limit}")
    private int parallelLimit;

    private final AuthUserPort authUserPort;

    private final SiteQueryPort siteQueryPort;
    private final SiteCreationAsyncProcessor asyncProcessor;
    private final SiteValidationService validationService;

    private final SitePersistenceUtils persistenceUtils;

    @Override
    public void create(TemplateType type, Site site) {
        User owner = authUserPort.getAuthUser();
        throwIfCreationLimitReached(owner);

        validationService.validateSiteCreation(type, site);

        Site pending = persistenceUtils.saveCreationInProgress(site.getId(), type);
        persistenceUtils.saveUserSite(owner, pending);

        asyncProcessor.createAsync(pending);
    }

    @Override
    public void redeploy(long siteId) {
        User authUser = authUserPort.getAuthUser();
        validationService.validateSiteOwner(siteId, authUser);

        Site site = siteQueryPort.findById(siteId);
        site.validateReadyForRedeploy();

        asyncProcessor.publishDeployAsync(site);
    }

    @Override
    public void recreate(long siteId) {
        User authUser = authUserPort.getAuthUser();

        validationService.validateSiteOwner(siteId, authUser);
        throwIfCreationLimitReached(authUser);

        Site site = siteQueryPort.findById(siteId);
        site.validateReadyForRecreation();

        Site pending = persistenceUtils.saveRecreationInProgress(siteId);
        persistenceUtils.saveUserSite(authUser, pending);

        asyncProcessor.createAsync(pending);
    }

    @Override
    public void activate(long siteId) {
        User authUser = authUserPort.getAuthUser();
        validationService.validateSiteOwner(siteId, authUser);

        Site site = siteQueryPort.findById(siteId);
        site.validateReadyForActivation();

        asyncProcessor.publishActivationAsync(site);
    }


    private void throwIfCreationLimitReached(User user) {
        List<Site> entities = siteQueryPort.findAllById(Site.collectIds(user.getSites()));
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }

        if (Site.hasMoreOrEqualInProgressThanLimit(parallelLimit, entities)) {
            throw new SiteLimitReachedException("Maximum %d sites can be created in parallel.".formatted(parallelLimit));
        }
    }
}
