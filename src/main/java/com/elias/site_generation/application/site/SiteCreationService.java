package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.exception.DomainAlreadyExistsException;
import com.elias.site_generation.domain.site.Site;
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
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class SiteCreationService implements SiteCreationUseCase {

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
}
