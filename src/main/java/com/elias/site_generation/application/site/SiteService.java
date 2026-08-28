package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.exception.DomainAlreadyExistsException;
import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.Status;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.event.ThemePublishEvent;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.port.theme.ThemeGenerationPort;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.site.usecase.SiteUseCase;
import com.elias.site_generation.port.theme.TemplateQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeQueryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class SiteService implements SiteUseCase {

    private final TemplateQueryPort templateQueryPort;

    private final SiteCommandPort siteCommandPort;
    private final ThemeGenerationPort themeGenerationPort;
    private final WebsiteThemeQueryPort websiteThemeQueryPort;

    private final ApplicationEventPublisher publisher;

    @Override
    public void create(TemplateType type, Site site) {
        throwIfTemplateNotExists(type);
        throwIfDomainAlreadyExists(site.getHostname());
//        process(type, site);
    }

    @Async
    protected void process(TemplateType type, Site site) {
        Site saved = savePending(type, site);

        Theme theme = themeGenerationPort.generate(site);
        saveCreated(saved.getId(), theme.id());

        publisher.publishEvent(new ThemePublishEvent(saved, theme));;
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

    private Site savePending(TemplateType type, Site site) {
        Db db = Site.generateDbCreds();

        site.setStatus(Status.PENDING);
        site.setType(type);
        site.setDbName(db.name());
        site.setDbPass(db.password());

        return siteCommandPort.save(site);
    }

    private void saveCreated(long siteId, String themeId) {
        Site forUpdate = Site.builder().status(Status.CREATED).themeId(themeId).build();
        siteCommandPort.update(siteId, forUpdate);
    }
}
