package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.event.SiteEditEvent;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.port.site.SiteCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SiteEditAsyncProcessor {

    private final SiteCommandPort siteCommandPort;

    private final ApplicationEventPublisher publisher;

    @Async
    public void editAsync(String content, TemplateComponent component, Site site) {
        saveEditInProgress(site.getId());
        publishEdit(content, component, site);
    }

    private void saveEditInProgress(long siteId) {
        siteCommandPort.update(siteId, Site.builder().creationStatus(CreationStatus.IN_PROGRESS).build());
    }

    private void publishEdit(String content, TemplateComponent component, Site site) {
        publisher.publishEvent(new SiteEditEvent(content, component, site));
    }

}
