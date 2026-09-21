package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.DomainAlreadyExistsException;
import com.elias.site_generation.domain.site.exception.NotSiteOwnerException;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.theme.TemplateQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SiteValidationService {

    private final TemplateQueryPort templateQueryPort;
    private final WebsiteThemeQueryPort websiteThemeQueryPort;

    void validateSiteCreation(TemplateType type, Site site) {
        throwIfTemplateNotExists(type);
        throwIfDomainAlreadyExists(site.getHostname());
    }

    void validateSiteOwner(long siteId, User owner) {
        if (!owner.containsSiteId(siteId)) {
            throw new NotSiteOwnerException("You're not site owner.");
        }
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
