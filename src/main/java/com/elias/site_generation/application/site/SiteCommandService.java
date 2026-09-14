package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.DomainAlreadyExistsException;
import com.elias.site_generation.domain.site.exception.NotSiteOwnerException;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.auth.AuthUserPort;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.site.SiteQueryPort;
import com.elias.site_generation.port.site.usecase.SiteCommandUseCase;
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

    private final SiteQueryPort siteQueryPort;
    private final SiteDeployAsyncProcessor asyncProcessor;

    private final WebsiteThemeQueryPort websiteThemeQueryPort;

    @Override
    public void changeDomain(long siteId, String domain) {
        User authUser = authUserPort.getAuthUser();
        Site existence = siteQueryPort.findById(siteId);

        validateSiteOwner(siteId, authUser);
        validateDomainExistence(domain);

        cleanUpDomain(existence);
        asyncProcessor.publishAsync(existence);
    }

    private void validateDomainExistence(String hostname) {
        if (websiteThemeQueryPort.exists(hostname)) {
            throw new DomainAlreadyExistsException("Domain %s already exists.".formatted(hostname));
        }
    }

    private void validateSiteOwner(long siteId, User owner) {
        if (!owner.containsSiteId(siteId)) {
            throw new NotSiteOwnerException("You're not site owner.");
        }
    }

    private void cleanUpDomain(Site site) {
        // TODO if delete db failed or returned exception of already deleted should be handled
        hostingPort.deleteDb(site.getDb().name());
        hostingPort.deleteDomain(site.getHostname());
    }

}
