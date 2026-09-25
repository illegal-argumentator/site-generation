package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.DomainExistsException;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.auth.AuthUserPort;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.site.SiteQueryPort;
import com.elias.site_generation.port.site.usecase.SiteCommandUseCase;
import com.elias.site_generation.port.theme.ThemeDeletionPort;
import com.elias.site_generation.port.website.WebsiteThemeQueryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteCommandService implements SiteCommandUseCase {

    private final AuthUserPort authUserPort;

    private final HostingPort hostingPort;
    private final WebsiteThemeQueryPort websiteThemeQueryPort;

    private final SiteQueryPort siteQueryPort;
    private final SiteCommandPort siteCommandPort;
    private final SiteDeployAsyncProcessor deployAsyncProcessor;
    private final SiteEditAsyncProcessor editAsyncProcessor;
    private final SiteValidationService validationService;

    private final ThemeDeletionPort themeDeletionPort;

    @Override
    public void changeDomain(long siteId, String domain) {
        User authUser = authUserPort.getAuthUser();
        Site existence = siteQueryPort.findById(siteId);

        validationService.validateSiteOwner(siteId, authUser);
        validateDomainExistence(domain);

        cleanUpDomain(existence);
        deployAsyncProcessor.publishAsync(domain, existence);
    }

    @Override
    public void delete(long siteId) {
        User authUser = authUserPort.getAuthUser();
        Site existence = siteQueryPort.findById(siteId);

        validationService.validateSiteOwner(siteId, authUser);

        cleanUpDomain(existence);
        cleanUpSite(existence);
    }

    @Override
    public void edit(long siteId, String content, TemplateComponent component) {
        User authUser = authUserPort.getAuthUser();
        validationService.validateSiteOwner(siteId, authUser);

        Site existence = siteQueryPort.findById(siteId);

        existence.validateHasComponent(component);
        existence.validateReadyForEdit();

        editAsyncProcessor.editAsync(content, component, existence);
    }

    private void validateDomainExistence(String hostname) {
        if (websiteThemeQueryPort.exists(hostname)) {
            throw new DomainExistsException("Domain %s already exists.".formatted(hostname));
        }
    }

    private void cleanUpDomain(Site site) {
        if (site.hasDb()) hostingPort.deleteDb(site.getDb().name());
        hostingPort.deleteDomain(site.getHostname());
    }

    private void cleanUpSite(Site site) {
        themeDeletionPort.delete(site.getTheme().id());
        siteCommandPort.delete(site.getId());
    }

}
