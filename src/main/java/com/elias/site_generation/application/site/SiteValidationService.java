package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.DomainExistsException;
import com.elias.site_generation.domain.site.exception.SiteOwnerException;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.template.TemplateQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class SiteValidationService {

    private final TemplateQueryPort templateQueryPort;
    private final WebsiteThemeQueryPort websiteThemeQueryPort;

    void validateSiteCreation(TemplateType type, Site site) {
        throwIfTemplateNotExists(type);
        throwIfDomainAlreadyExists(site.getHostname());
    }

    void validateSiteCreation(Site site) {
        throwIfTemplateNotExists(site.getType());
        throwIfDomainAlreadyExists(site.getHostname());
    }

    void validateSiteOwner(long siteId, User owner) {
        if (!owner.containsSiteId(siteId)) {
            throw new SiteOwnerException("You're not site owner.");
        }
    }

    private void throwIfTemplateNotExists(TemplateType type) {
        if (!templateQueryPort.exists(type)) {
            throw new TemplateNotFoundException("Template not found by type: %s.".formatted(type));
        }
    }

    private void throwIfDomainAlreadyExists(String hostname) {
        if (websiteThemeQueryPort.exists(hostname)) {
            throw new DomainExistsException("Domain %s already exists.".formatted(hostname));
        }
    }

}
