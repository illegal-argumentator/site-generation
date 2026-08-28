package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.exception.DomainAlreadyExistsException;
import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.site.type.Status;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.event.ThemePublishEvent;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.port.site.SiteQueryPort;
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

    private final SiteQueryPort siteQueryPort;
    private final SiteCommandPort siteCommandPort;

    private final ThemeGenerationPort themeGenerationPort;
    private final WebsiteThemeQueryPort websiteThemeQueryPort;

    private final ApplicationEventPublisher publisher;

    @Override
    public void create(TemplateType type, Site site) {
        throwIfTemplateNotExists(type);
        throwIfDomainAlreadyExists(site.getHostname());
        process(type, site);
    }

    @Override
    public void redeploy(long siteId) {
        Site site = siteQueryPort.findById(siteId);
        site.validateReadyForRedeploy();
        publish(site);
    }

    @Async
    protected void process(TemplateType type, Site site) {
        Site savedPending = savePending(type, site);

        Theme theme = themeGenerationPort.generate(site);
        Site savedCreated = saveCreated(savedPending.getId(), theme.id());

        publish(savedCreated);
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
        site.setDb(db);

        return siteCommandPort.save(site);
    }

    private Site saveCreated(long siteId, String themeId) {
        Site forUpdate = Site.builder().status(Status.CREATED).themeId(themeId).build();
        return siteCommandPort.update(siteId, forUpdate);
    }

    private void publish(Site site) {
        siteCommandPort.update(site.getId(), Site.builder().deployStatus(DeployStatus.PENDING).build());
        publisher.publishEvent(new ThemePublishEvent(site));
    }
}
