package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.exception.DomainAlreadyExistsException;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.SiteParallelCreationLimitReachedException;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.auth.AuthUserPort;
import com.elias.site_generation.port.site.SiteQueryPort;
import com.elias.site_generation.port.site.usecase.SiteCreationUseCase;
import com.elias.site_generation.port.theme.TemplateQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeQueryPort;
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

    private final TemplateQueryPort templateQueryPort;
    private final WebsiteThemeQueryPort websiteThemeQueryPort;

    private final AuthUserPort authUserPort;
    private final SiteQueryPort siteQueryPort;
    private final SiteCreationAsyncProcessor asyncProcessor;

    @Override
    public void create(TemplateType type, Site site) {
        throwIfTemplateNotExists(type);
        throwIfDomainAlreadyExists(site.getHostname());

        User owner = authUserPort.getAuthUser();
        throwIfParallelCreationLimitReached(owner);

        asyncProcessor.createAsync(type, owner, site);
    }

    @Override
    public void redeploy(long siteId) {
        Site site = siteQueryPort.findById(siteId);
        site.validateReadyForRedeploy();
        asyncProcessor.publishDeployAsync(site);
    }

    @Override
    public void activate(long siteId) {
        Site site = siteQueryPort.findById(siteId);
        site.validateReadyForActivation();
        asyncProcessor.publishActivationAsync(site);
    }

    private void throwIfTemplateNotExists(TemplateType type) {
        if (!templateQueryPort.exists(type)) {
            throw new TemplateNotFoundException("Template not found by type: %s.".formatted(type));
        }
    }

    private void throwIfDomainAlreadyExists(String hostname) {
        if (websiteThemeQueryPort.exists(hostname)) {
            throw new DomainAlreadyExistsException("Domain %s already exists.".formatted(hostname));
        }
    }

    private void throwIfParallelCreationLimitReached(User user) {
        List<Site> entities = siteQueryPort.findAllById(Site.collectIds(user.getSites()));
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }

        if (Site.hasMoreOrEqualInProgressThanLimit(parallelLimit, entities)) {
            throw new SiteParallelCreationLimitReachedException("Maximum %d sites can be created in parallel.".formatted(parallelLimit));
        }
    }
}
