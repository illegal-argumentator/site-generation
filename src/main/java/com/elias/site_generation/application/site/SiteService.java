package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.Status;
import com.elias.site_generation.domain.site.event.SiteCreationFailedEvent;
import com.elias.site_generation.domain.site.exception.SiteCreationException;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.port.SiteDeploymentPort;
import com.elias.site_generation.port.SiteThemeGenerationPort;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.site.usecase.SiteUseCase;
import com.elias.site_generation.port.theme.TemplateQueryPort;
import com.elias.site_generation.shared.response.ResponseBody;
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
    private final SiteThemeGenerationPort siteThemeGenerationPort;
    private final SiteDeploymentPort siteDeploymentPort;

    private final ApplicationEventPublisher publisher;

    @Async
    @Override
    public void create(TemplateType type, Site site) {
        throwIfTemplateNotExists(type);

        Site newSite = save(type, site);
        String themeId = generate(newSite);
        deploy(themeId);
        complete(themeId, newSite.getId());
    }

    private void throwIfTemplateNotExists(TemplateType type) {
        if (!templateQueryPort.exists(type)) {
            throw new TemplateNotFoundException("Template not found by type: %s.".formatted(type));
        }
    }

    private Site save(TemplateType type, Site site) {
        site.setStatus(Status.PENDING);
        site.setType(type);
        return siteCommandPort.save(site);
    }

    private String generate(Site site) {
        ResponseBody<String> body = siteThemeGenerationPort.generate(site);

        if (!body.isSuccessful()) {
            publisher.publishEvent(new SiteCreationFailedEvent(site.getId(), body.message()));
            throw new SiteCreationException("Failed site creation on theme generation step.");
        }

        log.info("Finished generating theme.");
        return body.data();
    }

    private void complete(String themeId, Long siteId) {
        siteCommandPort.update(siteId, Site.builder().status(Status.CREATED).themeId(themeId).build());
    }

    private void deploy(String themeId) {

    }
}
